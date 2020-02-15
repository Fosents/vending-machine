package states.implement;

import main.VendingMachine;
import menu.MenuPrinter;
import org.omg.CORBA.PRIVATE_MEMBER;
import states.IState;
import storage.ProductData;
import utils.UserInput;

public class MaintenanceState implements IState {

    private static final String ENTER_PASSWORD = "PLEASE ENTER PASSWORD OR TYPE CANCEL";
    private static final String WRONG_PASSWORD = "WRONG PASSWORD";
    private static final String PASSWORD = "parola";
    private static final String CANCEL = "cancel";
    private static final String ENTER_NAME = "PLEASE ENTER NAME";
    private static final String ENTER_PRICE = "PLEASE ENTER PRICE";
    private static final String ENTER_QUANTITY = "PLEASE ENTER QUANTITY";
    private static final String REMOVE_PRODUCT = "PLEASE SELECT A PRODUCT TO REMOVE";
    private static final String REMOVE_PRODUCT_CONFIRMATION = "ARE YOU SURE? ALL QUANTITY WILL BE REMOVED\nTYPE YES OR ANYTHING ELSE TO CANCEL";


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
        checkForWarnings();
        boolean isPassEntered = enterPasswordState(); //TODO not best option
        while (true) {
            if (!isPassEntered) {
                break;
            }
            printer.printMaintenanceMenu();
            int choice = input.getInt();
            if (choice == 0) {
                break;
            } else if (choice > 0 && choice <= 3) {
                switch (choice) {
                    case 1 :
                        printer.printProducts(vm.getProductsStorage());
                        printer.printCoins(vm.getCoinsStorage());
                        break;
                    case 2 :
                        maintenanceInsertCoinsState();
                        break;
                    case 3 :
                        maintenanceInsertProductState();
                        break;
                }
            } else {
                printer.printNotValidOption();
            }
        }
    }

    /**
     * Password for maintenance
     */
    boolean enterPasswordState() {
        while (true) {
            printer.printWithSeparator(ENTER_PASSWORD);
            String password = input.getString();
            if (password.equals(PASSWORD)) {
                return true;
            } else if (password.equalsIgnoreCase(CANCEL)) {
                return false;
            } else {
                printer.printWithSeparator(WRONG_PASSWORD);
            }
        }
    }

    /**
     * Check if there is an emergency
     */
    private void checkForWarnings() {
        if (vm.getTenCentsCoinsQuantity() < 20) {
            printer.printWithSeparator("ATTENTION! Not enough coins to operate\n" +
                    "Please INSERT COINS in the machine");
        }
        for (ProductData product : vm.getProducts().getStorage().values()) {
            if (product.getQuantity() == 0) {
                printer.printWithSeparator("ATTENTION! " + product.getName() + " is out of quantity\n" +
                        "Please INSERT " + product.getName() + " in the machine");
            }
        }
    }

    /**
     * The maintenance insert coins quantity menu
     */
    void maintenanceInsertCoinsState() {
        while (true) {
            printer.printCoins(vm.getCoinsStorage());
            printer.printCoinsMenuMaintenance(vm);
            int choice = input.getInt();
            if (choice > 0 && choice <= vm.getCoinsSize()) {
                printer.printWithSeparator("ENTER " + vm.getCoins().getItem(choice).getName() + " COINS AMOUNT");
                int quantityInsert = input.getInt();
                vm.getCoins().getItem(choice).increaseQuantity(quantityInsert);
                printer.printWithSeparator(quantityInsert + " " + vm.getCoins().getItem(choice).getName() + " COINS ADDED");
            } else if (choice == 0) {
                break;
            } else {
                printer.printNotValidOption();
            }
        }
    }

    /**
     * The maintenance insert products quantity menu
     */
    void maintenanceInsertProductState() {
        while (true) {
            printer.printProductMenuMaintenance(vm);
            int choice = input.getInt();
            if (choice == 0) {
                break;
            } else if (choice > 0 && choice <= vm.getProductsSize()) {
                printer.printWithSeparator("ENTER " + vm.getProducts().getItem(choice).getName() + " QUANTITY TO INSERT");
                int quantityInsert = input.getInt();
                vm.getProducts().getItem(choice).increaseQuantity(quantityInsert);
                printer.printWithSeparator(quantityInsert + " " + vm.getProducts().getItem(choice).getName() + " QUANTITY ADDED");
            } else if (choice == vm.getProductsSize() + 1) {
                insertNewProductState();
            } else if (choice == vm.getProductsSize() + 2) {
                removeProductState();
            } else {
                printer.printNotValidOption();
            }
        }
    }

    void insertNewProductState() {
        printer.printWithSeparator(ENTER_NAME);
        String name = input.getString();
        printer.printWithSeparator(ENTER_PRICE);
        double price = input.getDouble();
        printer.printWithSeparator(ENTER_QUANTITY);
        int quantity = input.getInt();
        vm.loadProduct(name, price, quantity);
    }

    void removeProductState() {
        printer.printWithSeparator(REMOVE_PRODUCT);
        int select = input.getInt();
        if (select > 0 && select <= vm.getProductsSize()) {
            printer.printWithSeparator(REMOVE_PRODUCT_CONFIRMATION);
            String name = input.getString();
            if (name.equalsIgnoreCase("yes") && select < vm.getProductsSize()) {
                vm.removeProduct(select);
            }
        } else {
            printer.printNotValidOption();
        }
    }
}
