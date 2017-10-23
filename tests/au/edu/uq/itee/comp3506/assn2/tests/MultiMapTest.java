package au.edu.uq.itee.comp3506.assn2.tests;

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
        map.put("Hello", 5, 5);
        map.put("Hello", 6, 6);
        assertEquals("Hello should have more than 2", map.getTree("Hello").size(), 5);
        assertEquals("Item not expected", (int) map.getTree("Hello").getFirst().getElement(), 1);
        assertEquals("Item not expected", (int) map.getTree("Hello").getLast().getElement(), 6);
        assertEquals("Item not expected", (int) map.getTree("Hello").getRoot().getElement(), 2);




    }

}
