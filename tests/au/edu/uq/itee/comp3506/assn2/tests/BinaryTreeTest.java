package au.edu.uq.itee.comp3506.assn2.tests;

import au.edu.uq.itee.comp3506.assn2.entities.ADTs.AbstractBinaryTree.Node;
import au.edu.uq.itee.comp3506.assn2.entities.ADTs.BinaryTree;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BinaryTreeTest {

    BinaryTree<String> tree;
    Node<String> root;

    @Before
    public void setupTree() {
        tree = new BinaryTree<>();
        root = tree.addRoot("Hello");
        assertEquals("Root element incorrect.", "Hello", tree.getRoot().getElement());
        assertEquals("Expected tree size of 1.", 1, tree.size());
        assertEquals("Root already exists.", null, tree.addRoot("Fail"));
    }


    @Test
    public void addElementsTest() {
        Node<String> root = tree.getRoot();
        Node<String> left = tree.addLeft(root, "World");
        Node<String> right = tree.addRight(root, "Daniel");

        assertEquals("Wrong element returned for left child.", "World", root.getLeft().getElement());
        assertEquals("Wrong element returned for right child.", "Daniel", root.getRight().getElement());

        /* Check Parent. */
        assertEquals("Wrong parent found.", root, root.getLeft().getParent());

    }


    @Test
    public void removeNodeTest() {
        Node<String> toRemove = tree.addLeft(root, "1");
        Node<String> node1 = tree.addLeft(toRemove, "2");
        Node<String> node2 = tree.addLeft(node1, "3");
        Node<String> node3 = tree.addLeft(node2, "4");
        Node<String> node4 = tree.addLeft(node3, "5");
        Node<String> node5 = tree.addLeft(node4, "6");

        assertEquals("Wrong node returned on removal.", toRemove,tree.remove(toRemove));

        assertEquals("Tree restructured incorrectly.", node1, root.getLeft());
        assertEquals("Wrong parent found.", root, node1.getParent());
        assertEquals("Wrong child found.", node2, node1.getLeft());
    }


    @Test
    public void getNodeTest() {
        Node<String> left = tree.addLeft(root, "World");
        tree.addRight(root, "Daniel");
        tree.addRight(left, "Third Level");
        Node<String> node1 = tree.addLeft(root, "2");
        Node<String> node2 = tree.addLeft(node1, "3");
        Node<String> node3 = tree.addLeft(node2, "4");
        Node<String> node4 = tree.addLeft(node3, "5");
        Node<String> node5 = tree.addLeft(node4, "6");

        assertEquals("Expected node5.", node5, tree.get("6"));

    }
}
