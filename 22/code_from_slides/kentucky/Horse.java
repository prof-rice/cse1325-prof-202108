public class Horse implements Runnable {
    public Horse(String name, int speed) {
        this.name = name;
        this.speed = speed;
        this.position = 30;
    }
    
    @Override
    public void run() {
        while(winner().isEmpty()) { // Nobody has won yet            
            if(position > 0) --position;
            if(position > 0) {
                try {Thread.sleep(speed + (int) (200 * Math.random()));}
                catch (InterruptedException e) {}
            } else {
                synchronized(lock) {
                    if(winner.isEmpty()) winner = name;
                }
            }
        }
    }
    
    // TODO: Should use StringBuffer here!
    String view() { // text for this horse's row in the Track
        String result = "";
        for (int i = 0; i < position; ++i) result += (i%5 == 0 ? ':' : '.');
        result += " " + name;
        return result;    
    }

    String name() {return name;}
    public static String winner() {
        String result;
        synchronized(lock) {
            result = winner;
        }
        return result;
    }
    
    private final String name;    // What the horse is called on the Track
    private int position;         // Distance from the finish line
    private int speed;            // Rough time between steps (in ms)
    
    private static Object lock = new Object();   // Controls write access to winner
    private static String winner = "";  // Name of 1st horse across the finish line
}
