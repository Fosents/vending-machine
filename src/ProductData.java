/**
 * Class for products
 */
class ProductData {
    private String name;
    private int quantity;
    private double price;

    /**
     * Products constructor
     * @param name - String name
     * @param quantity - int quantity
     * @param price - double price - 0.00
     */
    ProductData(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    String getName() {
        return this.name;
    }

    int getQuantity() {
        return quantity;
    }

    double getPrice() {
        return price;
    }
    void decreaseQuantity() {
        this.quantity--;
    }

    void increaseQuantity(int quantity) {
        this.quantity += quantity;
    }
}
