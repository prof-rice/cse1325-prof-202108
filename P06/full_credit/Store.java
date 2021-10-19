import java.util.ArrayList;

public class Store {
    public Store(String storeName) {
        this.storeName = storeName;
        this.products = new ArrayList<>();
    }
    String name() {return storeName;}
    public void addProduct(Product product) {
        products.add(product);
    }
    public int numberOfProducts() {
        return products.size();
    }
    public String toString(int index) {
        return products.get(index).toString();
    }
    @Override
    public String toString() {
        String result = "\nWelcome to " + storeName + "\n\n";
        for(int i=0; i<products.size(); ++i) {
            result += i + ") " + products.get(i) + "\n";
        }
        return result;
    }
    private String storeName;
    private ArrayList<Product> products;
}
