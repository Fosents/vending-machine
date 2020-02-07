package products;

/**
 * Class for products
 */
public class ProductData {
    private String name;
    private int quantity;
    private double price;

    /**
     * Products constructor
     * @param name - String name
     * @param quantity - int quantity
     * @param price - double price - 0.00
     */
    public ProductData(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
    public void decreaseQuantity() {
        this.quantity--;
    }

    public void increaseQuantity(int quantity) {
        this.quantity += quantity;
    }
}
