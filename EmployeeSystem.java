import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * EmployeeSystem is responsible for generating employee salary data,
 * saving it to a CSV file, loading it into a Map structure,
 * and performing salary adjustments.
 */
public class EmployeeSystem {

    /**
     * Generates random employee salary data and writes it to a CSV file.
     * Each employee is assigned an ID like "EMP00001" to "EMP10000" and
     * a random salary between 3000 and 30000.
     *
     * @param filePath The destination file path to save the CSV.
     * @param count The total number of employee records to generate.
     */
    public static void generateCSV(String filePath, int count) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("EmployeeID,Salary\n");  // Write CSV header

            for (int i = 1; i <= count; i++) {
                String employeeId = String.format("EMP%05d", i);
                double salary = 3000 + Math.random() * 27000;  // Salary: 3000 ~ 30000
                writer.write(employeeId + "," + String.format("%.2f", salary) + "\n");
            }

            System.out.println("CSV file has been generated successfully at: " + filePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads employee salary data from a CSV file into the provided Map.
     * Each line of the CSV is parsed into an EmployeeID and Salary pair.
     *
     * @param filePath The path to the CSV file.
     * @param map The Map structure (HashMap, LinkedHashMap, TreeMap, etc.) to store the data.
     */
    public static void loadFromCSV(String filePath, Map<String, Double> map) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine();// Skip CSV header
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    map.put(parts[0], Double.parseDouble(parts[1]));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Increases the salary of employees who earn less than 10,000 by 10%.
     * This simulates a salary adjustment business logic.
     *
     * @param map The Map containing employee salary data.
     */
    public static void increaseSalary(Map<String, Double> map) {
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            double salary = entry.getValue();
            if (salary < 10000) {
                map.put(entry.getKey(), salary * 1.1);  // Increase by 10%
            }
        }
    }
}
