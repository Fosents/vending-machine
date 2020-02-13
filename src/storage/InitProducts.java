package storage;

import java.util.ArrayList;
import java.util.Arrays;

public class InitProducts {

    /**
     * Method to preload products into the machine.
     */
    public Storage<ProductData> preload(Storage<ProductData> storage) {
        storage.loadItem(1, new ProductData("Water", 0.60, 1));
        storage.loadItem(2, new ProductData("Coke", 1.10, 10));
        storage.loadItem(3, new ProductData("Croissant", 0.70, 10));
        storage.loadItem(4, new ProductData("Mars", 0.50, 10));
        storage.loadItem(5, new ProductData("Snickers", 0.60, 10));
        storage.loadItem(6, new ProductData("Juice", 0.90, 10));
        storage.loadItem(7, new ProductData("Crackers", 1.30, 10));
        storage.loadItem(8, new ProductData("Beans", 1.20, 10));
        return storage;
    }
}
