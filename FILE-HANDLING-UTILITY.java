import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filePath = "example.txt";

        while (true) {
            System.out.println("\nFile Operations Menu:");
            System.out.println("1. Write to file");
            System.out.println("2. Read from file");
            System.out.println("3. Modify file");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter text to write to the file: ");
                    String content = scanner.nextLine();
                    writeFile(filePath, content);
                    break;

                case 2:
                    System.out.println("File contents:");
                    readFile(filePath);
                    break;

                case 3:
                    System.out.println("Enter the text to find: ");
                    String findText = scanner.nextLine();
                    System.out.println("Enter the replacement text: ");
                    String replaceText = scanner.nextLine();
                    modifyFile(filePath, findText, replaceText);
                    break;

                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to write to a file
    private static void writeFile(String filePath, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(content);
            writer.newLine();
            System.out.println("Content written to file.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Method to read from a file
    private static void readFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // Method to modify a file
    private static void modifyFile(String filePath, String findText, String replaceText) {
        Path path = Paths.get(filePath);
        try {
            List<String> lines = Files.readAllLines(path);
            List<String> modifiedLines = new ArrayList<>();

            for (String line : lines) {
                modifiedLines.add(line.replace(findText, replaceText));
            }

            Files.write(path, modifiedLines);
            System.out.println("File modified successfully.");
        } catch (IOException e) {
            System.out.println("Error modifying file: " + e.getMessage());
        }
    }
}
