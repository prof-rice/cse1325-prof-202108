package cart;

public class Taxfree extends Product {
    public Taxfree(String name, double unitCost) {
        super(name, unitCost);
    }
    @Override
    public Product placeOrder(int quantity) {
        Taxfree t = new Taxfree(name, unitCost);
        t.quantity = quantity;
        return t;
    }
    @Override
    public double price() {
        return quantity * unitCost;
    }
}
