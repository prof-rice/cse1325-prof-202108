
// int x = 0;	// Global variable – NOT supported by Java!

public class ScopeNested {
    public static void f() {
        int x;          // block scope (Note – now there are two x’s - main and here)
        x = 7;          // local x, not main's x
        System.out.println("f's x = " + x);
        {
            int x = 42;  // another local block scope with  x
                        // (Now there are three x’s)
            System.out.println("Before increment, nested x = " + x);
            // Unlike C/C++, we don't have access to any of the other x's
            ++x;        // increment the local x in this scope
            System.out.println("After increment, nested x = " + x);
        }
        System.out.println("f's x is now = " + x);
    }
    public static void main() {
        int x = 0;  // in main's block scope
        System.out.println("main's x = " + x);
        f();
        System.out.println("main's x is now = " + x);
    }
}
