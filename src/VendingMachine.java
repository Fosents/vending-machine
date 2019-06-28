import java.util.ArrayList;
import java.util.Scanner;

/**
 * Start VM Class
 */
class VendingMachine {

    private ArrayList<ProductData> products = new ArrayList<>();
    private ArrayList<CoinsData> coins = new ArrayList<>();
    private Scanner input = new Scanner(System.in);

    /**
     * Method to preload coins into the machine.
     */
    void preloadCoins(CoinsData coin) {
        this.coins.add(coin);
    }

    /**
     * Method to preload products
     */
    void preloadProduct(ProductData product) {
        this.products.add(product);
    }

    /**
     * Method showing the remaining coins in the machine as String.
     */
    private String checkCoins() {
        StringBuilder builder = new StringBuilder();

        for (CoinsData coin : coins)
            builder.append(coin.getName()).append(" ").append(coin.getQuantity()).append(" ");

        builder.deleteCharAt(builder.toString().length() - 1);
        return builder.toString();
    }

    /**
     * Method showing the remaining products in the machine as String.
     */
    private String checkProducts() {
        StringBuilder builder = new StringBuilder();

        for (ProductData product : products)
            builder.append(product.getName()).append(" ").append(product.getQuantity()).append(" ");

        builder.deleteCharAt(builder.toString().length() - 1);
        return builder.toString();
    }

    /**
     * @return - the chosen product price
     */
    private double getProductPrice(int choice) {
        return products.get(choice - 1).getPrice();
    }

    /**
     * @return - the chosen product quantity
     */
    private int getProductQuantity(int product) {
        return products.get(product).getQuantity();
    }

    /**
     * @return - ten cents coins quantity
     */
    private int getTenCentsCoinsQuantity() {
        return this.coins.get(coins.size() - 1).getQuantity();
    }

    /**
     * The main menu
     */
    void mainMenu() {
        while (true) {
            System.out.println("----------\nMAIN MENU\n[1] User\n[2] Maintenance\n[0] Shutdown\n" +
                    "PLEASE CHOOSE AN OPTION [1-2]");
            int choice = input.nextInt();
            if (choice == 0) {
                break;
            }
            if (choice == 1) {
                buyProductUserSelect();
            } else if (choice == 2) {
                maintenancePassword();
                maintenanceMenuMain();

            } else System.out.println("----------\nNOT A VALID OPTION\n");
        }
    }

    /**
     * Loops through buy product and insert coins until the machine is out of coins
     */
    private void buyProductUserSelect() {
        System.out.println("----------\n" + checkProducts());
        while (true) {
            int MIN_TEN_CENTS_TO_OPERATE = 20;
            if (getTenCentsCoinsQuantity() < MIN_TEN_CENTS_TO_OPERATE) {
                System.out.println("----------\nSORRY MACHINE OUT OF ORDER\nENTER MAINTENANCE");
                break;
            }
            int choice = chooseProductUserMenu();

            if (choice == 0) {
                break;
            }
            if (choice > 0 && choice <= getProductsLength()) { // if valid product choice enters insert coin menu
                if (getProductQuantity(choice - 1) == 0) {
                    System.out.println("----------\nOUT OF PRODUCT");
                } else insertCoinsUserMenu(choice);
            } else System.out.println("----------\nNOT A VALID OPTION");
            System.out.println("\n" + checkCoins());
            System.out.println(checkProducts());

            CoinsCounter.insertedCoinsAmountTemp = "0.00";
        }
    }

    /**
     * Loops through insert coins until coins meet the price or user cancels order
     * @param chosenProduct - number of the chosen product
     */
    private void insertCoinsUserMenu(int chosenProduct) {

        double chosenProductPrice = getProductPrice(chosenProduct);

        while (chosenProductPrice > Double.valueOf(CoinsCounter.insertedCoinsAmountTemp)) {

            int choiceCoin = chooseCoinUserMenu(chosenProduct);

            if (choiceCoin == 0) {
                CoinsCounter.returnInsertedCoins();
                break;
            } else if (choiceCoin > 0 && choiceCoin <= getCoinsLength()) {
                CoinsCounter.insertCoin(choiceCoin, coins);
            } else System.out.println("NOT A VALID OPTION");
        }

        if (Double.valueOf(CoinsCounter.insertedCoinsAmountTemp) >= chosenProductPrice) {
            CoinsCounter.addInsertedCoins(coins);

            products.get(chosenProduct - 1).decreaseQuantity();

            String change = CoinsCounter.calculateChange(CoinsCounter.insertedCoinsAmountTemp,
                    String.valueOf(chosenProductPrice));

            System.out.println(getProductName(chosenProduct - 1) + " price: " +
                    String.format("%.2f", chosenProductPrice) + " EUR | inserted coins: " +
                    CoinsCounter.insertedCoinsAmountTemp + " EUR\n" +
                    "----------\nTHANK YOU! PLEASE GET YOUR PRODUCT.\n----------" +
                    "\nCOINS RETURNED AS CHANGE: " + CoinsCounter.calculateReturningCoins(change, coins));
        }
    }

    /**
     * Method showing the menu and prompt for product select
     * @return - chosen product as int
     */
    private int chooseProductUserMenu() {
        String builder = chooseProductDefaultMenu() + "[0] Return to MAIN MENU\n" +
                "PLEASE CHOOSE A PRODUCT [1-" + products.size() + "]";
        System.out.println(builder);
        return input.nextInt();
    }

