package states.implement;

import main.VendingMachine;
import menu.MenuPrinter;
import states.IState;
import storage.ProductData;
import utils.MenuHelper;
import utils.UserInput;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MaintenanceState implements IState {

    private static final String SEPARATOR = "----------"; //TODO REFACTOR
    private static final String ENTER_PASSWORD = "PLEASE ENTER PASSWORD";
    private static final String WRONG_PASSWORD = "WRONG PASSWORD";
    private static final String PASSWORD = "proba123";
    private static final String MENU = "[1] Check Coins And Product Quantities\n" +
            "[2] Insert Coins\n" +
            "[3] Insert Product\n" +
            "[0] Return to MAIN MENU";
    private static final String NOT_A_VALID_OPTION = "NOT A VALID OPTION";
    private static final  String INVALID_INPUT = "INVALID INPUT! PLEASE SELECT AGAIN";

    UserInput input;
    VendingMachine vm;
    MenuPrinter printer;

    public MaintenanceState(VendingMachine vm, UserInput input, MenuPrinter printer) {
        this.vm = vm;
        this.input = input;
        this.printer = printer;
    }

    /**
     * The maintenance main menu
     */
    @Override
    public void initMenu() {
        MenuHelper helper = new MenuHelper(vm);
        checkForWarnings();
        while (true) {
            System.out.println(SEPARATOR);
            System.out.println(MENU);
            System.out.println(SEPARATOR);
            System.out.println("PLEASE SELECT AN OPTION [1-3]");
            System.out.println(SEPARATOR);
            int choice = input.getInt();
            if (choice == 0) {
                break;
            } else if (choice > 0 && choice <= 3) {
                switch (choice) {
                    case 1 :
                        System.out.println(helper.checkCoins());
                        System.out.println(SEPARATOR);
                        System.out.println(helper.checkProducts());
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

    //    /**
//     * Password for maintenance
//     */
//    void enterPassword() {
//        while (true) {
//            System.out.println(SEPARATOR);
//            System.out.println(ENTER_PASSWORD);
//            System.out.println(SEPARATOR);
//
//            String password = input.next();
//            if (password.equals(PASSWORD)) {
//                break;
//            } else {
//                System.out.println(SEPARATOR);
//                System.out.println(WRONG_PASSWORD);
//                MenuMain menuMain = new MenuMain(vm); //TODO NOT CORRECT
//                menuMain.initMain();
//            }
//        }
//    }

    /**
     * Check if there is an emergency
     */
    private void checkForWarnings() {
        if (vm.getTenCentsCoinsQuantity() < 20) {
            System.out.println(SEPARATOR);
            System.out.println("ATTENTION! Not enough coins to operate");
            System.out.println("Please INSERT COINS in the machine");
        }
        for (ProductData product : vm.getProducts().getStorage().values()) {
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
        MenuHelper helper = new MenuHelper(vm);
        while (true) {
            System.out.println(helper.checkCoins());
            System.out.println(helper.selectCoinMenu());
            int choice = input.getInt();
            if (choice > 0 && choice <= vm.getCoinsSize()) {
                System.out.println(SEPARATOR);
                System.out.println("ENTER " + vm.getCoins().getItem(choice).getName() + " COINS AMOUNT");
                System.out.println(SEPARATOR);
                int quantityInsert = input.getInt();
                vm.getCoins().getItem(choice).increaseQuantity(quantityInsert);
                System.out.println(SEPARATOR);
                System.out.println(quantityInsert + " " + vm.getCoins().getItem(choice).getName() + " COINS ADDED");
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
        MenuHelper helper = new MenuHelper(vm);
        while (true) {
            System.out.println(helper.checkProducts());
            System.out.println(helper.selectProductMenuMaintenance(vm));
            int choice = input.getInt();
            if (choice == 0) {
                break;
            } else if (choice > 0 && choice <= vm.getProductsSize()) {
                System.out.println(SEPARATOR);
                System.out.println("ENTER " + vm.getProducts().getItem(choice).getName() + " QUANTITY TO INSERT");
                int quantityInsert = input.getInt();
                vm.getProducts().getItem(choice).increaseQuantity(quantityInsert);
                System.out.println(SEPARATOR);
                System.out.println(quantityInsert + " " + vm.getProducts().getItem(choice).getName() + " QUANTITY ADDED");
                System.out.println(SEPARATOR);
            } else if (choice == vm.getProductsSize() + 1) {
                vm.loadProduct("Joker", 5.00, 30);
            } else {
                System.out.println(SEPARATOR);
                System.out.println(NOT_A_VALID_OPTION);
            }
        }
    }

    //TODO REFACTOR
    public void printState(String str) {
        System.out.println(SEPARATOR);
        System.out.println(str);
        System.out.println(SEPARATOR);
    }
}
