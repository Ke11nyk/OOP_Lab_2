package lab.comparator;

import java.util.Comparator;
import lab.generated.Flowers;

public class FlowerComparators {
    public static Comparator<Flowers.FlowerItem> byName() {
        return Comparator.comparing(Flowers.FlowerItem::getName);
    }

    public static Comparator<Flowers.FlowerItem> byOrigin() {
        return Comparator.comparing(Flowers.FlowerItem::getOrigin);
    }

    public static Comparator<Flowers.FlowerItem> bySoil() {
        return Comparator.comparing(Flowers.FlowerItem::getSoil);
    }

    public static Comparator<Flowers.FlowerItem> byAverageSize() {
        return Comparator.comparing(f -> f.getVisualParameters().getAverageSize());
    }

    public static Comparator<Flowers.FlowerItem> byTemperature() {
        return Comparator.comparingInt(f -> f.getGrowingTips().getTemperature());
    }

    public static Comparator<Flowers.FlowerItem> byWatering() {
        return Comparator.comparingInt(f -> f.getGrowingTips().getWatering());
    }

    public static Comparator<Flowers.FlowerItem> byMultiplying() {
        return Comparator.comparing(Flowers.FlowerItem::getMultiplying);
    }
}