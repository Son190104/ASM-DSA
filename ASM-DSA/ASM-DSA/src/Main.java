import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Generate Random Students");
            System.out.println("2. Display All Students");
            System.out.println("3. Add Student");
            System.out.println("4. Edit Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Sort Students By Marks");
            System.out.println("7. Search Student By ID");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter number of students to generate: ");
                        int num = scanner.nextInt();
                        manager.generateRandomStudents(num);
                        System.out.println(num + " random students generated successfully!");
                    }
                    case 2 -> {
                        System.out.println("\n--- List of Students ---");
                        manager.printStudents();
                        System.out.println("-----------------------");
                    }
                    case 3 -> {
                        try {
                            System.out.print("Enter ID: ");
                            int id = scanner.nextInt();

                            if (manager.searchStudentById(id) != null) {
                                System.out.println("ID already exists. Student not added.");
                                continue;
                            }

                            System.out.print("Enter Name: ");
                            scanner.nextLine(); // Consume newline
                            String name = scanner.nextLine();

                            if (manager.isDuplicateName(name)) {
                                System.out.println("Name already exists. Student not added.");
                                continue;
                            }

                            if (!name.matches("[a-zA-Z ]+")) {
                                System.out.println("Invalid name. Names cannot contain numbers or special characters.");
                                continue;
                            }

                            System.out.print("Enter Marks: ");
                            double marks = scanner.nextDouble();
                            if (marks > 10) {
                                System.out.println("Marks cannot be greater than 10. Student not added.");
                            } else {
                                manager.addStudent(new Student(id, name, marks));
                                System.out.println("Student added successfully!");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Ensure ID is an integer and Marks is a number.");
                            scanner.nextLine(); // Clear buffer
                        }
                    }
                    case 4 -> {
                        try {
                            System.out.print("Enter ID of student to edit: ");
                            int id = scanner.nextInt();
                            scanner.nextLine(); // Consume newline

                            Student existingStudent = manager.searchStudentById(id);
                            if (existingStudent == null) {
                                System.out.println("Student not found. Edit failed.");
                                continue;
                            }

                            System.out.print("Enter New Name (or press Enter to keep current name): ");
                            String newName = scanner.nextLine();
                            boolean editName = !newName.isBlank();

                            if (editName) {
                                if (manager.isDuplicateName(newName)) {
                                    System.out.println("Name already exists. Edit failed.");
                                    continue;
                                }
                                if (!newName.matches("[a-zA-Z ]+")) {
                                    System.out.println("Invalid name. Names cannot contain numbers or special characters.");
                                    continue;
                                }
                            }

                            System.out.print("Enter New Marks (or -1 to keep current marks): ");
                            double newMarks = scanner.nextDouble();
                            boolean editMarks = newMarks != -1;

                            if (editMarks && newMarks > 10) {
                                System.out.println("Marks cannot be greater than 10. Edit failed.");
                                continue;
                            }

                            boolean success = manager.editStudent(
                                    id,
                                    editName ? newName : existingStudent.getName(),
                                    editMarks ? newMarks : existingStudent.getMarks()
                            );

                            if (success) {
                                System.out.println("Student updated successfully!");
                            } else {
                                System.out.println("Student not found.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Ensure ID is an integer and Marks is a number.");
                            scanner.nextLine(); // Clear buffer
                        }
                    }
                    case 5 -> {
                        try {
                            System.out.print("Enter ID of student to delete: ");
                            int id = scanner.nextInt();
                            if (manager.deleteStudent(id)) {
                                System.out.println("Student deleted successfully!");
                            } else {
                                System.out.println("Student not found.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Ensure ID is an integer.");
                            scanner.nextLine(); // Clear buffer
                        }
                    }
                    case 6 -> {
                        System.out.println("1. QuickSort");
                        System.out.println("2. BubbleSort");
                        System.out.print("Enter your choice: ");

                        try {
                            int sortChoice = scanner.nextInt();
                            long startTime, endTime;
                            if (sortChoice == 1) {
                                startTime = System.nanoTime();
                                manager.quickSort(0, manager.students.size() - 1);
                                endTime = System.nanoTime();
                                System.out.println("QuickSort completed.");
                            } else if (sortChoice == 2) {
                                startTime = System.nanoTime();
                                manager.bubbleSort();
                                endTime = System.nanoTime();
                                System.out.println("BubbleSort completed.");
                            } else {
                                System.out.println("Invalid choice! Returning to menu.");
                                break;
                            }
                            manager.printStudents();
                            long duration = endTime - startTime;
                            System.out.printf("Sorting time: %.3f ms%n", duration / 1_000_000.0);
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Enter 1 or 2 for sorting choice.");
                            scanner.nextLine(); // Clear buffer
                        }
                    }
                    case 7 -> {
                        try {
                            System.out.print("Enter ID to search: ");
                            int id = scanner.nextInt();
                            Student student = manager.searchStudentById(id);
                            if (student != null) {
                                System.out.println(student);
                            } else {
                                System.out.println("Student not found.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Ensure ID is an integer.");
                            scanner.nextLine(); // Clear buffer
                        }
                    }
                    case 8 -> {
                        System.out.println("Exiting...");
                        break;
                    }
                    default -> System.out.println("Invalid choice! Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine(); // Clear the buffer
                choice = -1; // Reset choice to continue the loop
            }
        } while (choice != 8);

        scanner.close();
    }
}
