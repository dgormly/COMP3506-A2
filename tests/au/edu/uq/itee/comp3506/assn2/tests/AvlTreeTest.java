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
        root = tree.addRoot(44, 44);
        tree.add(17, 17);
        tree.add(32, 32);
        //tree.add(44);
        tree.add(48, 48);
        tree.add(50, 50);
        //tree.add(62);
        tree.add(78, 79);
        tree.add(88, 88);
//
//        tree.add(2);
//        tree.add(3);
//        tree.add(4);
//        tree.add(5);

//        tree.printTreeIndent(tree.getRoot(), 0);


    }
}
