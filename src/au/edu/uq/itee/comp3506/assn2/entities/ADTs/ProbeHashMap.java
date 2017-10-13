package au.edu.uq.itee.comp3506.assn2.entities.ADTs;


/**
 * Hashmap using the Linear-probing technique to store data into an array.
 * This class does not use any collections library.
 *
 * Map used to hold key-value pairs.
 *
 * Uses the hashcode value of the given key to store determine the position
 * in the array to store the key-value pair. If a key is already using a
 * given location it scans forward to the next available spot.
 *
 * Made for COMP3506 Assignment 2.
 *
 * Memory efficiency: O(n)
 *
 * @author Daniel Gormly - s4350334
 * @date 05/10/2017
 *
 * @param <K>
 *     Key Object to use as search key.
 * @param <V>
 *     Value Object to use as value key.
 *
 * TODO Implement auto-resizing and update tests.
 *
 */
public class ProbeHashMap<K, V> implements AbstractMap<K, V> {

    private int size = 1000; // Initial size of the array map to initialize.
    private int numEntries = 0; // Number of key-value pairs.

    private final MapEntry<K, V> DEFUNCT = new MapEntry<>(null, null); // Sentinel Entry.
    private MapEntry<K, V>[] map; // Map to store data.


    /**
     * Linear-probing Hashmap constructor.
     *
     */
    public ProbeHashMap() {
        map = new MapEntry[size];
    }


    /**
     * Returns the amount of entities the map has.
     *
     * Runtime Efficiency: O(1)
     *
     * @return number of key-value pairs stored.
     */
    @Override
    public int size() {
        return numEntries;
    }


    /**
     * Checks if the Hashmap is empty.
     *
     * Runtime Efficiency: O(1)
     *
     * @return true if no key-value pairs in Map.
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }


    /**
     * Retrieves a value associated with a key.
     *
     * Runtime Efficiency: Expected O(1)    |   Worst O(n)
     *
     * @param key
     *      the key to search for.
     * @return
     *      Value associated with key.
     *      Null if key does not exist.
     */
    @Override
    public V get(K key) {
        int pos = findAvailablePosition(key);
        if (pos == -1) {
            return null;
        } else {
            return map[pos].getV();
        }
    }


    /**
     * Stores a key-value pair.
     *
     * Runtime Efficiency: Expected O(1)    |   Worst O(n)
     *
     * @param key
     *      Key to store
     * @param value
     *      Value to store
     * @return
     *      Old value associated with the key.
     *      Null otherwise.
     */
    @Override
    public V put(K key, V value) {
        int pos = findAvailablePosition(key);
        if (pos == -1 && map[-pos] != DEFUNCT) {
            // TODO resizing here.
            return null;
        }
        if (pos < 0) {
            pos = -pos;
        }
        if (map[pos] == null) {
            MapEntry<K, V> entry = new MapEntry<>(key, value);
            map[pos] = entry;
            numEntries++;
            return value;
        } else {
            V val = map[pos].getV();
            map[pos].setV(value);
            numEntries++;
            return val;
        }
    }


    /**
     * Removes a key-value pair from the map.
     *
     * Runtime Efficiency: Expected O(1)    |   Worst O(n)
     *
     * @param key
     *      Key to search for.
     * @return
     *      Value removed from the map associated with given key.
     *      Null otherwise.
     */
    @Override
    public V remove(K key) {
        int pos = findAvailablePosition(key);

        if (pos < 0) {
            return null;
        }

        if (map[pos].getK().equals(key)) {
            V val = map[pos].getV();
            map[pos] = DEFUNCT;
            numEntries--;
            return val;
        }
        return null;
    }


    /**
     * Checks if a spot is available.
     *
     * Runtime Efficiency: O(1)
     *
     * @param pos
     *      Position to check.
     * @return
     *      True of a sentinal is in place or value is null.
     */
    private boolean isAvailable(int pos) {
        return map[pos] == DEFUNCT || map[pos] == null;
    }


    /**
     * Linear-probing process to find the next available spot in
     * the map.
     *
     * Runtime Efficiency: Expected O(1)    |   Worst O(n)
     *
     * @param key
     *      key used to find the initial position in the map.
     * @return
     *      Next available position.
     *      -1 if no spot is available
     *      Negative value if DEFUNCT is found.
     */
    private int findAvailablePosition(K key) {
        int home = key.hashCode() % size;
        int noSpots = -1;

        for (int i = 0; i < size; i++) {
            int pos = (home + i) % size;
            if (isAvailable(pos)) {
                if (map[pos] == null) {
                    return pos;
                } else {
                    noSpots = -pos;
                    continue;
                }
            }

            if (map[pos].getK().equals(key)) {
                return pos;
            }
        }
        return noSpots;
    }
}
