package menu;

import coins.CoinsData;
import products.ProductData;
import utils.CoinsCounter;
import utils.MenuHelper;

import java.util.ArrayList;

public class MenuUser extends MenuMain {

    ArrayList<ProductData> products;
    ArrayList<CoinsData> coins;

    MenuUser(ArrayList<ProductData> products, ArrayList<CoinsData> coins) {
        this.products = products;
        this.coins = coins;
    }
    /**
     * Loops through buy product and insert coins until the machine is out of coins
     */
    void init() {
        MenuHelper helper = new MenuHelper();
        System.out.println(SEPARATOR);
        System.out.println(helper.checkProducts(products));
        while (true) {
            int productIndex;
            if (!notEnoughCoins(coins)) {
                productIndex = selectProduct();
            } else {
                break;
            }
            //check input in bounds
            if (productIndex > 0 && productIndex <= products.size()) {
                if (getProductQuantity(productIndex) != 0) {
                    insertCoins(productIndex);
                } else {
                    System.out.println(SEPARATOR);
                    System.out.println(OUT_OF_PRODUCT + getProductName(productIndex).toUpperCase());
                }
            } else if (productIndex == 0) {
                break;
            } else {
                System.out.println(NOT_A_VALID_OPTION);
            }
            System.out.println(SEPARATOR);
            //print products and coins to check vm is working
            System.out.println(helper.checkCoins(coins));
            System.out.println(helper.checkProducts(products));
        }
    }

    /**
     * Method showing the menu and prompt for product select
     * @return - chosen product as int
     */
    private int selectProduct() {
        MenuHelper helper = new MenuHelper();
        System.out.println(helper.selectProductMenu(products));
        return userInput();
    }

    /**
     * Loops through insert coins until coins meet the price or user cancels order
     * @param productIndex - number of the chosen product
     */
    private void insertCoins(int productIndex) {
        MenuHelper helper = new MenuHelper();
        CoinsCounter counter = new CoinsCounter(coins);

        double selectedProductPrice = getProductPrice(productIndex);
        double insertedAmount = 0.00;

        while (selectedProductPrice > insertedAmount) {
            int selectedCoin = selectCoin(productIndex, insertedAmount);
            if (selectedCoin > 0.00 && selectedCoin <= coins.size()) {
                insertedAmount = counter.insert(selectedCoin);
            } else if (selectedCoin == 0) {
                counter.returnInsertedCoins();
                break;
            } else {
                System.out.println(NOT_A_VALID_OPTION);
            }
        }

        if (insertedAmount >= selectedProductPrice) {
            counter.addInsertedCoins(coins);

            products.get(productIndex - 1).decreaseQuantity();

            String change = counter.calculateChange(insertedAmount, selectedProductPrice);
            System.out.println(helper.remainingAmountToInsert(productIndex, insertedAmount, products));
//            System.out.println(getProductName(selectedProduct - 1) + " price: " +
//                    String.format("%.2f", selectedProductPrice) + " EUR | inserted coins: " +
//                    String.format("%.2f", insertedAmount) + " EUR");
            System.out.println(SEPARATOR);
            System.out.println("THANK YOU! PLEASE GET YOUR " + getProductName(productIndex).toUpperCase());
            if (!change.equals("0.0")) {
                System.out.println(SEPARATOR);
                System.out.println("COINS RETURNED AS CHANGE: " + counter.calculateReturningCoins(change, coins));
            }
        }
    }

    /**
     * Method showing the menu and prompt for coin select
     * @return - selected coin as int
     */
    private int selectCoin(int selectedProduct, Double insertedAmount) {
        MenuHelper helper = new MenuHelper();
        System.out.println(helper.remainingAmountToInsert(selectedProduct, insertedAmount, products));
        System.out.println(helper.selectCoinMenu(coins));
        return userInput();
    }

    private String getProductName(int productIndex) {
        return products.get(productIndex - 1).getName();
    }

    /**
     * @return - the chosen product price
     */
    private double getProductPrice(int productIndex) {
        return products.get(productIndex - 1).getPrice();
    }

    /**
     * @return - the chosen product quantity
     */
    private int getProductQuantity(int productIndex) {
        return products.get(productIndex - 1).getQuantity();
    }
}
