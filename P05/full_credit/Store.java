import java.util.ArrayList;
import java.util.Scanner;

class InvalidQuantityException extends Exception {
    InvalidQuantityException(int quantity) {
        super("Invalid quantity: " + quantity);
    }
}    

class InvalidProductException extends Exception {
    InvalidProductException(int product) {
        super("Invalid product number: " + product);
    }
}    

public class Store {
  public static void main(String[] args) {
      Store store = new Store();
      store.cli();
  }
  
  public void cli() {  
    Scanner in = new Scanner(System.in);
    
    Taxed.setSalesTaxRate(0.0825);
    
    ArrayList<Product> products = new ArrayList<>();
    
    // Offer some products
    
    products.add(new Taxfree("Milk", 2.85));
    products.add(new Taxfree("Bread", 1.99));
    products.add(new Taxfree("Cheese", 0.99));
    products.add(new Taxed("Ice Cream", 4.95));
    products.add(new Taxed("Poptarts", 3.49));
    products.add(new Taxed("Oreos", 5.99));
    
    ArrayList<Product> order = new ArrayList<>();
    
    double total = 0;

    while(true) {
        System.out.println("========================");
        System.out.println("  Welcome to the Store  ");
        System.out.println("========================\n");

        for(int i=0; i<products.size(); ++i) {
            System.out.println("" + i + ") " + products.get(i));
        } 
        
        total = 0;
        System.out.println("\nCurrent order:");
        for(var p : order) {
            System.out.println("    " + p);
            total += p.price();
        }
        total = ((double)(int)(total*100))/100;
        System.out.println("\nTotal due today: $" + total + "\n");
        
        int quantity; // The number of items to buy
        int index;    // The item to buy, i.e., products.get(index)

        try {
            System.out.println("\nEnter quantity (0 to complete order) and product #: ");
            quantity = in.nextInt();
            if(quantity == 0) break;
            if(quantity < 0) throw new InvalidQuantityException(quantity);
            
            index = in.nextInt();
            if(index < 0 || index >= products.size())
                throw new InvalidProductException(index);

            order.add(products.get(index).placeOrder(quantity));

        } catch(Exception e) {
            System.err.println("### Error: " + e.getMessage());
        }        
    }
    
    System.out.println("Your total is $" + total + "\nThank you for your order!");
    
  }
}

