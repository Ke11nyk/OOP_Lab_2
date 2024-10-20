package lab.parsers;

import java.util.List;
import java.util.ArrayList;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

import lab.FlowerItem;

public class domParser {
    public static List<FlowerItem> parseFlowers(String xmlFile) {
        List<FlowerItem> flowerList = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(xmlFile));
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("FlowerItem");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    FlowerItem flower = new FlowerItem();
                    flower.setId(element.getAttribute("id"));
                    flower.setName(element.getElementsByTagName("Name").item(0).getTextContent());
                    flower.setSoil(element.getElementsByTagName("Soil").item(0).getTextContent());
                    flower.setOrigin(element.getElementsByTagName("Origin").item(0).getTextContent());

                    Element visualParamsElement = (Element) element.getElementsByTagName("VisualParameters").item(0);
                    flower.setStemColor(visualParamsElement.getElementsByTagName("StemColor").item(0).getTextContent());
                    flower.setLeafColor(visualParamsElement.getElementsByTagName("LeafColor").item(0).getTextContent());
                    flower.setAverageSize(Double.parseDouble(visualParamsElement.getElementsByTagName("AverageSize").item(0).getTextContent()));

                    Element growingTipsElement = (Element) element.getElementsByTagName("GrowingTips").item(0);
                    flower.setTemperature(Integer.parseInt(growingTipsElement.getElementsByTagName("Temperature").item(0).getTextContent()));
                    flower.setLightLoving(Boolean.parseBoolean(growingTipsElement.getElementsByTagName("LightLoving").item(0).getTextContent()));
                    flower.setWatering(Integer.parseInt(growingTipsElement.getElementsByTagName("Watering").item(0).getTextContent()));

                    flower.setMultiplying(element.getElementsByTagName("Multiplying").item(0).getTextContent());

                    flowerList.add(flower);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flowerList;
    }
}