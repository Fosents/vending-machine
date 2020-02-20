package states;

import enums.State;
import main.VendingMachine;
import states.implement.MainState;
import states.implement.MaintenanceState;
import menu.MenuPrinter;
import states.implement.UserState;
import utils.UserInput;

public class StateLoader {
    public static MainState initMainState(VendingMachine vm, UserInput input, MenuPrinter printer) {
        return new MainState(vm, input, printer);
    }

    public static IState initState(VendingMachine vm, UserInput input, MenuPrinter printer, State state) {
        if (state == State.USER) {
            return new UserState(vm, input, printer);
        } else if (state == State.MAINTENANCE) {
            return new MaintenanceState(vm, input, printer);
        }
        return null;
    }
}
