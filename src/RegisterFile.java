public class RegisterFile {

    public RegisterFile() {
        Regs = new int[32];
        for (int i = 1; i < Regs.length; i++) { // we skip initializing the first register, since it will be 0
            Regs[i] = 0x100 + i;
        }
    }


    // _______________________ FIELDS __________________________


    private int Regs[];
    private int rsRegNum;
    private int rtRegNum;
    private int writeRegNum;
    private int dataToReg;      // data to write back to register (rformat or lw)
    private int regWrite;



    // _______________________ PRIMARY METHODS _______________________________


    // input from decoder
    public void setRsRegNum(int rs) {
        rsRegNum = rs;
    }

    public void setRtRegNum(int regDstMuxOutput) {
        rtRegNum = regDstMuxOutput;
    }

    public void setWriteRegNum(int writeRegNum) {
        this.writeRegNum = writeRegNum;
    }

    public void setDataToReg(int dataToReg) {
        this.dataToReg = dataToReg;
    }



    // the output of this class
    public int getRsContents() {
        return Regs[rsRegNum];
    }

    public int getRtContents() {
        return Regs[rtRegNum];
    }

    public int getWriteRegNum() {
        return writeRegNum;
    }

    public void writeDataToRegister() {
        Regs[writeRegNum] = dataToReg;
    }


    // regWrite from WB pipeline register

    public void setRegWrite(int regWrite) {
        this.regWrite = regWrite;
    }

    public int getRegWrite() {
        return regWrite;
    }

    public String regFileToString() {
        String rtn = "";
        rtn += "rsRegNum: " + rsRegNum + "     ";
        rtn += "rtRegNum: " + rtRegNum + "     ";
        rtn += "writeRegNum: " + writeRegNum + "     ";
        rtn += "dataToReg: 0x" + Integer.toHexString(dataToReg) + "     ";
        rtn += "rsContents: 0x" + Integer.toHexString(getRsContents()) + "     ";
        rtn += "rtContents: 0x" + Integer.toHexString(getRtContents()) + "     ";
        return rtn;
    }

    public String toString() {
        String rtn = "\n";
        for (int i = 0; i < Regs.length; i++) {
            rtn += "Reg " + i + ":  " + Integer.toHexString(Regs[i]) + "\n";
        }
        return rtn;
    }

}
