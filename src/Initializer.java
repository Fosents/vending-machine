/**
 * Class to initialize and load VM
 */
class Initializer {
    /**
     * Method initializing a VM
     */
    static void initializeVM() {
        VendingMachine vendingMachine = new VendingMachine();

        //preloading coins
        vendingMachine.preloadCoins(new CoinsData("TWO EUR", "2.00", 0));
        vendingMachine.preloadCoins(new CoinsData("ONE EUR", "1.00", 0));
        vendingMachine.preloadCoins(new CoinsData("FIFTY CENTS", "0.50", 10));
        vendingMachine.preloadCoins(new CoinsData("TWENTY CENTS", "0.20", 20));
        vendingMachine.preloadCoins(new CoinsData("TEN CENTS", "0.10", 30));

        //preloading products
        vendingMachine.preloadProduct(new ProductData("Water", 4, 0.60));
        vendingMachine.preloadProduct(new ProductData("Coke", 10, 1.10));
        vendingMachine.preloadProduct(new ProductData("Croissant", 20, 0.70));
        vendingMachine.preloadProduct(new ProductData("Mars", 40, 0.50));
        vendingMachine.preloadProduct(new ProductData("Snickers", 40, 0.60));
        vendingMachine.preloadProduct(new ProductData("Juice", 10, 0.90));
        vendingMachine.preloadProduct(new ProductData("Crackers", 20, 1.30));
        vendingMachine.preloadProduct(new ProductData("Beans", 30, 1.20));

        vendingMachine.mainMenu();
    }
}
