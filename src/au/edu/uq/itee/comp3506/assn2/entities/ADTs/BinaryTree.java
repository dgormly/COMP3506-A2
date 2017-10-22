package au.edu.uq.itee.comp3506.assn2.entities.ADTs;







/**
 * Generic Binary tree Implementation of a Data Tree.
 * Each node has, at most, two child nodes and is sorted by the Comparable.
 * insert and removal time.
 *
 * Created for COMP3506 Assignment 2 at the University Of Queensland.
 *
 * @author Daniel Gormly, Student Number: 43503348
 *
 * @param <K>
 *     Key to order the tree by.
 * @param <E>
 *     Element type to store inside the nodes.
 */
public class BinaryTree<K extends Comparable<? super K>, E> implements AbstractBinaryTree<K, E> {

    private Node<K, E> root = null;
    private ProbeHashMap<K, Node<K, E>> map = new ProbeHashMap<>();

    private int size = 0;

    @Override
    public Node<K, E> addRoot(K key, E element) {
        if (size() > 0) {
            return null;
        } else {
            root = new Node<>(null, key, element);
            map.put(key, root);
            size++;
            return root;
        }
    }


    /**
     *
     *
     * @param element
     * @return
     */
    public Node<K, E> add(K key, E element) {
        Node<K, E> current = root;
        Node<K, E> newNode;
        while (true) {
            /* Add to the left if key less or equal to current. */
            if (key.compareTo(current.key) <= 0) {
                if (current.left == null) {
                    return addLeft(current, key, element);
                } else {
                    current = current.left;
                }
            } else {
                if (current.right == null) {
                    return addRight(current, key, element);
                } else {
                    current = current.right;
                }
            }
        }
    }

    @Override
    public Node<K, E> addLeft(Node<K, E> parent, K key, E element) {
        if (parent == null || parent.left != null) {
            return null;
        } else {
            Node<K, E> n = new Node<>(parent, key, element);
            parent.left = n;
            map.put(key, n);
            size++;
            return n;
        }
    }


    @Override
    public Node<K, E> addRight(Node<K, E> parent, K key, E element) {

        if (parent == null || parent.right != null) {
            return null;
        } else {
            Node<K, E> n = new Node<>(parent, key, element);
            parent.right = n;
            map.put(key, n);
            size++;
            return n;
        }
    }


    @Override
    public Node<K, E> getRoot() {
        return root;
    }


    @Override
    public Node<K, E> get(K key) {
        return map.get(key);
    }


    // TODO update hashmap to update keys.
    @Override
    public Node<K, E> set(Node<K, E> position, E element) {
        E e = position.element;
        position.element = element;
        return position;
    }


    @Override
    public int size() {
        return size;
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    public void printInOrder(Node<K, E> node) {

        if (node == null) {
            return;
        }

        printInOrder(node.left);

        System.out.println(node.getElement().toString());

        printInOrder(node.right);
    }

}
