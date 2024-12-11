public class FunctionCallDemo {
    public static void main(String[] args) {
        System.out.println("Main starts");
        int result = add(5, 10);  // Call the 'add' function
        System.out.println("Result: " + result);
        System.out.println("Main ends");
    }
    public static int add(int a, int b) {
        System.out.println("Add function starts");
        int sum = a + b;  // Local variable
        System.out.println("Sum: " + sum);
        System.out.println("Add function ends");
        return sum;  // Return value
    }
}

