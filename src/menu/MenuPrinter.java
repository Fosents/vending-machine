package menu;

import main.VendingMachine;
import storage.CoinsData;
import storage.ProductData;

import java.util.Map;

public class MenuPrinter {

    private static final String SEPARATOR = "----------";
    private static final String MAIN_MENU = "MAIN MENU";
    private static final String MAIN_MENU_OPTIONS = "[1] User\n[2] Maintenance\n[0] Shutdown";
    //TODO options map
    private static final String MAINTENANCE_MENU_OPTIONS = "[1] Check Coins And Product Quantities\n[2] Insert Coins\n[3] Insert Product\n[0] Return to MAIN MENU";
    private static final String MAIN_MENU_SELECT = "PLEASE SELECT AN OPTION [1-2]";
    private static final String MAINTENANCE_MENU_SELECT = "PLEASE SELECT AN OPTION [1-3]";
    private static final String NOT_A_VALID_OPTION = "NOT A VALID OPTION";
    private static final String SELECT_OPTION = "Select an option";
    private static final String NO_PRODUCTS = "NO PRODUCTS INSERTED IN THE MACHINE";
    private static final String NO_COINS = "NO COINS INSERTED IN MACHINE";
    private static final String ENTER_MAINTENANCE = "\nPLEASE ENTER MAINTENANCE";
    private static final String OUT_OF_PRODUCT = "SORRY! MACHINE OUT OF ";
    private static final String OUT_OF_ORDER = "SORRY! MACHINE OUT OF ORDER\nENTER MAINTENANCE";
    private static final String PRODUCTS_TITLE = "PRODUCTS";
    private static final String COINS_TITLE = "COINS";
    private static final String CANCEL_OPTION = "[0] Cancel";
    private static final String INSERT_NEW = "Insert new";
    private static final String REMOVE = "Remove";

    public void printMainMenu() {
        printWithSeparator(MAIN_MENU);
        printWithSeparator(MAIN_MENU_OPTIONS);
        printWithSeparator(MAIN_MENU_SELECT);
    }

    public void printMaintenanceMenu() {
        printWithSeparator(MAINTENANCE_MENU_OPTIONS);
        printWithSeparator(MAINTENANCE_MENU_SELECT);
    }



    /**
     * Print the remaining products in the machine.
     */
    public void printProducts(Map<Integer, ProductData> storage) {
        StringBuilder builder = new StringBuilder();
        if (storage.size() > 0) {
            storage.forEach((key, value) -> builder.append(value.getName()).append(" ").append(value.getQuantity()).append(" "));
            builder.deleteCharAt(builder.toString().length() - 1);
        } else {
            builder.append(NO_PRODUCTS);
        }
        printWithSeparator(builder.toString());
    }

    /**
     * Print products default
     * @param vm instance of VendingMachine
     * @param isMaintenance if maintenance options
     * @return menu
     */
    String defProductsMenu(VendingMachine vm, boolean isMaintenance) {
        StringBuilder builder = new StringBuilder();
        printWithSeparator(PRODUCTS_TITLE);
        vm.getProductsStorage().forEach((key, value) -> builder
                .append(sameLength("[" + key + "] " + value.getName(),18))
                .append("| ")
                .append(String.format("%.2f", (value.getPrice())))
                .append(" EUR")
                .append(isMaintenance ? " | QUANTITY: " + value.getQuantity() : "")
                .append(System.lineSeparator()));
        if (!isMaintenance) {
            builder.delete(builder.length() - 2, builder.length());
        }
                builder.append(isMaintenance ? maintenanceAddOptions(vm) : "");

        return builder.toString();
    }

    /**
     * The default coins menu
     * @return select coin menu
     */
    String defCoinMenu(VendingMachine vm, boolean isMaintenance) {
        StringBuilder builder = new StringBuilder();
        vm.getCoinsStorage().forEach((key, value) -> builder
                .append("[")
                .append(key)
                .append("] ")
                .append(sameLength(value.getName(),8))
                .append(isMaintenance ? " | QUANTITY: " + value.getQuantity() : "")
                .append(System.lineSeparator()));
        if (!isMaintenance) {
            builder.delete(builder.length() - 2, builder.length());
        }
        builder.append(isMaintenance ? maintenanceAddOptions(vm) : "");

        return builder.toString();
    }

