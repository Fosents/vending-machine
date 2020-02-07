package coins;

import java.util.ArrayList;
import java.util.Arrays;

public class InitCoins {

    /**
     * Method to preload coins into the machine.
     */
    public ArrayList<CoinsData> preload() {
        return new ArrayList<>(Arrays.asList(
                new CoinsData("TEN CENTS", 0.10, 30),
                new CoinsData("TWENTY CENTS", 0.20, 20),
                new CoinsData("FIFTY CENTS", 0.50, 10),
                new CoinsData("ONE EUR", 1.00, 0),
                new CoinsData("TWO EUR", 2.00, 0)
        ));
    }
}
