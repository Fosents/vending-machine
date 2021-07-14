package utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import storage.CoinsData;
import storage.ProductData;
import storage.Storage;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

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

    public Storage<CoinsData> loadCoinsToStorage() {
        NodeList nodeList = XmlHelper.getNodeItems("coins");
        Storage<CoinsData> storage = new Storage<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Node node = nodeList.item(i);
                String name = node.getAttributes().getNamedItem("name").getNodeValue();
                double price = Double.parseDouble(node.getAttributes().getNamedItem("price").getNodeValue());
                int quantity = Integer.parseInt(node.getAttributes().getNamedItem("quantity").getNodeValue());
                storage.loadItem(i + 1, new CoinsData(name, price, quantity));
            }
        }
        return storage;
    }

    public Storage<ProductData> loadProductsToStorage() {
        NodeList nodeList = XmlHelper.getNodeItems("products");
        Storage<ProductData> storage = new Storage<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Node node = nodeList.item(i);
                String name = node.getAttributes().getNamedItem("name").getNodeValue();
                double price = Double.parseDouble(node.getAttributes().getNamedItem("price").getNodeValue());
                int quantity = Integer.parseInt(node.getAttributes().getNamedItem("quantity").getNodeValue());
                storage.loadItem(i + 1, new ProductData(name, price, quantity));
            }
        }
        return storage;
    }
}
