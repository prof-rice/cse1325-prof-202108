import java.util.Scanner;

public class Grades {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        System.out.print("Enter student's name: ");
        String name = in.nextLine();
        
        Student student = new Student(name);
        
        while(true) {
            System.out.print("Enter next grade (< 0 when done): ");
            double grade = in.nextDouble();
            if(grade < 0) break;
            student.addExam(grade);
        }
        
        System.out.println(student.getName() + " has a " 
                         + student.average() +  " average.");
    }

}
