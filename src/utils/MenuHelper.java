package utils;

import main.VendingMachine;

public class MenuHelper {

    final String SEPARATOR = "----------";
    final String CANCEL_OPTION = "[0] Cancel";
    final String INSERT_PRODUCT = "Insert product";
    final String NO_PRODUCTS = "NO PRODUCTS INSERTED IN THE MACHINE";
    final String NO_COINS = "NO COINS INSERTED IN THE MACHINE";

    VendingMachine vm;

    public MenuHelper(VendingMachine vm) {
        this.vm = vm;
    }

    /**
     * The default coins menu
     * @return select coin menu
     */
    public String selectCoinMenu() {
        StringBuilder builder = new StringBuilder();
        builder.append(SEPARATOR).append(System.lineSeparator());
        for (int i = 1; i <= vm.getCoinsSize(); i++) { builder
                .append("[")
                .append(i)
                .append("] ")
                .append(vm.getCoins().getStorage().get(i).getName())
                .append(System.lineSeparator());
        }
        builder.append(CANCEL_OPTION).append(System.lineSeparator())
                .append(SEPARATOR).append(System.lineSeparator())
                .append(selectCoinsRange()).append(System.lineSeparator()).append(SEPARATOR);
        return builder.toString();
    }

    public String remainingAmountToInsert(int selectedProduct, Double insertedAmount) {
        return SEPARATOR + "\n" + vm.getProducts().getStorage().get(selectedProduct).getName() +
                " price: " +
                String.format("%.2f", vm.getProducts().getStorage().get(selectedProduct).getPrice()) +
                " EUR" + System.lineSeparator() +
                "Inserted coins: " +
                String.format("%.2f", insertedAmount) +
                " EUR";
    }

    /**
     * dynamic select coin range menu
     * @return coins menu range to select
     */
    public String selectCoinsRange() {
        return "PLEASE INSERT A COIN [1-" + vm.getCoinsSize() + "]";
    }

    /**
     * The user products menu
     * @return select products menu
     */
    public String selectProductMenuUser() {
        StringBuilder builder = new StringBuilder();
        builder.append(SEPARATOR).append(System.lineSeparator());
        for (int i = 1; i <= vm.getProductsSize(); i++) { builder
                .append("[")
                .append(i)
                .append("] ")
                .append(vm.getProducts().getStorage().get(i).getName())
                .append(" ")
                .append(String.format("%.2f", (vm.getProducts().getStorage().get(i).getPrice())))
                .append(" EUR")
                .append(System.lineSeparator());
        }
        builder.append(SEPARATOR).append(System.lineSeparator())
                .append(selectProductRange()).append(System.lineSeparator())
                .append(SEPARATOR);
        return builder.toString();
    }

    /**
     * The maintenance products menu
     * @return select products menu
     */
    public String selectProductMenuMaintenance(VendingMachine vm) {
        StringBuilder builder = new StringBuilder();
        builder.append(SEPARATOR).append(System.lineSeparator());
        for (int i = 1; i <= vm.getProductsSize(); i++) { builder
                .append("[")
                .append(i)
                .append("] ")
                .append(vm.getProducts().getStorage().get(i).getName())
                .append(System.lineSeparator());
        }
        builder.append("[")
                .append(vm.getProductsSize() + 1)
                .append("] ")
                .append(INSERT_PRODUCT)
                .append(System.lineSeparator())
                .append(CANCEL_OPTION)
                .append(System.lineSeparator());
        builder.append(SEPARATOR).append(System.lineSeparator())
                .append(selectProductRange()).append(System.lineSeparator())
                .append(SEPARATOR);
        return builder.toString();
    }

    /**
     * dynamic select product range menu
     * @return products menu range to select
     */
    String selectProductRange() {
        return "PLEASE SELECT A PRODUCT [1-" + vm.getProductsSize() + "]";
    }

    /**
     * show the remaining products in the machine.
     * @return products quantities
     */
    public String checkProducts() {
        StringBuilder builder = new StringBuilder();
        if (vm.getProductsSize() > 0) {
            vm.getProducts().getStorage().forEach((key, value) -> builder.append(value.getName()).append(" ").append(value.getQuantity()).append(" "));
            builder.deleteCharAt(builder.toString().length() - 1);
        } else {
            builder.append(NO_PRODUCTS);
        }
        return builder.toString();
    }

    /**
     * show the remaining coins in the machine.
     * @return coins quantities
     */
    public String checkCoins() {
        StringBuilder builder = new StringBuilder();
        if (vm.getCoinsSize() > 0) {
            vm.getCoins().getStorage().forEach((key, value) -> builder.append(value.getName()).append(" ").append(value.getQuantity()).append(" "));
            builder.deleteCharAt(builder.toString().length() - 1);
        } else {
            builder.append(NO_COINS);
        }
        return builder.toString();
    }

    //TODO REFACTOR
    public void printState(String str) {
        System.out.println(SEPARATOR);
        System.out.println(str);
        System.out.println(SEPARATOR);
    }
}
