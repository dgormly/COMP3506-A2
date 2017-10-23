package au.edu.uq.itee.comp3506.assn2.tests;

import au.edu.uq.itee.comp3506.assn2.entities.ADTs.AvlTree;
import au.edu.uq.itee.comp3506.assn2.entities.ADTs.TreeMultiMap;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MultiMapTest {

    private TreeMultiMap<String, Integer, Integer> map = new TreeMultiMap<>();

    @Before
    public void setup() {
        map.put("Hello", 1, 1);
    }


    @Test
    public void addTest() {
        map.put("Hello", 2, 2);
        map.put("Hello", 3, 3);
        map.put("Hello", 4, 4);
        map.put("Hello", 5, 5);
        map.put("Hello", 6, 6);

        AvlTree<Integer, Integer> tree = map.getTree("Hello");

        assertEquals("Hello should have more than 2", map.getTree("Hello").size(), 6);
        assertEquals("Item not expected", 1, (int) map.getTree("Hello").getFirst().getElement());
        assertEquals("Item not expected", 6, (int) map.getTree("Hello").getLast().getElement());
        assertEquals("Item not expected", 3,(int) map.getTree("Hello").getRoot().getElement());




    }

}
