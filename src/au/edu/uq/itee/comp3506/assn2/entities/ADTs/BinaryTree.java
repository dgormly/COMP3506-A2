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
    protected ProbeHashMap<K, Node<K, E>> map = new ProbeHashMap<>();

    protected int size = 0;


    // TODO I don't think I need this
    protected Node<K, E> setRoot(K key, E element) {
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
        Node<K, E> current;

        if (size == 0) {
            setRoot(key, element);
            return root;
        } else {
            current = root;
        }

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


    public Node<K, E> getFrom(K key) {
        Node<K, E> firstNode = getRoot();
        while (true) {
            if (key.compareTo(firstNode.getKey()) < 0 && firstNode.getLeft() != null) {
                firstNode = firstNode.getLeft();
            } else if (key.compareTo(firstNode.getKey()) > 0 && firstNode.getRight() != null) {
                firstNode = firstNode.getRight();
            } else {
                return firstNode;
            }
        }
    }


    public Node<K, E> getTo(K key) {
        Node<K, E> firstNode = getRoot();
        while (true) {
            if (key.compareTo(firstNode.getKey()) < 0 && firstNode.getLeft() != null) {
                firstNode = firstNode.getLeft();
            } else if (key.compareTo(firstNode.getKey()) > 0 && firstNode.getRight() != null) {
                firstNode = firstNode.getRight();
            } else {
                return firstNode;
            }
        }
    }


    /**
     * Returns the right most element in the Array.
     *
     * @return
     */
    public Node<K, E> getFirst() {
        Node<K, E> current = getRoot();
        if (current == null) {
            return null;
        }

        while (current.left != null) {
            current = current.left;
        }
        return current;
    }


    public Node<K, E> getLast() {
        Node<K, E> current = getRoot();
        if (current == null) {
            return null;
        }

        while (current.right != null) {
            current = current.right;
        }
        return current;
    }

}
