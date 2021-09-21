public class Fuse {
    public Fuse(int time) {
        this.time = time;
    }
    public boolean burn() {  // true if any fuse remains
        if(time > 0) --time;
        return time > 0;
    }
    @Override 
    public String toString() {
        String result = "   ";
        for(int i = 0; i < time; ++i) result += '_';
        result += "*\n  /\n,+,\n| |\n|_|\n";
        return result;
    }
    private int time;
}

