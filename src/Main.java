public class  Main {

    public void runProgram(int[] instructionSequence) {
        CPU cpu = new CPU();
        cpu.setInstructionCache(instructionSequence);
        for (int i = 0; i < instructionSequence.length; i++) {
            System.out.println("\n\n\n\n\n\n\n\n                                            CPU CYCLE : " + (i + 1));
            System.out.println("                                            _____________\n\n\n");
            cpu.advanceCycle();
        }
    }

    public static void main(String[] args) {
        Main demo = new Main();
        int[] instructions = new int[]{0xa1020000, 0x810AFFFC, 0x00831820, 0x01263820, 0x01224820, 0x81180000, 0x81510010, 0x00624022, 0x00000000, 0x00000000, 0x00000000, 0x00000000};
        demo.runProgram(instructions);
    }
}
