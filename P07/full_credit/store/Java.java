package store;

import java.util.ArrayList;

public class Java extends Product {
    public Java(String name, double price, double cost, Darkness darkness) {
        super(name, price, cost);
        this.darkness = darkness;
        this.shots = new ArrayList<>();
    }
    public void addShot(Shot shot) {
        shots.add(shot);
    }
    @Override
    public String toString() {
        String result = name + " (" + darkness + " with ";
        if (shots.size() == 0) result += "no shots";
        else {
            String separator = "";
            for(Shot s : shots) {
                result += separator + s; 
                separator = ", ";
            }
        }
        result += ") $" + price;
        return result;
    }

    Darkness darkness;
    ArrayList<Shot> shots;
}
