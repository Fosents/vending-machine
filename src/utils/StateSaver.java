package utils;

import storage.CoinsData;
import storage.ProductData;
import storage.Storage;

import java.io.FileWriter;
import java.io.IOException;

public class StateSaver {
    public void updateProducts(Storage<ProductData> storage) {
        String path = "files/products.txt";
        StringBuilder builder = new StringBuilder();
        storage.getStorage().forEach((key, value) -> builder.append(value.getName()).append(",")
                .append(value.getPrice()).append(",").append(value.getQuantity()).append(System.lineSeparator()));
        try (FileWriter writer = new FileWriter(path)) {
            writer.write(builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateCoins(Storage<CoinsData> storage) {
        String path = "files/coins.txt";
        StringBuilder builder = new StringBuilder();
        storage.getStorage().forEach((key, value) -> builder.append(value.getName()).append(",")
                .append(value.getValue()).append(",").append(value.getQuantity()).append(System.lineSeparator()));
        try (FileWriter writer = new FileWriter(path)) {
            writer.write(builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
