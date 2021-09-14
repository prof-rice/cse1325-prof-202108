public class Test {
    public static void main(String[] args) {
        int results = 0;
        int vector = 1;
        
        // Initialize and retrieve name
        final String name = "Prof Rice";
        Student student = new Student(name);
        if(!student.getName().equals(name)) {
            results |= vector;  // mark failure
            System.err.println("FAIL: Expected name as " + name 
                             + " but was " + student.getName());
        }
        vector <<= 1; // Shift failure bit 1 left
        
        // Empty case - no grades provided, ave == 100
        if(student.average() != 100) {
            results |= vector;  // mark failure
            System.err.println("FAIL: Expected average with no grades as 100" 
                             + " but was " + student.average());
        }
        vector <<= 1; // Shift failure bit 1 left

        // Normal case;
        student.addExam(100);
        student.addExam(80);
        if(student.average() != 90) {
            results |= vector;  // mark failure
            System.err.println("FAIL: Expected 2-grade average as 90" 
                             + " but was " + student.average());
        }
        vector <<= 1; // Shift failure bit 1 left
        
        // Show test results only if any test failed
        if(results != 0) {
            System.err.println("FAIL: Error code is " + results);
            System.exit(results);
        }
    }
}
