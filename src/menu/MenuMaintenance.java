package menu;

import coins.CoinsData;
import products.ProductData;
import utils.MenuHelper;

import java.util.ArrayList;

public class MenuMaintenance extends MenuMain {

    private static final String ENTER_PASSWORD = "PLEASE ENTER PASSWORD";
    private static final String WRONG_PASSWORD = "WRONG PASSWORD";
    private static final String PASSWORD = "proba123";
    private static final String MENU = "[1] Check Coins And Product Quantities\n" +
            "[2] Insert Coins\n" +
            "[3] Insert Product\n" +
            "[0] Return to MAIN MENU";


    ArrayList<ProductData> products;
    ArrayList<CoinsData> coins;

    MenuMaintenance(ArrayList<ProductData> products, ArrayList<CoinsData> coins) {
        this.products = products;
        this.coins = coins;
    }

    /**
     * Password for maintenance
     */
    void enterPassword() {
        while (true) {
            System.out.println(SEPARATOR);
            System.out.println(ENTER_PASSWORD);
            System.out.println(SEPARATOR);

            String password = input.next();
            if (password.equals(PASSWORD)) {
                break;
            } else {
                System.out.println(SEPARATOR);
                System.out.println(WRONG_PASSWORD);
                MenuMain menuMain = new MenuMain();
                menuMain.init(products, coins);
            }
        }
    }

    /**
     * The maintenance main menu
     */
    void init() {
        MenuHelper helper = new MenuHelper();
        checkForWarnings();
        while (true) {
            System.out.println(SEPARATOR);
            System.out.println(MENU);
            System.out.println(SEPARATOR);
            System.out.println("PLEASE SELECT AN OPTION [1-3]");
            System.out.println(SEPARATOR);
            int choice = userInput();
            if (choice == 0) {
                break;
            } else if (choice > 0 && choice <= 3) {
                switch (choice) {
                    case 1 :
                        System.out.println(helper.checkCoins(coins));
                        System.out.println(SEPARATOR);
                        System.out.println(helper.checkProducts(products));
                        break;
                    case 2 :
                        maintenanceInsertCoinsMenu();
                        break;
                    case 3 :
                        maintenanceInsertProductMenu();
                        break;
                }
            } else {
                System.out.println(SEPARATOR);
                System.out.println(NOT_A_VALID_OPTION);
            }
        }
    }

    /**
     * Check if there is an emergency
     */
    private void checkForWarnings() {
        if (getTenCentsCoinsQuantity(coins) < 20) {
            System.out.println(SEPARATOR);
            System.out.println("ATTENTION! Not enough coins to operate");
            System.out.println("Please INSERT COINS in the machine");
        }
        for (ProductData product : products) {
            if (product.getQuantity() == 0) {
                System.out.println("ATTENTION! " + product.getName() + " is out of quantity");
                System.out.println("Please INSERT " + product.getName() + " in the machine");
            }
        }
    }

    /**
     * The maintenance insert coins quantity menu
     */
    void maintenanceInsertCoinsMenu() {
        MenuHelper helper = new MenuHelper();
        while (true) {
            System.out.println(helper.checkCoins(coins));
            System.out.println(helper.selectCoinMenu(coins));
            int choice = userInput();
            if (choice > 0 && choice <= coins.size()) {
                System.out.println(SEPARATOR);
                System.out.println("ENTER " + coins.get(choice - 1).getName() + " COINS AMOUNT");
                System.out.println(SEPARATOR);
                int quantityInsert = userInput();
                coins.get(choice - 1).increaseQuantity(quantityInsert);
                System.out.println(SEPARATOR);
                System.out.println(quantityInsert + " " + coins.get(choice - 1).getName() + " COINS ADDED");
                System.out.println(SEPARATOR);
            } else if (choice == 0) {
                break;
            } else {
                System.out.println(SEPARATOR);
                System.out.println(NOT_A_VALID_OPTION);
            }
        }
    }

    /**
     * The maintenance insert products quantity menu
     */
    void maintenanceInsertProductMenu() {
        MenuHelper helper = new MenuHelper();
        while (true) {
            System.out.println(helper.checkProducts(products));
            System.out.println(helper.selectProductMenu(products));
            int choice = userInput();
            if (choice == 0) {
                break;
            } else if (choice > 0 && choice <= products.size()) {
                System.out.println(SEPARATOR);
                System.out.println("ENTER " + products.get(choice - 1).getName() + " QUANTITY TO INSERT");
                int quantityInsert = userInput();
                products.get(choice - 1).increaseQuantity(quantityInsert);
                System.out.println(SEPARATOR);
                System.out.println(quantityInsert + " " + products.get(choice - 1).getName() +" QUANTITY ADDED");
                System.out.println(SEPARATOR);
            } else {
                System.out.println(SEPARATOR);
                System.out.println(NOT_A_VALID_OPTION);
            }
        }
    }
}
