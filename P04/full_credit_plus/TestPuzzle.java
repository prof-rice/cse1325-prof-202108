public class TestPuzzle {
    public static void main(String[] args) {
        String phrase  = "abcdaaa";
        String phrase1 = "_______";
        String phrase2 = "a___aaa";
        Puzzle puzzle = new Puzzle(phrase);
        
        // Puzzle as secret
        if(!phrase1.equals(puzzle.toString()))
            System.err.println("FAIL test 0: Invalid secret puzzle representation\n" 
                               + "  |" + puzzle  + "| returned\n"
                               + "  |" + phrase1 + "| expected");
        
        // Correct guess
        if(puzzle.guess('a') != true)
            System.err.println("FAIL test 1: Correct guess marked incorrect");
            
        // Puzzle as partially-guessed secret
        if(!phrase2.equals(puzzle.toString()))
            System.err.println("FAIL test 2: Invalid part-secret puzzle representation\n" 
                               + "  |" + puzzle  + "| returned\n"
                               + "  |" + phrase2 + "| expected");
        
        // Repeated guess (originally correct)
        if(puzzle.guess('a') != false)
            System.err.println("FAIL test 3: Repeated guess marked correct");
            
        // Incorrect guess
        if(puzzle.guess('x') == true)
            System.err.println("FAIL test 4: Incorrect guess marked correct");
            
        // Invalid guess
        if(puzzle.guess('#') == true)
            System.err.println("FAIL test 5: Invalid guess marked correct");
            
        // Invalid guesses to puzzle status
        if(!phrase2.equals(puzzle.toString()))
            System.err.println("FAIL test 6: Invalid guesses changed representation\n" 
                               + "  |" + puzzle  + "| returned\n"
                               + "  |" + phrase2 + "| expected");
        
       // Correct solution
       if(puzzle.solve(phrase) != true)
            System.err.println("FAIL test 7: Valid solution rejected");
    
       // Incorrect solution
       if(puzzle.solve("incorrect") == true)
            System.err.println("FAIL test 8: Invalid solution accepted");

       // Get solution
       if(!puzzle.getSolution().equals(phrase))
            System.err.println("FAIL test 9: Incorrect solution from Puzzle");
    }
}
