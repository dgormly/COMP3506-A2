package au.edu.uq.itee.comp3506.assn2.tests;

import au.edu.uq.itee.comp3506.assn2.entities.ADTs.AbstractBinaryTree.Node;
import au.edu.uq.itee.comp3506.assn2.entities.ADTs.BinaryTree;
import au.edu.uq.itee.comp3506.assn2.entities.CallRecord;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BinaryTreeTest {

    BinaryTree<String, String> tree;
    Node<String, String> root;

    @Before
    public void setupTree() {
        tree = new BinaryTree<>();
        root = tree.add("C", "C");
        assertEquals("Root element incorrect.", "C", tree.getRoot().getElement());
        assertEquals("Expected tree size of 1.", 1, tree.size());
    }


    @Test
    public void addElementsTest() {
        Node<String, String> root = tree.getRoot();
        Node<String, String> left = tree.addLeft(root, "World", "World");
        Node<String, String> right = tree.addRight(root, "Daniel", "Daniel");

        assertEquals("Wrong element returned for left child.", "World", root.getLeft().getElement());
        assertEquals("Wrong element returned for right child.", "Daniel", root.getRight().getElement());

        /* Check Parent. */
        assertEquals("Wrong parent found.", root, root.getLeft().getParent());

    }


    @Test
    public void getNodeTest() {
        Node<String, String> left = tree.addLeft(root, "World", "World");
        tree.addRight(root, "Daniel", "Daniel");
        tree.addRight(left, "Third Level", "Third Level");
        Node<String, String> node1 = tree.addLeft(root, "2", "2");
        Node<String, String> node2 = tree.addLeft(node1, "3", "3");
        Node<String, String> node3 = tree.addLeft(node2, "4", "4");
        Node<String, String> node4 = tree.addLeft(node3, "5", "5");
        Node<String, String> node5 = tree.addLeft(node4, "6", "6");

        assertEquals("Expected node5.", node5, tree.get("6"));

    }


    @Test
    public void binaryAddTest() {
        tree.add("B", "B");
        tree.add("D", "D");

        assertEquals("Right child should be D", "D", tree.getRoot().getRight().getElement());
        assertEquals("Left child should be B", "B", tree.getRoot().getLeft().getElement());

    }


    @Test
    public void traverseTree() {

        List<String> list = new ArrayList<>();

        tree.add("B", "B");
        tree.add("A", "A");
        tree.add("D", "D");
        tree.add("E", "E");
        tree.add("F","F");
        tree.add("G", "G");
        tree.add("H", "H");
        tree.add("I", "I");

        Node<String, String> firstNode = tree.get("B");
        Node<String, String> lastNode = tree.get("G");

        while (firstNode != lastNode) {
            list.add(firstNode.getElement());
            firstNode = firstNode.getNext(firstNode);
        }


        for (String s : list) {
            System.out.println(s);
        }

    }

    @Test
    public void traverseTree2() {

        List<String> list = new ArrayList<>();

        tree.add("B", "B");
        tree.add("A", "A");
        tree.add("D", "D");
        tree.add("F","F");
        tree.add("G", "G");
        tree.add("E", "E");
        tree.add("H", "H");
        tree.add("I", "I");

        String key1 = "A";
        String key2 = "E";

        Node<String, String> firstNode = tree.getRoot();
        Node<String, String> lastNode = tree.getRoot();

        while (true) {
            if (key1.compareTo(firstNode.getKey()) < 0 && firstNode.getLeft() != null) {
                firstNode = firstNode.getLeft();
            } else if (key1.compareTo(firstNode.getKey()) > 0 && firstNode.getRight() != null) {
                firstNode.getRight();
            } else {
                break;
            }
        }

        while (true) {
            if (key2.compareTo(lastNode.getKey()) < 0 && lastNode.getLeft() != null) {
                lastNode = lastNode.getLeft();
            } else if (key2.compareTo(lastNode.getKey()) > 0 && lastNode.getRight() != null) {
                lastNode = lastNode.getRight();
            } else {
                break;
            }
        }

        while (firstNode != lastNode) {
            list.add(firstNode.getElement());
            firstNode = firstNode.getNext(firstNode);
        }
        list.add(lastNode.getElement());


        for (String s : list) {
            System.out.println(s);
        }
    }


    @Test
    public void getFirstTest() {

        tree.add("B", "B");
        tree.add("A", "A");
        tree.add("D", "D");
        tree.add("F","F");
        tree.add("G", "G");
        tree.add("E", "E");
        tree.add("H", "H");
        tree.add("I", "I");



        assertEquals("Incorrect first Item found", "A", tree.getFirst().getElement());
        assertEquals("Incorrect last Item found", "I", tree.getLast().getElement());


    }



}
