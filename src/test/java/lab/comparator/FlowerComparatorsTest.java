package lab.comparator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import lab.FlowerItem;

class FlowerComparatorsTest {

    // Helper method to create FlowerItems for testing
    private FlowerItem createFlowerItem(
            String name,
            String origin,
            String soil,
            double averageSize,
            int temperature,
            int watering,
            String multiplying
    ) {
        FlowerItem flower = new FlowerItem();
        flower.setName(name);
        flower.setOrigin(origin);
        flower.setSoil(soil);
        flower.setAverageSize(averageSize);
        flower.setTemperature(temperature);
        flower.setWatering(watering);
        flower.setMultiplying(multiplying);
        return flower;
    }

    @Test
    void testByName() {
        FlowerItem rose = createFlowerItem("Rose", "Europe", "Loamy", 1.5, 20, 3, "Cutting");
        FlowerItem lily = createFlowerItem("Lily", "Asia", "Sandy", 1.2, 18, 4, "Bulb");
        FlowerItem daisy = createFlowerItem("Daisy", "Europe", "Clay", 0.8, 15, 2, "Seed");

        List<FlowerItem> flowers = Arrays.asList(rose, lily, daisy);
        flowers.sort(FlowerComparators.byName());

        assertEquals(Arrays.asList(daisy, lily, rose), flowers);
    }

    @Test
    void testByOrigin() {
        FlowerItem rose = createFlowerItem("Rose", "Europe", "Loamy", 1.5, 20, 3, "Cutting");
        FlowerItem lily = createFlowerItem("Lily", "Asia", "Sandy", 1.2, 18, 4, "Bulb");
        FlowerItem orchid = createFlowerItem("Orchid", "South America", "Loamy", 1.0, 22, 3, "Division");

        List<FlowerItem> flowers = Arrays.asList(rose, lily, orchid);
        flowers.sort(FlowerComparators.byOrigin());

        assertEquals(Arrays.asList(lily, rose, orchid), flowers);
    }

    @Test
    void testBySoil() {
        FlowerItem rose = createFlowerItem("Rose", "Europe", "Loamy", 1.5, 20, 3, "Cutting");
        FlowerItem lily = createFlowerItem("Lily", "Asia", "Sandy", 1.2, 18, 4, "Bulb");
        FlowerItem daisy = createFlowerItem("Daisy", "Europe", "Clay", 0.8, 15, 2, "Seed");

        List<FlowerItem> flowers = Arrays.asList(rose, lily, daisy);
        flowers.sort(FlowerComparators.bySoil());

        assertEquals(Arrays.asList(daisy, rose, lily), flowers);
    }

    @Test
    void testByAverageSize() {
        FlowerItem rose = createFlowerItem("Rose", "Europe", "Loamy", 1.5, 20, 3, "Cutting");
        FlowerItem lily = createFlowerItem("Lily", "Asia", "Sandy", 1.2, 18, 4, "Bulb");
        FlowerItem daisy = createFlowerItem("Daisy", "Europe", "Clay", 0.8, 15, 2, "Seed");

        List<FlowerItem> flowers = Arrays.asList(rose, lily, daisy);
        flowers.sort(FlowerComparators.byAverageSize());

        assertEquals(Arrays.asList(daisy, lily, rose), flowers);
    }

    @Test
    void testByTemperature() {
        FlowerItem rose = createFlowerItem("Rose", "Europe", "Loamy", 1.5, 20, 3, "Cutting");
        FlowerItem lily = createFlowerItem("Lily", "Asia", "Sandy", 1.2, 18, 4, "Bulb");
        FlowerItem daisy = createFlowerItem("Daisy", "Europe", "Clay", 0.8, 15, 2, "Seed");

        List<FlowerItem> flowers = Arrays.asList(rose, lily, daisy);
        flowers.sort(FlowerComparators.byTemperature());

        assertEquals(Arrays.asList(daisy, lily, rose), flowers);
    }

    @Test
    void testByWatering() {
        FlowerItem rose = createFlowerItem("Rose", "Europe", "Loamy", 1.5, 20, 3, "Cutting");
        FlowerItem lily = createFlowerItem("Lily", "Asia", "Sandy", 1.2, 18, 4, "Bulb");
        FlowerItem daisy = createFlowerItem("Daisy", "Europe", "Clay", 0.8, 15, 2, "Seed");

        List<FlowerItem> flowers = Arrays.asList(rose, lily, daisy);
        flowers.sort(FlowerComparators.byWatering());

        assertEquals(Arrays.asList(daisy, rose, lily), flowers);
    }

    @Test
    void testByMultiplying() {
        FlowerItem rose = createFlowerItem("Rose", "Europe", "Loamy", 1.5, 20, 3, "Cutting");
        FlowerItem lily = createFlowerItem("Lily", "Asia", "Sandy", 1.2, 18, 4, "Bulb");
        FlowerItem daisy = createFlowerItem("Daisy", "Europe", "Clay", 0.8, 15, 2, "Seed");

        List<FlowerItem> flowers = Arrays.asList(rose, lily, daisy);
        flowers.sort(FlowerComparators.byMultiplying());

        assertEquals(Arrays.asList(lily, rose, daisy), flowers);
    }

    @Test
    void testChainedComparators() {
        // Test combining multiple comparators
        Comparator<FlowerItem> combinedComparator =
                FlowerComparators.byOrigin()
                        .thenComparing(FlowerComparators.byName());

        FlowerItem rose = createFlowerItem("Rose", "Europe", "Loamy", 1.5, 20, 3, "Cutting");
        FlowerItem lily = createFlowerItem("Lily", "Asia", "Sandy", 1.2, 18, 4, "Bulb");
        FlowerItem tulip = createFlowerItem("Tulip", "Europe", "Clay", 1.0, 16, 3, "Bulb");

        List<FlowerItem> flowers = Arrays.asList(rose, lily, tulip);
        flowers.sort(combinedComparator);

        assertEquals(Arrays.asList(lily, rose, tulip), flowers);
    }
}