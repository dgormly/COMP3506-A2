package au.edu.uq.itee.comp3506.assn2.entities.ADTs;


/**
 * Hash-map using the Linear-probing technique to store data into an array.
 * This class does not use any collections library.
 *
 * Map used to hold key-value pairs.
 *
 * Uses the hashcode value of the given key to store determine the position
 * in the array to store the key-value pair. If a key is already using a
 * given location it scans forward to the next available spot.
 *
 * To maintain efficiency, this map resizes when it reaches 75% full.
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
 */
public class ProbeHashMap<K, V> implements AbstractMap<K, V> {

    private int arraySize = 1000; // Initial size of the array map to initialize.
    private int numEntries = 0; // Number of key-value pairs.

    private MapEntry<K, V>[] map; // Map to store data.


    /**
     * Linear-probing Hashmap constructor.
     */
    public ProbeHashMap() {
        map = new MapEntry[arraySize];
    }


    public ProbeHashMap(int size) {
        arraySize = size;
        map = new MapEntry[arraySize];
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
     * Checks if the map contains a given key.
     *
     * @param key
     *  Key to search for.
     *
     * @return
     *      true if key is found,
     *      false otherwise.
     */
    @Override
    public boolean contains(K key) {
        int pos = key.hashCode();
        for (int i = 0; i < map.length; i++) {
            if (map[(pos + i) % map.length] == null) {
                return false;
            }
            if (map[(pos + i) % map.length].getK().equals(key)) {
                return true;
            }
        }
        return false;
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
        int pos = findKey(key);
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
        if (findKey(key) != -1) {
            int pos = findKey(key);
            map[pos].setV(value);
        }

        if (numEntries > map.length * 0.75) {
            resize();
        }
        int pos = findAvailablePosition(key);
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
        return map[pos] == null;
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
        int home = Math.abs(key.hashCode() % map.length);
        int noSpots = -1;

        for (int i = 0; i < map.length; i++) {
            int pos = (home + i) % map.length;
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


    /**
     * Returns the location of a given key.
     *
     * Starts with the initial hash position, searching
     * linear until found.
     *
     * Runtime Efficiency: Expected O(1)    |   Worst O(n)
     *
     * @param key
     *      Key to look find.
     * @return
     *      Position of key inside the array.
     */
    private int findKey(K key) {
        int home = key.hashCode() % map.length;

        for (int i = 0; i < map.length; i++) {
            int pos = Math.abs((home + i) % map.length);
            if (map[pos] != null && map[pos].getK().equals(key)) {
                return pos;
            }
        }
        return -1;
    }

    /**
     * Resize map to 2x current map size and copy key-values over.
     */
    private void resize() {
        arraySize *= 2;
        MapEntry<K, V>[] newMap = new MapEntry[arraySize];
        MapEntry<K, V>[] temp = map;
        map = newMap;
        numEntries = 0;
        for (MapEntry<K, V> entry : temp) {
            if (entry == null) {
                continue;
            }
            put(entry.getK(), entry.getV());
        }
    }
}
