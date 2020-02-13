package menu;

public class MenuPrinter {

    private static final String SEPARATOR = "----------";
    private static final String MAIN_MENU = "MAIN MENU";
    private static final String MAIN_MENU_OPTIONS = "[1] User\n[2] Maintenance\n[0] Shutdown";
    private static final String MAIN_MENU_SELECT = "PLEASE SELECT AN OPTION [1-2]";
    private static final String NOT_A_VALID_OPTION = "NOT A VALID OPTION";

    public MenuPrinter() {}

    public void printMainMenu() {
        printState(MAIN_MENU);
        System.out.println(MAIN_MENU_OPTIONS);
        printState(MAIN_MENU_SELECT);
//        StringBuilder builder = new StringBuilder(MAIN_MENU)
//                .append(System.lineSeparator())
//                .append(SEPARATOR)
//                .append(System.lineSeparator())
//                .append(MAIN_MENU_OPTIONS)
//                .append(System.lineSeparator())
//                .append(SEPARATOR)
//                .append(System.lineSeparator())
//                .append(MAIN_MENU_SELECT)
//                .append(System.lineSeparator())
//                .append(SEPARATOR);
//        System.out.println(builder);
    }

    public void printNotValidInput() {
        printState(NOT_A_VALID_OPTION);
    }

    public void printState(String str) {
        System.out.println(SEPARATOR);
        System.out.println(str);
        System.out.println(SEPARATOR);
    }
}
