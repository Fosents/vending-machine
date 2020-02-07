package main;

import coins.CoinsData;
import coins.InitCoins;
import menu.MenuMain;
import products.InitProducts;
import products.ProductData;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Start VM Class
 */
public class VendingMachine {

    final String WELCOME = "WELCOME TO MY VM VER. 2.00\n";


    ArrayList<ProductData> products;
    ArrayList<CoinsData> coins;


    public VendingMachine() {
        InitCoins initCoins = new InitCoins();
        InitProducts initProducts = new InitProducts();
        coins = initCoins.preload();
        products = initProducts.preload();
    }

    /**
     * The main menu
     */
    public void initMainMenu() {
        MenuMain menuMain = new MenuMain();
        System.out.println(WELCOME);
        menuMain.init(products, coins);
    }
}