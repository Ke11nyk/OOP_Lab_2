package lab.parsers;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import lab.FlowerItem;

public class saxParser {
    public static List<FlowerItem> parseFlowers(String xmlFile) {
        List<FlowerItem> flowerList = new ArrayList<>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {
                FlowerItem flower;
                String currentElement = "";

                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    currentElement = qName;
                    if (qName.equals("FlowerItem")) {
                        flower = new FlowerItem();
                        flower.setId(attributes.getValue("id"));
                    }
                }

                public void characters(char[] ch, int start, int length) throws SAXException {
                    String value = new String(ch, start, length).trim();
                    if (value.isEmpty()) return;

                    switch (currentElement) {
                        case "Name":
                            flower.setName(value);
                            break;
                        case "Soil":
                            flower.setSoil(value);
                            break;
                        case "Origin":
                            flower.setOrigin(value);
                            break;
                        case "StemColor":
                            flower.setStemColor(value);
                            break;
                        case "LeafColor":
                            flower.setLeafColor(value);
                            break;
                        case "AverageSize":
                            flower.setAverageSize(Double.parseDouble(value));
                            break;
                        case "Temperature":
                            flower.setTemperature(Integer.parseInt(value));
                            break;
                        case "LightLoving":
                            flower.setLightLoving(Boolean.parseBoolean(value));
                            break;
                        case "Watering":
                            flower.setWatering(Integer.parseInt(value));
                            break;
                        case "Multiplying":
                            flower.setMultiplying(value);
                            break;
                    }
                }

                public void endElement(String uri, String localName, String qName) throws SAXException {
                    if (qName.equals("FlowerItem")) {
                        flowerList.add(flower);
                    }
                    currentElement = "";
                }
            };
            parser.parse(xmlFile, handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flowerList;
    }
}