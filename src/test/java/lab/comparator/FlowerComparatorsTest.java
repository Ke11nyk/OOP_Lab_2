package lab.comparator;

import lab.generated.Flowers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

class FlowerComparatorsTest {
    private List<Flowers.FlowerItem> flowers;
    private Flowers.FlowerItem rose;
    private Flowers.FlowerItem tulip;
    private Flowers.FlowerItem lily;

    @BeforeEach
    void setUp() {
        flowers = new ArrayList<>();
        rose = createTestFlower("Rose", "China", "podzolic", 30.5, 20, 250, "leaf", 1);
        tulip = createTestFlower("Tulip", "Netherlands", "ground", 25.0, 18, 200, "bulb", 2);
        lily = createTestFlower("Lily", "Asia", "sandy", 35.0, 22, 300, "bulb", 3);

        flowers.add(rose);
        flowers.add(tulip);
        flowers.add(lily);
    }

    private Flowers.FlowerItem createTestFlower(String name, String origin, String soil,
                                                double avgSize, int temp, int watering, String multiplying, int id) {
        Flowers.FlowerItem flower = new Flowers.FlowerItem();
        flower.setName(name);
        flower.setOrigin(origin);
        flower.setSoil(soil);
        flower.setMultiplying(multiplying);
        flower.setId(BigInteger.valueOf(id));

        Flowers.FlowerItem.VisualParameters vp = new Flowers.FlowerItem.VisualParameters();
        vp.setAverageSize(BigDecimal.valueOf(avgSize));
        flower.setVisualParameters(vp);

        Flowers.FlowerItem.GrowingTips gt = new Flowers.FlowerItem.GrowingTips();
        gt.setTemperature(temp);
        gt.setWatering(watering);
        flower.setGrowingTips(gt);

        return flower;
    }

    @Test
    void testCompareByName() {
        Collections.sort(flowers, FlowerComparators.byName());
        assertAll(
                () -> assertEquals("Lily", flowers.get(0).getName(), "Lily should be first"),
                () -> assertEquals("Rose", flowers.get(1).getName(), "Rose should be second"),
                () -> assertEquals("Tulip", flowers.get(2).getName(), "Tulip should be last")
        );
    }

    @Test
    void testCompareByOrigin() {
        Collections.sort(flowers, FlowerComparators.byOrigin());
        assertAll(
                () -> assertEquals("Asia", flowers.get(0).getOrigin(), "Asia should be first"),
                () -> assertEquals("China", flowers.get(1).getOrigin(), "China should be second"),
                () -> assertEquals("Netherlands", flowers.get(2).getOrigin(), "Netherlands should be last")
        );
    }

    @Test
    void testCompareBySoil() {
        Collections.sort(flowers, FlowerComparators.bySoil());
        assertAll(
                () -> assertEquals("ground", flowers.get(0).getSoil(), "ground should be first"),
                () -> assertEquals("podzolic", flowers.get(1).getSoil(), "podzolic should be second"),
                () -> assertEquals("sandy", flowers.get(2).getSoil(), "sandy should be last")
        );
    }

    @Test
    void testCompareByAverageSize() {
        Collections.sort(flowers, FlowerComparators.byAverageSize());
        assertAll(
                () -> assertEquals(BigDecimal.valueOf(25.0),
                        flowers.get(0).getVisualParameters().getAverageSize(), "Smallest size should be first"),
                () -> assertEquals(BigDecimal.valueOf(30.5),
                        flowers.get(1).getVisualParameters().getAverageSize(), "Medium size should be second"),
                () -> assertEquals(BigDecimal.valueOf(35.0),
                        flowers.get(2).getVisualParameters().getAverageSize(), "Largest size should be last")
        );
    }

    @Test
    void testCompareByTemperature() {
        Collections.sort(flowers, FlowerComparators.byTemperature());
        assertAll(
                () -> assertEquals(18,
                        flowers.get(0).getGrowingTips().getTemperature(), "Lowest temperature should be first"),
                () -> assertEquals(20,
                        flowers.get(1).getGrowingTips().getTemperature(), "Medium temperature should be second"),
                () -> assertEquals(22,
                        flowers.get(2).getGrowingTips().getTemperature(), "Highest temperature should be last")
        );
    }

    @Test
    void testCompareByWatering() {
        Collections.sort(flowers, FlowerComparators.byWatering());
        assertAll(
                () -> assertEquals(200,
                        flowers.get(0).getGrowingTips().getWatering(), "Lowest watering should be first"),
                () -> assertEquals(250,
                        flowers.get(1).getGrowingTips().getWatering(), "Medium watering should be second"),
                () -> assertEquals(300,
                        flowers.get(2).getGrowingTips().getWatering(), "Highest watering should be last")
        );
    }

    @Test
    void testCompareByMultiplying() {
        Collections.sort(flowers, FlowerComparators.byMultiplying());
        assertAll(
                () -> assertEquals("bulb",
                        flowers.get(0).getMultiplying(), "bulb should be first"),
                () -> assertEquals("bulb",
                        flowers.get(1).getMultiplying(), "bulb should be second"),
                () -> assertEquals("leaf",
                        flowers.get(2).getMultiplying(), "leaf should be last")
        );
    }
}