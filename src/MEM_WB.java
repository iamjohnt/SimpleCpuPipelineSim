public class MEM_WB {

    // ____________________________ FIELDS _________________________________________________________________________________________________


    /*************** READ VERSION OF THE FIELDS ***********/


    // data
    private int ALResult_WRITE;      // alu result that either represents rformat result, or address for mem operation
    private int WriteRegNum_WRITE;       // reg num to write to if regWrite = 1
    private int LWValue_WRITE;    // data that was retrieved from memory in mem stage
    private String assemblyInstruc_WRITE = "noop";

    // control bits
    private int memToReg_WRITE;        // control bit
    private int regWrite_WRITE;       // control bit


    /********** WRITE VERSION OF THE FIELDS **************/


    // data
    private int ALUResult_READ;      // alu result that either represents rformat result, or address for mem operation
    private int WriteRegNum_READ;       // reg num to write to if regWrite = 1
    private int LWValue_READ;    // data that was retrieved from memory in mem stage
    private String assemblyInstruc_READ = "noop";

    // control bits
    private int memToReg_READ;        // control bit
    private int regWrite_READ;       // control bit


    // ______________________________ PRIMARY METHODS _____________________________________________________________________________________________


    public void copy_write_to_read() {
        regWrite_READ = regWrite_WRITE;
        memToReg_READ = memToReg_WRITE;
        WriteRegNum_READ = WriteRegNum_WRITE;
        LWValue_READ = LWValue_WRITE;
        ALUResult_READ = ALResult_WRITE;
        assemblyInstruc_READ = assemblyInstruc_WRITE;
    }

    @Override
    public String toString() {
        return "\n____________________________________________________________________ MEM_WB STATE ____________________________" +
                "\n" +
                assemblyInstruc_WRITE + "                         " + assemblyInstruc_READ +
                "\nALUResult_WRITE =   0x" + Integer.toHexString(ALResult_WRITE) +      "      ALUResult_READ =    0x" + Integer.toHexString(ALUResult_READ) +
                "\nLWValue_WRITE =     0x" + Integer.toHexString(LWValue_WRITE) +       "      LWValue_READ =      0x" + Integer.toHexString(LWValue_READ) +
                "\nwriteRegNum_WRITE = " + WriteRegNum_WRITE +                          "      writeRegNum_READ =  " + WriteRegNum_READ +
                "\n" +
                "\nregWrite_WRITE =    " + regWrite_WRITE +                             "         regWrite_READ =     " + regWrite_READ +
                "\nmemToReg_WRITE =    " + memToReg_WRITE +                             "         memToReg_READ =      " + memToReg_READ;
    }

    // ____________________________ GETTERS SETTERS ______________________________________________________________________________________________

    // setters

    public void setRegWrite_WRITE(int regWrite_WRITE) {
        this.regWrite_WRITE = regWrite_WRITE;
    }

    public void setMemToReg_WRITE(int memToReg_WRITE) {
        this.memToReg_WRITE = memToReg_WRITE;
    }

    public void setWriteRegNum_WRITE(int writeRegNum_WRITE) {
        this.WriteRegNum_WRITE = writeRegNum_WRITE;
    }

    public void setLWValue_WRITE(int LWValue_WRITE) {
        this.LWValue_WRITE = LWValue_WRITE;
    }

    public void setALResult_WRITE(int ALResult_WRITE) {
        this.ALResult_WRITE = ALResult_WRITE;
    }

    public void setAssemblyInstruc_WRITE(String assemblyInstruc_WRITE) {
        this.assemblyInstruc_WRITE = assemblyInstruc_WRITE;
    }

// getters


    public int getRegWrite_READ() {
        return regWrite_READ;
    }

    public int getMemToReg_READ() {
        return memToReg_READ;
    }

    public int getWriteRegNum_READ() {
        return WriteRegNum_READ;
    }

    public int getLWValue_READ() {
        return LWValue_READ;
    }

    public int getALUResult_READ() {
        return ALUResult_READ;
    }

    public String getAssemblyInstruc_READ() {
        return assemblyInstruc_READ;
    }
}
