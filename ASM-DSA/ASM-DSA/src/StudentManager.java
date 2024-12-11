import java.util.Random;
import java.util.Scanner;

public class StudentManager {
    CustomStack<Student> students;
    private CustomStack<String> nameSamples;

    public StudentManager() {
        this.students = new CustomStack<>();
        this.nameSamples = new CustomStack<>();
    }

    private double roundToOneDecimal(double value) {
        return Math.round(value * 10.0) / 10.0;
    }

    public void generateRandomStudents(int numberOfStudents) {
        Random random = new Random();
        String[] names = {
                "Quyen", "Son", "Tuan", "Trang", "Viet", "Yen",
                "Kien", "Thao", "Bich", "Hoang", "Thanh", "Hoai",
                "An", "Binh", "Cuong", "Duy", "Hoa", "Huynh",
                "Lan", "Linh", "Minh", "Nam", "Nga", "Nguyet"
        };

        for (int i = 1; i <= numberOfStudents; i++) {
            int id = i;
            String name = names[random.nextInt(names.length)] + " " + names[random.nextInt(names.length)];
            double marks = roundToOneDecimal(1 + (9 * random.nextDouble()));
            students.push(new Student(id, name, marks));
        }
    }

    public void printStudents() {
        CustomStack<Student> tempStack = new CustomStack<>();
        while (!students.isEmpty()) {
            Student student = students.pop();
            System.out.println(student);
            tempStack.push(student);
        }
        while (!tempStack.isEmpty()) {
            students.push(tempStack.pop());
        }
    }

    public void addStudent(Student student) {
        if (student.getMarks() > 10) {
            System.out.println("Marks cannot be greater than 10. Student not added.");
            return; // Kết thúc phương thức ngay tại đây
        }
        student.setMarks(roundToOneDecimal(student.getMarks()));
        students.push(student);
        System.out.println("Student added successfully: " + student);
    }


    public boolean editStudent(int id, String newName, double newMarks) {
        CustomStack<Student> tempStack = new CustomStack<>();
        boolean found = false;

        while (!students.isEmpty()) {
            Student student = students.pop();
            if (student.getId() == id) {
                String finalName = (newName != null) ? newName : student.getName();
                double finalMarks = (newMarks != -1) ? newMarks : student.getMarks();
                tempStack.push(new Student(id, finalName, roundToOneDecimal(finalMarks)));
                found = true;
            } else {
                tempStack.push(student);
            }
        }

        while (!tempStack.isEmpty()) {
            students.push(tempStack.pop());
        }

        return found;
    }

    public boolean deleteStudent(int id) {
        CustomStack<Student> tempStack = new CustomStack<>();
        boolean deleted = false;

        while (!students.isEmpty()) {
            Student student = students.pop();
            if (student.getId() == id) {
                deleted = true;
            } else {
                tempStack.push(student);
            }
        }

        while (!tempStack.isEmpty()) {
            students.push(tempStack.pop());
        }

        return deleted;
    }

    public Student searchStudentById(int id) {
        CustomStack<Student> tempStack = new CustomStack<>();
        Student found = null;

        while (!students.isEmpty()) {
            Student student = students.pop();
            if (student.getId() == id) {
                found = student;
            }
            tempStack.push(student);
        }

        while (!tempStack.isEmpty()) {
            students.push(tempStack.pop());
        }

        return found;
    }

    public void quickSort(int i, int i1) {
    }

    public void bubbleSort() {
    }

    public boolean isDuplicateName(String newName) {
        return false;
    }
}
