package utils;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class XmlHelper {

    public static NodeList getNodeItems(String fileName) {
        File file = createFile(fileName);
        Document doc = parseFileToDoc(file);
        return doc.getElementsByTagName("item");
    }

    public static Document parseFileToDoc(File file) {
        try {
            String filepath = file.getPath();
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            return docBuilder.parse(filepath);
//            return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file.getPath());
        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot read VM machine data");
        }
    }

    public static File createFile(String fileName) {
        return new File(
                System.getProperty("user.dir") + File.separator +
                        "files" + File.separator +
                        fileName + ".xml");
    }

    public static void writeXmlFile(Document doc, String filePath) {
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update quantity!");
        }
    }
}
