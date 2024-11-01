package lab.parsers;

import static org.junit.jupiter.api.Assertions.*;

import lab.validation.xmlValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import lab.generated.Flowers;

import java.io.File;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class ParsersTest {
    @TempDir
    Path tempDir;

    private String xsdPath = "src/main/resources/flowers.xsd";
    private String validXml;
    private String invalidXml;
    private String missingRequiredElementXml;
    private File validXmlFile;
    private File invalidXmlFile;
    private File missingRequiredElementXmlFile;


    @BeforeEach
    void setUp() throws Exception {
        // Valid XML content
        validXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Flowers>\n" +
                "    <FlowerItem id=\"1\">\n" +
                "        <Name>Rose</Name>\n" +
                "        <Soil>podzolic</Soil>\n" +
                "        <Origin>China</Origin>\n" +
                "        <VisualParameters>\n" +
                "            <StemColor>green</StemColor>\n" +
                "            <LeafColor>dark-green</LeafColor>\n" +
                "            <AverageSize>30.5</AverageSize>\n" +
                "        </VisualParameters>\n" +
                "        <GrowingTips>\n" +
                "            <Temperature>20</Temperature>\n" +
                "            <LightLoving>true</LightLoving>\n" +
                "            <Watering>250</Watering>\n" +
                "        </GrowingTips>\n" +
                "        <Multiplying>leaf</Multiplying>\n" +
                "    </FlowerItem>\n" +
                "</Flowers>";

        // Invalid XML with wrong element order
        invalidXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Flowers>\n" +
                "    <FlowerItem id=\"1\">\n" +
                "        <Name>Rose</Name>\n" +
                "        <Soil>podzolic</Soil>\n" +
                "        <Origin>China</Origin>\n" +
                "        <InvalidElement>test</InvalidElement>\n" +
                "        <GrowingTips>\n" +
                "            <Temperature>20</Temperature>\n" +
                "            <LightLoving>true</LightLoving>\n" +
                "            <Watering>250</Watering>\n" +
                "        </GrowingTips>\n" +
                "        <Multiplying>leaf</Multiplying>\n" +
                "    </FlowerItem>\n" +
                "</Flowers>";

        // XML missing required VisualParameters element
        missingRequiredElementXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Flowers>\n" +
                "    <FlowerItem id=\"1\">\n" +
                "        <Name>Rose</Name>\n" +
                "        <Soil>podzolic</Soil>\n" +
                "        <Origin>China</Origin>\n" +
                "        <GrowingTips>\n" +
                "            <Temperature>20</Temperature>\n" +
                "            <LightLoving>true</LightLoving>\n" +
                "            <Watering>250</Watering>\n" +
                "        </GrowingTips>\n" +
                "        <Multiplying>leaf</Multiplying>\n" +
                "    </FlowerItem>\n" +
                "</Flowers>";

        // Create temporary XML files
        validXmlFile = tempDir.resolve("valid.xml").toFile();
        try (FileWriter writer = new FileWriter(validXmlFile)) {
            writer.write(validXml);
        }

        invalidXmlFile = tempDir.resolve("invalid.xml").toFile();
        try (FileWriter writer = new FileWriter(invalidXmlFile)) {
            writer.write(invalidXml);
        }

        missingRequiredElementXmlFile = tempDir.resolve("missing.xml").toFile();
        try (FileWriter writer = new FileWriter(missingRequiredElementXmlFile)) {
            writer.write(missingRequiredElementXml);
        }
    }

    static Stream<Class<?>> parserClasses() {
        return Stream.of(domParser.class, saxParser.class, staxParser.class);
    }

    @ParameterizedTest
    @MethodSource("parserClasses")
    void testValidXml(Class<?> parserClass) {
        List<Flowers.FlowerItem> flowers = parseWithClass(parserClass, validXmlFile.getAbsolutePath());

        assertNotNull(flowers, "Parser should return non-null list");
        assertEquals(1, flowers.size(), "Parser should return 1 flower");

        Flowers.FlowerItem flower = flowers.get(0);
        assertAll(
                () -> assertEquals("Rose", flower.getName()),
                () -> assertEquals("podzolic", flower.getSoil()),
                () -> assertEquals("China", flower.getOrigin()),
                () -> assertEquals("leaf", flower.getMultiplying()),
                () -> assertEquals(20, flower.getGrowingTips().getTemperature()),
                () -> assertEquals(250, flower.getGrowingTips().getWatering()),
                () -> assertTrue(flower.getGrowingTips().isLightLoving()),
                () -> assertEquals(BigDecimal.valueOf(30.5), flower.getVisualParameters().getAverageSize()),
                () -> assertEquals("green", flower.getVisualParameters().getStemColor()),
                () -> assertEquals("dark-green", flower.getVisualParameters().getLeafColor())
        );
    }

    @ParameterizedTest
    @MethodSource("parserClasses")
    void testInvalidXml(Class<?> parserClass) {
        List<Flowers.FlowerItem> flowers = new ArrayList<>();

        if (xmlValidator.validateXMLSchema(missingRequiredElementXmlFile.getAbsolutePath(), xsdPath))
            flowers = parseWithClass(parserClass, invalidXmlFile.getAbsolutePath());

        assertTrue(flowers.isEmpty(), "Parser should return empty list for invalid XML");
    }

    @ParameterizedTest
    @MethodSource("parserClasses")
    void testMissingRequiredElement(Class<?> parserClass) {
        List<Flowers.FlowerItem> flowers = new ArrayList<>();
        if (xmlValidator.validateXMLSchema(missingRequiredElementXmlFile.getAbsolutePath(), xsdPath))
            flowers = parseWithClass(parserClass, missingRequiredElementXmlFile.getAbsolutePath());

        assertTrue(flowers.isEmpty(), "Parser should return empty list when required element is missing");
    }

    @ParameterizedTest
    @MethodSource("parserClasses")
    void testNonexistentFile(Class<?> parserClass) {
        List<Flowers.FlowerItem> flowers = new ArrayList<>();
        if (xmlValidator.validateXMLSchema(missingRequiredElementXmlFile.getAbsolutePath(), xsdPath))
            flowers = parseWithClass(parserClass, "nonexistent.xml");

        assertTrue(flowers.isEmpty(), "Parser should return empty list for nonexistent file");
    }

    private List<Flowers.FlowerItem> parseWithClass(Class<?> parserClass, String filePath) {
        if (parserClass == domParser.class) {
            return domParser.parseFlowers(filePath);
        } else if (parserClass == saxParser.class) {
            return saxParser.parseFlowers(filePath);
        } else {
            return staxParser.parseFlowers(filePath);
        }
    }
}