package au.edu.uq.itee.comp3506.assn2.entities;

import au.edu.uq.itee.comp3506.assn2.entities.ADTs.AbstractMap;


public class HashMap<K, V> implements AbstractMap<K, V> {

    private int size;
    private int numEntries = 0;

    private final MapEntry<K, V> DEFUNCT = new MapEntry<>(null, null);
    private MapEntry<K, V>[] map;

    public HashMap(int size) {
        this.size = size;
        map = new MapEntry[size];
    }


    @Override
    public int size() {
        return numEntries;
    }


    @Override
    public boolean isEmpty() {
        return size() == 0;
    }


    @Override
    public V get(K key) {
        int pos = findAvailablePosition(key);
        if (pos == -1) {
            return null;
        } else {
            return map[pos].getV();
        }
    }


    @Override
    public V put(K key, V value) {
        int pos = findAvailablePosition(key);
        if (pos == -1 && map[-pos] != DEFUNCT) {
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


    private boolean isAvailable(int pos) {
        return map[pos] == DEFUNCT || map[pos] == null;
    }


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


    private class MapEntry<K, V> {
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
