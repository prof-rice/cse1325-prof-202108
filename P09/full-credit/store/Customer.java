package store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Customer extends Person {
    public static final String ID = "store.Customer";
    public Customer(String name, String phone) {
        super(name, phone);
    }
    public Customer(BufferedReader in) throws IOException {
        super(in);
    }
    @Override
    public void save(BufferedWriter out) throws IOException {
        out.write(ID + '\n');
        super.save(out);
    }
}
