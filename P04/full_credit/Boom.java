import java.util.Scanner;

public class Boom {
    public Boom() {
        puzzle = new Puzzle("java rocks");
        fuse = new Fuse(8);
    }
    public void cli() {
        
        System.out.print("         =================\n" 
                       + "             B O O M !\n"     
                       + "         =================\n" 
                       + "Enter lower case letters to guess, \n"
                       + "! to propose a solution,\n"
                       + "0 to exit.\n\n");
       while (true) {
           System.out.println(fuse);
           System.out.println(puzzle);
           System.out.print("Guess a letter: ");
           char guess = in.nextLine().charAt(0);
           if(guess == '0') System.exit(0);
           else if(guess == '!') {
               System.out.print("What is the phrase? ");
               if(puzzle.solve(in.nextLine())) {
                   System.out.println("*** W I N N E R ***");
               } else {
                   System.out.println("###### BOOM ######");
               }
               break;
           } else {
               if(puzzle.guess(guess)) { // Correct guess
                   System.out.println(guess + " is in the phrase!");
               } else {
                   System.out.println("No, " + guess + " is incorrect");
                   if (!fuse.burn()) {
                       System.out.println("###### BOOM ######");
                       break;
                   }
               }
           }
       }
       System.out.println("The secret phrase was " + puzzle.getSolution());
    }
    public static void main(String[] args) {
        Boom boom = new Boom();
        boom.cli();
    }
    Puzzle puzzle;
    Fuse fuse;
    Scanner in = new Scanner(System.in);
}
