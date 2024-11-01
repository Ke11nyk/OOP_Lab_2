package lab.parsers;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import lab.generated.Flowers;

public class staxParser {
    public static List<Flowers.FlowerItem> parseFlowers(String xmlFile) {
        try {
            JAXBContext context = JAXBContext.newInstance(Flowers.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(xmlFile));

            Flowers flowers = (Flowers) unmarshaller.unmarshal(reader);
            return flowers.getFlowerItem();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}