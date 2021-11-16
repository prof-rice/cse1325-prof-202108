package store;

import java.util.HashMap;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import store.Store;
import store.Customer;
import store.Server;
import store.Product;

public class Order implements Comparable<Order> {
    public Order(Customer customer, Server server) {
        this.customer = customer;
        this.server = server;
        this.id = nextID++;
        products = new HashMap<>();
    }
    public Order(BufferedReader in) throws IOException {
        products = new HashMap<>();
        id = Integer.parseInt(in.readLine());
        if(id >= nextID) nextID = id+1;
        customer = new Customer(in);
        server = new Server(in);
        
        int size = Integer.parseInt(in.readLine());
        for(int i=0; i<size; ++i) {
            int quantity = Integer.parseInt(in.readLine());
            Product product = Store.readProduct(in);
            products.put(product, quantity);
        }
    }
    public void save(BufferedWriter out) throws IOException {
        out.write("" + id + '\n');
        customer.save(out);
        server.save(out);
        
        out.write("" + products.size() + '\n');
        for(var product : products.keySet()) {
            out.write("" + products.get(product) + "\n");
            product.save(out);
        }
    }

    public int getID() {return id;}
    public void addProduct(int quantity, Product product) {
        if(products.containsKey(product)) quantity += products.get(product);
        products.put(product, quantity);
    }
    public int numberOfProducts() {return products.size();}
   
    @Override
    public String toString() {
        String result = "Order " + id + " for " + customer
            + "\n    Server: " + server + "\n";
        double price = 0.00;
        for(var product : products.keySet()) {
            result += String.format(formatProduct, products.get(product), product);
            price += product.price();
        }
        result += String.format(formatPrice, price);
        return result;
    }
    
    @Override
    public int compareTo(Order order) {  // for sorting
        return Integer.compare(id, order.id);
    }

    private int id;
    private Customer customer;
    private Server server;
    private HashMap<Product, Integer> products;
    
    private static int nextID = 0;
    private static String formatProduct = "%3d  %s\n"; 
    private static String formatPrice = "Total price: $%5.2f\n"; 
}
