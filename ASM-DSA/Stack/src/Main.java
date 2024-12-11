import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();  // Initialize the stack
        System.out.println("Stack initialized.");
    }
}


public class Main {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();  // Initialize the stack

        stack.push(10);  // Pushing an element to the stack
        stack.push(20);  // Pushing another element
        stack.push(30);

        System.out.println("Stack after push operations: " + stack);
    }
}

public class Main {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        stack.push(10);
        stack.push(20);
        stack.push(30);

        int poppedElement = stack.pop();  // Pop the top element
        System.out.println("Popped element: " + poppedElement);
        System.out.println("Stack after pop operation: " + stack);
    }
}

public class Main {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        stack.push(10);
        stack.push(20);
        stack.push(30);

        int topElement = stack.peek();  // Peek at the top element

        System.out.println("Top element (peek): " + topElement);
        System.out.println("Stack after peek operation: " + stack);
    }
}

public class Main {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        System.out.println("Is stack empty? " + stack.isEmpty());

        stack.push(10);

        System.out.println("Is stack empty after push? " + stack.isEmpty());
    }
}
