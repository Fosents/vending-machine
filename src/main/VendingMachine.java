package main;

import storage.*;

/**
 * Start VM Class
 */
public class VendingMachine {

    Storage<ProductData> products;
    Storage<CoinsData> coins;

    public VendingMachine() {
        this.products = new Storage<>();
        //load products for test
        this.products = new InitProducts().preload(products);
        this.coins = new Storage<>();
        //load coins for test
        this.coins = new InitCoins().preload(coins);
    }

    /**
     * @return - ten cents coins quantity
     */
    public int getTenCentsCoinsQuantity() {
        return coins.getStorage().get(1).getQuantity();
    }

    public void loadProduct(String name, double price, int quantity) {
        this.products.loadItem(products.getSize() + 1, new ProductData(name, price, quantity));
    }

    public Storage<ProductData> getProducts() {
        return this.products;
    }

    public int getProductsSize() {
        return this.products.getSize();
    }

    public Storage<CoinsData> getCoins() {
        return this.coins;
    }

    public int getCoinsSize() {
        return this.coins.getSize();
    }

    public void removeProduct(int position) {
        this.products.removeItem(position);
    }

    public void loadCoin(String name, double price, int quantity) {
        this.coins.loadItem(coins.getSize() + 1, new CoinsData(name, price, quantity));
    }

    public void removeCoin(int position) {
        this.coins.removeItem(position);
    }

}