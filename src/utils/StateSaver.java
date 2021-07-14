package utils;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import storage.CoinsData;
import storage.ProductData;
import storage.Storage;
import storage.StorageType;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
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

    public void updateItemQuantity(StorageType type, String itemName, int quantity) {
        File file = XmlHelper.createFile(type.toString());
        Document doc = XmlHelper.parseFileToDoc(file);
        NodeList items = doc.getElementsByTagName("item");
        for (int i = 0; i < items.getLength(); i++) {
            String name = items.item(i).getAttributes().getNamedItem("name").getNodeValue();
            if (name.equalsIgnoreCase(itemName)) {
                items.item(i).getAttributes()
                        .getNamedItem("quantity")
                        .setTextContent(String.valueOf(quantity));
                XmlHelper.writeXmlFile(doc, file.getPath());
                break;
            }
        }
    }
}
