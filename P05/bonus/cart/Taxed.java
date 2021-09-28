package cart;

public class Taxed extends Product {
    public Taxed(String name, double unitCost) {
        super(name, unitCost);
    }
    public static void setSalesTaxRate(double salesTaxRate) {
        Taxed.salesTaxRate = salesTaxRate;
    }
    @Override
    public Product placeOrder(int quantity) {
        Taxed t = new Taxed(name, unitCost);
        t.quantity = quantity;
        return t;
    }
    @Override
    public double price() {
        return quantity * unitCost * (1 + salesTaxRate);
    }
    private static double salesTaxRate = 0.08;
}
