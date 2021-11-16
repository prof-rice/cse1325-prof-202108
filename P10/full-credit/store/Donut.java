package store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Donut extends Product {
    public static final String ID = "store.Donut";
    public Donut(String name, double price, double cost, Frosting frosting, Filling filling, boolean sprinkles) {
        super(name, price, cost);
        this.frosting = frosting;
        this.filling = filling;
        this.sprinkles = sprinkles;
    }

    public Donut(BufferedReader in) throws IOException {
        super(in);
        this.frosting  = Frosting.valueOf(in.readLine());
        this.filling   = Filling.valueOf(in.readLine());
        this.sprinkles = Boolean.parseBoolean(in.readLine());
    }
    @Override
    public void save(BufferedWriter out) throws IOException {
        out.write(ID + '\n');
        super.save(out);
        out.write("" + frosting  + '\n');
        out.write("" + filling  + '\n');
        out.write("" + sprinkles + '\n');
    }

    @Override
    public String toString() {
         return name + " (" 
              + ((frosting != Frosting.Unfrosted) ? "frosted with " : "") + frosting
              + ((filling != Filling.Unfilled) ? " filled with " : " ") + filling
              + ((sprinkles) ? " add sprinkles" : "")
              + ") $" + price;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null) return false; 
        if(this.getClass() != o.getClass()) return false;
        Donut p = (Donut) o;
        return super.equals((Product) p)
            && (frosting == p.frosting)
            && (filling == p.filling)
            && (sprinkles == p.sprinkles);
    }
    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 31*hash + (frosting == null ? 0 : frosting.hashCode());
        hash = 31*hash + (filling  == null ? 0 : filling.hashCode());
        hash = 31*hash + (sprinkles ? 7177 : 7717); // or Boolean.hashCode(sprinkles)
        return hash;
    }

    Frosting frosting;
    Filling filling;
    boolean sprinkles;
}
