package utils;

import storage.CoinsData;
import storage.ProductData;
import storage.Storage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InitData {

    /**
     * Method to preload products into the machine.
     */
    public Storage<ProductData> products() {
        Storage<ProductData> storage = new Storage<>();
        String path = "files/products.txt";
        try (
                BufferedReader reader = new BufferedReader(new FileReader(path));
        ) {
            String line;
            int index = 1;
            while((line = reader.readLine()) != null) {
                String[] product = line.split("[,]");
                storage.loadItem(index,  new ProductData(product[0], Double.parseDouble(product[1]), Integer.parseInt(product[2])));
                index++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return storage;
    }

    /**
     * Method to preload coins into the machine.
     */
    public Storage<CoinsData> coins() {
        Storage<CoinsData> storage = new Storage<>();
        String path = "files/coins.txt";
        try (
                BufferedReader reader = new BufferedReader(new FileReader(path));
        ) {
            String line;
            int index = 1;
            while((line = reader.readLine()) != null) {
                String[] product = line.split("[,]");
                storage.loadItem(index,  new CoinsData(product[0], Double.parseDouble(product[1]), Integer.parseInt(product[2])));
                index++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return storage;
    }
}
