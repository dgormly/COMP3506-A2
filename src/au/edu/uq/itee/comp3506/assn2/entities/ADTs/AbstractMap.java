package au.edu.uq.itee.comp3506.assn2.entities.ADTs;

public interface AbstractMap<K, V> {

    /**
     * Returns the amount of entities the map has.
     *
     * @return number of key-value pairs stored.
     */
    int size();

    /**
     * Checks if the Hashmap is empty.
     *
     * @return true if no key-value pairs in Map.
     */
    boolean isEmpty();


    /**
     * Retrieves a value associated with a key.
     *
     * @param key
     *      the key to search for.
     * @return
     *      Value associated with key.
     *      Null if key does not exist.
     */
    V get(K key);


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
    boolean contains(K key);


    /**
     * Stores a key-value pair.
     *
     * @param key
     *      Key to store
     * @param value
     *      Value to store
     * @return
     *      Old value associated with the key.
     *      Null otherwise.
     */
    V put(K key, V value);


    /**
     * Nested class: Key-Value pair.
     *
     * @param <K>
     *     Key to store.
     * @param <V>
     *     Value to store.
     */
    class MapEntry<K, V> {
        private K k;
        private V v;

        public MapEntry(K key, V value) {
            k = key;
            v = value;
        }

        public K getK() {
            return k;
        }

        public V getV() {
            return v;
        }

        public void setV(V v) {
            this.v = v;
        }
    }

}
