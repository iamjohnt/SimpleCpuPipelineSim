public class EX_MEM {

    // _______________________________ FIELDS ____________________________________


    /*********************************************** READ VERSION OF THE FIELDS ******************************************************/


    // data
    private int ALUResult_WRITE;      // alu result that either represents rformat result, or address for mem operation
    private int SWValue_WRITE;     // data to store if memWrite = 1
    private int WriteRegNum_WRITE;       // reg num to write to if regWrite = 1


    // control bits
    private int memRead_WRITE;        // control bit
    private int memWrite_WRITE;       // control bit
    private int mem2reg_WRITE;        // control bit
    private int regWrite_WRITE;       // control bit
    private String assemblyInstruc_WRITE = "noop";


    /*********************************************** WRITE VERSION OF THE FIELDS ******************************************************/


    // data
    private int ALUResult_READ;      // alu result that either represents rformat result, or address for mem operation
    private int SWValue_READ;     // data to store if memWrite = 1
    private int WriteRegNum_READ;       // reg num to write to if regWrite = 1

    // control bits
    private int memRead_READ;        // control bit
    private int memWrite_READ;       // control bit
    private int mem2reg_READ;        // control bit
    private int regWrite_READ;       // control bit
    private String assemblyInstruc_READ = "noop";


    // _______________________________ PRIMARY METHODS ___________________________________

    public void copy_write_to_read() {
        memRead_READ = memRead_WRITE;
        memWrite_READ = memWrite_WRITE;
        SWValue_READ = SWValue_WRITE;
        regWrite_READ = regWrite_WRITE;
        mem2reg_READ = mem2reg_WRITE;
        WriteRegNum_READ = WriteRegNum_WRITE;
        ALUResult_READ = ALUResult_WRITE;
        assemblyInstruc_READ = assemblyInstruc_WRITE;
    }

    @Override
    public String toString() {
        return "\n___________________________________________________________________ EX_MEM STATE ____________________________________" +
                "\n" +
                assemblyInstruc_WRITE + "                     " + assemblyInstruc_READ +
                "\nALUResult_WRITE =    0x" + Integer.toHexString(ALUResult_WRITE) +    "      ALUResult_READ =   0x" + Integer.toHexString(ALUResult_READ) +
                "\nSWValue_WRITE =      0x" + Integer.toHexString(SWValue_WRITE) +      "      SWValue_READ =     0x" + Integer.toHexString(SWValue_READ) +
                "\nWriteRegNum_WRITE =  " + WriteRegNum_WRITE +                         "         WriteRegNum_READ = " + WriteRegNum_READ +
                "\n" +
                "\nmemWrite_WRITE =     " + memWrite_WRITE +                            "         memWrite_READ =    " + memWrite_READ +
                "\nmemRead_WRITE =      " + memRead_WRITE +                             "         memRead_READ =     " + memRead_READ +
                "\nmem2reg_WRITE =      " + mem2reg_WRITE +                             "         mem2reg_READ =     " + mem2reg_READ +
                "\nregWrite_WRITE =     " + regWrite_WRITE +                            "         regWrite_READ =    " + regWrite_READ;
    }

    // _______________________________ GETTERS SETTERS ___________________________

    // setters


    public void setMemRead_WRITE(int memRead_WRITE) {
        this.memRead_WRITE = memRead_WRITE;
    }

    public void setMemWrite_WRITE(int memWrite_WRITE) {
        this.memWrite_WRITE = memWrite_WRITE;
    }

    public void setSWValue_WRITE(int SWValue_WRITE) {
        this.SWValue_WRITE = SWValue_WRITE;
    }

    public void setRegWrite_WRITE(int regWrite_WRITE) {
        this.regWrite_WRITE = regWrite_WRITE;
    }

    public void setMem2reg_WRITE(int mem2reg_WRITE) {
        this.mem2reg_WRITE = mem2reg_WRITE;
    }

    public void setWriteRegNum_WRITE(int writeRegNum_WRITE) {
        this.WriteRegNum_WRITE = writeRegNum_WRITE;
    }

    public void setALUResult_WRITE(int ALUResult_WRITE) {
        this.ALUResult_WRITE = ALUResult_WRITE;
    }

    public void setAssemblyInstruc_WRITE(String assemblyInstruc_WRITE) {
        this.assemblyInstruc_WRITE = assemblyInstruc_WRITE;
    }

    // getters


    public int getMemRead_READ() {
        return memRead_READ;
    }

    public int getMemWrite_READ() {
        return memWrite_READ;
    }

    public int getSWValue_READ() {
        return SWValue_READ;
    }

    public int getRegWrite_READ() {
        return regWrite_READ;
    }

    public int getMem2reg_READ() {
        return mem2reg_READ;
    }

    public int getWriteRegNum_READ() {
        return WriteRegNum_READ;
    }

    public int getALUResult_READ() {
        return ALUResult_READ;
    }

    public String getAssemblyInstruc_READ() {
        return assemblyInstruc_READ;
    }
}
