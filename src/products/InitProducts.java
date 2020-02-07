package products;

import java.util.ArrayList;
import java.util.Arrays;

public class InitProducts {

    /**
     * Method to preload products into the machine.
     */
    public ArrayList<ProductData> preload() {
        return new ArrayList<>(Arrays.asList(
                new ProductData("Water", 1, 0.60),
                new ProductData("Coke", 10, 1.10),
                new ProductData("Croissant", 20, 0.70),
                new ProductData("Mars", 40, 0.50),
                new ProductData("Snickers", 40, 0.60),
                new ProductData("Juice", 10, 0.90),
                new ProductData("Crackers", 20, 1.30),
                new ProductData("Beans", 30, 1.20)
        ));
    }
}
