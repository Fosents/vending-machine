package states.implement;

import enums.State;
import main.VendingMachine;
import menu.MenuPrinter;
import utils.UserInput;

public class MainState {

    UserInput input;
    VendingMachine vm;
    MenuPrinter printer;

    public MainState(VendingMachine vm, UserInput input, MenuPrinter printer) {
        this.vm = vm;
        this.input = input;
        this.printer = printer;
    }

    public State initMenu() {
        while (true) {
            printer.printMainMenu();
            int select = input.getInt();
            if (select == 0) {
                return State.SHUTDOWN;
            } else if (select == 1) {
                if (isProductsAndCoins()) {
                    return State.USER;
                } else {
                    break;
                }
            } else if (select == 2) {
                return State.MAINTENANCE;
            } else {
                printer.printNotValidOption();
            }
        }
        return null;
    }

    boolean isProductsAndCoins() {
        if (vm.getProductsSize() == 0) {
            printer.printOutOfProducts();
            return false;
        } else if (vm.getCoinsSize() == 0) {
            printer.printOutOfCoins();
            return false;
        }
        return true;
    }
}
