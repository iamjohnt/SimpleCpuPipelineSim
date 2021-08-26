public class ControlUnit {

    // ___________________ FIELDS ________________________________________


    private final int R_FORMAT = 0;
    private final int LW = 0b100011;  // 35 decimal
    private final int SW = 0b101011;  // 43 dec
    private final int LB = 0b100000;  // 32 dec
    private final int SB = 0b101000;  // 40 dec

    private int opCodeInput = -1;
    private int regDst;     // when 0: reg dest = rt code; when 1: reg dest = rd code
    private int aluSrc;     // 2nd alu input is immediate value (otherwise 2nd register value)
    private int regWrite;   // will we need to write to register?
    private int memRead;    // will we need to read from the memory?
    private int memWrite;   // will we need to write to the memory?
    private int memtoReg;   // will a register be writtin from memory (otherwise from ALU)
    private int aluOp1;     // what alu op do we need?
    private int aluOp2;
    // PCSrc                // we don't need PCSrc, since that is for branch instructions


    // __________________ PRIMARY METHODS ______________________________


    public void setControlBits() {
        if (opCodeInput == R_FORMAT) {
            regDst = 1;
            aluSrc = 0;
            memtoReg = 0;
            regWrite = 1;
            memRead = 0;
            memWrite = 0;
            aluOp1 = 1;
            aluOp2 = 0;
        } else if (opCodeInput == LW || opCodeInput == LB) {
            regDst = 0;
            aluSrc = 1;
            memtoReg = 1;
            regWrite = 1;
            memRead = 1;
            memWrite = 0;
            aluOp1 = 0;
            aluOp2 = 0;
        } else if (opCodeInput == SW || opCodeInput == SB) {
            regDst = -1; // don't care
            aluSrc = 1;
            memtoReg = -1; // don't care
            regWrite = 0;
            memRead = 0;
            memWrite = 1;
            aluOp1 = 0;
            aluOp2 = 0;
        } else {
            System.out.println("ERROR: opcode input " + opCodeInput + " not recognized");
            System.exit(1);
        }
    }

    public String toString() {
        String rtn = "\nControl Unit state:\n";
        rtn += "opCode   " + opCodeInput + "\n";
        rtn += "regDst   " + regDst + "\n";
        rtn += "aluSrc   " + aluSrc + "\n";
        rtn += "regWrite " + regWrite + "\n";
        rtn += "memRead  " + memRead + "\n";
        rtn += "memWrite " + memWrite + "\n";
        rtn += "memtoReg " + memtoReg + "\n";
        rtn += "aluOp1   " + aluOp1 + "\n";
        rtn += "aluOp2   " + aluOp2 + "\n";
        return rtn;
    }


    // __________________ HELPER, GETTER, SETTER ____________________________


    public void setOpCodeInput(int opInput) {
        this.opCodeInput = opInput;
    }

    public int getRegDst() {
        return regDst;
    }

    public int getAluSrc() {
        return aluSrc;
    }

    public int getRegWrite() {
        return regWrite;
    }

    public int getMemRead() {
        return memRead;
    }

    public int getMemWrite() {
        return memWrite;
    }

    public int getMemtoReg() {
        return memtoReg;
    }

    public int getAluOp1() {
        return aluOp1;
    }

    public int getAluOp2() {
        return aluOp2;
    }


}
