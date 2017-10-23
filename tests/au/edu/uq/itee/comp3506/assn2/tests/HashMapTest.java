package au.edu.uq.itee.comp3506.assn2.tests;
import au.edu.uq.itee.comp3506.assn2.entities.ADTs.ProbeHashMap;
import org.junit.*;
import static org.junit.Assert.assertEquals;


public class HashMapTest {

    ProbeHashMap<String, Integer> map;

    @Before
    public void setupHashMap() {
        map = new ProbeHashMap<>(2);
    }


    @Test
    public void putInMapTest() {
        map.put("Hello", 0);
        assertEquals("Entry was not added to map.", 1, map.size());
        map.put("World", 1);
        assertEquals("Entry was not added to map.", 2, map.size());
        map.put("HelloWorld", 5);
        assertEquals("Map should have resized.", 3, map.size());
        assertEquals("Old value expected", 0, (int) map.put("Hello", 5));
        assertEquals("Old value expected", 5, (int) map.put("Hello", 10));
    }


    @Test
    public void putWithSameKeyHashCode() {
        ProbeHashMap<Integer, String> map = new ProbeHashMap<>();
        map.put(0, "Hello");
        assertEquals("Entry was not added to map.", 1, map.size());
        map.put(2, "World");
        assertEquals("Entry was not added to map.", 2, map.size());
        assertEquals("Old value expected", "Hello", map.put(0, "HelloWorld"));
        assertEquals("Old value expected", "HelloWorld", map.put(0, "HelloWorldWorld"));
    }


    @Test
    public void getMapTest() {
        map.put("Hello", 0);
        map.put("World", 1);

        assertEquals("Incorrect key found.", 0, (int) map.get("Hello"));
    }


    @Test
    public void isEmptyTest() {
        assertEquals("Map should be empty.", true, map.isEmpty());
        map.put("Hello", 0);
        assertEquals("Map shouldn't be empty", false, map.isEmpty());
    }


    @Test
    public void containsTest() {
        map.put("Hello", 0);
        assertEquals("Map should contain 'Hello'", true, map.contains("Hello"));
        assertEquals("Map should not contain 'World'", false, map.contains("World"));
    }

}
