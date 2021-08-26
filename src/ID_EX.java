public class ID_EX {

    /*  READ ME
    *
    *   the reason why there is a read and a write version of each field is because
    *   normally a pipeline can read from a field, then write to it at the same time
    *   software unfortunately cannot, we would ovewrite something when we don't want to
    *
    *
     *       BEFORE reading, copy_write_to_read must be called
     *       or else you will get an error   */

    /*********************************************** READ VERSION OF THE FIELDS ******************************************************/

    // data

    private int ReadReg1Value_WRITE;            // aluOperand1 goes to alu, and aluOperand2 goes to aluSrc mux
    private int ReadReg2Value_WRITE;            // aluOperand2 goes to aluSrc mux
    private int SEOffset_WRITE;         // goes to aluSrc mux
    private int WriteReg_rt_WRITE;               // goes to regDst mux in ex stage - result passed down to WB
    private int WriteReg_rd_WRITE;               // goes to regDst mux in ex stage - result passed down to WB
    private int Function_WRITE;
    private String assemblyInstruc_WRITE = "noop";


    // control bits
    private int regDst_WRITE;                 // control bit determines destination register (rt or rd)
    private int aluOpCode1_WRITE;      // control bit goes to alu control unit
    private int aluOpCode2_WRITE;      // control bit goes to alu control unit
    private int aluSrc_WRITE;                 // control bit determins alu src for 2nd operand (rt or immediate val)
    private int memRead_WRITE;                // control bit for mem stage
    private int memWrite_WRITE;               // control bit for mem stage
    private int regWrite_WRITE;               // control bit for wb stage
    private int memToReg_WRITE;               // control bit for wb stage

    /*********************************************** WRITE VERSION OF THE FIELDS ******************************************************/


    // data

    private int ReadReg1Value_READ;            // aluOperand1 goes to alu, and aluOperand2 goes to aluSrc mux
    private int ReadReg2Value_READ;            // aluOperand2 goes to aluSrc mux
    private int SEOffset_READ;         // goes to aluSrc mux
    private int WriteReg_rt_READ;               // goes to regDst mux in ex stage - result passed down to WB
    private int WriteReg_rd_READ;               // goes to regDst mux in ex stage - result passed down to WB
    private int Function_READ;
    private String assemblyInstruc_READ = "noop";

    // control bits
    private int regDst_READ;                 // control bit determines destination register (rt or rd)
    private int aluOpCode1_READ;      // control bit goes to alu control unit
    private int aluOpCode2_READ;      // control bit goes to alu control unit
    private int aluSrc_READ;                 // control bit determins alu src for 2nd operand (rt or immediate val)
    private int memRead_READ;                // control bit for mem stage
    private int memWrite_READ;               // control bit for mem stage
    private int regWrite_READ;               // control bit for wb stage
    private int memToReg_READ;               // control bit for wb stage
    

    // ________________________________________ PRIMARY METHODS __________________________________
    

    public void copy_write_to_read() {
        regDst_READ = regDst_WRITE;
        aluSrc_READ = aluSrc_WRITE;
        aluOpCode1_READ = aluOpCode1_WRITE;
        aluOpCode2_READ = aluOpCode2_WRITE;
        ReadReg1Value_READ = ReadReg1Value_WRITE;
        ReadReg2Value_READ = ReadReg2Value_WRITE;
        SEOffset_READ = SEOffset_WRITE;
        WriteReg_rt_READ = WriteReg_rt_WRITE;
        WriteReg_rd_READ = WriteReg_rd_WRITE;
        memRead_READ = memRead_WRITE;
        memWrite_READ = memWrite_WRITE;
        memToReg_READ = memToReg_WRITE;
        regWrite_READ = regWrite_WRITE;
        Function_READ = Function_WRITE;
        assemblyInstruc_READ = assemblyInstruc_WRITE;
    }

    @Override
    public String toString() {
        return "\n_________________________________________________________________ ID_EX STATE ______________________________" +
                "\n" +
                assemblyInstruc_WRITE + "                  " + assemblyInstruc_READ +
                "\nReadReg1Value_WRITE =  0x" + Integer.toHexString(ReadReg1Value_WRITE) +       "      ReadReg1Value_READ =   0x" + Integer.toHexString(ReadReg1Value_READ) +
                "\nReadReg2Value_WRITE =  0x" + Integer.toHexString(ReadReg2Value_WRITE) +       "      ReadReg2Value_READ =   0x" + Integer.toHexString(ReadReg2Value_READ) +
                "\nSEOffset_WRITE =       " + SEOffset_WRITE +                                   "         SEOffset_READ =        " + SEOffset_READ +
                "\nWriteReg_rt_WRITE =    " + WriteReg_rt_WRITE +                                "         WriteReg_rt_READ =     " + WriteReg_rt_READ +
                "\nWriteReg_rd_WRITE =    " + WriteReg_rd_WRITE +                                "         WriteReg_rd_READ =     " + WriteReg_rd_READ +
                "\nFunction_WRITE =       " + Function_WRITE +                                   "         Function_READ =        " + Function_READ +
                "\n" +
                "\nregDst_WRITE =         " + regDst_WRITE +            "         regDst_READ =          " + regDst_READ +
                "\naluOpCode1_WRITE =     " + aluOpCode1_WRITE +        "         aluOpCode1_READ =      " + aluOpCode1_READ +
                "\naluOpCode2_WRITE =     " + aluOpCode2_WRITE +        "         aluOpCode2_READ =      " + aluOpCode2_READ +
                "\naluSrc_WRITE =         " + aluSrc_WRITE +            "         aluSrc_READ =          " + aluSrc_READ +
                "\nmemRead_WRITE =        " + memRead_WRITE +           "         memRead_READ =         " + memRead_READ +
                "\nmemWrite_WRITE =       " + memWrite_WRITE +          "         memWrite_READ =        " + memWrite_READ +
                "\nregWrite_WRITE =       " + regWrite_WRITE +          "         regWrite_READ =        " + regWrite_READ +
                "\nmemToReg_WRITE =       " + memToReg_WRITE +          "         memToReg_READ =        " + memToReg_READ;
    }


    // ________________________________________ GETTER SETTERS ___________________________________


    /*      write fields can set, read fields, can write
    *       so BEFORE reading, copy_write_to_read must be called
    *       or else you will get an error   */


    // setters

    public void setRegDst_WRITE(int regDst_WRITE) {
        this.regDst_WRITE = regDst_WRITE;
    }

    public void setAluSrc_WRITE(int aluSrc_WRITE) {
        this.aluSrc_WRITE = aluSrc_WRITE;
    }

    public void setAluOpCode1_WRITE(int aluOpCode1_WRITE) {
        this.aluOpCode1_WRITE = aluOpCode1_WRITE;
    }

    public void setAluOpCode2_WRITE(int aluOpCode2_WRITE) {
        this.aluOpCode2_WRITE = aluOpCode2_WRITE;
    }

    public void setReadReg1Value_WRITE(int readReg1Value_WRITE) {
        this.ReadReg1Value_WRITE = readReg1Value_WRITE;
    }

    public void setReadReg2Value_WRITE(int readReg2Value_WRITE) {
        this.ReadReg2Value_WRITE = readReg2Value_WRITE;
    }

    public void setSEOffset_WRITE(int SEOffset_WRITE) {
        this.SEOffset_WRITE = SEOffset_WRITE;
    }

    public void setWriteReg_rt_WRITE(int writeReg_rt_WRITE) {
        this.WriteReg_rt_WRITE = writeReg_rt_WRITE;
    }

    public void setWriteReg_rd_WRITE(int writeReg_rd_WRITE) {
        this.WriteReg_rd_WRITE = writeReg_rd_WRITE;
    }

    public void setMemRead_WRITE(int memRead_WRITE) {
        this.memRead_WRITE = memRead_WRITE;
    }

    public void setMemWrite_WRITE(int memWrite_WRITE) {
        this.memWrite_WRITE = memWrite_WRITE;
    }

    public void setMemToReg_WRITE(int memToReg_WRITE) {
        this.memToReg_WRITE = memToReg_WRITE;
    }

    public void setRegWrite_WRITE(int regWrite_WRITE) {
        this.regWrite_WRITE = regWrite_WRITE;
    }

    public void setFunction_WRITE(int function_WRITE) {
        this.Function_WRITE = function_WRITE;
    }

    public void setAssemblyInstruc_WRITE(String assemblyInstruc_WRITE) {
        this.assemblyInstruc_WRITE = assemblyInstruc_WRITE;
    }

    // getters


    public int getRegDst_READ() {
        return regDst_READ;
    }

    public int getAluSrc_READ() {
        return aluSrc_READ;
    }

    public int getAluOpCode1_READ() {
        return aluOpCode1_READ;
    }

    public int getAluOpCode2_READ() {
        return aluOpCode2_READ;
    }

    public int getReadReg1Value_READ() {
        return ReadReg1Value_READ;
    }

    public int getReadReg2Value_READ() {
        return ReadReg2Value_READ;
    }

    public int getSEOffset_READ() {
        return SEOffset_READ;
    }

    public int getWriteReg_rt_READ() {
        return WriteReg_rt_READ;
    }

    public int getWriteReg_rd_READ() {
        return WriteReg_rd_READ;
    }

    public int getMemRead_READ() {
        return memRead_READ;
    }

    public int getMemWrite_READ() {
        return memWrite_READ;
    }

    public int getMemToReg_READ() {
        return memToReg_READ;
    }

    public int getRegWrite_READ() {
        return regWrite_READ;
    }

    public int getFunction_READ() {
        return Function_READ;
    }

    public String getAssemblyInstruc_READ() {
        return assemblyInstruc_READ;
    }
}
