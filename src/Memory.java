public class Memory {

    private int[] Main_Mem;
    private final int memSize = 1024;

    public Memory() {
        Main_Mem = new int[1024];
        short num2store = 0;
        for (int i = 0; i < Main_Mem.length; i++) {
            Main_Mem[i] = num2store;
            num2store++;
            if (num2store == 0x100) {
                num2store = 0;
            }
        }
    }

    public int read(int address) {
        return Main_Mem[address];
    }

    public void write(int data, int address) {
        Main_Mem[address] = data;
    }

    public String toString() {
        String rtn = "";
        for (int i = 0; i < Main_Mem.length; i++) {
            rtn += "Address 0x:" + Integer.toHexString(i) + ", Data 0x" + Integer.toHexString(Main_Mem[i]) + "\n";
        }
        return rtn;
    }

    // test
    public static void main(String[] args) {
        Memory mem = new Memory();
        System.out.println("mem length: " + mem.Main_Mem.length);
        int data = 69;
        int address = 0xa;
        System.out.println("data: ---- " + data);
        System.out.println("address: ---- " + address);
        mem.write(data, address);
        System.out.println("data read: --- " + mem.read(0xa));
        System.out.println(mem.toString());
        System.out.println(mem.read(264));
    }
}
