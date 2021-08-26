public class IF_ID {


    // _____________________________________ FIELDS __________________________________________
    private int inst_WRITE;
    private int inst_READ;
    private InstructionDecoder decoder = new InstructionDecoder();
    private String assemblyInstruc_WRITE = "noop";
    private String assemblyInstruc_READ = "noop";


    // ____________________________________ PRIMARY METHODS _______________________________________

    @Override
    public String toString() {
        return "\n________________________________________________________________ IF_ID STATE _____________________________" +
                "\nIF_ID STATE:" +
                "\n" +
                "\ninst_WRITE = 0x" + Integer.toHexString(inst_WRITE) + ",  " + decoder.instructToString(inst_WRITE) +
                "       inst_READ  = 0x" + Integer.toHexString(inst_READ) + ",  " + decoder.instructToString(inst_READ);
    }

    public void copy_write_to_read() {
        inst_READ = inst_WRITE;
        assemblyInstruc_READ = assemblyInstruc_WRITE;

    }


    // _____________________________________ GETTER / SETTER _______________________________________

    // setters
    public void setInst_WRITE(int inst_WRITE) {
        this.inst_WRITE = inst_WRITE;
    }

    public void setAssemblyInstruc_WRITE(String assemblyInstruc_WRITE) {
        this.assemblyInstruc_WRITE = assemblyInstruc_WRITE;
    }

    // getters
    public int getInst_READ() {
        return inst_READ;
    }

    public String getAssemblyInstruc_READ() {
        return assemblyInstruc_READ;
    }
}
