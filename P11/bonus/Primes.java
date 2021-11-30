import java.util.ArrayList;
import java.text.NumberFormat;

public class Primes {
    public Primes(int numThreads) {
        this.numThreads = numThreads;
        primes = new ArrayList<>();
    }
    public Primes findPrimes(int lower, int upper) {
        slice = (upper - lower) / 100;
        nextLower = lower - slice;
        final int finalUpper = upper;
        Thread[] threads = new Thread[numThreads];
        for(int i=0; i<numThreads; ++i) {          
            final int thread = i;
            threads[i] = new Thread(() -> addPrimes(finalUpper, thread));
            threads[i].start();
        }
        for(Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("Interrupted Exception");
            }
        }
        return this;    
    }
    protected void addPrimes(int upper, int thread) {
        while(true) {
            int lowerRange = getRange();
            if (lowerRange >= upper) break;
            int upperRange = Math.min(lowerRange + slice -1, upper);
            String format = "Thread %02d searching %10d to %10d\n";
            System.err.printf(format, thread, lowerRange, upperRange);
            for(int i=lowerRange; i<=upperRange; ++i) {
                if(isPrime(i)) 
                    addPrime(i);
            }
        }
    }
    protected boolean isPrime(int number) {
        if(number < 2) return false;
        for (int i=2; i <= Math.sqrt(number); ++i) {
            if ((number % i) == 0) return false;
        }
        return true;
    }
    public synchronized void addPrime(int prime) {
        primes.add(prime);
    }
    public synchronized int getRange() {
        nextLower += slice;
        return nextLower;
    }
    public int numberOfPrimes() {return primes.size();}
    public Integer[] toArray() {return primes.toArray(new Integer[primes.size()]);}
    
    private int nextLower;
    private int slice;
    private int numThreads;
    private ArrayList<Integer> primes;
    
    public static void main(String[] args) {
        int numThreads = 1;
        int lower = 0;
        int upper = 30000000; // defaults
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

