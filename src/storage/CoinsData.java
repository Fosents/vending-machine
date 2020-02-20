package storage;

/**
 * Class for coins data
 */
public class CoinsData {

    private String name;
    private double value;
    private int quantity;

    /**
     * Init coin
     * @param name coin name
     * @param value coin value
     */
    public CoinsData(String name, double value, int quantity) {
        this.name = name;
        this.value = value;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    public int getQuantity() {
        return quantity;
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
