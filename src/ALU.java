public class ALU {


    // __________________________________ FIELDS _____________________________________


    private final int ADD_SIGNAL = 0b0010; //2
    private final int SUB_SIGNAL = 0b0110; //6
    private final int AND_SIGNAL = 0b0000; // 0
    private final int OR_SIGNAL = 0b0001;  //1
    private final int SLT_SIGNAL = 0b0111; //7
    private final int SLL_SIGNAL = 0b1111; //15     this is made up SLL signal, since i don't know how SLL works

    private int aluOperation;
    private int aluOperand1;
    private int aluOperand2;
    private int aluOutput;

    // __________________________________ PRIMARY METHODS ____________________________


    public void calculate() {
        switch (aluOperation) {
            case ADD_SIGNAL:
                aluOutput = aluOperand1 + aluOperand2;
                break;
            case SUB_SIGNAL:
                aluOutput = aluOperand1 - aluOperand2;
                break;
            case AND_SIGNAL:
                aluOutput = aluOperand1 & aluOperand2;
                break;
            case OR_SIGNAL:
                aluOutput = aluOperand1 | aluOperand2;
                break;
            case SLT_SIGNAL:
                if (aluOperand1 < aluOperand2) {
                    aluOutput = 1;
                } else {
                    aluOutput = 0;
                }
                break;
            case SLL_SIGNAL:
                aluOutput = (int) ( aluOperand1 * Math.pow(2, aluOperand2) );   // shifts rs cont left by rt amount
                break;
            default:
                System.out.println("ERROR: ALU.calculate(): alu op " + aluOperation + " not recognized");
                aluOutput = -12345;
                System.exit(1);
                break;
        }
    }

    @Override
    public String toString() {
        return "ALU{" +
                "aluOperation=" + aluOperation +
                ", aluOperand1=" + aluOperand1 +
                ", aluOperand2=" + aluOperand2 +
                ", aluOutput=" + aluOutput +
                '}';
    }

    // __________________________________ SECONDARY METHODS __________________________

    // __________________________________ SETTERS GETTERS ____________________________


    // setters
    public void setAluOperation(int aluOperation) {
        this.aluOperation = aluOperation;
    }

    public void setAluOperand1(int aluOperand1) {
        this.aluOperand1 = aluOperand1;
    }

    public void setAluOperand2(int aluOperand2) {
        this.aluOperand2 = aluOperand2;
    }

    // getters
    public int getAluOutput() {
        return aluOutput;
    }
}
