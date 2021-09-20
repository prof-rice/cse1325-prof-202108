public class Student {
    public Student(String name) {
        this.studentName = name;
    }
    public String getName() {
        return "~" + studentName;
    }
    public void addExam(double grade) {
        examSum += grade;
        ++examNumGrades;
    }
    public double average() {
        return (2*examSum) / examNumGrades;
    }
    
    private String studentName;
    private double examSum;
    private double examNumGrades;
}
