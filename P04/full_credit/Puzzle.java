public class Puzzle {
    public Puzzle(String solution) {
        this.solution = solution.toLowerCase();
    } 
    public boolean guess(char c) {                  // true if valid guess
        c = Character.toLowerCase(c);
        if(c < 'a' || 'z' < c) return false;
        int index = (int) (c - 'a');
        if(guessed[index]) return false;
        guessed[index] = true;
        return solution.indexOf(c) != -1;
    }
    public boolean solve(String proposedSolution) { // true if correctly guessed
         return (proposedSolution.equals(solution));
    }
    public String toString() {
        String result = "";
        for(char c : solution.toCharArray()) {
            if(c < 'a' || 'z' < c) result += c;
            else result += guessed[c - 'a'] ? c : '_';
        }
        return result;
    }
    public String getSolution() {
        return solution;
    }
    
    // For English alphabet (26 letters)
    private boolean[] guessed = new boolean[26]; 
    private String solution;    
}
