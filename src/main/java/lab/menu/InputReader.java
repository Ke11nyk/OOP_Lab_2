package lab.menu;

import java.util.Scanner;

public class InputReader {
    private Scanner scanner;

    public InputReader() {
        this.scanner = new Scanner(System.in);
    }

    public int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input.");
            scanner.next();
        }
        return scanner.nextInt();
    }
}