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

    public void quickSort(int low, int high) {
        if (students.isEmpty() || students.size() <= 1) return;

        CustomStack<Student> tempStack = copyStack(students);
        CustomStack<Student> sortedStack = quickSortHelper(tempStack);

        // Sao chép stack đã sắp xếp về stack chính
        while (!students.isEmpty()) {
            students.pop();
        }
        while (!sortedStack.isEmpty()) {
            students.push(sortedStack.pop());
        }
    }

    private CustomStack<Student> quickSortHelper(CustomStack<Student> stack) {
        if (stack.size() <= 1) {
            return stack; // Trả về stack nếu chỉ có 1 phần tử hoặc rỗng
        }

        // Chọn pivot
        Student pivot = stack.pop();
        CustomStack<Student> left = new CustomStack<>();  // Chứa điểm >= pivot
        CustomStack<Student> right = new CustomStack<>(); // Chứa điểm < pivot

        // Phân chia các phần tử vào left và right
        while (!stack.isEmpty()) {
            Student current = stack.pop();
            if (current.getMarks() >= pivot.getMarks()) { // Sắp xếp từ cao đến thấp
                left.push(current);
            } else {
                right.push(current);
            }
        }

        // Đệ quy sắp xếp hai phần
        CustomStack<Student> sortedLeft = quickSortHelper(left);
        CustomStack<Student> sortedRight = quickSortHelper(right);

        // Gộp lại kết quả
        CustomStack<Student> result = new CustomStack<>();
        while (!sortedRight.isEmpty()) {
            result.push(sortedRight.pop());
        }
        result.push(pivot);
        while (!sortedLeft.isEmpty()) {
            result.push(sortedLeft.pop());
        }

        return result;
    }





    public void bubbleSort() {
        if (students.isEmpty() || students.size() <= 1) return;

        CustomStack<Student> tempStack = copyStack(students);
        CustomStack<Student> sortedStack = new CustomStack<>();

        int size = tempStack.size();
        for (int i = 0; i < size - 1; i++) {
            CustomStack<Student> tempBuffer = new CustomStack<>();
            Student previous = tempStack.pop();

            for (int j = 0; j < size - i - 1; j++) {
                Student current = tempStack.pop();
                if (previous.getMarks() > current.getMarks()) {
                    tempBuffer.push(previous);
                    previous = current;
                } else {
                    tempBuffer.push(current);
                }
            }
            sortedStack.push(previous);
            while (!tempBuffer.isEmpty()) {
                tempStack.push(tempBuffer.pop());
            }
        }
        sortedStack.push(tempStack.pop());

        // Sao chép stack đã sắp xếp về stack chính
        while (!students.isEmpty()) {
            students.pop();
        }
        while (!sortedStack.isEmpty()) {
            students.push(sortedStack.pop());
        }
    }
    private CustomStack<Student> copyStack(CustomStack<Student> original) {
        CustomStack<Student> tempStack = new CustomStack<>();
        CustomStack<Student> copyStack = new CustomStack<>();

        // Sao chép tạm thời
        while (!original.isEmpty()) {
            tempStack.push(original.pop());
        }

        // Khôi phục stack gốc và sao chép
        while (!tempStack.isEmpty()) {
            Student item = tempStack.pop();
            original.push(item);
            copyStack.push(item);
        }

        return copyStack;
    }

    private void copyStackTo(CustomStack<Student> source, CustomStack<Student> target) {
        CustomStack<Student> tempStack = new CustomStack<>();
        while (!source.isEmpty()) tempStack.push(source.pop());
        while (!tempStack.isEmpty()) target.push(tempStack.pop());
    }



    public boolean isDuplicateName(String newName) {
        return false;
    }


}
