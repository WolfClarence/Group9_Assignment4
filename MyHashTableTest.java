import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MyHashTableTest {

    // A generic test method for any Map implementation.
    // This method tests the performance of different Map implementations by measuring write and read times.
    public static void testMapPerformance(Map<Integer, Integer> map, String mapName) {
        int dataSize = 1_000_000;  // Set the number of operations to test
        Random random = new Random();  // Random object to generate random values for testing

        // Prepare test data: an array of keys and an array of corresponding values
        int[] keys = new int[dataSize];
        int[] values = new int[dataSize];
        for (int i = 0; i < dataSize; i++) {
            keys[i] = i;  // Use integers as keys (0 to 999,999)
            values[i] = random.nextInt(100000);  // Generate a random value for each key
        }

        // Measure write performance (insertion time)
        long startWrite = System.currentTimeMillis();  // Start time for write operations
        for (int i = 0; i < dataSize; i++) {
            map.put(keys[i], values[i]);  // Insert the key-value pair into the map
        }
        long endWrite = System.currentTimeMillis();  // End time for write operations

        // Measure read performance (lookup time)
        long startRead = System.currentTimeMillis();  // Start time for read operations
        for (int i = 0; i < dataSize; i++) {
            map.get(keys[i]);  // Retrieve the value associated with the key from the map
        }
        long endRead = System.currentTimeMillis();  // End time for read operations

        // Print the results for the given Map implementation
        System.out.println(mapName + " Write Time: " + (endWrite - startWrite) + " ms");
        System.out.println(mapName + " Read Time: " + (endRead - startRead) + " ms");
        System.out.println("---------------------------------------------");
    }

    public static void main(String[] args) {
        // Test with HashMap
        testMapPerformance(new HashMap<>(), "HashMap");

        // Test with LinkedHashMap
        testMapPerformance(new LinkedHashMap<>(), "LinkedHashMap");

        // Test with ConcurrentHashMap
        testMapPerformance(new ConcurrentHashMap<>(), "ConcurrentHashMap");

        // Test with Hashtable
        testMapPerformance(new Hashtable<>(), "Hashtable");

        // Test with MyHashTable (custom implementation)
        testMapPerformance(new MyHashTableWrapper<>(), "MyHashTable");
    }

    // Adapter class to make MyHashTable behave like a Map for testing purposes.
    // MyHashTable does not directly implement the Map interface, so we wrap it in this adapter.
    static class MyHashTableWrapper<K extends Comparable<K>, V> extends AbstractMap<K, V> {
        private final MyHashTable<K, V> myHashTable = new MyHashTable<>();  // Instance of MyHashTable

        // Implement the put method for MyHashTableWrapper. It delegates the put operation to MyHashTable.
        @Override
        public V put(K key, V value) {
            myHashTable.put(key, value);  // Use the put method of MyHashTable
            return null;  // We do not need to return the previous value for simplicity
        }

        // Implement the get method for MyHashTableWrapper. It delegates the get operation to MyHashTable.
        @Override
        public V get(Object key) {
            return myHashTable.get((K) key);  // Use the get method of MyHashTable
        }

        // EntrySet is not implemented for MyHashTableWrapper, so we throw an UnsupportedOperationException.
        @Override
        public Set<Entry<K, V>> entrySet() {
            throw new UnsupportedOperationException("EntrySet is not implemented for MyHashTableWrapper");
        }
    }
}
