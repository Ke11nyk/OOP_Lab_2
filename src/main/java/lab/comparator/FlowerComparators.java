package lab.comparator;

import java.util.Comparator;
import lab.FlowerItem;

public class FlowerComparators {

    public static Comparator<FlowerItem> byName() {
        return Comparator.comparing(FlowerItem::getName);
    }

    public static Comparator<FlowerItem> byOrigin() {
        return Comparator.comparing(FlowerItem::getOrigin);
    }

    public static Comparator<FlowerItem> bySoil() {
        return Comparator.comparing(FlowerItem::getSoil);
    }

    public static Comparator<FlowerItem> byAverageSize() {
        return Comparator.comparing(FlowerItem::getAverageSize);
    }

    public static Comparator<FlowerItem> byTemperature() {
        return Comparator.comparing(FlowerItem::getTemperature);
    }

    public static Comparator<FlowerItem> byWatering() {
        return Comparator.comparing(FlowerItem::getWatering);
    }

    public static Comparator<FlowerItem> byMultiplying() {
        return Comparator.comparing(FlowerItem::getMultiplying);
    }
}