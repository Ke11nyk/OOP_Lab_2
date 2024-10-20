package lab;

public class FlowerItem {
    private String id;
    private String name;
    private String soil;
    private String origin;
    private String stemColor;
    private String leafColor;
    private double averageSize;
    private int temperature;
    private boolean lightLoving;
    private int watering;
    private String multiplying;

    public FlowerItem() {}

    public FlowerItem(String id, String name, String soil, String origin, String stemColor, String leafColor,
                      double averageSize, int temperature, boolean lightLoving, int watering, String multiplying) {
        this.id = id;
        this.name = name;
        this.soil = soil;
        this.origin = origin;
        this.stemColor = stemColor;
        this.leafColor = leafColor;
        this.averageSize = averageSize;
        this.temperature = temperature;
        this.lightLoving = lightLoving;
        this.watering = watering;
        this.multiplying = multiplying;
    }

    // Getters and setters for all fields

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSoil() {
        return soil;
    }

    public void setSoil(String soil) {
        this.soil = soil;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getStemColor() {
        return stemColor;
    }

    public void setStemColor(String stemColor) {
        this.stemColor = stemColor;
    }

    public String getLeafColor() {
        return leafColor;
    }

    public void setLeafColor(String leafColor) {
        this.leafColor = leafColor;
    }

    public double getAverageSize() {
        return averageSize;
    }

    public void setAverageSize(double averageSize) {
        this.averageSize = averageSize;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public boolean isLightLoving() {
        return lightLoving;
    }

    public void setLightLoving(boolean lightLoving) {
        this.lightLoving = lightLoving;
    }

    public int getWatering() {
        return watering;
    }

    public void setWatering(int watering) {
        this.watering = watering;
    }

    public String getMultiplying() {
        return multiplying;
    }

    public void setMultiplying(String multiplying) {
        this.multiplying = multiplying;
    }

    @Override
    public String toString() {
        return this.name + ": " +
                "\n- ID: " + this.id +
                "\n- Soil: " + this.soil +
                "\n- Origin: " + this.origin +
                "\n- Visual Parameters:" +
                "\n\t* Stem Color: " + this.stemColor +
                "\n\t* Leaf Color: " + this.leafColor +
                "\n\t* Average Size: " + this.averageSize + " cm" +
                "\n- Growing Tips:" +
                "\n\t* Temperature: " + this.temperature + "°C" +
                "\n\t* Light Loving: " + this.lightLoving +
                "\n\t* Watering: " + this.watering + " ml/week" +
                "\n- Multiplying: " + this.multiplying;
    }
}