    /**
     * Print user products menu
     * @param vm instance of VendingMachine
     */
    public void printProductMenuUser(VendingMachine vm) {
        printWithSeparator(defProductsMenu(vm, false));
        printWithSeparator(selectProductRange(vm));
    }

    /**
     * Print maintenance products menu
     */
    public void printProductMenuMaintenance(VendingMachine vm) {
        printWithSeparator(defProductsMenu(vm, true));
        printWithSeparator(SELECT_OPTION);
    }

    /**
     * Print user products menu
     * @param vm instance of VendingMachine
     */
    public void printCoinsMenuUser(VendingMachine vm) {
        printWithSeparator(defCoinMenu(vm, false));
        printWithSeparator(selectCoinsRange(vm));
    }

    /**
     * Print maintenance products menu
     */
    public void printCoinsMenuMaintenance(VendingMachine vm) {
        printWithSeparator(defCoinMenu(vm, true));
        printWithSeparator(SELECT_OPTION);
    }

    /**
     * dynamic select product range menu
     * @return products menu range to select
     */
    String selectProductRange(VendingMachine vm) {
        return "PLEASE SELECT A PRODUCT [1-" + vm.getProductsSize() + "]";
    }

    /**
     * dynamic select coin range menu
     * @return coins menu range to select
     */
    public String selectCoinsRange(VendingMachine vm) {
        return "PLEASE INSERT A COIN [1-" + vm.getCoinsSize() + "]";
    }



    StringBuilder maintenanceAddOptions(VendingMachine vm) {
        return new StringBuilder("[").append(vm.getProductsSize() + 1).append("] ")
                .append(INSERT_NEW).append(System.lineSeparator())
                .append("[").append(vm.getProductsSize() + 2).append("] ")
                .append(REMOVE).append(System.lineSeparator())
                .append(CANCEL_OPTION);
    }

    /**
     * show the remaining coins in the machine.
     */
    public void printCoins(Map<Integer, CoinsData> storage) {
        StringBuilder builder = new StringBuilder();
        if (storage.size() > 0) {
            storage.forEach((key, value) -> builder.append(value.getName()).append(" ").append(value.getQuantity()).append(" "));
            builder.deleteCharAt(builder.toString().length() - 1);
        } else {
            builder.append(NO_COINS);
        }
        printWithSeparator(builder.toString());
    }

    public void printRemainingAmountToInsert(VendingMachine vm, int selectedProduct, Double insertedAmount) {
        printWithSeparator(SEPARATOR + "\n" + sameLength(vm.getProducts().getStorage().get(selectedProduct).getName() +
                " price: ", 18) + "| " +
                String.format("%.2f", vm.getProducts().getStorage().get(selectedProduct).getPrice()) +
                " EUR" + System.lineSeparator() +
                sameLength("Inserted coins: ", 18) + "| " +
                String.format("%.2f", insertedAmount) +
                " EUR");
    }

    public void printOutOfProducts() {
        printWithSeparator(NO_PRODUCTS + ENTER_MAINTENANCE);
    }

    public void printOutOfProduct(ProductData product) {
        printWithSeparator(OUT_OF_PRODUCT + product.getName().toUpperCase());
    }

    public void printOutOfCoins() {
        printWithSeparator(NO_COINS + ENTER_MAINTENANCE);
    }

    public void printNotValidOption() {
        printWithSeparator(NOT_A_VALID_OPTION);
    }

    public void printOutOfOrder() {
        printWithSeparator(OUT_OF_ORDER);
    }

    public void printWithSeparator(String str) {
        System.out.println(str);
        System.out.println(SEPARATOR);
    }

    public static String sameLength(String value, int length) {
        int difference = length - value.length();
        StringBuilder sb = new StringBuilder(value);
        for (int i = 0; i < difference; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }
}
