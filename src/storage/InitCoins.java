package storage;

import java.util.ArrayList;
import java.util.Arrays;

public class InitCoins {

    /**
     * Method to preload coins into the machine.
     */
    public Storage<CoinsData> preload(Storage<CoinsData> storage) {
        storage.loadItem(1, new CoinsData("TEN CENTS", 0.10, 30));
        storage.loadItem(2, new CoinsData("TWENTY CENTS", 0.20, 20));
        storage.loadItem(3, new CoinsData("FIFTY CENTS", 0.50, 10));
        storage.loadItem(4, new CoinsData("ONE EUR", 1.00, 0));
        storage.loadItem(5, new CoinsData("TWO EUR", 2.00, 0));
        return storage;
    }
}
