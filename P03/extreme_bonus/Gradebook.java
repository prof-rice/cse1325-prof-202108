import java.util.Scanner;
import java.util.ArrayList;

public class Gradebook {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        ArrayList<Student> students = new ArrayList<>();
        
        String menu = "\n\n"  // Use text block in Java 16
                    + "Main Menu\n"
                    + "=========\n\n"
                    + "Add new  (S)tudents\n"
                    + "Add exam (G)rades\n"
                    + "List all (A)verages\n"
                    + "E(X)it\n\n"
                    + "Command? ";
        
        String command = "";
        
        while(!command.equals("X")) {
            System.out.print(menu);
            command = in.nextLine().toUpperCase();
            switch(command) {
                case "S" -> {
                    String name = "";
                    while(true) {
                        System.out.print("New student's name (Enter when done)? ");
                        name = in.nextLine();
                        if(name.length() == 0) break;
                        students.add(new Student(name));
                    }
                }
                case "G" -> {
                    for(Student s : students) {
                        System.out.print(s.getName() + "'s grade (-1 to skip)? ");
                        int grade = in.nextInt();
                        if(grade >= 0) s.addExam(grade);
                    }
                    String nl = in.nextLine(); // clear newline
                }
                case "A" -> {
                    int maxNameSize = 0;
                    for(Student s : students) 
                        maxNameSize = Math.max(maxNameSize, s.getName().length());
                    String fmt = "%" + maxNameSize + "s %8.2f\n";
                    for(Student s : students) 
                        System.out.printf(fmt, s.getName(), s.average());
                }
                case "X" -> {
                }
                default -> {
                    System.err.println("Invalid command!");
                }
            }
        }
    }
}
