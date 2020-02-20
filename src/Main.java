import enums.State;
import factory.MenuPrinterFactory;
import factory.UserInputFactory;
import factory.VendingMachineFactory;
import main.VendingMachine;
import states.*;
import menu.MenuPrinter;
import states.implement.MainState;
import utils.UserInput;

/**
 * The main class that starts the VM
 */
public class Main {

    public static void main(String[] args) {
        startVM();
    }

    static void startVM() {
        VendingMachine vm = VendingMachineFactory.createVM();
        UserInput input = UserInputFactory.createUserInput();
        MenuPrinter printer = MenuPrinterFactory.createMenuPrinter();
        MainState main = StateLoader.initMainState(vm, input, printer);
        do {
            State state = main.initMenu();
            IState currentState = StateLoader.initState(vm, input, printer, state);
            if (currentState != null) {
                currentState.initMenu();
            }
            if (state == State.SHUTDOWN) {
                break;
            }
        } while (true);
    }
}