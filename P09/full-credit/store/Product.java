package store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Product {
    public Product(String name, double price, double cost) {
        this.name  = name;
        this.price = price;
        this.cost  = cost;
    }
    public String name() {return name;}
    public Product(BufferedReader in) throws IOException {
        this.name  = in.readLine();
        this.cost  = Double.parseDouble(in.readLine());
        this.price = Double.parseDouble(in.readLine());
    }
    public void save(BufferedWriter out) throws IOException {
        out.write("" + name  + '\n');
        out.write("" + cost  + '\n');
        out.write("" + price + '\n');
    }
    @Override
    public String toString() {
        return name + " ($" + price + ", ";
    }

    protected String name;
    protected double cost;
    protected double price;
}
