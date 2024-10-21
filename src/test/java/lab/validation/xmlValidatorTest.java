package lab.validation;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class xmlValidatorTest {

    @Test
    void testValidXMLSchema() throws IOException {
        String validXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Flowers>\n" +
                "  <FlowerItem id=\"1\">\n" +
                "    <Name>Rose</Name>\n" +
                "    <Soil>Loamy</Soil>\n" +
                "    <Origin>Asia</Origin>\n" +
                "    <VisualParameters>\n" +
                "      <StemColor>Green</StemColor>\n" +
                "      <LeafColor>Green</LeafColor>\n" +
                "      <AverageSize>50.0</AverageSize>\n" +
                "    </VisualParameters>\n" +
                "    <GrowingTips>\n" +
                "      <Temperature>22</Temperature>\n" +
                "      <LightLoving>true</LightLoving>\n" +
                "      <Watering>300</Watering>\n" +
                "    </GrowingTips>\n" +
                "    <Multiplying>Cuttings</Multiplying>\n" +
                "  </FlowerItem>\n" +
                "</Flowers>";

        File xmlFile = File.createTempFile("valid", ".xml");
        File xsdFile = new File("src/main/resources/flowers.xsd");

        try (FileWriter writer = new FileWriter(xmlFile)) {
            writer.write(validXml);
        }

        assertTrue(xmlValidator.validateXMLSchema(xmlFile.getPath(), xsdFile.getPath()));
        xmlFile.delete();
    }

    @Test
    void testInvalidXMLSchema() throws IOException {
        String invalidXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Flowers>\n" +
                "  <FlowerItem id=\"1\">\n" +
                "    <Name>Rose</Name>\n" +
                "    <Soil>Loamy</Soil>\n" +
                "    <Origin>Asia</Origin>\n" +
                "    <InvalidElement>This should not be here</InvalidElement>\n" +
                "  </FlowerItem>\n" +
                "</Flowers>";

        File xmlFile = File.createTempFile("invalid", ".xml");
        File xsdFile = new File("src/main/resources/flowers.xsd");

        try (FileWriter writer = new FileWriter(xmlFile)) {
            writer.write(invalidXml);
        }

        assertFalse(xmlValidator.validateXMLSchema(xmlFile.getPath(), xsdFile.getPath()));
        xmlFile.delete();
    }
}