/**
 * Class for coins
 */
class CoinsData {
    private String name;
    private int quantity;
    private String value;

    /**
     * Coins constructor
     * @param name String coin name
     * @param value String value - 0.00
     * @param quantity - int quantity
     */
    CoinsData(String name, String value, int quantity) {
        this.name = name;
        this.value = value;
        this.quantity = quantity;
    }

    String getName() {
        return name;
    }

    int getQuantity() {
        return quantity;
    }

    String getValue() {
        return value;
    }

    void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    void decreaseQuantity() {
        this.quantity--;
    }

    void increaseQuantity(int quantity) {
        this.quantity += quantity;
    }

}
