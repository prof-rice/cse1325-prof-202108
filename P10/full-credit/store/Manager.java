package store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Manager extends Person {
    public Manager(String name, String phone) {
        super(name, phone);
    }
    public Manager(BufferedReader in) throws IOException {
        super(in);
    }
    public void save(BufferedWriter out) throws IOException {
        super.save(out);
    }
}
