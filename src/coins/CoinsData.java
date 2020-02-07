package coins;

/**
 * Class for coins
 */
public class CoinsData {

    private String name;
    private int quantity;
    private double value;

    /**
     * Coins constructor
     * @param name String coin name
     * @param value String value - 0.00
     * @param quantity - int quantity
     */
    public CoinsData(String name, double value, int quantity) {
        this.name = name;
        this.value = value;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getValue() {
        return value;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void decreaseQuantity() {
        this.quantity--;
    }

    public void increaseQuantity(int quantity) {
        this.quantity += quantity;
    }

}
