package lab.menu;

import lab.parsers.*;
import lab.FlowerItem;
import lab.comparator.FlowerComparators;
import lab.validation.xmlValidator;
import lab.transform.xslTransformer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private String xmlFilePath;
    private String xsdFilePath;
    private String xslByOriginPath;
    private String xslBySoilPath;
    List<FlowerItem> flowerList;

    public Menu() {
        this.scanner = new Scanner(System.in);
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

        String options = "\n1. Parse" +
                "\n2. Print flower list" +
                "\n3. Sort flower list" +
                "\n4. Transform XML" +
                "\n0. Exit\n";

        while (true) {
            System.out.println(options);

            int choice = readInt();
            switch (choice) {
                case 1:
                    parseOptions();
                    break;
                case 2:
                    if (flowerList.isEmpty()) {
                        System.out.println("Flower list is empty!");
                        break;
                    }
                    flowerList.forEach(plant -> System.out.println(plant + "\n"));
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

    private void parseOptions() {
        System.out.println("\nParse using:" +
                "\n1. DOM" +
                "\n2. SAX" +
                "\n3. StAX" +
                "\n4. Return\n");

        int choice = readInt();
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
        System.out.println("Parsed successfully. " + flowerList.size() + " flowers loaded.");
    }

    private void sortOptions() {
        System.out.println("\nSort by:" +
                "\n1. Name" +
                "\n2. Origin" +
                "\n3. Soil" +
                "\n4. Average Size" +
                "\n5. Temperature" +
                "\n6. Watering" +
                "\n7. Multiplying" +
                "\n8. Return\n");

        int choice = readInt();
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
        System.out.println("\nTransform by:" +
                "\n1. Origin" +
                "\n2. Soil" +
                "\n3. Return\n");

        int choice = readInt();
        switch (choice) {
            case 1:
                System.out.println("Transforming by origin...");
                String outputFileOrigin = xslTransformer.transform(xmlFilePath, xslByOriginPath);
                System.out.println("Transformation complete. Output file: " + outputFileOrigin);
                break;
            case 2:
                System.out.println("Transforming by soil...");
                String outputFileSoil = xslTransformer.transform(xmlFilePath, xslBySoilPath);
                System.out.println("Transformation complete. Output file: " + outputFileSoil);
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid option. Returning to main menu.");
                return;
        }
    }

    private int readInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}