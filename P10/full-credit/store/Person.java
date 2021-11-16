package store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Person {
    public Person(String name, String phone) {
        this.name  = name;
        this.phone = phone;
    }
    public String name() {return name;}
    public Person(BufferedReader in) throws IOException {
        this.name  = in.readLine();
        this.phone  = in.readLine();
    }
    public void save(BufferedWriter out) throws IOException {
        out.write("" + name  + '\n');
        out.write("" + phone  + '\n');
    }
    @Override
    public String toString() {
        return "Person " + name + " (" + phone + ")";
    }

    protected String name;
    protected String phone;
}
