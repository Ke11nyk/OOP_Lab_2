package lab;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FlowerItemTest {

    @Test
    void testConstructorAndGetters() {
        FlowerItem flower = new FlowerItem("1", "Rose", "Loamy", "Asia", "Green", "Green",
                50.0, 22, true, 300, "Cuttings");

        assertEquals("1", flower.getId());
        assertEquals("Rose", flower.getName());
        assertEquals("Loamy", flower.getSoil());
        assertEquals("Asia", flower.getOrigin());
        assertEquals("Green", flower.getStemColor());
        assertEquals("Green", flower.getLeafColor());
        assertEquals(50.0, flower.getAverageSize(), 0.001);
        assertEquals(22, flower.getTemperature());
        assertTrue(flower.isLightLoving());
        assertEquals(300, flower.getWatering());
        assertEquals("Cuttings", flower.getMultiplying());
    }

    @Test
    void testSetters() {
        FlowerItem flower = new FlowerItem();

        flower.setId("2");
        flower.setName("Tulip");
        flower.setSoil("Sandy");
        flower.setOrigin("Europe");
        flower.setStemColor("Green");
        flower.setLeafColor("Dark Green");
        flower.setAverageSize(30.0);
        flower.setTemperature(18);
        flower.setLightLoving(false);
        flower.setWatering(200);
        flower.setMultiplying("Bulbs");

        assertEquals("2", flower.getId());
        assertEquals("Tulip", flower.getName());
        assertEquals("Sandy", flower.getSoil());
        assertEquals("Europe", flower.getOrigin());
        assertEquals("Green", flower.getStemColor());
        assertEquals("Dark Green", flower.getLeafColor());
        assertEquals(30.0, flower.getAverageSize(), 0.001);
        assertEquals(18, flower.getTemperature());
        assertFalse(flower.isLightLoving());
        assertEquals(200, flower.getWatering());
        assertEquals("Bulbs", flower.getMultiplying());
    }

    @Test
    void testToString() {
        FlowerItem flower = new FlowerItem("3", "Orchid", "Bark mix", "Tropical", "Green", "Green",
                40.0, 25, false, 250, "Division");
        String expected = "Orchid: \n- ID: 3\n- Soil: Bark mix\n- Origin: Tropical\n- Visual Parameters:" +
                "\n\t* Stem Color: Green\n\t* Leaf Color: Green\n\t* Average Size: 40.0 cm" +
                "\n- Growing Tips:\n\t* Temperature: 25°C\n\t* Light Loving: false" +
                "\n\t* Watering: 250 ml/week\n- Multiplying: Division";
        assertEquals(expected, flower.toString());
    }
}