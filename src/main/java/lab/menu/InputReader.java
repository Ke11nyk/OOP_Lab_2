package lab.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Scanner;

public class InputReader {
    private static final Logger logger = LoggerFactory.getLogger(InputReader.class);
    private final Scanner scanner;

    public InputReader() {
        this.scanner = new Scanner(System.in);
    }

    public int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt + " ");

                // Check if there's an integer input
                if (scanner.hasNextInt()) {
                    int input = scanner.nextInt();
                    logger.debug("Successfully read integer: {}", input);
                    return input;
                } else {
                    // Log the invalid input
                    String invalidInput = scanner.next();
                    logger.warn("Invalid input received: {}", invalidInput);
                    System.out.println("Invalid input. Please enter a number.");
                }
            } catch (Exception e) {
                // Log any unexpected exceptions
                logger.error("Error during input reading", e);
                System.out.println("An error occurred. Please try again.");

                // Clear the scanner to prevent infinite loop
                scanner.nextLine();
            }
        }
    }
}