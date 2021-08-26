public class CPU {

    // ________________________________________ CONSTRUCTOR(S) ________________________________________________________________________________


    // ________________________________________ FIELDS ________________________________________________________________________________


    // pipeline registers
        IF_ID IF_ID = new IF_ID();
        ID_EX ID_EX = new ID_EX();
        EX_MEM EX_MEM = new EX_MEM();
        MEM_WB MEM_WB = new MEM_WB();

    // IF components
        public int programCounter = 0;
        int[] instructionCache;

    // ID components
        InstructionDecoder decoder = new InstructionDecoder();
        RegisterFile Regs = new RegisterFile();
        ControlUnit controlUnit = new ControlUnit();

    // EX components
        Mux regDstMux = new Mux("regDstMux");
        Mux aluSrcMux = new Mux("aluSrcMux");
        ALUControl aluControl = new ALUControl();
        ALU alu = new ALU();

    // MEM components
        Memory Main_Mem = new Memory();
        Mux memReadMux = new Mux("memReadMux");
        Mux memWriteMux = new Mux("memWriteMux");
        int address;
        int readData;
        int data2write;
        int memRead;
        int memWrite;

    // WB components
        Mux memToRegMux = new Mux("memToRegMux");


    // ________________________________________ PRIMARY LOGIC ________________________________________________________________________________


    public void advanceCycle() {
        /*  about   this represents one clock cycle in the cpu  */
        IF_stage();
        ID_stage();
        EX_stage();
        MEM_stage();
        WB_stage();
        print_out_everything();
        copy_write_to_read();
    }

    public void IF_stage() {
        /*  fetches instruc from instruction cache, and increments pc   */
        int fetchedInstruc = instructionCache[programCounter];
        IF_ID.setInst_WRITE(fetchedInstruc);
        IF_ID.setAssemblyInstruc_WRITE(decoder.instructToString(fetchedInstruc));
        programCounter++;
    }

    public void ID_stage() {

        // decode instruction, and send its parts to appropriate places
        int instruc = IF_ID.getInst_READ();
        decoder.dissassembleInstruction(instruc);
        controlUnit.setOpCodeInput(decoder.getOp());            // op
        Regs.setRsRegNum(decoder.getRs());                      // rs
        Regs.setRtRegNum(decoder.getRt());                      // rt for aluSrc mux
        ID_EX.setWriteReg_rt_WRITE(decoder.getRt());            // rt again for regDst mux
        ID_EX.setWriteReg_rd_WRITE(decoder.getRd());            // rd
        ID_EX.setFunction_WRITE(decoder.getFunct());            // funct
        ID_EX.setSEOffset_WRITE((int) decoder.getImmediate());  // immediate

        // determine control bits and pass to ID_EX pipe reg
        controlUnit.setControlBits();
        ID_EX.setRegDst_WRITE(controlUnit.getRegDst());
        ID_EX.setAluSrc_WRITE(controlUnit.getAluSrc());
        ID_EX.setAluOpCode1_WRITE(controlUnit.getAluOp1());
        ID_EX.setAluOpCode2_WRITE(controlUnit.getAluOp2());
        ID_EX.setMemRead_WRITE(controlUnit.getMemRead());
        ID_EX.setMemWrite_WRITE(controlUnit.getMemWrite());
        ID_EX.setMemToReg_WRITE(controlUnit.getMemtoReg());
        ID_EX.setRegWrite_WRITE(controlUnit.getRegWrite());

        // pass relevant data to ID_EX pipe reg
        ID_EX.setReadReg1Value_WRITE(Regs.getRsContents());
        ID_EX.setReadReg2Value_WRITE(Regs.getRtContents());
        ID_EX.setAssemblyInstruc_WRITE(IF_ID.getAssemblyInstruc_READ());
    }

    public void EX_stage() {
        // load regDst mux with inputs, output to EX_MEM pipeline reg
        regDstMux.setDataWhen0(ID_EX.getWriteReg_rt_READ());
        regDstMux.setDataWhen1(ID_EX.getWriteReg_rd_READ());
        regDstMux.setControlBit(ID_EX.getRegDst_READ());
        EX_MEM.setWriteRegNum_WRITE(regDstMux.output());

        // load aluSrc mux with inputs, output to alu
        aluSrcMux.setDataWhen0(ID_EX.getReadReg2Value_READ());
        aluSrcMux.setDataWhen1(ID_EX.getSEOffset_READ());
        aluSrcMux.setControlBit(ID_EX.getAluSrc_READ());
        alu.setAluOperand2(aluSrcMux.output());

        // load aluControl, and output alu operation to alu
        aluControl.setAluOp1(ID_EX.getAluOpCode1_READ());
        aluControl.setAluOp2(ID_EX.getAluOpCode2_READ());
        aluControl.setFunct(ID_EX.getFunction_READ());
        alu.setAluOperation(aluControl.getAluOperation());

        // load ALU, and output result EX_MEM
        alu.setAluOperand1(ID_EX.getReadReg1Value_READ());
        alu.calculate();
        EX_MEM.setALUResult_WRITE(alu.getAluOutput());

        // pass control bits to be used later
        EX_MEM.setMemRead_WRITE(ID_EX.getMemRead_READ());
        EX_MEM.setMemWrite_WRITE(ID_EX.getMemWrite_READ());
        EX_MEM.setMem2reg_WRITE(ID_EX.getMemToReg_READ());
        EX_MEM.setRegWrite_WRITE(ID_EX.getRegWrite_READ());

        // pass down data to EX_MEM
        EX_MEM.setSWValue_WRITE(ID_EX.getReadReg2Value_READ());
        EX_MEM.setAssemblyInstruc_WRITE(ID_EX.getAssemblyInstruc_READ());
    }

    public void MEM_stage() {

        // get data from EX_MEM pipeline register
        address = EX_MEM.getALUResult_READ();
        readData = 0;
        data2write = EX_MEM.getSWValue_READ();
        memRead = EX_MEM.getMemRead_READ();
        memWrite = EX_MEM.getMemWrite_READ();

        // read, write, or do nothing
        if (memRead == 1) {
            readData = Main_Mem.read(address);
        } else if (memWrite == 1) {
            Main_Mem.write(data2write, address);
        } else {
        }

        // pass control bits to MEM_WB
        MEM_WB.setRegWrite_WRITE(EX_MEM.getRegWrite_READ());
        MEM_WB.setMemToReg_WRITE(EX_MEM.getMem2reg_READ());

        // pass necessary data to MEM_WB stage
        MEM_WB.setALResult_WRITE(EX_MEM.getALUResult_READ());
        MEM_WB.setLWValue_WRITE(readData);
        MEM_WB.setWriteRegNum_WRITE(EX_MEM.getWriteRegNum_READ());
        MEM_WB.setAssemblyInstruc_WRITE(EX_MEM.getAssemblyInstruc_READ());
    }

    public void WB_stage() {
        // load memToReg mux, and output back to register file
        memToRegMux.setDataWhen0(MEM_WB.getALUResult_READ());
        memToRegMux.setDataWhen1(MEM_WB.getLWValue_READ());
        memToRegMux.setControlBit(MEM_WB.getMemToReg_READ());
        Regs.setDataToReg(memToRegMux.output());

        // send writeRegNum and regWrite control bit back to reg file
        Regs.setWriteRegNum(MEM_WB.getWriteRegNum_READ());
        Regs.setRegWrite(MEM_WB.getRegWrite_READ());

        // write data to register if regWrite = 1
        if (Regs.getRegWrite() == 1) {
            Regs.writeDataToRegister();
        }
    }

    public void print_out_everything() {
        System.out.println(IF_ID.toString());
        System.out.println(ID_EX.toString());
        System.out.println(EX_MEM.toString());
        System.out.println(MEM_WB.toString());
        System.out.println("\n____________________________________________________________________ REG FILE STATE AT END OF CYCLE: ____________\n");
        System.out.println(Regs.toString());
    }

    public void copy_write_to_read() {
        IF_ID.copy_write_to_read();
        ID_EX.copy_write_to_read();
        EX_MEM.copy_write_to_read();
        MEM_WB.copy_write_to_read();
    }

    public void setInstructionCache(int[] instructionCache) {
        this.instructionCache = instructionCache;
    }
}
