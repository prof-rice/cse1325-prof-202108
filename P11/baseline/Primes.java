import java.util.ArrayList;
import java.text.NumberFormat;

// Committed to the Public Domain by Prof Rice
// You MAY adopt and modify this code without attribution

public class Primes {
    public Primes(int numThreads) {
    }
    public Primes findPrimes(int lower, int upper) {
        return this;    
    }
    public int numberOfPrimes() {
        return 0; // replace
    }
    public Integer[] toArray() {
        return new Integer[0]; // replace
    }
    
    public static void main(String[] args) {
        int numThreads = 1;
        int lower = 0;
        int upper = 10000000; // defaults
        if(args.length > 0) numThreads = Integer.parseInt(args[0]);
        if(args.length > 1) lower = Integer.parseInt(args[1]);
        if(args.length > 2) upper = Integer.parseInt(args[2]);
        if(args.length > 3) {
            System.err.println("usage: java Primes [lower upper]");
            System.exit(-args.length);
        }
        
        Primes primes = new Primes(numThreads); // Search using one thread
        int sumOfPrimes = 0;
        for(int prime : primes.findPrimes(lower, upper).toArray())
            sumOfPrimes += prime;
        System.out.println("Sum of the " + primes.numberOfPrimes() + " primes between " 
                         + lower + " and " 
                         + upper + " is " 
                         + NumberFormat.getIntegerInstance().format(sumOfPrimes));
    }
}

