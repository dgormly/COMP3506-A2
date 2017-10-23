package au.edu.uq.itee.comp3506.assn2.entities.ADTs;


/**
 * A balanced implementation of a Binary tree allowing for a more consistant O(Logn)
 * insert and removal time.
 *
 * Created for COMP3506 Assignment 2 at the University Of Queensland.
 *
 * @author Daniel Gormly, Student Number: 43503348
 *
 * @param <E>
 *     Element type to store inside the nodes.
 */
public class AvlTree<K extends Comparable<? super K>, E> extends BinaryTree<K, E> implements AbstractBinaryTree<K, E> {


    /**
     * Returns the distance to the root node of the tree of a given node.
     *
     * @param node,
     *  Node to return the distance of from the root node.
     *
     * @return
     *      Number of edges to the top of the tree (root).
     *
     */
    private static int height(Node node) {
        return node != null ? node.height : 0;
    }


    /**
     * Checks whether the tree needs balancing. If it does it will call the required rotation.
     *
     * Runtime: O(1)
     *
     * @param root
     *      Node that may need rebalancing.
     * @return
     *      The given node after it has been rebalanced.
     *
     */
    private Node<K, E> balanceTree(Node<K, E> root) {
        int rHeight;
        int lHeight;
        if (height(root.left) - height(root.right) > 1) {

            lHeight = root.left == null ? 0 : height(root.left.left);
            rHeight = root.left == null ? 0 : height(root.left.right);

            if (lHeight > rHeight) {
                root = rotateRight(root);
            } else {
                rotateLeft(root.left);
                root = rotateRight(root);
            }

        } else if ((height(root.right) - height(root.left)) > 1) {

            lHeight = root.right == null ? 0 : height(root.right.right);
            rHeight = root.right == null ? 0 : height(root.right.left);

            if (lHeight > rHeight) {
                root = rotateLeft(root);
            } else {
                rotateRight(root.right);
                root = rotateLeft(root);
            }
        }
        return root;
    }


    /**
     * Inserts a node into the tree in a position that maintains the order.
     *
     * The element will be sorted by it's hashcode.
     *
     * Runtime: O(logn)
     *
     * @param element
     *      Element to be inserted.
     * @return
     *      The node after insertion.
     */
    @Override
    public Node<K, E> add(K key, E element) {
        Node<K, E> current;

        if (size() == 0) {
            return setRoot(key, element);
        }

        current = getRoot();
        while (!current.getKey().equals(key)) {
            if (key.compareTo(current.getKey()) < 0) {
                if (current.left != null) {
                    current = current.left;
                } else {
                    current.left = new Node<>(current, key, element);
                    current = current.left;
                }
            } else if (key.compareTo(current.getKey()) > 0) {
                if (current.right != null) {
                    current = current.right;
                } else {
                    current.right = new Node<>(current, key, element);
                    current = current.right;
                }
            } else {
                map.put(current.key, current);
                size++;
                return getRoot();
            }
        }


        do {
            current = current.parent;
            changeHeight(current);
            current = balanceTree(current);
        } while (current.parent != null);

        map.put(current.key, current);
        size++;
        return current;
    }


    /**
     * Does a tri-rotation to the left.
     *
     * Runtime: O(1)
     *
     * @param root,
     *      Parent node of the three nodes to rotate.
     *
     * @return
     *      Root node after the rotation has been applied.
     */
    private Node<K, E> rotateLeft(Node<K, E> root) {
        Node<K, E> node = root.right;

        if (root.parent != null) {
            // Check if left or right node.
            if (root.parent.right == root) {
                root.parent.right = node;
            } else {
                root.parent.left = node;
            }
        }
        node.parent = root.parent;
        root.parent = node;
        root.right = node.left;
        if (root.right != null) {
            root.right.parent = root;
        }
        node.left = root;

        /* Adjust heights for nodes from base. */
        changeHeight(root);
        changeHeight(node);
        return node;
    }



    /**
     * Does a tri-rotation to the right.
     *
     * Runtime: O(1)
     *
     * @param root
     *      Parent node of the three nodes to rotate.
     *
     * @return
     *      Root node after the reation has been applied.
     */
    public Node<K, E> rotateRight(Node<K, E> root) {
        Node<K, E> node = root.left;

        if (root.parent != null) {
            if (root.parent.left == root) {
                root.parent.left = node;
            } else {
                root.parent.right = node;
            }
        }

        node.parent = root.parent;
        root.parent = node;
        root.left = node.right;
        if (root.left != null) {
            root.left.parent = root;
        }

        node.right = root;

        /* Adjust heights for nodes from base. */
        changeHeight(root);
        changeHeight(node);
        return node;
    }


    /**
     * Increments the height the given node by one.
     *
     * Runtime: O(1)
     *
     * @param node
     *      Node to increment.
     *
     * @return
     *      New height of the given node.
     */
    private int changeHeight(Node<K, E> node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
        return node.height;
    }

}
