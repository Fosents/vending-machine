package states.implement;

import enums.State;
import main.VendingMachine;
import menu.MenuPrinter;
import utils.UserInput;

public class MainState {

    final int MIN_TEN_CENTS_TO_OPERATE = 20;
    final String SEPARATOR = "----------";
    final String OUT_OF_ORDER = "SORRY! MACHINE OUT OF ORDER\nENTER MAINTENANCE";
    final String OUT_OF_PRODUCT = "SORRY! MACHINE OUT OF ";
    final String NOT_A_VALID_OPTION = "NOT A VALID OPTION";
    final String MAIN_MENU = "MAIN MENU";
    final String MAIN_MENU_OPTIONS = "[1] User\n[2] Maintenance\n[0] Shutdown";
    final String MAIN_MENU_SELECT = "PLEASE SELECT AN OPTION [1-2]";
    final String NO_PRODUCTS = "NO PRODUCTS INSERTED IN THE MACHINE\nPLEASE ENTER MAINTENANCE";
    final String NO_COINS = "NO COINS INSERTED IN MACHINE\nPLEASE ENTER MAINTENANCE";
    final String INVALID_INPUT = "INVALID INPUT! PLEASE SELECT AGAIN";

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
//                    MenuUser menuUser = new MenuUser(vm, input);
//                    menuUser.initUser();
                    return State.USER;
                } else {
                    break;
                }
            } else if (select == 2) {
//                MenuMaintenance menuMaintenance = new MenuMaintenance(vm, input);
//                menuMaintenance.enterPassword();
//                menuMaintenance.initMaintenance();
                return State.MAINTENANCE;
            } else {
                System.out.println(SEPARATOR);
                System.out.println(NOT_A_VALID_OPTION);
                System.out.println(SEPARATOR);
            }
        }
        return null;
    }

    boolean isProductsAndCoins() {
        if (vm.getProductsSize() == 0) {
            printState(NO_PRODUCTS);
            return false;
        } else if (vm.getCoinsSize() == 0) {
            printState(NO_COINS);
            return false;
        }
        return true;
    }

    public void printState(String str) {
        System.out.println(SEPARATOR);
        System.out.println(str);
        System.out.println(SEPARATOR);
    }
}
