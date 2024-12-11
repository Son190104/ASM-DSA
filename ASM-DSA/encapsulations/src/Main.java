public class Student {
    // Private properties
    private String name;
    private int grade;

    // Constructor to initialize Student object
    public Student(String name, int grade) {
        this.name = name;
        if (grade >= 0 && grade <= 10) {
            this.grade = grade;
        } else {
            System.out.println("Invalid grade. Grade will be assigned as 0.");
            this.grade = 0; // Assign default value if grade is invalid
        }
    }
    // Method to get student name
    public String getName() {
        return name;
    }

    // Method to get student grade
    public int getGrade() {
        return grade;
    }

    // Method to update grades (ensure valid grades)
    public void setGrade(int grade) {
        if (grade >= 0 && grade <= 10) {
            this.grade = grade;
        } else {
            System.out.println("Invalid grade. Please enter a grade from 0 to 10.");
        }
    }
    // Method to get student's academic performance based on grades
    public String getPerformance() {
        if (grade >= 8) {
            return "Excellent";
        } else if (grade >= 5) {
            return "Fair";
        } else {
            return "Weak";
        }
    }
    public static void main(String[] args) {
// Create a student object
        Student student = new Student("Nguyen Van A", 7);

// Get and print student information
        System.out.println("Student name: " + student.getName()); // Output: Nguyen Van A
        System.out.println("Score: " + student.getGrade()); // Output: 7
        System.out.println("Academic performance: " + student.getPerformance()); // Output: Good

// Update student score
        student.setGrade(9);
        System.out.println("Score after update: " + student.getGrade()); // Output: 9
        System.out.println("Academic performance: " + student.getPerformance()); // Output: Excellent
// Invalid score attempt
        student.setGrade(11); // Output: Invalid score. Please enter score from 0 to 10. }}