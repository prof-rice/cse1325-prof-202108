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
    public double price() {return price;}
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

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null) return false; 
        if(this.getClass() != o.getClass()) return false;
        Product p = (Product) o; // Downcast to a Product
        return name.equals(p.name)
            && (cost == p.cost)
            && (price == p.price);
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31*hash + (name == null ? 0 : name.hashCode());
        hash = 31*hash + (int) (cost * 100.0);
        hash = 31*hash + (int) (price * 100.0);
        return hash;
    }
    
    protected String name;
    protected double cost;
    protected double price;
}
