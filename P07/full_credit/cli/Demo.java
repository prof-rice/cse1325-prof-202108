package cli;

import store.Store;
import store.Product;
import store.Donut;
import store.Frosting;
import store.Filling;
import store.Java;
import store.Darkness;
import store.Shot;

public class Demo {
    public static void main(String[] args) {
        Store store = new Store("Demo JADE");

        store.addProduct(new Donut("Plain Donut", 1.25, .5, Frosting.Unfrosted, Filling.Unfilled, false));
        store.addProduct(new Donut("Glazed Donut", 1.5, .75, Frosting.Glazed, Filling.Unfilled, false));
        store.addProduct(new Donut("Long John", 1.75, 1.25, Frosting.Chocolate, Filling.Bavarian, false));
        store.addProduct(new Donut("Creme Filled Donut", 1.75, 1, Frosting.Unfrosted, Filling.Creme, true));
        store.addProduct(new Java("Black Coffee", 2.25, .25, Darkness.Light));

        Java regular = new Java("Coffee with Sugar and Creamer", 2.25, .35, Darkness.Medium);
        regular.addShot(Shot.Creamer);
        regular.addShot(Shot.Sugar);
        store.addProduct(regular);

        Java latte = new Java("Mocha Latte", 4.25, .65, Darkness.Dark);
        latte.addShot(Shot.Chocolate);
        latte.addShot(Shot.Milk);
        store.addProduct(latte);

        for(int i=0; i<store.numberOfProducts(); ++i) 
            System.out.println(store.toString(i));
    }
}
