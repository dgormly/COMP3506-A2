package au.edu.uq.itee.comp3506.assn2.entities.ADTs;

public class AvlTree<E> extends BinaryTree<E> {

    public int getDepth(Node<E> node) {
        return 0;
    }


    public static int height(Node node) {
        return node != null ? node.height : 0;
    }



    public Node<E> balance(Node<E> root) {
        int rHeight = 0;
        int lHeight = 0;
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



    @Override
    public Node<E> add(E element) {

        Node<E> current = getRoot();
        while (!current.getElement().equals(element))
        {
            if (element.hashCode() < current.getElement().hashCode())
            {
                if (current.left != null) current = current.left;
                else
                {
                    current.left = new Node<>(current, element);
                    current = current.left;
                }
            }
            else if (element.hashCode() > current.getElement().hashCode())
            {
                if (current.right != null) current = current.right;
                else
                {
                    current.right = new Node<E>(current, element);
                    current = current.right;
                }
            }
            else return getRoot(); /* Value was in the tree, dumbass */
        }

        do {
            current = current.parent;
            changeHeight(current);
            current = balance(current);
        } while (current.parent != null);
        return current;
    }



    public Node<E> rotateLeft(Node<E> root) {
        Node<E> node = root.right;

        if (root.parent != null) {
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




    public Node<E> rotateRight(Node<E> root) {
        Node<E> node = root.left;

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





    public Node<E> insert(Node<E> node) {
        return null;
    }


    private void changeHeight(Node<E> node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }


    /* For testing purposes! */
    public void printTreeIndent(Node<E> node, int indent) {
        int ix;
        for (ix = 0; ix < indent; ix++) System.out.print(" ");
        if (node == null) System.out.print("Empty child\n");
        else
        {
            System.out.printf("node: %s; height: %d\n", node.element, node.height);
            printTreeIndent(node.left, indent + 4);
            printTreeIndent(node.right, indent + 4);
        }
    }

    public void printTree(Node<E> node)
    {
        printTreeIndent(node, 0);
    }

}
