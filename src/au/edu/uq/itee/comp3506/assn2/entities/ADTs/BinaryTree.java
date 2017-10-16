package au.edu.uq.itee.comp3506.assn2.entities.ADTs;

public class BinaryTree<E> implements AbstractBinaryTree<E> {

    private Node<E> root = null;
    private ProbeHashMap<E, Node<E>> map = new ProbeHashMap<>();

    private int size = 0;

    @Override
    public Node<E> addRoot(E element) {
        if (size() > 0) {
            return null;
        } else {
            root = new Node<>(null, element);
            map.put(element, root);
            size++;
            return root;
        }
    }


    /**
     *
     * @param element
     * @return
     */
    public Node<E> add(E element) {
        Node<E> current = root;
        Node<E> newNode;
        int hash = element.hashCode();
        while (true) {
            if (hash < current.getElement().hashCode()) {
                if (current.left == null) {
                    newNode = new Node<>(current, element);
                    current.left = newNode;
                    map.put(element, newNode);
                    size++;
                    return newNode;
                } else {
                    current = current.left;
                }
            } else {
                if (current.right == null) {
                    newNode = new Node<E>(current, element);
                    current.right = newNode;
                    map.put(element, newNode);
                    size++;
                    return newNode;
                } else {
                    current = current.right;
                }
            }
        }
    }

    @Override
    public Node<E> addLeft(Node<E> parent, E element) {
        if (parent == null || parent.left != null) {
            return null;
        } else {
            Node<E> n = new Node<>(parent, element);
            parent.left = n;
            map.put(element, n);
            size++;
            return n;
        }
    }


    @Override
    public Node<E> addRight(Node<E> parent, E element) {

        if (parent == null || parent.right != null) {
            return null;
        } else {
            Node<E> n = new Node<>(parent, element);
            parent.right = n;
            map.put(element, n);
            size++;
            return n;
        }
    }


    @Override
    public Node<E> getRoot() {
        return root;
    }


    @Override
    public Node<E> get(E element) {
        return map.get(element);
    }


    // TODO update hashmap to update keys.
    @Override
    public Node<E> set(Node<E> position, E element) {
        E e = position.element;
        position.element = element;
        return position;
    }


    @Override
    public Node<E> remove(Node<E> position) {
        if (position == null || position.parent == null) {
            return null;
        } else {
            if (position.parent.left == position) {
                position.parent.left = position.left;
                position.left.parent = position.parent;
            } else {
                position.parent.right = position.left;
                position.left.parent = position.parent;
            }
            map.remove(position.element);
            size--;
            return position;
        }
    }


    @Override
    public int size() {
        return size;
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    public void printInOrder(Node<E> node) {

        if (node == null) {
            return;
        }

        printInOrder(node.left);

        System.out.println(node.getElement().toString());

        printInOrder(node.right);
    }

}
