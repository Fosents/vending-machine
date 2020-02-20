package main;

import storage.*;
import utils.InitData;
import utils.StateSaver;

import java.util.Map;

/**
 * Start VM Class
 */
public class VendingMachine {

    Storage<ProductData> products;
    Storage<CoinsData> coins;

    public VendingMachine() {
        this.products = new Storage<>();
        //load products for test
        this.products = new InitData().products();
        this.coins = new Storage<>();
        //load coins for test
        this.coins = new InitData().coins();
    }

    public void loadProduct(String name, double price, int quantity) {
        this.products.loadItem(products.getSize() + 1, new ProductData(name, price, quantity));
        new StateSaver().updateProducts(this.products);
    }

    public void loadCoin(String name, double price, int quantity) {
        this.coins.loadItem(coins.getSize() + 1, new CoinsData(name, price, quantity));
        new StateSaver().updateCoins(this.coins);
    }

    public void removeProduct(int position) {
        this.products.removeItem(position);
        new StateSaver().updateProducts(products);
    }

    public void removeCoin(int position) {
        this.coins.removeItem(position);
        new StateSaver().updateCoins(this.coins);
    }

    public void increaseProductQuantity(int productIndex, int quantity) {
        products.getItem(productIndex).increaseQuantity(quantity);
        new StateSaver().updateProducts(this.products);
    }

    public void increaseCoinQuantity(int coinIndex, int quantity) {
        coins.getItem(coinIndex).increaseQuantity(quantity);
        new StateSaver().updateCoins(this.coins);
    }

    public void decreaseProductQuantity(int productIndex) {
        products.getItem(productIndex).decreaseQuantity();
        new StateSaver().updateProducts(this.products);
    }

    public Storage<ProductData> getProducts() {
        return this.products;
    }

    public Map<Integer, ProductData> getProductsStorage() {
        return products.getStorage();
    }

    public int getProductsSize() {
        return this.products.getSize();
    }

    public Storage<CoinsData> getCoins() {
        return this.coins;
    }

    public Map<Integer, CoinsData> getCoinsStorage() {
        return coins.getStorage();
    }

    public int getCoinsSize() {
        return this.coins.getSize();
    }

    /**
     * @return - ten cents coins quantity
     */
    public int getTenCentsCoinsQuantity() {
        return coins.getStorage().get(1).getQuantity();
    }

}