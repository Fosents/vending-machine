package utils;

import coins.CoinsData;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Class that handles the insert and returning coins with successful or cancel order
 */
public class CoinsCounter {

    ArrayList<CoinsData> coins;

    public CoinsCounter(ArrayList<CoinsData> coins) {
        this.coins = coins;
    }

    private double insertedCoinsAmountTemp = 0.00;
    private ArrayList<CoinsData> coinsTemp = new ArrayList<>();

    /**
     * Insert coins method
     *
     * @param selectedCoin - coin inserted
     */
    public double insert(int selectedCoin) {
        String selectedCoinName = coins.get(selectedCoin - 1).getName();
        double selectedCoinValue = coins.get(selectedCoin - 1).getValue();
        boolean isPresent = false;

        for (CoinsData coin : coinsTemp) {
            if (coin.getName().equals(selectedCoinName)) {
                coin.increaseQuantity(1);
                isPresent = true;
            }
        }
        if (!isPresent) {
            coinsTemp.add(new CoinsData(selectedCoinName, selectedCoinValue, 1));
        }
        insertedCoinsAmountTemp = getSumValue(insertedCoinsAmountTemp, selectedCoinValue);
        return insertedCoinsAmountTemp;
    }

    /**
     * Method adding the new coin value to the coins amount
     *
     * @param insertedCoinsAmountTemp - the amount of coins
     * @param selectedCoinValue   - the coin value in string format
     * @return - the new coins amount
     */
    private double getSumValue(double insertedCoinsAmountTemp, double selectedCoinValue) {
        BigDecimal sumDeci = new BigDecimal(String.valueOf(insertedCoinsAmountTemp));
        BigDecimal coinValueDeci = new BigDecimal(String.valueOf(selectedCoinValue));
        BigDecimal bSum = sumDeci.add(coinValueDeci);
        return Double.parseDouble(bSum.toString());
    }

    /**
     * Calculates the change
     *
     * @param sumCoinsInserted - the sum of the inserted coins
     * @param productPrice     - the product price
     * @return - the change amount
     */
    public String calculateChange(double sumCoinsInserted, double productPrice) {
        BigDecimal sumCoinsInsertedDeci = new BigDecimal(String.valueOf(sumCoinsInserted));
        BigDecimal productPriceDeci = new BigDecimal(String.valueOf(productPrice));
        BigDecimal bSum = sumCoinsInsertedDeci.subtract(productPriceDeci);
        return bSum.toString();
    }

    /**
     * Method to calculate the coins needed for change in descending order
     *
     * @param calcChange - the amount of change
     * @return - returned coins as String
     */
    public String calculateReturningCoins(String calcChange, ArrayList<CoinsData> coins) {
        StringBuilder coinsAsChange = new StringBuilder();
        BigDecimal calculatedChangeDecimal = new BigDecimal(calcChange);

        while (Double.parseDouble(calculatedChangeDecimal.toString()) > 0.00) {
            int coinIndex = 0;
            for (int i = 0; i < coins.size(); i++) {
                if (coins.get(i).getQuantity() > 0 &&
                        coins.get(i).getValue() <= Double.parseDouble(calculatedChangeDecimal.toString()) &&
                                coins.get(i).getValue() > coins.get(coinIndex).getValue()){
                    coinIndex = i;
                }
            }
            int quantityTemp = 0;
            while (coins.get(coinIndex).getValue() <= Double.parseDouble(calculatedChangeDecimal.toString()) &&
                    coins.get(coinIndex).getQuantity() > 0) {
                coins.get(coinIndex).decreaseQuantity();
                quantityTemp ++;
                BigDecimal coinValueDecimal = BigDecimal.valueOf(coins.get(coinIndex).getValue());
                calculatedChangeDecimal = calculatedChangeDecimal.subtract(coinValueDecimal);
            }
            coinsAsChange.append(coins.get(coinIndex).getName()).append(" ")
                    .append("(").append(quantityTemp).append(")").append(" ");
        }
        return coinsAsChange.toString();
    }

    /**
     * Adds inserted coins to the coins in the machine and reset
     */
    public void addInsertedCoins(ArrayList<CoinsData> coins) {
        for (CoinsData coinTemp : coinsTemp) {
            for (CoinsData coin : coins) {
                if (coin.getName().equals(coinTemp.getName())) {
                    coin.increaseQuantity(coinTemp.getQuantity());
                }
            }
        }
        coinsTemp = new ArrayList<>();
    }

    /**
     * Returns inserted coins to the user and reset
     */
    public void returnInsertedCoins() {
        if (coinsTemp.size() > 0) {
            System.out.print("GET YOUR COINS: ");
            for (CoinsData coin :
                    coinsTemp) {
                System.out.print("(" + coin.getQuantity() + ")" + " " + coin.getName() + " ");
            }
            System.out.println();
            coinsTemp.clear();
        }
    }
}
