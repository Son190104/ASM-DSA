// Node class
class Node {
    int data;       // Data stored in the node
    Node next;      // Reference to the next node

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

// Queue class using linked list
class LinkedListBasedQueue {
    private Node head;  // Front of the queue
    private Node tail;  // Rear of the queue
    private int size;   // Number of elements in the queue

    // Constructor to initialize the queue
    public LinkedListBasedQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // Enqueue operation
    public void enqueue(int value) {
        Node newNode = new Node(value);
        if (isEmpty()) {
            head = tail = newNode; // Both head and tail point to the new node
        } else {
            tail.next = newNode;  // Link the new node to the current tail
            tail = newNode;       // Update the tail pointer
        }
        size++;
    }

    // Dequeue operation
    public int dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        int value = head.data; // Retrieve the data from the front node
        head = head.next;      // Move the head pointer to the next node
        if (head == null) {    // If the queue becomes empty
            tail = null;       // Set tail to null as well
        }
        size--;
        return value;
    }

    // Peek operation
    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return head.data; // Return the data at the front
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return head == null;
    }

    // Get the size of the queue
    public int getSize() {
        return size;
    }

    // Display the queue elements
    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        System.out.print("Queue: ");
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
}

// Demonstration class
public class LinkedListQueueDemo {
    public static void main(String[] args) {
        // Initialize the queue
        LinkedListBasedQueue queue = new LinkedListBasedQueue();

        // Enqueue operations
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        // Display the queue
        queue.display();

        // Dequeue operation
        System.out.println("Dequeued: " + queue.dequeue());

        // Peek operation
        System.out.println("Front element: " + queue.peek());

        // Display the queue after dequeue
        queue.display();

        // Enqueue more elements
        queue.enqueue(40);
        queue.enqueue(50);

        // Display the updated queue
        queue.display();

        // Get the size of the queue
        System.out.println("Queue size: " + queue.getSize());
    }
}
