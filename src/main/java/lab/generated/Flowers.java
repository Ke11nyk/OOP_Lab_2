package lab.generated;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Flowers")
@XmlAccessorType(XmlAccessType.FIELD)
public class Flowers {
    @XmlElement(name = "FlowerItem", required = true)
    protected List<Flowers.FlowerItem> flowerItem;

    public List<Flowers.FlowerItem> getFlowerItem() {
        if (flowerItem == null) {
            flowerItem = new ArrayList<>();
        }
        return this.flowerItem;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "name",
            "soil",
            "origin",
            "visualParameters",
            "growingTips",
            "multiplying"
    })
    public static class FlowerItem {
        @XmlElement(name = "Name", required = true)
        protected String name;
        @XmlElement(name = "Soil", required = true)
        protected String soil;
        @XmlElement(name = "Origin", required = true)
        protected String origin;
        @XmlElement(name = "VisualParameters", required = true)
        protected Flowers.FlowerItem.VisualParameters visualParameters;
        @XmlElement(name = "GrowingTips", required = true)
        protected Flowers.FlowerItem.GrowingTips growingTips;
        @XmlElement(name = "Multiplying", required = true)
        protected String multiplying;
        @XmlAttribute(name = "id", required = true)
        @XmlSchemaType(name = "positiveInteger")
        protected BigInteger id;

        public String getName() {
            return name;
        }

        public void setName(String value) {
            this.name = value;
        }

        public String getSoil() {
            return soil;
        }

        public void setSoil(String value) {
            this.soil = value;
        }

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String value) {
            this.origin = value;
        }

        public Flowers.FlowerItem.VisualParameters getVisualParameters() {
            return visualParameters;
        }

        public void setVisualParameters(Flowers.FlowerItem.VisualParameters value) {
            this.visualParameters = value;
        }

        public Flowers.FlowerItem.GrowingTips getGrowingTips() {
            return growingTips;
        }

        public void setGrowingTips(Flowers.FlowerItem.GrowingTips value) {
            this.growingTips = value;
        }

        public String getMultiplying() {
            return multiplying;
        }

        public void setMultiplying(String value) {
            this.multiplying = value;
        }

        public BigInteger getId() {
            return id;
        }

        public void setId(BigInteger value) {
            this.id = value;
        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "stemColor",
                "leafColor",
                "averageSize"
        })
        public static class VisualParameters {
            @XmlElement(name = "StemColor", required = true)
            protected String stemColor;
            @XmlElement(name = "LeafColor", required = true)
            protected String leafColor;
            @XmlElement(name = "AverageSize", required = true)
            protected BigDecimal averageSize;

            public String getStemColor() {
                return stemColor;
            }

            public void setStemColor(String value) {
                this.stemColor = value;
            }

            public String getLeafColor() {
                return leafColor;
            }

            public void setLeafColor(String value) {
                this.leafColor = value;
            }

            public BigDecimal getAverageSize() {
                return averageSize;
            }

            public void setAverageSize(BigDecimal value) {
                this.averageSize = value;
            }
        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "temperature",
                "lightLoving",
                "watering"
        })
        public static class GrowingTips {
            @XmlElement(name = "Temperature")
            protected int temperature;
            @XmlElement(name = "LightLoving")
            protected boolean lightLoving;
            @XmlElement(name = "Watering")
            protected int watering;

            public int getTemperature() {
                return temperature;
            }

            public void setTemperature(int value) {
                this.temperature = value;
            }

            public boolean isLightLoving() {
                return lightLoving;
            }

            public void setLightLoving(boolean value) {
                this.lightLoving = value;
            }

            public int getWatering() {
                return watering;
            }

            public void setWatering(int value) {
                this.watering = value;
            }
        }
    }
}