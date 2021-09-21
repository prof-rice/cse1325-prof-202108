import java.util.Scanner;

public class Boom {
    public Boom() {
        String phrase = phrases[(int) (Math.random() * phrases.length)];
        // phrase = "java rocks"; // For testing
        puzzle = new Puzzle(phrase);
        fuse = new Fuse(8);
    }
    public void cli() {
        
        System.out.print("         =================\n" 
                       + "             B O O M !\n"     
                       + "         =================\n" 
                       + "Enter lower case letters to guess, \n"
                       + "! to propose a solution,\n"
                       + "0 to exit.\n\n");
       int guesses = 0;
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
               guesses++;
               if(puzzle.guess(guess)) { // Correct guess
                   System.out.println(guess + " is in the phrase!");
               } else {
                   System.out.println("No, " + guess + " is incorrect");
                   if (!fuse.burn()) {
                       System.out.println("Oh no - we're out of fuse!");
                       System.out.println("###### BOOM ######");
                       break;
                   }
               }
           }
       }
       System.out.println("The secret phrase was " + puzzle.getSolution()
                        + "\nYou guessed it in " + guesses + " guesses.");
    }
    public static void main(String[] args) {
        Boom boom = new Boom();
        boom.cli();
    }
    Puzzle puzzle;
    Fuse fuse;
    Scanner in = new Scanner(System.in);
    
    // NOT required - just makes the game more interesting
    String[] phrases = new String[] {
        "agreement",
        "amusement",
        "apparatus",
        "attention",
        "authority",
        "automatic",
        "beautiful",
        "behaviour",
        "committee",
        "condition",
        "conscious",
        "dependent",
        "different",
        "digestion",
        "direction",
        "discovery",
        "education",
        "existence",
        "expansion",
        "important",
        "insurance",
        "invention",
        "knowledge",
        "necessary",
        "operation",
        "political",
        "secretary",
        "selection",
        "statement",
        "structure",
        "substance",
        "transport",
        "yesterday",
        "adjustment",
        "attraction",
        "comparison",
        "connection",
        "discussion",
        "experience",
        "government",
        "instrument",
        "punishment",
        "suggestion",
        "competition",
        "destruction",
        "development",
        "observation",
        "responsible",
        "distribution",
        "organization",
        "advertisement",
        "representative",
    };
}
