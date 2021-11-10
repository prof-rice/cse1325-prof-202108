import java.util.Collections;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class Track {
    public final int HORSES; // Number of horses to race
    public Track(int numHorses) {
        this.HORSES = numHorses;
    
        // Randomly assign vaguely clever names to each horse
        names = Arrays.asList(
            "Legs of Spaghetti", "Ride Like the Calm", "Duct-taped Lightning",
            "Flash Light", "Speedphobia", "Cheat Ah!", "Go For Broken",
            "Whining Racer", "Spectacle", "Cannons a'Boring", "Plodding Prince",
            "Lucky Snooze", "Wrong Way", "Fawlty Powers", "Broken Tip",
            "American Zero", "Exterminated", "Great Regret", "Manual", "Lockout",
            "2 Biggaherd");
        Collections.shuffle(names);
    
        // Instance the horses
        horses = new ArrayList<>();
        for (int i=0; i<HORSES; ++i)
            horses.add(new Horse(names.get(Math.min(i, names.size()-1)),
                                 100 + (int) (Math.random()*100)));
    
        // Create the threads
        threads = new ArrayList<>();
        for (int i=0; i<HORSES; ++i) 
            threads.add(new Thread(horses.get(i)));
    }
    
    public void startRace() {
        for(Thread t : threads) t.start();
    }
    
    public void showTrack() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        for(Horse horse : horses) {
            System.out.println(horse.view());
        }
    }
    
    public void endRace() {
        for(Thread t : threads) 
            try {t.join();}
            catch (InterruptedException e) {}
    }
    
    public static void main(String[] args) {
        Track track = new Track(20);
        track.startRace(); // And they're off!!!
        
        while(Horse.winner().isEmpty()) {
            try {Thread.sleep(100);}
            catch (InterruptedException e) {}
            track.showTrack();
        }
        
        track.endRace(); 
        System.out.println("\n### The winner is " + Horse.winner() + "!!!\n");
    }
        
    public final List<String> names; 
    public ArrayList<Horse> horses;
    public ArrayList<Thread> threads;
}
