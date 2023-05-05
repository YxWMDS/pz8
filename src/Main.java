import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws TransformerException, IOException, SAXException, ParserConfigurationException {
        File xmlFile = new File("src/Devices.xml");
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = domFactory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);
        List<Device> deviceList = new ArrayList<>();
        NodeList deviceNodes = doc.getElementsByTagName("device");
        for (int i = 0; i < deviceNodes.getLength(); i++) {
            Node deviceNode = deviceNodes.item(i);
            if (deviceNode.getNodeType() == Node.ELEMENT_NODE) {
                Element deviceElement = (Element) deviceNode;
                deviceList.add(new Device(
                        deviceElement.getElementsByTagName("name").item(0).getTextContent(),
                        deviceElement.getElementsByTagName("origin").item(0).getTextContent(),
                        Integer.parseInt(deviceElement.getElementsByTagName("price").item(0).getTextContent()),
                        Boolean.parseBoolean(deviceElement.getElementsByTagName("isPeripheral").item(0).getTextContent()),
                        Integer.parseInt(deviceElement.getElementsByTagName("energy").item(0).getTextContent()),
                        Boolean.parseBoolean(deviceElement.getElementsByTagName("hasCooler").item(0).getTextContent()),
                        deviceElement.getElementsByTagName("componentGroup").item(0).getTextContent(),
                        deviceElement.getElementsByTagName("port").item(0).getTextContent(),
                        Integer.parseInt(deviceElement.getAttribute("id"))
                ));
            }
        }

        for (Device device : deviceList) {
            System.out.println("Device name is:" + device.getName());
        }

        TransformerFactory factory = TransformerFactory.newInstance();
        Source xslt = new StreamSource(new File("src/Devices.xsl"));
        Transformer transformer = factory.newTransformer(xslt);
        Source xml = new StreamSource(new File("src/Devices.xml"));
        transformer.transform(xml, new StreamResult(new File("src/Critical.xml")));
        System.out.println("DONE");
    }
}