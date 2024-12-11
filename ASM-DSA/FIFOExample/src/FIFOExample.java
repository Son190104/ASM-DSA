import java.util.LinkedList;
import java.util.Queue;

public class FIFOExample {
    public static void main(String[] args) {
        // Initialize a queue using LinkedList
        Queue<Integer> queue = new LinkedList<>();

        // Enqueue operations
        System.out.println("Adding elements to the queue...");
        queue.add(10); // Add 10
        queue.add(20); // Add 20
        queue.add(30); // Add 30

        System.out.println("Queue after enqueue operations: " + queue);

        // Dequeue operation
        int removed = queue.remove();
        System.out.println("Removed Element: " + removed);

        // Peek operation
        int front = queue.peek();
        System.out.println("Front Element: " + front);

        // Display final state of the queue
        System.out.println("Queue after dequeue operation: " + queue);

        // Check if queue is empty
        System.out.println("Is the queue empty? " + queue.isEmpty());

        // Display size of the queue
        System.out.println("Size of the queue: " + queue.size());
    }
}
