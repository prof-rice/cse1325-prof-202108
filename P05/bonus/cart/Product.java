package cart;

/**
 * Represents a grocery product for sale.
 *
 * @author             Professor George F. Rice
 * @version            1.0
 * @since              1.0
 * @license.agreement  Gnu General Public License 3.0
 */

public abstract class Product {

    /**
     * Constructs a new product for the shelf or website.
     *
     * @param name      the name of the product
     * @param unitCost  the price of a single unit
     * @since           1.0
     */
    public Product(String name, double unitCost) {
        if(unitCost < 0) 
            throw new IllegalArgumentException("Negative unit cost");
        this.name = name;
        this.unitCost = unitCost;
        this.quantity = 0;
    }

    /**
     * Constructs a product with associated quantity for the shopping cart.
     *
     * @param quantity  the positive number of units to buy
     * @since           1.0
     * @returns         an instance of a subclass of Product with non-zero quantity
     */
    public abstract Product placeOrder(int quantity);

    /**
     * Calculates to total cost for quantity items.
     *
     * @since           1.0
     * @returns         the total price for these products, including any tax
     */
    public abstract double price();    // Cost for quantity of this product

    /**
     * Formats the name and unit cost, and if non-zero the quantity.
     *
     * @since           1.0
     * @returns         a string representation of all non-zero attributes
     */
    @Override
    public String toString() {
        String result = name + " (";
        if(quantity > 0) result += quantity + " @ ";
        result += "$" + unitCost + ")";
        return result;
    }
    
    /**
     * The name of this product
     *
     * @since           1.0
     */
    protected String name;

    /**
     * The cost of one unit of this product
     *
     * @since           1.0
     */
    protected double unitCost;

    /**
     * If zero, indicates this instance is on the store shelf or website
     * If positive, is the number of units in the shopping cart
     *
     * @since           1.0
     */
    protected int quantity;
}
