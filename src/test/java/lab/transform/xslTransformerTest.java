package lab.transform;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class xslTransformerTest {

    @Test
    void testTransform() throws IOException {
        String xmlContent = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Plants>\n" +
                "  <PlantItem id=\"1\">\n" +
                "    <Name>Rose</Name>\n" +
                "    <Origin>Asia</Origin>\n" +
                "  </PlantItem>\n" +
                "  <PlantItem id=\"2\">\n" +
                "    <Name>Tulip</Name>\n" +
                "    <Origin>Europe</Origin>\n" +
                "  </PlantItem>\n" +
                "</Plants>";

        String xslContent = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<xsl:stylesheet version=\"1.0\" xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\">\n" +
                "  <xsl:output method=\"xml\" indent=\"yes\"/>\n" +
                "  <xsl:template match=\"/\">\n" +
                "    <TransformedPlants>\n" +
                "      <xsl:for-each select=\"Plants/PlantItem\">\n" +
                "        <Plant>\n" +
                "          <xsl:value-of select=\"Name\"/>\n" +
                "          <xsl:text> from </xsl:text>\n" +
                "          <xsl:value-of select=\"Origin\"/>\n" +
                "        </Plant>\n" +
                "      </xsl:for-each>\n" +
                "    </TransformedPlants>\n" +
                "  </xsl:template>\n" +
                "</xsl:stylesheet>";

        File xmlFile = File.createTempFile("test", ".xml");
        File xslFile = File.createTempFile("test", ".xsl");

        try (FileWriter xmlWriter = new FileWriter(xmlFile);
             FileWriter xslWriter = new FileWriter(xslFile)) {
            xmlWriter.write(xmlContent);
            xslWriter.write(xslContent);
        }

        String outputPath = xslTransformer.transform(xmlFile.getPath(), xslFile.getPath());
        assertNotEquals("", outputPath);

        File outputFile = new File(outputPath);
        assertTrue(outputFile.exists());

        // Clean up
        xmlFile.delete();
        xslFile.delete();
        outputFile.delete();
    }
}