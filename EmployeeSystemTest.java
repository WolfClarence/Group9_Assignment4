import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * EmployeeSystemTest is designed to test the performance of
 * various Hash-based Map implementations with the EmployeeSystem.
 */
public class EmployeeSystemTest {

    /**
     * Tests loading and salary adjustment performance for the given Map implementation.
     * Measures time taken for both loading CSV data and applying salary increases.
     *
     * @param map Map instance to test (HashMap, LinkedHashMap, etc.)
     * @param filePath CSV file path containing employee data.
     */
    public static void testMapPerformance(Map<String, Double> map, String filePath) {

        long startTime = System.currentTimeMillis();

        // Load employee salary data into the provided Map
        EmployeeSystem.loadFromCSV(filePath, map);

        // Apply salary increase to employees earning less than 10,000
        EmployeeSystem.increaseSalary(map);

        long endTime = System.currentTimeMillis();

        // Just to get the class name without prefix
        String[] ss = map.getClass().toString().split("\\.");
        String className = ss[ss.length - 1];
        System.out.println(className + " processing time: " + (endTime - startTime) + " ms");
    }

    public static void main(String[] args) {
        String filePath = "employee_salaries.csv";

        // Generate sample data
        //EmployeeSystem.generateCSV(filePath, 10000);

        // Test HashMap
        testMapPerformance(new HashMap<>(), filePath);

        // Test LinkedHashMap
        testMapPerformance(new LinkedHashMap<>(), filePath);

        // Test ConcurrentHashMap
        testMapPerformance(new ConcurrentHashMap<>(), filePath);

        // Test Hashtable
        testMapPerformance(new Hashtable<>(), filePath);
    }
}
