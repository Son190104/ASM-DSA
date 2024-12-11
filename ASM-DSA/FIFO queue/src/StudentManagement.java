import java.util.LinkedList;
import java.util.Queue;

public class StudentManagement {
    public static void main(String[] args) {
        // Initialize a FIFO queue for student consultations
        Queue<String> consultationQueue = new LinkedList<>();

        // Students register for consultation
        System.out.println("Students registering for consultation...");
        consultationQueue.add("Student A");
        consultationQueue.add("Student B");
        consultationQueue.add("Student C");

        System.out.println("Initial Consultation Queue: " + consultationQueue);

        // First student is served
        System.out.println("Serving: " + consultationQueue.remove());

        // Check the next student in line
        System.out.println("Next in Line: " + consultationQueue.peek());

        // Another student registers
        System.out.println("Student D registers for consultation...");
        consultationQueue.add("Student D");

        // Display the updated queue
        System.out.println("Updated Consultation Queue: " + consultationQueue);

        // Serve all remaining students
        System.out.println("Serving remaining students:");
        while (!consultationQueue.isEmpty()) {
            System.out.println("Serving: " + consultationQueue.remove());
        }

        // Final state of the queue
        System.out.println("All consultations completed. Queue Empty: " + consultationQueue.isEmpty());
    }
}
