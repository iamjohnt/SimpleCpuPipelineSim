public class ALUControl {


    // ____________________________ FIELDS _____________________________________


    private int funct;
    private int aluOp1;
    private int aluOp2;
    private final int ADD_SIGNAL = 0b0010;
    private final int SUB_SIGNAL = 0b0110;
    private final int AND_SIGNAL = 0b0000;
    private final int OR_SIGNAL = 0b0001;
    private final int SLT_SIGNAL = 0b0111;
    private final int SLL_SIGNAL = 0b1111; // i don't know the SLL signal, so making it up

    private final int ADD_FUNCT = 0b100000;
    private final int SUB_FUNCT = 0b100010;
    private final int AND_FUNCT = 0b100100;
    private final int OR_FUNCT = 0b100101;
    private final int SLT_FUNCT = 0b101010;
    private final int SLL_FUNCT = 0b000000;


    // ____________________________ PRIMARY METHODS ____________________________


    public int getAluOperation() {
        if ((aluOp1 == 0) && (aluOp2 == 0)) {
            // lw and sw
            return ADD_SIGNAL;
        } else if ((aluOp1 == 1) && (aluOp2 == 0)) {
            // r format
            switch (funct) {
                case ADD_FUNCT: return ADD_SIGNAL;
                case SUB_FUNCT: return SUB_SIGNAL;
                case AND_FUNCT: return AND_SIGNAL;
                case OR_FUNCT: return OR_SIGNAL;
                case SLT_FUNCT: return SLT_SIGNAL;
                case SLL_FUNCT: return SLL_SIGNAL;
                default:
                    System.out.println("ERROR Funct " + funct + " not recongized");
                    return -1;
            }
        } else {
            System.out.println("ERROR aluOp " + aluOp1 + aluOp2 + " not valid");
            System.exit(1);
            return -1;
        }
    }

    public String toString() {
        String rtn = "\n ALU control state \n";
        rtn += "funct: " + funct + "\n";
        rtn += "aluOp1: " + aluOp1 + "\n";
        rtn += "aluOp2: " + aluOp2 + "\n";
        rtn += "alu op (decimal): " + getAluOperation() + "\n";
        return rtn;
    }

    // ____________________ GETTERS, SETTERS ________________________________

    public void setFunct(int funct) {
        this.funct = funct;
    }

    public void setAluOp1(int aluOp1) {
        this.aluOp1 = aluOp1;
    }

    public void setAluOp2(int aluOp2) {
        this.aluOp2 = aluOp2;
    }
}
