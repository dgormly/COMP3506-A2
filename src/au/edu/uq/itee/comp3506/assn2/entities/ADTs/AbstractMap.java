package au.edu.uq.itee.comp3506.assn2.entities.ADTs;

public interface AbstractMap<K, V> {
    int size();

    boolean isEmpty();

    V get(K key);

    V put(K key, V value);

    V remove(K key);


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

        public void setK(K k) {
            this.k = k;
        }

        public V getV() {
            return v;
        }

        public void setV(V v) {
            this.v = v;
        }
    }

}
