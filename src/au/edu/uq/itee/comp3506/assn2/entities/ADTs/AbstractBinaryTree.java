package au.edu.uq.itee.comp3506.assn2.entities.ADTs;

public interface AbstractBinaryTree<E> {

    Node<E> addRoot(E element);


    Node<E> addLeft(Node<E> parent, E element);


    Node<E> addRight(Node<E> parent, E element);


    Node<E> getRoot();


    Node<E> get(E element);


    Node<E> set(Node<E> position, E element);


    Node<E> remove(Node<E> element);



    int size();

    boolean isEmpty();


    class Node<E> {
        protected Node<E> parent;
        protected Node<E> left = null;
        protected Node<E> right = null;
        protected E element;


        Node(Node<E> parent, E element) {
            this.parent = parent;
            this.element = element;
        }

        Node<E> getSibling() {
            if (parent == null) {
                return null;
            }
            if (parent.left == this) {
                return parent.right;
            } else {
                return parent.left;
            }
        }

        public Node<E> getParent() {
            return parent;
        }

        public Node<E> getLeft() {
            return left;
        }

        public Node<E> getRight() {
            return right;
        }

        public E getElement() {
            return element;
        }
    }


}