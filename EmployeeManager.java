import java.io.*;
import java.util.*;

public class EmployeeManager {

    private static final String FILE_NAME = "employees.txt";

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Error: Provide an argument (l, s, +, ?, c, u, d).");
            return;
        }

        String command = args[0];

        if (command.equals("l")) {
            System.out.println("Loading data ...");
            String[] employees = readEmployees();
            if (employees != null) {
                for (String employee : employees) {
                    System.out.println(employee);
                }
            }
            System.out.println("Data Loaded.");

        } else if (command.equals("s")) {
            System.out.println("Loading data ...");
            String[] employees = readEmployees();
            if (employees != null) {
                Random random = new Random();
                int randomIndex = random.nextInt(employees.length);
                System.out.println(employees[randomIndex]);
            }
            System.out.println("Data Loaded.");

        } else if (command.startsWith("+")) {
            System.out.println("Loading data ...");
            String newEmployee = command.substring(1);
            appendToFile(newEmployee);
            System.out.println("Data Loaded.");

        } else if (command.startsWith("?")) {
            System.out.println("Loading data ...");
            String[] employees = readEmployees();
            if (employees != null) {
                String searchName = command.substring(1);
                boolean found = false;
                for (String employee : employees) {
                    if (employee.equals(searchName)) {
                        System.out.println("Employee found!");
                        found = true;
                        break;
                    }
                }
            }
            System.out.println("Data Loaded.");

        } else if (command.contains("c")) {
            System.out.println("Loading data ...");
            String[] employees = readEmployees();
            if (employees != null) {
                String allText = String.join(",", employees);
                char[] characters = allText.toCharArray();
                boolean inWord = false;
                int wordCount = 0;
                for (char c : characters) {
                    if (c == ' ') {
                        if (!inWord) {
                            wordCount++;
                            inWord = true;
                        } else {
                            inWord = false;
                        }
                    }
                }
                System.out.println(wordCount + " word(s) found " + characters.length);
            }
            System.out.println("Data Loaded.");

        } else if (command.startsWith("u")) {
            System.out.println("Loading data ...");
            String[] employees = readEmployees();
            if (employees != null) {
                String nameToUpdate = command.substring(1);
                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].equals(nameToUpdate)) {
                        employees[i] = "Updated";
                    }
                }
                writeToFile(employees);
            }
            System.out.println("Data Updated.");

        } else if (command.contains("d")) {
            System.out.println("Loading data ...");
            String[] employees = readEmployees();
            if (employees != null) {
                String nameToDelete = command.substring(1);
                List<String> employeeList = new ArrayList<>(Arrays.asList(employees));
                employeeList.remove(nameToDelete);
                writeToFile(employeeList.toArray(new String[0]));
            }
            System.out.println("Data Deleted.");
        }
    }

    // Reads employees.txt and returns array of employee names
    private static String[] readEmployees() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line = reader.readLine();
            if (line != null && !line.isEmpty()) {
                return line.split(",");
            }
        } catch (Exception e) {
            System.out.println("Error reading file.");
        }
        return null;
    }

    private static void writeToFile(String[] employees) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write(String.join(",", employees));
        } catch (Exception e) {
            System.out.println("Error writing file.");
        }
    }

    private static void appendToFile(String employee) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write("," + employee);
        } catch (Exception e) {
            System.out.println("Error writing file.");
        }
    }
}
