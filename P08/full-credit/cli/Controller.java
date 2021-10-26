package cli;

import store.Store;
import store.Product;
import store.Donut;
import store.Frosting;
import store.Filling;
import store.Java;
import store.Darkness;
import store.Shot;

import java.util.Scanner;
import java.util.ArrayList;

public class Controller {
    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.cli();
    }
    public void cli() {
        System.out.print("What is the name of the new store? ");
        String name = in.nextLine();  // Object names
        String ignore = "";           // Discarded inputs
        store = new Store(name);
        while(true) {
            System.out.println(store);
            System.out.print("Options:\n  0) Exit\n  1) Add java\n  2) Add donut\n\nChoice? ");
            int choice = in.nextInt(); ignore = in.nextLine();
            if(choice == 0) break;
            if(choice <0 || choice > 2) {
                System.err.println("\n\n### Error: Invalid option: " + choice + "\n\n");
                continue;
            }
            System.out.print("Name:  ");
            name = in.nextLine();
            System.out.print("Cost:  ");
            double cost  = in.nextDouble();
            System.out.print("Price: ");
            double price = in.nextDouble();

            if(choice == 1) { // name price cost darkness
                System.out.println("\nBrew Options\n============");
                for(int i=0; i<Darkness.values().length; ++i) {
                    System.out.println(i + ") " + Darkness.values()[i]);
                }
                System.out.print("\nWhich darkness? ");
                int darkness = in.nextInt(); ignore = in.nextLine();

                Java java = new Java(name, price, cost, Darkness.values()[darkness]);

                while(true) {
                    System.out.println("\nShot Options\n============");
                    for(int i=0; i<Shot.values().length; ++i) {
                        System.out.println(i + ") " + Shot.values()[i]);
                    }
                    System.out.print("\nAdd shot (-1 when done)? ");
                    int shot = in.nextInt(); ignore = in.nextLine();
                    if(shot < 0 || shot >= Shot.values().length) break;
                     java.addShot(Shot.values()[shot]);
                }

                store.addProduct(java);

                continue;
            }
            if(choice == 2) { // name price cost frosting filling sprinkles
                System.out.println("\nFrosting Options\n================");
                for(int i=0; i<Frosting.values().length; ++i) {
                    System.out.println(i + ") " + Frosting.values()[i]);
                }
                System.out.print("\nWhich frosting? ");
                int frosting = in.nextInt(); ignore = in.nextLine();

                System.out.println("\nFilling Options\n================");
                for(int i=0; i<Filling.values().length; ++i) {
                    System.out.println(i + ") " + Filling.values()[i]);
                }
                System.out.print("\nWhich filling? ");
                int filling = in.nextInt(); ignore = in.nextLine();

                System.out.print("\nSprinkles? ");
                String sprinkles = in.nextLine();

                store.addProduct(new Donut(name, price, cost, 
                                       Frosting.values()[frosting],
                                       Filling.values()[filling],
                                       sprinkles.toUpperCase().charAt(0) == 'Y'));

                continue;
            }
        }
    }

    Store store;
    private static Scanner in = new Scanner(System.in);;
}
