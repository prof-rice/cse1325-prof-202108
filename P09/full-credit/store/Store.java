package store;

import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Store {
    public Store(String storeName) {
        this.storeName = storeName;
        this.products = new ArrayList<>();
        this.people = new ArrayList<>();
    }
    public Store(BufferedReader in) throws IOException {
        this(in.readLine());
        
        // Read products
        int size = Integer.parseInt(in.readLine());
        for(int i=0; i<size; ++i) {
            String productType = in.readLine();
            switch(productType) {
                case Java.ID:  products.add(new Java(in));  break;
                case Donut.ID: products.add(new Donut(in)); break;
                default: throw new IOException("Invalid product type: " + productType);
            }
        }
        
        // Read people
        size = Integer.parseInt(in.readLine());
        for(int i=0; i<size; ++i) {
            String personType = in.readLine();
            switch(personType) {
                case Customer.ID:  people.add(new Customer(in));  break;
                default: throw new IOException("Invalid person type: " + personType);
            }
        }
    }
    public void save(BufferedWriter out) throws IOException {
        out.write(storeName + '\n');
        out.write("" + products.size() + '\n');
        for(Product p : products)
            p.save(out);
        out.write("" + people.size() + '\n');
        for(Person p : people)
            p.save(out);
    }

    String name() {return storeName;}

    // Products
    public void addProduct(Product product) {
        products.add(product);
    }
    public int numberOfProducts() {
        return products.size();
    }
    public String toString(int index) {
        return products.get(index).toString();
    }

    // People
    public void addPerson(Person person) {
        people.add(person);
    }
    public int numberOfPeople() {
        return people.size();
    }
    public String personToString(int index) {
        return people.get(index).toString();
    }

    @Override
    public String toString() {
        String result = "Welcome to " + storeName + "\n\nToday's Menu\n\n";
        for(int i=0; i<products.size(); ++i) {
            result += i + ") " + products.get(i) + "\n";
        }
        return result;
    }
    public String peopleToString() {
        String result = "Welcome to " + storeName + "\n\nOur Beloved Customers\n\n";
        for(int i=0; i<people.size(); ++i) {
            result += i + ") " + people.get(i) + "\n";
        }
        return result;
    }
    
    private String storeName;
    private ArrayList<Product> products;
    private ArrayList<Person> people;
}
