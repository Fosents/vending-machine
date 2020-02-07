import main.VendingMachine;

/**
 * The main class that starts the VM
 */
public class Start {

    public static void main(String[] args) {
        startVM();
    }

    static void startVM() {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.initMainMenu();
    }
}