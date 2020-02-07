package utils;

import coins.CoinsData;
import products.ProductData;

import java.util.ArrayList;

public class MenuHelper {

    final String SEPARATOR = "----------";
    final String CANCEL_OPTION = "[0] Cancel";

    public MenuHelper() {
    }

    /**
     * The default coins menu
     * @param coins instance of coins
     * @return select coin menu
     */
    public String selectCoinMenu(ArrayList<CoinsData> coins) {
        StringBuilder builder = new StringBuilder();
        builder.append(SEPARATOR).append(System.lineSeparator());
        for (int i = 0; i < coins.size(); i++) { builder
                .append("[")
                .append(i + 1)
                .append("] ")
                .append(coins.get(i).getName())
                .append(System.lineSeparator());
        }
        builder.append(CANCEL_OPTION).append(System.lineSeparator())
                .append(SEPARATOR).append(System.lineSeparator())
                .append(selectCoinsRange(coins)).append(System.lineSeparator()).append(SEPARATOR);
        return builder.toString();
    }

    public String remainingAmountToInsert(int selectedProduct, Double insertedAmount, ArrayList<ProductData> products) {
        return SEPARATOR + "\n" + products.get(selectedProduct - 1).getName() +
                " price: " +
                String.format("%.2f", products.get(selectedProduct - 1).getPrice()) +
                " EUR" + System.lineSeparator() +
                "Inserted coins: " +
                String.format("%.2f", insertedAmount) +
                " EUR";
    }

    /**
     * dynamic select coin range menu
     * @param coins instance of coins
     * @return coins menu range to select
     */
    public String selectCoinsRange(ArrayList<CoinsData> coins) {
        return "PLEASE INSERT A COIN [1-" + coins.size() + "]";
    }

    /**
     * The default products menu
     * @param products instance of products
     * @return select products menu
     */
    public String selectProductMenu(ArrayList<ProductData> products) {
        StringBuilder builder = new StringBuilder();
        builder.append(SEPARATOR).append(System.lineSeparator());
        for (int i = 0; i < products.size(); i++) { builder
                .append("[")
                .append(i + 1)
                .append("] ")
                .append(products.get(i).getName())
                .append(" ")
                .append(String.format("%.2f", products.get(i).getPrice()))
                .append(" EUR")
                .append(System.lineSeparator());
        }
        builder.append(SEPARATOR).append(System.lineSeparator())
                .append(selectProductRange(products)).append(System.lineSeparator())
                .append(SEPARATOR);
        return builder.toString();
    }

    /**
     * dynamic select product range menu
     * @param products instance of products
     * @return products menu range to select
     */
    String selectProductRange(ArrayList<ProductData> products) {
        return "PLEASE SELECT A PRODUCT [1-" + products.size() + "]";
    }

    /**
     * show the remaining products in the machine.
     * @param products instance of products
     * @return products quantities
     */
    public String checkProducts(ArrayList<ProductData> products) {
        StringBuilder builder = new StringBuilder();
        products.forEach(product -> builder.append(product.getName()).append(" ").append(product.getQuantity()).append(" "));
        builder.deleteCharAt(builder.toString().length() - 1);
        return builder.toString();
    }

    /**
     * show the remaining coins in the machine.
     * @param coins instance of coins
     * @return coins quantities
     */
    public String checkCoins(ArrayList<CoinsData> coins) {
        StringBuilder builder = new StringBuilder();
        coins.forEach(coin -> builder.append(coin.getName()).append(" ").append(coin.getQuantity()).append(" "));
        builder.deleteCharAt(builder.toString().length() - 1);
        return builder.toString();
    }
}
