package lab.parsers;

import lab.FlowerItem;
import java.util.List;
import java.util.ArrayList;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;

public class staxParser {
    public static List<FlowerItem> parseFlowers(String xmlFile) {
        List<FlowerItem> flowerList = new ArrayList<>();
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader = factory.createXMLEventReader(new FileInputStream(xmlFile));

            FlowerItem flower = null;

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                if (event.isStartElement()) {
                    String localPart = event.asStartElement().getName().getLocalPart();
                    switch (localPart) {
                        case "FlowerItem":
                            flower = new FlowerItem();
                            flower.setId(event.asStartElement().getAttributeByName(new QName("id")).getValue());
                            break;
                        case "Name":
                            flower.setName(eventReader.nextEvent().asCharacters().getData());
                            break;
                        case "Soil":
                            flower.setSoil(eventReader.nextEvent().asCharacters().getData());
                            break;
                        case "Origin":
                            flower.setOrigin(eventReader.nextEvent().asCharacters().getData());
                            break;
                        case "StemColor":
                            flower.setStemColor(eventReader.nextEvent().asCharacters().getData());
                            break;
                        case "LeafColor":
                            flower.setLeafColor(eventReader.nextEvent().asCharacters().getData());
                            break;
                        case "AverageSize":
                            flower.setAverageSize(Double.parseDouble(eventReader.nextEvent().asCharacters().getData()));
                            break;
                        case "Temperature":
                            flower.setTemperature(Integer.parseInt(eventReader.nextEvent().asCharacters().getData()));
                            break;
                        case "LightLoving":
                            flower.setLightLoving(Boolean.parseBoolean(eventReader.nextEvent().asCharacters().getData()));
                            break;
                        case "Watering":
                            flower.setWatering(Integer.parseInt(eventReader.nextEvent().asCharacters().getData()));
                            break;
                        case "Multiplying":
                            flower.setMultiplying(eventReader.nextEvent().asCharacters().getData());
                            break;
                    }
                }

                if (event.isEndElement() && event.asEndElement().getName().getLocalPart().equals("FlowerItem")) {
                    flowerList.add(flower);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flowerList;
    }
}