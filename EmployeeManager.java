import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Error: Provide an argument (l, s, +, ?, c, u, d).");
            return;
        }

        String command = args[0];

        if (command.equals("l")) {

            System.out.println("Loading data ...");

            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")
                        )
                );
                String line = reader.readLine();
                String[] employees = line.split(",");

                for (String employee : employees) {
                    System.out.println(employee);
                }

            } catch (Exception ex) {
            }

            System.out.println("Data Loaded.");

        } else if (command.equals("s")) {

            System.out.println("Loading data ...");

            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")
                        )
                );
                String line = reader.readLine();
                String[] employees = line.split(",");
                Random random = new Random();
                int randomIndex = random.nextInt(employees.length);
                System.out.println(employees[randomIndex]);

            } catch (Exception ex) {
            }

            System.out.println("Data Loaded.");

        } else if (command.startsWith("+")) {

            System.out.println("Loading data ...");

            try {
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter("employees.txt", true)
                );
                String newEmployee = command.substring(1);
                writer.write("," + newEmployee);
                writer.close();

            } catch (Exception ex) {
            }

            System.out.println("Data Loaded.");

        } else if (command.startsWith("?")) {

            System.out.println("Loading data ...");

            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")
                        )
                );
                String line = reader.readLine();
                String[] employees = line.split(",");
                boolean found = false;
                String searchName = command.substring(1);

                for (int i = 0; i < employees.length && !found; i++) {
                    if (employees[i].equals(searchName)) {
                        System.out.println("Employee found!");
                        found = true;
                    }
                }

            } catch (Exception ex) {
            }

            System.out.println("Data Loaded.");

        } else if (command.contains("c")) {

            System.out.println("Loading data ...");

            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")
                        )
                );
                String line = reader.readLine();
                char[] characters = line.toCharArray();
                boolean inWord = false;
                int wordCount = 0;

                for (char character : characters) {
                    if (character == ' ') {
                        if (!inWord) {
                            wordCount++;
                            inWord = true;
                        } else {
                            inWord = false;
                        }
                    }
                }

                System.out.println(wordCount + " word(s) found " + characters.length);

            } catch (Exception ex) {
            }

            System.out.println("Data Loaded.");

        } else if (command.startsWith("u")) {

            System.out.println("Loading data ...");

            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")
                        )
                );
                String line = reader.readLine();
                String[] employees = line.split(",");
                String nameToUpdate = command.substring(1);

                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].equals(nameToUpdate)) {
                        employees[i] = "Updated";
                    }
                }

                BufferedWriter writer = new BufferedWriter(
                        new FileWriter("employees.txt")
                );
                writer.write(String.join(",", employees));
                writer.close();

            } catch (Exception ex) {
            }

            System.out.println("Data Updated.");

        } else if (command.contains("d")) {

            System.out.println("Loading data ...");

            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("employees.txt")
                        )
                );
                String line = reader.readLine();
                String[] employees = line.split(",");
                String nameToDelete = command.substring(1);
                List<String> employeeList = new ArrayList<>(Arrays.asList(employees));
                employeeList.remove(nameToDelete);

                BufferedWriter writer = new BufferedWriter(
                        new FileWriter("employees.txt")
                );
                writer.write(String.join(",", employeeList));
                writer.close();

            } catch (Exception ex) {
            }

            System.out.println("Data Deleted.");

        }
    }
}
