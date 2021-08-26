public class InstructionDecoder {

    public InstructionDecoder() {

    }


    // ______________________ FIELDS __________________________________________


    // instruction pieces
    private int op;
    private int rs;
    private int rt;
    private int rd;
    private int funct;
    private short immediate;

    // decoded fields
    private int readReg1;
    private int readReg2;
    private int writeReg;

//    // func codes for r format (r format op codes are always 0)
//    private final int ADD = 0b100000;
//    private final int SUB = 0b100010;
//    private final int AND = 0b100100;
//    private final int OR = 0b100101;
//    private final int SLT = 0b101010;
//
//    // op codes for i format
//    private final int LW = 0b100011;
//    private final int SW = 0b101011;
//    private final int BEQ = 0b000100;
//    private final int BNE = 0b000101;
//    private final int LB = 0b100000;
//    private final int SB = 0b101000;


    // _________________________ PRIMARY METHODS _____________________________________


    public void dissassembleInstruction(int instruction2decode) {
        op = extractOp(instruction2decode);
        rs = extractRs(instruction2decode);
        rt = extractRt(instruction2decode);
        rd = extractRd(instruction2decode);
        funct = extractFunct(instruction2decode);
        immediate = (short) extractImmediate(instruction2decode);
    }

    public String instructToString(int machineInstruct) {
        // extracts parts of instruction to separate vars
        int op = extractOp(machineInstruct);
        int rs = extractRs(machineInstruct);
        int rt = extractRt(machineInstruct);
        int rd = extractRd(machineInstruct);
        int format = extractFunct(machineInstruct);
        int immediate = (short) extractImmediate(machineInstruct);

        // checks what kind of format
        if (isRFormat(machineInstruct) == true) {
            // R format detected
            return (
                    determineRFormatOperation(format) + " " + determineRegister(rd) + ", " + determineRegister(rs) + ", " + determineRegister(rt)
            );
        } else if (isIFormat(machineInstruct) == true){
            // I format detected
            if (op == 0b100011 || op == 0b101011 || op == 0b101000 || op == 0b100000) {
                // sw or lw or lw or sb detected
                short offsetNumber = (short) (immediate);   // 16 bit immediate value is cast to 16 bit short, to cut of any leading zeros that would be added if it was an 32 bit int
                return (
                        determineIFormatOperation(op) + " " + determineRegister(rt) + ", " + Short.toString(offsetNumber) + "(" + determineRegister(rs) + ")"
                );
            } else {
                // bne or beq detected
                short offset = (short) (immediate << 2);    // here after we uncompress immediate value, we then cast to short, to preserve negative value if negative
                return (
                        determineIFormatOperation(op) + " " + determineRegister(rs) + ", " + determineRegister(rt) + " offest:" + Integer.toHexString(offset)
                );
            }
        } else {
            // not I or R format
            return "not R or I. Maybe not a valid format altogether. Don't know, don't care";
        }
    }

    public String toString() {
        String rtn = "\n Decoder State:\n";
        rtn += "op        " + op + "\n";
        rtn += "rs        " + rs + "\n";
        rtn += "rt        " + rt + "\n";
        rtn += "rd        " + rd + "\n";
        rtn += "funct     " + funct + "\n";
        rtn += "immediate " + immediate + "\n";
        return rtn;
    }

    // _________________________ HELPER _____________________________________


    private int extractOp(int instruction) {
        return (instruction & 0b11111100000000000000000000000000) >>> 26;
    }

    private int extractRs(int instruction) {
        return (instruction & 0b00000011111000000000000000000000) >> 21;
    }

    private int extractRt(int instruction) {
        return (instruction & 0b00000000000111110000000000000000) >> 16;
    }

    private int extractRd(int instruction) {
        return (instruction & 0b00000000000000001111100000000000) >> 11;
    }

    private int extractShamt(int instruction) {
        return (instruction & 0b00000000000000000000011111000000) >> 6;
    }

    private int extractFunct(int instruction) {
        return (instruction & 0b00000000000000000000000000111111);
    }

    private short extractImmediate(int instruction) {
        return (short) (instruction & 0b00000000000000001111111111111111);
    }

    private boolean isRFormat(int instruction) {
        return extractOp(instruction) == 0;
    }

    private boolean isIFormat(int instruction) {
        int op = extractOp(instruction);
        return !isRFormat(instruction) && (op != 2) && (op != 3) && (op != 0x10);
    }

    String determineRegister(int registerCode) {
        return "$" + registerCode;
    }

    public String determineRFormatOperation(int functCode) {
        if (functCode == 0b100000) {
            return "add";
        } else if (functCode == 0b100010) {
            return "sub";
        } else if (functCode == 0b100100) {
            return "and";
        } else if (functCode == 0b100101) {
            return "or";
        } else if (functCode == 0b101010) {
            return "slt";
        } else if (functCode == 0b000000) {
            return "sll";
        } else {
            return "ERROR: invalid code";
        }
    }

    public String determineIFormatOperation(int opCode) {
        if (opCode == 0b100011) {
            return "lw";
        } else if (opCode == 0b101011) {
            return "sw";
        } else if (opCode == 0b000100) {
            return "beq";
        } else if (opCode == 0b000101) {
            return "bne";
        } else if (opCode == 0b100000) {
            return "lb";
        } else if (opCode == 0b101000) {
            return "sb";
        } else {
            return "ERROR: invalid code";
        }
    }

    // ______________________________ GETTER SETTERS ______________________________________


    public int getOp() {
        return op;
    }

    public int getRs() {
        return rs;
    }

    public int getRt() {
        return rt;
    }

    public int getRd() {
        return rd;
    }

    public int getFunct() {
        return funct;
    }

    public short getImmediate() {
        return immediate;
    }
}
