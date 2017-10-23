package au.edu.uq.itee.comp3506.assn2.tests;

import au.edu.uq.itee.comp3506.assn2.entities.ADTs.AbstractBinaryTree;
import au.edu.uq.itee.comp3506.assn2.entities.ADTs.AvlTree;
import au.edu.uq.itee.comp3506.assn2.entities.ADTs.BinaryTree;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AvlTreeTest {
    AvlTree<Integer, Integer> tree;

    AbstractBinaryTree.Node<Integer, Integer> root;

    @Before
    public void setupTree() {
        tree = new AvlTree<>();
    }

    @Test
    public void restructure() {
        root = tree.add(1, 1);
        tree.add(2, 2);
        tree.add(3, 3);
        assertEquals("Root should equal 2", 2, (int) tree.getRoot().getKey());
        tree.add(4, 4);
        tree.add(5, 5);
        tree.add(6, 6);
        tree.add(7, 7);
        assertEquals("Root should equal 4", 4, (int) tree.getRoot().getKey());
    }
}
