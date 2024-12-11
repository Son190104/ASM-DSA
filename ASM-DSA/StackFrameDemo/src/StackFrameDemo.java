public class StackFrameDemo {
    public static void main(String[] args) {
        functionA();
    }

    public static void functionA() {
        int x = 10; // Local variable in functionA
        functionB(x);
    }

    public static void functionB(int y) {
        int z = y + 5; // Local variable in functionB
        System.out.println("Value of z: " + z);
    }
}
