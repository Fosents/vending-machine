package factory;

import menu.MenuPrinter;

public class MenuPrinterFactory {

    public static MenuPrinter createMenuPrinter() {
        return new MenuPrinter();
    }
}
