import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Random;

// This program compares appending doubles to ArrayList, LinkedList, and Deque

class Append {
    public final static int DOUBLES = 1000;
    public final static String FORMAT = "%10s  %4i";

    public static void main(String[] args) {
        ArrayList<Integer> arraylist = new ArrayList<>();
        ArrayDeque<Integer> arraydeque = new ArrayDeque<>();
        LinkedList<Integer> linkedlist = new LinkedList<>();
        
        
    
    
    
    
    
        Random random = new Random();
        
        
        
        
        
        // Get number of chars (default 1000)
        if(args.length > 1) {
            System.err.println(
                "usage: java StringBufferVsString [#chars]");
            System.exit(-1);
        }
        int size = (args.length > 0) 
                 ? Integer.parseInt(args[0]) 
                 : 1000;
    
        // Create really big String
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<size; ++i) 
            sb.append((char)(random.nextInt(26) + 'a'));
        String s = sb.toString();
        // System.out.println(s);
        
        // Try using String concatenation
        long scStartTime = System.nanoTime();
        String scString = ReverseWithNewString(s);
        long scElapsedTime = System.nanoTime() - scStartTime;

        // Try using StringBuffer
        long sbStartTime = System.nanoTime();
        String sbString = ReverseWithStringBuffer(s);
        long sbElapsedTime = System.nanoTime() - sbStartTime;

        // Print results
        if(!sbString.equals(scString))
            System.err.println("Reversed string mismatch!"
                + "\n  sb = " + sbString
                + "\n  sc = " + scString);
        System.out.println("String concatenation took " 
            +  scElapsedTime + " nanoseconds");
        System.out.println("StringBuffer         took " 
            +  sbElapsedTime + " nanoseconds");
        System.out.println(
            ((scElapsedTime < sbElapsedTime) 
                 ? "String concatenation is faster by "
                 : "StringBuffer is faster by ")
          + 100 * Math.abs(scElapsedTime - sbElapsedTime)
            / Long.min(scElapsedTime, sbElapsedTime)
          + "%"
        );
    }
}
