public class Mux {

    public Mux(String name) {
        muxName = name;
    }

    private int dataWhen0;
    private int dataWhen1;
    private int controlBit = 0;
    private String muxName = "";

    /*  About   outputs either input0 or input1 depending on control bit set*/
    public int output()  {
        if (controlBit == 0) {
            return dataWhen0;
        } else if (controlBit == 1) {
            return dataWhen1;
        } else if (controlBit == -1) {
            System.out.println(muxName + "control unit said they don't care");
            return dataWhen0;
        } else {
            System.out.println("ERROR: Control bit is not 0 or 1 or -1, you dingus");
            return -12345;
        }
    }

    public String toString() {
        String rtn = muxName + " Mux state \n";
        rtn += "data when control 0: " + dataWhen0 + "\n";
        rtn += "data when control 0: " + dataWhen1 + "\n";
        rtn += "current control bit: " + controlBit + "\n";
        return rtn;
    }


    // __________ GETTER / SETTER _____________________________


    public void setControlBit(int control) {
        this.controlBit = control;
    }

    public void setDataWhen0(int inputA) {
        this.dataWhen0 = inputA;
    }

    public void setDataWhen1(int inputB) {
        this.dataWhen1 = inputB;
    }

}