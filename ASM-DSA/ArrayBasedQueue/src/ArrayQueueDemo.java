class ArrayBasedQueue {
    private int[] queue; // Array to hold the elements
    private int front;   // Index of the front element
    private int rear;    // Index of the rear position
    private int size;    // Current number of elements
    private int capacity; // Maximum capacity of the queue

    // Constructor to initialize the queue
    public ArrayBasedQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new int[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    // Enqueue operation
    public void enqueue(int value) {
        if (isFull()) {
            throw new IllegalStateException("Queue is full");
        }
        rear = (rear + 1) % capacity; // Circular increment
        queue[rear] = value;
        size++;
    }

    // Dequeue operation
    public int dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        int value = queue[front];
        front = (front + 1) % capacity; // Circular increment
        size--;
        return value;
    }

    // Peek operation
    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return queue[front];
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Check if the queue is full
    public boolean isFull() {
        return size == capacity;
    }

    // Return the current size of the queue
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
        for (int i = 0; i < size; i++) {
            System.out.print(queue[(front + i) % capacity] + " ");
        }
        System.out.println();
    }
}

public class ArrayQueueDemo {
    public static void main(String[] args) {
        // Initialize a queue with capacity 5
        ArrayBasedQueue queue = new ArrayBasedQueue(5);

        // Enqueue operations
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);
        queue.enqueue(50);

        // Display the queue
        queue.display();

        // Dequeue operation
        System.out.println("Dequeued: " + queue.dequeue());

        // Peek operation
        System.out.println("Front element: " + queue.peek());

        // Display the queue after dequeue
        queue.display();

        // Enqueue another element
        queue.enqueue(60);
        queue.display();

        // Check if queue is full
        System.out.println("Is queue full? " + queue.isFull());
    }
}
