import java.util.ArrayList;
import java.util.Iterator;
import java.util.Comparator;
import java.util.Scanner;

class Student {
    private String id;
    private String name;
    private float marks;

    // Constructor
    public Student(String id, String name, float marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMarks() {
        return marks;
    }

    public void setMarks(float marks) {
        this.marks = marks;
    }

    public String getRank() {
        if (marks < 5.0) return "Fail";
        else if (marks < 6.5) return "Medium";
        else if (marks < 7.5) return "Good";
        else if (marks < 9.0) return "Very Good";
        else return "Excellent";
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Marks: " + marks + ", Rank: " + getRank();
    }
}

class StudentManager {
    private ArrayList<Student> students;

    // Constructor
    public StudentManager() {
        students = new ArrayList<>();
    }

    // Add a new student
    public void addStudent(String id, String name, float marks) {
        if (id == null || id.trim().isEmpty()) {
            System.out.println("Error: ID cannot be empty.");
            return;
        }
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Error: Name cannot be empty.");
            return;
        }
        if (name.matches(".*\\d.*")) {  // Check if name contains digits
            System.out.println("Error: Name cannot contain numbers.");
            return;
        }
        if (!id.matches("\\d+")) {
            System.out.println("Error: ID must be numeric.");
            return;
        }

        for (Student student : students) {
            if (student.getId().equals(id)) {
                System.out.println("Error: A student with this ID already exists.");
                return;
            }
        }
        if (marks < 0 || marks > 10) {
            System.out.println("Error: Marks must be between 0 and 10.");
            return;
        }
        students.add(new Student(id, name, marks));
        System.out.println("Student added successfully.");
    }

    // Edit an existing student
    public void editStudent(String id, String newName, Float newMarks) {
        if (newName != null && !newName.isEmpty() && newName.matches(".*\\d.*")) {
            System.out.println("Error: Name cannot contain numbers.");
            return;
        }

        for (Student student : students) {
            if (student.getId().equals(id)) {
                if (newName != null && !newName.isEmpty()) student.setName(newName);
                if (newMarks != null) {
                    if (newMarks < 0 || newMarks > 10) {
                        System.out.println("Error: Marks must be between 0 and 10.");
                        return;
                    }
                    student.setMarks(newMarks);
                }
                System.out.println("Student updated successfully.");
                return;
            }
        }
        System.out.println("Error: Student not found.");
    }


    // Delete a student
    public void deleteStudent(String id) {
        if (students.isEmpty()) {
            System.out.println("Error: No students to delete.");
            return;
        }

        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId().equals(id)) {
                iterator.remove();
                System.out.println("Student removed successfully.");
                return;
            }
        }
        System.out.println("Error: Student not found.");
    }

    // Search for a student by ID
    public void searchStudent(String id) {
        if (students.isEmpty()) {
            System.out.println("Error: No students to search.");
            return;
        }

        for (Student student : students) {
            if (student.getId().equals(id)) {
                System.out.println(student);
                return;
            }
        }
        System.out.println("Error: Student not found.");
    }

    // Sort students by marks or name
    public void sortStudents(String criterion) {
        if (students.isEmpty()) {
            System.out.println("Error: No students to sort.");
            return;
        }
        if (criterion.equalsIgnoreCase("marks")) {
            students.sort(Comparator.comparingDouble(Student::getMarks));
        } else if (criterion.equalsIgnoreCase("name")) {
            students.sort(Comparator.comparing(Student::getName));
        } else {
            System.out.println("Error: Invalid sorting criterion.");
            return;
        }
        System.out.println("Students sorted by " + criterion + ":");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    // Display all students
    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("Error: No students in the list.");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("\nStudent Management System");
                System.out.println("1. Add Student");
                System.out.println("2. Edit Student");
                System.out.println("3. Delete Student");
                System.out.println("4. Search Student");
                System.out.println("5. Sort Students");
                System.out.println("6. Display All Students");
                System.out.println("7. Exit");
                System.out.print("Choose an option: ");

                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Enter ID: ");
                        String id = scanner.nextLine();
                        System.out.print("Enter Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Marks: ");
                        float marks = Float.parseFloat(scanner.nextLine());
                        manager.addStudent(id, name, marks);
                        break;
                    case 2:
                        System.out.print("Enter ID of student to edit: ");
                        String editId = scanner.nextLine();
                        System.out.print("Enter New Name (or press Enter to skip): ");
                        String newName = scanner.nextLine();
                        System.out.print("Enter New Marks (or -1 to skip): ");
                        String marksInput = scanner.nextLine();
                        Float newMarks = marksInput.equals("-1") ? null :          Float.parseFloat(marksInput);
                        manager.editStudent(editId, newName.isEmpty() ? null : newName, newMarks);
                        break;
                    case 3:
                        System.out.print("Enter ID of student to delete: ");
                        String deleteId = scanner.nextLine();
                        manager.deleteStudent(deleteId);
                        break;
                    case 4:
                        System.out.print("Enter ID of student to search: ");
                        String searchId = scanner.nextLine();
                        manager.searchStudent(searchId);
                        break;
                    case 5:
                        System.out.print("Sort by 'marks' or 'name': ");
                        String criterion = scanner.nextLine();
                        manager.sortStudents(criterion);
                        break;
                    case 6:
                        manager.displayAllStudents();
                        break;
                    case 7:
                        System.out.println("Exiting...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Error: Invalid choice. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input. Please enter a number.");
            } catch (Exception e) {
                System.out.println("Error: An unexpected error occurred: " + e.getMessage());
            }
        }
    }
}
