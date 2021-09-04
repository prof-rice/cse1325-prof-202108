// no a here
public class Scope {
    public Scope() {
        a = new int[SIZE]; // a is in class scope, so visible here
        for(int i=0; i<SIZE; ++i) a[i] = (int) (Math.random() * 100);
    }
    public int largest() {
        int r = 0;            // r is local scope to method largest
	    for (int i = 0; i<a.length; ++i) {  // i is in block scope
		    r = Math.max(r, Math.abs(a[i]));
        }
	    // no i here
	    return r;
    }
    // no r here

    public static void main(String[] args) {
        Scope s = new Scope();    // s in local scope to main
        System.out.println(s.largest());
    }
     // All attributes are class scope - visible anywhere in class
    private final static int SIZE = 10; 
    private int[] a; 
}
// no a here

