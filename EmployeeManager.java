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

        if (command.equals(Constants.LIST_EMPLOYEES)) {
            System.out.println("Loading data ...");
            String[] employees = readEmployees();
            if (employees != null) {
                for (String employee : employees) {
                    System.out.println(employee);
                }
            }
            System.out.println("Data Loaded.");

        } else if (command.equals(Constants.SHOW_RANDOM_EMPLOYEE)) {
            System.out.println("Loading data ...");
            String[] employees = readEmployees();
            if (employees != null) {
                Random random = new Random();
                int randomIndex = random.nextInt(employees.length);
                System.out.println(employees[randomIndex]);
            }
            System.out.println("Data Loaded.");

        } else if (command.startsWith(Constants.ADD_EMPLOYEE)) {
            System.out.println("Loading data ...");
            String newEmployee = command.substring(1);
            appendToFile(newEmployee);
            System.out.println("Data Loaded.");

        } else if (command.startsWith(Constants.FIND_EMPLOYEE)) {
            System.out.println("Loading data ...");
            String[] employees = readEmployees();
            
            if (employees != null) {
                String searchName = command.substring(1);

                if (Arrays.asList(employees).contains(searchName)) {
                    System.out.println("Employee found: " + searchName);
                } else {
                    System.out.println("Employee not found: " + searchName);
                }

            }

            System.out.println("Data Loaded.");
        } else if (command.contains(Constants.COUNT_EMPLOYEES)) {
            System.out.println("Loading data ...");
            String[] employees = readEmployees();
            if (employees != null) {
                String allText = String.join(",", employees);
                long wordCount = Arrays.stream(allText.split("\\s+"))
                                       .filter(s -> !s.isEmpty())
                                       .count();
                System.out.println(wordCount + " word(s) found " + allText.length());
            }
            System.out.println("Data Loaded.");
        }
         else if (command.startsWith(Constants.UPDATE_EMPLOYEE)) {
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

        } else if (command.contains(Constants.DELETE_EMPLOYEE)) {
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
