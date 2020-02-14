package states.implement;

import main.VendingMachine;
import menu.MenuPrinter;
import states.IState;
import utils.CoinsCounter;
import utils.UserInput;

public class UserState implements IState {

    final int MIN_TEN_CENTS_TO_OPERATE = 20;

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
        while (true) {
//            print products and coins to check vm is working
//            printer.printCoins(vm.getCoins().getStorage());
//            printer.printProducts(vm.getProducts().getStorage());
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
                    printer.printOutOfProduct(vm.getProducts().getItem(productIndex));
                }
            } else if (productIndex == 159357) {
                break;
            } else {
                printer.printNotValidOption();
            }


        }
    }

    boolean notEnoughCoins() {
        if (vm.getTenCentsCoinsQuantity() < this.MIN_TEN_CENTS_TO_OPERATE) {
            printer.printOutOfOrder();
            return true;
        }
        return false;
    }

    /**
     * Method showing the menu and prompt for product select
     * @return - chosen product as int
     */
    private int selectProduct() {
        printer.printProductMenuUser(vm);
        return input.getInt();
    }

    /**
     * Loops through insert coins until coins meet the price or user cancels order
     * @param productIndex - number of the chosen product
     */
    private void insertCoins(int productIndex) {
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
                printer.printNotValidOption();
            }
        }

        if (insertedAmount >= selectedProductPrice) {
            counter.addInsertedCoins(vm.getCoins());

            vm.getProducts().getItem(productIndex).decreaseQuantity();

            String change = counter.calculateChange(insertedAmount, selectedProductPrice);
            printer.printRemainingAmountToInsert(vm, productIndex, insertedAmount);
//            System.out.println(getProductName(selectedProduct - 1) + " price: " +
//                    String.format("%.2f", selectedProductPrice) + " EUR | inserted coins: " +
//                    String.format("%.2f", insertedAmount) + " EUR");
            printer.printWithSeparator("THANK YOU! PLEASE GET YOUR " + getProductName(productIndex).toUpperCase());
            if (!change.equals("0.0")) {
                printer.printWithSeparator("COINS RETURNED AS CHANGE: " + counter.calculateReturningCoins(change, vm.getCoins()));
            }
        }
    }

    /**
     * Method showing the menu and prompt for coin select
     * @return - selected coin as int
     */
    private int selectCoin(int selectedProduct, Double insertedAmount) {
        printer.printRemainingAmountToInsert(vm, selectedProduct, insertedAmount);
        printer.printCoinsMenuUser(vm);
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
}
