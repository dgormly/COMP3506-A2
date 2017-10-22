package au.edu.uq.itee.comp3506.assn2.entities.ADTs;

public class TreeMultiMap<K extends Comparable<? super K>,S extends Comparable<? super S>, V> {

    AbstractMap<K, AbstractBinaryTree<S, V>> map = new ProbeHashMap<>();
    int total = 0;

    public TreeMultiMap() {
    }


    public boolean isEmpty() {
        return total == 0;
    }

    public int size() {
        return total;
    }

    public void put(K key, S key2, V value) {
        AbstractBinaryTree<S, V> tree = map.get(key);
        if (tree == null) {
            tree = new AvlTree<>();
            map.put(key, tree);
        }
        tree.add(key2, value);
        total++;
    }


    public AbstractBinaryTree getTree(K key) {
        return map.get(key);
    }


    public V get(K key, S key2) {
        return map.get(key).get(key2).getElement();
    }

}
