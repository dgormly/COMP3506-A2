package au.edu.uq.itee.comp3506.assn2.entities.ADTs;

public interface AbstractMultiMap<K, V> {

    AbstractLinkedList<K> get(K key);

    void put(K key, V value);

    V remove(K key, V value);



}
