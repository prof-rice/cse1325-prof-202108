public abstract class Product {
    public Product(String name, double unitCost) {
        if(unitCost < 0) 
            throw new IllegalArgumentException("Negative unit cost");
        this.name = name;
        this.unitCost = unitCost;
        this.quantity = 0;
    }
    public abstract Product placeOrder(int quantity);
    public abstract double price();    // Cost for quantity of this product
    @Override
    public String toString() {
        String result = name + " (";
        if(quantity > 0) result += quantity + " @ ";
        result += "$" + unitCost + ")";
        return result;
    }
    
    protected String name;
    protected double unitCost;
    protected int quantity;
}
