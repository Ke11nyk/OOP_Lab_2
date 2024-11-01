package lab.menu;

import lab.parsers.*;
import lab.generated.Flowers;
import lab.validation.xmlValidator;
import lab.transform.xslTransformer;
import lab.comparator.FlowerComparators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Menu {
    private InputReader inputReader;
    private String xmlFilePath;
    private String xsdFilePath;
    private String xslByOriginPath;
    private String xslBySoilPath;
    List<Flowers.FlowerItem> flowerList;

    public Menu() {
        this.inputReader = new InputReader();
        this.xmlFilePath = getClass().getClassLoader().getResource("flowers.xml").getPath();
        this.xsdFilePath = getClass().getClassLoader().getResource("flowers.xsd").getPath();
        this.xslByOriginPath = getClass().getClassLoader().getResource("flowersByOrigin.xsl").getPath();
        this.xslBySoilPath = getClass().getClassLoader().getResource("flowersBySoil.xsl").getPath();
        flowerList = new ArrayList<>();
    }

    public void start() {
        System.out.println("Validating XML file...");
        if (!xmlValidator.validateXMLSchema(xmlFilePath, xsdFilePath)) {
            System.out.println("XML validation failed. Exiting...");
            System.exit(-1);
        }
        System.out.println("XML validated successfully!");

        while (true) {
            printMainMenu();
            int choice = inputReader.readInt("Enter your choice:");
            switch (choice) {
                case 1:
                    parseOptions();
                    break;
                case 2:
                    if (flowerList.isEmpty()) {
                        System.out.println("Flower list is empty!");
                        break;
                    }
                    printFlowerList();
                    break;
                case 3:
                    if (flowerList.isEmpty()) {
                        System.out.println("Flower list is empty!");
                        break;
                    }
                    sortOptions();
                    break;
                case 4:
                    transformOptions();
                    break;
                case 0:
                    System.out.println("Closing...");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private void printMainMenu() {
        System.out.println("\n=== Main Menu ===");
        System.out.println("1. Parse");
        System.out.println("2. Print flower list");
        System.out.println("3. Sort flower list");
        System.out.println("4. Transform XML");
        System.out.println("0. Exit");
        System.out.println("===============");
    }

    private void printFlowerList() {
        System.out.println("\n=== Flower List ===");
        for (Flowers.FlowerItem flower : flowerList) {
            System.out.printf("%s:%n", flower.getName());
            System.out.printf("- ID: %s%n", flower.getId());
            System.out.printf("- Soil: %s%n", flower.getSoil());
            System.out.printf("- Origin: %s%n", flower.getOrigin());
            System.out.println("- Visual Parameters:");
            System.out.printf("\t* Stem Color: %s%n", flower.getVisualParameters().getStemColor());
            System.out.printf("\t* Leaf Color: %s%n", flower.getVisualParameters().getLeafColor());
            System.out.printf("\t* Average Size: %s cm%n", flower.getVisualParameters().getAverageSize());
            System.out.println("- Growing Tips:");
            System.out.printf("\t* Temperature: %d°C%n", flower.getGrowingTips().getTemperature());
            System.out.printf("\t* Light Loving: %b%n", flower.getGrowingTips().isLightLoving());
            System.out.printf("\t* Watering: %d ml/week%n", flower.getGrowingTips().getWatering());
            System.out.printf("- Multiplying: %s%n", flower.getMultiplying());
            System.out.println();
        }
        System.out.println("=================");
    }

    private void parseOptions() {
        System.out.println("\n=== Parse Options ===");
        System.out.println("1. DOM");
        System.out.println("2. SAX");
        System.out.println("3. StAX");
        System.out.println("4. Return");
        System.out.println("===================");

        int choice = inputReader.readInt("Enter your choice:");
        switch (choice) {
            case 1:
                System.out.println("Parsing using DOM...");
                flowerList = domParser.parseFlowers(xmlFilePath);
                break;
            case 2:
                System.out.println("Parsing using SAX...");
                flowerList = saxParser.parseFlowers(xmlFilePath);
                break;
            case 3:
                System.out.println("Parsing using StAX...");
                flowerList = staxParser.parseFlowers(xmlFilePath);
                break;
            case 4:
                return;
            default:
                System.out.println("Invalid option. Returning to main menu.");
                return;
        }
        System.out.printf("Parsed successfully. %d flowers loaded.%n", flowerList.size());
    }

    private void sortOptions() {
        System.out.println("\n=== Sort Options ===");
        System.out.println("1. Name");
        System.out.println("2. Origin");
        System.out.println("3. Soil");
        System.out.println("4. Average Size");
        System.out.println("5. Temperature");
        System.out.println("6. Watering");
        System.out.println("7. Multiplying");
        System.out.println("8. Return");
        System.out.println("==================");

        int choice = inputReader.readInt("Enter your choice:");
        switch (choice) {
            case 1:
                Collections.sort(flowerList, FlowerComparators.byName());
                System.out.println("Sorted by name.");
                break;
            case 2:
                Collections.sort(flowerList, FlowerComparators.byOrigin());
                System.out.println("Sorted by origin.");
                break;
            case 3:
                Collections.sort(flowerList, FlowerComparators.bySoil());
                System.out.println("Sorted by soil.");
                break;
            case 4:
                Collections.sort(flowerList, FlowerComparators.byAverageSize());
                System.out.println("Sorted by average size.");
                break;
            case 5:
                Collections.sort(flowerList, FlowerComparators.byTemperature());
                System.out.println("Sorted by temperature.");
                break;
            case 6:
                Collections.sort(flowerList, FlowerComparators.byWatering());
                System.out.println("Sorted by watering.");
                break;
            case 7:
                Collections.sort(flowerList, FlowerComparators.byMultiplying());
                System.out.println("Sorted by multiplying method.");
                break;
            case 8:
                return;
            default:
                System.out.println("Invalid option. Returning to main menu.");
                return;
        }
    }

    private void transformOptions() {
        System.out.println("\n=== Transform Options ===");
        System.out.println("1. Origin");
        System.out.println("2. Soil");
        System.out.println("3. Return");
        System.out.println("=======================");

        int choice = inputReader.readInt("Enter your choice:");
        switch (choice) {
            case 1:
                System.out.println("Transforming by origin...");
                String outputFileOrigin = xslTransformer.transform(xmlFilePath, xslByOriginPath);
                System.out.printf("Transformation complete. Output file: %s%n", outputFileOrigin);
                break;
            case 2:
                System.out.println("Transforming by soil...");
                String outputFileSoil = xslTransformer.transform(xmlFilePath, xslBySoilPath);
                System.out.printf("Transformation complete. Output file: %s%n", outputFileSoil);
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid option. Returning to main menu.");
                return;
        }
    }
}