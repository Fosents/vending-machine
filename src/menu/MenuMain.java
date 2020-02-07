package menu;

import coins.CoinsData;
import products.ProductData;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuMain{

    final int MIN_TEN_CENTS_TO_OPERATE = 20;
    final String SEPARATOR = "----------";
    final String OUT_OF_ORDER = "SORRY! MACHINE OUT OF ORDER\nENTER MAINTENANCE";
    final String OUT_OF_PRODUCT = "SORRY! MACHINE OUT OF ";
    final String NOT_A_VALID_OPTION = "NOT A VALID OPTION";
    final String MAIN_MENU = "MAIN MENU";
    final String MAIN_MENU_OPTIONS = "[1] User\n[2] Maintenance\n[0] Shutdown";
    final String MAIN_MENU_SELECT =  "PLEASE SELECT AN OPTION [1-2]";

    Scanner input;

    public MenuMain() {
        this.input = new Scanner(System.in);
    }

    public void init(ArrayList<ProductData> products, ArrayList<CoinsData> coins) {
        while (true) {
            System.out.println(MAIN_MENU);
            System.out.println(SEPARATOR);
            System.out.println(MAIN_MENU_OPTIONS);
            System.out.println(SEPARATOR);
            System.out.println(MAIN_MENU_SELECT);
            System.out.println(SEPARATOR);
            int choice = userInput();
            if (choice == 0) {
                break;
            }
            if (choice == 1) {
                MenuUser menuUser = new MenuUser(products, coins);
                menuUser.init();
            } else if (choice == 2) {
                MenuMaintenance menuMaintenance = new MenuMaintenance(products, coins);
                menuMaintenance.enterPassword();
                menuMaintenance.init();
            } else {
                System.out.println(SEPARATOR);
                System.out.println(NOT_A_VALID_OPTION);
                System.out.println(SEPARATOR);
            }
        }
    }

    boolean notEnoughCoins(ArrayList<CoinsData> coins) {
        if (getTenCentsCoinsQuantity(coins) < this.MIN_TEN_CENTS_TO_OPERATE) {
            System.out.println(SEPARATOR);
            System.out.println(OUT_OF_ORDER);
            System.out.println(SEPARATOR);
            return true;
        }
        return false;
    }

    /**
     * @return - ten cents coins quantity
     */
    int getTenCentsCoinsQuantity(ArrayList<CoinsData> coins) {
        return coins.get(0).getQuantity();
    }

    int userInput() {
        int select = 0;
        boolean correct = false;
        do {
            try {
                select = input.nextInt();
                correct = true;
            } catch (InputMismatchException ignored) {
                System.out.println("INVALID INPUT! PLEASE SELECT AGAIN");
                System.out.println(SEPARATOR);
            }
            input.nextLine();
        } while (!correct);
        return select;
    }
}
