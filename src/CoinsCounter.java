import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Class that handles the insert and returning coins with successful or cancel order
 */
class CoinsCounter {

    static String insertedCoinsAmountTemp = "0.00";
    private static ArrayList<CoinsData> coinsTemp = new ArrayList<>();

    /**
     * Insert coins method
     *
     * @param coinChoice - coin inserted
     */
    static void insertCoin(int coinChoice, ArrayList<CoinsData> coins) {
        String coinName = coins.get(coinChoice - 1).getName();
        String coinValue = coins.get(coinChoice - 1).getValue();
        boolean isPresent = false;

        for (CoinsData coin : coinsTemp) {
            if (coin.getName().equals(coinName)) {
                coin.increaseQuantity(1);
                insertedCoinsAmountTemp = getSumValue(insertedCoinsAmountTemp, coinValue);
                isPresent = true;
            }
        }
        if (!isPresent) {
            coinsTemp.add(new CoinsData(coinName, coinValue, 1));
            insertedCoinsAmountTemp = getSumValue(insertedCoinsAmountTemp, coinValue);
        }
    }
    /**
     * Calculates the change
     *
     * @param sumCoinsInserted - the sum of the inserted coins
     * @param productPrice     - the product price
     * @return - the change amount
     */
    static String calculateChange(String sumCoinsInserted, String productPrice) {
        BigDecimal sumCoinsInsertedDeci = new BigDecimal(sumCoinsInserted);
        BigDecimal productPriceDeci = new BigDecimal(productPrice);
        BigDecimal bSum = sumCoinsInsertedDeci.subtract(productPriceDeci);
        return bSum.toString();
    }

    /**
     * Method to calculate the coins needed for change in descending order
     *
     * @param calcChange - the amount of change
     * @return - returned coins as String
     */
    static String calculateReturningCoins(String calcChange, ArrayList<CoinsData> coins) {
        StringBuilder coinsAsChange = new StringBuilder();
        BigDecimal calculatedChangeDecimal = new BigDecimal(calcChange);

        while (Double.valueOf(calculatedChangeDecimal.toString()) > 0) {
            for (CoinsData coin : coins) {
                if (Double.valueOf(coin.getValue()) <= Double.valueOf(calculatedChangeDecimal.toString()) &&
                        coin.getQuantity() > 0) {
                    int quantityTemp = 0;
                    while (Double.valueOf(coin.getValue()) <= Double.valueOf(calculatedChangeDecimal.toString()) &&
                            coin.getQuantity() > 0) {
                        coin.decreaseQuantity();
                        quantityTemp ++;
                        BigDecimal coinValueDecimal = new BigDecimal(coin.getValue());
                        calculatedChangeDecimal = calculatedChangeDecimal.subtract(coinValueDecimal);
                    }
                    coinsAsChange.append(coin.getName()).append(" ")
                            .append("(").append(quantityTemp).append(")").append(" ");
                }
            }
        }
        return coinsAsChange.toString();
    }

    /**
     * Method adding the new coin value to the coins amount
     *
     * @param coinsAmount - the amount of coins
     * @param coinValue   - the coin value in string format
     * @return - the new coins amount
     */
    private static String getSumValue(String coinsAmount, String coinValue) {
        BigDecimal sumDeci = new BigDecimal(coinsAmount);
        BigDecimal coinValueDeci = new BigDecimal(coinValue);
        BigDecimal bSum = sumDeci.add(coinValueDeci);
        return bSum.toString();
    }

    /**
     * Adds inserted coins to the coins in the machine and reset
     */
    static void addInsertedCoins(ArrayList<CoinsData> coins) {
        for (CoinsData coinTemp : coinsTemp) {
            for (CoinsData coin : coins) {
                if (coin.getName().equals(coinTemp.getName())) {
                    coin.increaseQuantity(coinTemp.getQuantity());
                }
            }
        }
        coinsTemp.clear();
    }

    /**
     * Returns inserted coins to the user and reset
     */
    static void returnInsertedCoins() {
        System.out.print("GET YOUR COINS: ");
        for (CoinsData coin :
                coinsTemp) {
            System.out.print("(" + coin.getQuantity() + ")" + " " + coin.getName() + " ");
        }
        System.out.println();
        coinsTemp.clear();
    }
}
