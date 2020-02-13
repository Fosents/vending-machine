package states.implement;

import main.VendingMachine;
import menu.MenuPrinter;
import states.IState;
import utils.CoinsCounter;
import utils.MenuHelper;
import utils.UserInput;

public class UserState implements IState {

    final int MIN_TEN_CENTS_TO_OPERATE = 20;
    final String SEPARATOR = "----------";
    final String OUT_OF_PRODUCT = "SORRY! MACHINE OUT OF ";
    final String OUT_OF_ORDER = "SORRY! MACHINE OUT OF ORDER\nENTER MAINTENANCE";

    UserInput input;
    VendingMachine vm;
    MenuPrinter printer;

    public UserState(VendingMachine vm, UserInput input, MenuPrinter printer) {
        this.vm = vm;
        this.input = input;
        this.printer = printer;
    }

    /**
     * Loops through buy product and insert coins until the machine is out of coins
     */
    @Override
    public void initMenu() {
        MenuHelper helper = new MenuHelper(vm);
        System.out.println(SEPARATOR);
//        print products and coins to check vm is working
        System.out.println(helper.checkProducts());
        while (true) {
            int productIndex;
            if (!notEnoughCoins()) {
                productIndex = selectProduct();
            } else {
                break;
            }
            //check input in bounds
            if (productIndex > 0 && productIndex <= vm.getProductsSize()) {
                if (getProductQuantity(productIndex) != 0) {
                    insertCoins(productIndex);
                } else {
                    System.out.println(SEPARATOR);
                    System.out.println(OUT_OF_PRODUCT + getProductName(productIndex).toUpperCase());
                }
            } else if (productIndex == 159357) {
                break;
            } else {
                printer.printNotValidInput();
            }
//            print products and coins to check vm is working
            System.out.println(SEPARATOR);
            System.out.println(helper.checkCoins());
            System.out.println(helper.checkProducts());
        }
    }

    boolean notEnoughCoins() {
        if (vm.getTenCentsCoinsQuantity() < this.MIN_TEN_CENTS_TO_OPERATE) {
            System.out.println(SEPARATOR);
            System.out.println(OUT_OF_ORDER);
            System.out.println(SEPARATOR);
            return true;
        }
        return false;
    }

    /**
     * Method showing the menu and prompt for product select
     * @return - chosen product as int
     */
    private int selectProduct() {
        MenuHelper helper = new MenuHelper(vm);
        System.out.println(helper.selectProductMenuUser());
        return input.getInt();
    }

    /**
     * Loops through insert coins until coins meet the price or user cancels order
     * @param productIndex - number of the chosen product
     */
    private void insertCoins(int productIndex) {
        MenuHelper helper = new MenuHelper(vm);
        CoinsCounter counter = new CoinsCounter(vm.getCoins());

        double selectedProductPrice = getProductPrice(productIndex);
        double insertedAmount = 0.00;

        while (selectedProductPrice > insertedAmount) {
            int selectedCoin = selectCoin(productIndex, insertedAmount);
            if (selectedCoin > 0.00 && selectedCoin <= vm.getCoinsSize()) {
                insertedAmount = counter.insert(selectedCoin);
            } else if (selectedCoin == 0) {
                counter.returnInsertedCoins();
                break;
            } else {
                printer.printNotValidInput();
            }
        }

        if (insertedAmount >= selectedProductPrice) {
            counter.addInsertedCoins(vm.getCoins());

            vm.getProducts().getItem(productIndex).decreaseQuantity();

            String change = counter.calculateChange(insertedAmount, selectedProductPrice);
            System.out.println(helper.remainingAmountToInsert(productIndex, insertedAmount));
//            System.out.println(getProductName(selectedProduct - 1) + " price: " +
//                    String.format("%.2f", selectedProductPrice) + " EUR | inserted coins: " +
//                    String.format("%.2f", insertedAmount) + " EUR");
            System.out.println(SEPARATOR);
            System.out.println("THANK YOU! PLEASE GET YOUR " + getProductName(productIndex).toUpperCase());
            if (!change.equals("0.0")) {
                System.out.println(SEPARATOR);
                System.out.println("COINS RETURNED AS CHANGE: " + counter.calculateReturningCoins(change, vm.getCoins()));
            }
        }
    }

    /**
     * Method showing the menu and prompt for coin select
     * @return - selected coin as int
     */
    private int selectCoin(int selectedProduct, Double insertedAmount) {
        MenuHelper helper = new MenuHelper(vm);
        System.out.println(helper.remainingAmountToInsert(selectedProduct, insertedAmount));
        System.out.println(helper.selectCoinMenu());
        return input.getInt();
    }

    private String getProductName(int productIndex) {
        return vm.getProducts().getItem(productIndex).getName();
    }

    /**
     * @return - the chosen product price
     */
    private double getProductPrice(int productIndex) {
        return vm.getProducts().getItem(productIndex).getPrice();
    }

    /**
     * @return - the chosen product quantity
     */
    private int getProductQuantity(int productIndex) {
        return vm.getProducts().getItem(productIndex).getQuantity();
    }

    //TODO REFACTOR
    public void printState(String str) {
        System.out.println(SEPARATOR);
        System.out.println(str);
        System.out.println(SEPARATOR);
    }

}
