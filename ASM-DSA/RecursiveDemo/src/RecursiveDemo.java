public class RecursiveDemo {
    public static void main(String[] args) {
        int result = factorial(5);
        System.out.println("Factorial of 5: " + result);
    }

    public static int factorial(int n) {
        if (n == 1) {
            return 1; // Base case: no further recursive calls
        }
        return n * factorial(n - 1); // Recursive call
    }
}