    /**
     * Method showing the menu and prompt for coin select
     * @return - chosen coin as int
     */
    private int chooseCoinUserMenu(int choice) {
        String builder = chooseCoinDefaultMenu() + "[0] Cancel" + System.lineSeparator() +
                products.get(choice - 1).getName() + " price: " +
                String.format("%.2f", products.get(choice - 1).getPrice()) +
                " EUR | inserted coins: " + CoinsCounter.insertedCoinsAmountTemp + " EUR" +
                "\nPLEASE INSERT A COIN [1-" + coins.size() + "]";
        System.out.println(builder);
        return input.nextInt();
    }

    /**
     * The default coins menu
     * @return - coin menu as String
     */
    private String chooseCoinDefaultMenu() {
        StringBuilder builder = new StringBuilder();

        builder.append("----------\n");
        for (int i = 0; i < coins.size(); i++) {
            builder.append("[").append(i + 1).append("] ").append(coins.get(i).getName())
                    .append(System.lineSeparator());
        }
        return builder.toString();
    }

    /**
     * The default products menu
     * @return - products menu as String
     */
    private String chooseProductDefaultMenu() {
        StringBuilder builder = new StringBuilder();

        builder.append("----------\n");
        for (int i = 0; i < products.size(); i++) {
            builder.append("[").append(i + 1).append("] ").append(products.get(i).getName()).append(" ")
                    .append(String.format("%.2f", products.get(i).getPrice())).append(" EUR")
                    .append(System.lineSeparator());
        }
        return builder.toString();
    }

    private String getProductName(int productIndex) {
        return products.get(productIndex).getName();
    }

    private int getProductsLength() {
        return products.size();
    }

    private int getCoinsLength() {
        return coins.size();
    }

    /**
     * Password for maintenance
     */
    private void maintenancePassword() {
        while (true) {
            System.out.println("----------\nPLEASE ENTER PASSWORD");
            String password = input.next();
            String MAINTENANCE_PASSWORD = "proba123";
            if (password.equals(MAINTENANCE_PASSWORD)) {
                break;
            } else {
                System.out.println("----------\nWRONG PASSWORD");
                mainMenu();
            }
        }
    }

    /**
     * The maintenance main menu
     */
    private void maintenanceMenuMain() {
        while (true) {
            if (getTenCentsCoinsQuantity() < 20) {
                System.out.println("----------\nATTENTION! Not enough coins to operate\n" +
                        "Please INSERT COINS in the machine");
            }
            for (ProductData product : products) {
                if (product.getQuantity() == 0) {
                    System.out.println("ATTENTION! " + product.getName() + " is out of quantity\nPlease INSERT " +
                            product.getName() + " in the machine");
                }
            }
            System.out.println("----------\n[1] Check Quantities\n[2] Insert Coins\n[3] Insert Product\n" +
                    "[0] Return to MAIN MENU\nPLEASE CHOOSE AN OPTION [1-3]");
            int choice = input.nextInt();
            if (choice == 0) {
                break;
            } else if (choice > 0 && choice <= 3) {
                switch (choice) {
                    case 1 :
                        System.out.println("----------\n" + checkCoins() + "\n----------\n" + checkProducts());
                        break;
                    case 2 :
                        maintenanceInsertCoinsMenu();
                        break;
                    case 3 :
                        maintenanceInsertProductMenu();
                        break;
                }
            } else System.out.println("----------\nNOT A VALID OPTION\n");
        }
    }

    /**
     * The maintenance insert coins quantity menu
     */
    private void maintenanceInsertCoinsMenu() {
        while (true) {
            System.out.println(checkCoins() + "\n" + chooseCoinDefaultMenu() +
                    "[0] Return to PREVIOUS MENU\nPLEASE INSERT A COIN [1-" + coins.size() + "]");
            int choice = input.nextInt();
            if (choice == 0) {
                break;
            } else if (choice > 0 && choice <= getCoinsLength()) {
                System.out.println("----------\nENTER " + coins.get(choice - 1).getName() + " COINS AMOUNT");
                int quantityInsert = input.nextInt();
                coins.get(choice - 1).increaseQuantity(quantityInsert);
                System.out.println("----------\n" + quantityInsert + " " + coins.get(choice - 1).getName() +
                        " COINS ADDED\n----------");
            } else System.out.println("----------\nNOT A VALID OPTION\n");
        }
    }

    /**
     * The maintenance insert products quantity menu
     */
    private void maintenanceInsertProductMenu() {
        while (true) {
            System.out.println(checkProducts() + "\n" + chooseProductDefaultMenu() + "[0] Return to PREVIOUS MENU\n" +
                    "PLEASE CHOOSE A PRODUCT [1-" + products.size() + "]");
            int choice = input.nextInt();
            if (choice == 0) {
                break;
            } else if (choice > 0 && choice <= getProductsLength()) {
                System.out.println("----------\nENTER " + products.get(choice - 1).getName() + " QUANTITY TO INSERT");
                int quantityInsert = input.nextInt();
                products.get(choice - 1).increaseQuantity(quantityInsert);
                System.out.println("----------\n" + quantityInsert + " " + products.get(choice - 1).getName() +
                        " QUANTITY ADDED\n----------");
            } else System.out.println("----------\nNOT A VALID OPTION\n");
        }
    }
}