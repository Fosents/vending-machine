package factory;

import main.VendingMachine;

public class VendingMachineFactory {

    public static VendingMachine createVM() {
        return new VendingMachine();
    }
}
