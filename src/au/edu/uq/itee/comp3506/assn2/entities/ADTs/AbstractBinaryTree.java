package au.edu.uq.itee.comp3506.assn2.entities.ADTs;


/**
 * Binary Interface implements a tree where each node has two leafs.
 * This does not mean that the data will be sorted.
 *
 * Created for COMP3506 at the University of Queensland, 2017.
 *
 * @auther Daniel Gormly
 *
 * @param <K>
 *      Data type to organise the tree by.
 * @param <E>
 *     Data to be stored.
 */
public interface AbstractBinaryTree<K extends Comparable<? super K>, E> {

    Node<K, E> addRoot(K key, E element);


    Node<K, E> addLeft(Node<K, E> parent,K key, E element);


    Node<K, E> addRight(Node<K, E> parent, K key, E element);


    Node<K, E> getRoot();


    Node<K, E> get(K key);


    Node<K,E> set(Node<K, E> position, E element);


    int size();


    boolean isEmpty();


    /**
     * Class used to manage nodes in the tree.
     *
     * @param <E>
     *          Datatype to be stored in the tree.
     */
    class Node<K extends Comparable<? super K>, E> {
        protected Node<K, E> parent = null;
        protected Node<K, E> left = null;
        protected Node<K, E> right = null;
        protected K key;
        protected E element;
        protected int height = 0;


        Node(Node<K, E> parent, K key, E element) {
            this.key = key;
            this.parent = parent;
            this.element = element;
        }

        Node<K, E> getSibling() {
            if (parent == null) {
                return null;
            }
            if (parent.left == this) {
                return parent.right;
            } else {
                return parent.left;
            }
        }

        public Node<K, E> getParent() {
            return parent;
        }

        public Node<K, E> getLeft() {
            return left;
        }

        public Node<K, E> getRight() {
            return right;
        }

        public E getElement() {
            return element;
        }

        public K getKey() {
            return key;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        private Node<K, E> next = null;

        public Node<K, E> getNext(Node<K, E> node) {
            if (node.right != null) {
                node = node.right;
            } else {
                /* find next valid parent. */
                return node.parent;

            }

            /* Do recursion. */
            while (next == null) {
                recurseInOrder(node);
                if (next == null) {
                    node = node.parent;
                }
            }
            return next;
        }


        private void recurseInOrder(Node<K, E> node) {
            if (node == null) {
                return;
            }

            recurseInOrder(node.left);

            if (next == null) {
                next = node;
                return;
            }

            if (next != null) {
                return;
            }

            recurseInOrder(node.right);
        }

    }
}
