public class TestFuse {
    public static void main(String[] args) {
        Fuse fuse = new Fuse(2);
        String expected = "   __*\n  /\n,+,\n| |\n|_|\n";

        if(!expected.equals(fuse.toString())) 
            System.out.println("FAIL: INIT expected \n" + expected + "\nbut saw \n" + fuse + "\n\n");

        fuse.burn();
        expected = "   _*\n  /\n,+,\n| |\n|_|\n";
        if(!expected.equals(fuse.toString())) 
            System.out.println("FAIL: +1 expected \n" + expected + "\nbut saw \n" + fuse + "\n\n");

        fuse.burn();
        expected = "   *\n  /\n,+,\n| |\n|_|\n";
        if(!expected.equals(fuse.toString())) 
            System.out.println("FAIL: +2 expected \n" + expected + "\nbut saw \n" + fuse + "\n\n");

        fuse.burn();
        expected = "   *\n  /\n,+,\n| |\n|_|\n";
        if(!expected.equals(fuse.toString())) 
            System.out.println("FAIL: +3 expected \n" + expected + "\nbut saw \n" + fuse + "\n\n");

    }
}
