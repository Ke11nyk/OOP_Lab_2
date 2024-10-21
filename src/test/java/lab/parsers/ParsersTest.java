package lab.parsers;

import lab.FlowerItem;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParsersTest {

    private static final String TEST_XML =
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                    "<Flowers>" +
                    "  <FlowerItem id=\"f1\">" +
                    "    <Name>Rose</Name>" +
                    "    <Soil>loam</Soil>" +
                    "    <Origin>Asia</Origin>" +
                    "    <VisualParameters>" +
                    "      <StemColor>green</StemColor>" +
                    "      <LeafColor>dark green</LeafColor>" +
                    "      <AverageSize>30.5</AverageSize>" +
                    "    </VisualParameters>" +
                    "    <GrowingTips>" +
                    "      <Temperature>20</Temperature>" +
                    "      <LightLoving>true</LightLoving>" +
                    "      <Watering>250</Watering>" +
                    "    </GrowingTips>" +
                    "    <Multiplying>cuttings</Multiplying>" +
                    "  </FlowerItem>" +
                    "</Flowers>";

    private static File testFile;

    @BeforeAll
    static void setup(@TempDir Path tempDir) throws Exception {
        testFile = tempDir.resolve("test.xml").toFile();
        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write(TEST_XML);
        }
    }

    @Test
    void testDomParser() {
        List<FlowerItem> flowers = domParser.parseFlowers(testFile.getPath());
        assertFalse(flowers.isEmpty());
        validateFlower(flowers.get(0));
    }

    @Test
    void testSaxParser() {
        List<FlowerItem> flowers = saxParser.parseFlowers(testFile.getPath());
        assertFalse(flowers.isEmpty());
        validateFlower(flowers.get(0));
    }

    @Test
    void testStaxParser() {
        List<FlowerItem> flowers = staxParser.parseFlowers(testFile.getPath());
        assertFalse(flowers.isEmpty());
        validateFlower(flowers.get(0));
    }

    private void validateFlower(FlowerItem flower) {
        assertEquals("f1", flower.getId());
        assertEquals("Rose", flower.getName());
        assertEquals("loam", flower.getSoil());
        assertEquals("Asia", flower.getOrigin());
        assertEquals("green", flower.getStemColor());
        assertEquals("dark green", flower.getLeafColor());
        assertEquals(30.5, flower.getAverageSize(), 0.001);
        assertEquals(20, flower.getTemperature());
        assertTrue(flower.isLightLoving());
        assertEquals(250, flower.getWatering());
        assertEquals("cuttings", flower.getMultiplying());
    }
}