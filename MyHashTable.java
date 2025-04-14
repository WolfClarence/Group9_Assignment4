public class MyHashTable<K, V> {

    // Inner class to represent a node in the hash table
    // Each node holds a key, a value, and a reference to the next node in case of collisions (linked list).
    // A linked list is used here for simplicity in handling collisions, as it's a straightforward approach.
    private static class Node<K, V> {
        final K key;         // Key of the entry (immutable)
        V value;             // Value associated with the key
        Node<K, V> next;     // Reference to the next node in case of collision

        // Constructor to initialize a new node with the key and value
        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    // Default capacity for the hash table (65536 buckets)
    private static final int DEFAULT_CAPACITY = 1 << 16; // 65536 buckets (2^16)
    private Node<K, V>[] table;  // Array of nodes (buckets)

    // Constructor to initialize the hash table with the default capacity
    public MyHashTable() {
        table = new Node[DEFAULT_CAPACITY];  // Create an array of Node with the default capacity
    }

    // Hash function to map the key to an array index
    // This function ensures a uniform distribution of keys by applying a hash code transformation
    private int hash(K key) {
        // If the key is null, return 0 (handle the null case)
        return (key == null) ? 0 : (key.hashCode() ^ (key.hashCode() >>> 16)) & (DEFAULT_CAPACITY - 1);
    }

    // Method to insert a key-value pair into the hash table
    public void put(K key, V value) {
        // Calculate the index in the table using the hash function
        int index = hash(key);
        Node<K, V> head = table[index];  // Retrieve the head of the linked list at the calculated index

        // Traverse the linked list at this index to check if the key already exists
        // If the key exists, update its value
        for (Node<K, V> current = head; current != null; current = current.next) {
            if (current.key.equals(key)) {
                current.value = value;  // Update the value if the key matches
                return;
            }
        }

        // If the key doesn't exist, create a new node and insert it at the head of the linked list
        Node<K, V> newNode = new Node<>(key, value);  // Create a new node with the key and value
        newNode.next = head;  // Set the new node's next to the current head (if any)
        table[index] = newNode;  // Update the head of the linked list at the index
    }

    // Method to retrieve the value associated with the given key
    public V get(K key) {
        // Calculate the index in the table using the hash function
        int index = hash(key);
        Node<K, V> current = table[index];  // Retrieve the head of the linked list at the calculated index

        // Traverse the linked list at this index to find the key
        while (current != null) {
            // If the key matches, return the associated value
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;  // Move to the next node in the list
        }

        // If the key is not found, return null (key does not exist in the table)
        return null;
    }
}
