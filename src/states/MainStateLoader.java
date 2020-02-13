package states;

import main.VendingMachine;
import menu.MenuPrinter;
import states.implement.MainState;
import utils.UserInput;

public class MainStateLoader {
    public static MainState initState(VendingMachine vm, UserInput input, MenuPrinter printer) {
        return new MainState(vm, input, printer);
    }
}
