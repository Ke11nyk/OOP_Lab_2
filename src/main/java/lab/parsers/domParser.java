package lab.parsers;

import java.util.List;
import java.util.ArrayList;
import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import lab.generated.Flowers;

public class domParser {
    public static List<Flowers.FlowerItem> parseFlowers(String xmlFile) {
        try {
            JAXBContext context = JAXBContext.newInstance(Flowers.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Flowers flowers = (Flowers) unmarshaller.unmarshal(new File(xmlFile));

            return flowers.getFlowerItem();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}