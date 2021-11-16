package store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Server extends Person {
    public static final String ID = "store.Server";
    public Server(String name, String phone, String ssn) {
        super(name, phone);
        this.ssn = ssn;
    }
    public Server(BufferedReader in) throws IOException {
        super(in);
        this.ssn  = in.readLine();
    }
    @Override
    public void save(BufferedWriter out) throws IOException {
        out.write(ID + '\n');
        super.save(out);
        out.write("" + ssn  + '\n');
    }
    @Override
    public String toString() {
        return "Server " + name + " (" + phone + ", SSN is " + ssn + ")";
    }
    String ssn;
}
