package au.edu.uq.itee.comp3506.assn2.tests;

import au.edu.uq.itee.comp3506.assn2.entities.ADTs.AbstractLinkedList;
import au.edu.uq.itee.comp3506.assn2.entities.ADTs.SinglyLinkedList;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

/**
 * Tests for LinkedList implemented by Lachlan Healey.
 *
 * @author leggy <l.healey@uq.edu.au>
 */
public class SinglyLinkedListTest {
    private SinglyLinkedList<String> list;


    @Before
    public void prepareList() {
        list = new SinglyLinkedList<>();

    }


    @Test
    public void addToList() {
        list.addToEnd("Hello");
        assertEquals("List should not be empty", false,  list.isEmpty());
        assertEquals("Wrong element expected.", "Hello", list.getElement());
        list.addToEnd("World");
        assertEquals("Wrong element expected.", "World", list.getElement());
        assertEquals("Wrong element found at end of the list.", "World", list.getLast());
        assertEquals("Wrong element found at start of the list.", "Hello", list.getFirst());
    }


    @Test
    public void removeFromListTest() {
        list.addToEnd("Hello");
        list.addToEnd("World");
        list.addToEnd("Daniel");
        list.remove();

        assertEquals("List is wrong size.", 2, list.length);
        assertEquals("Wrong element found.", "World", list.getElement());

        list.remove();
        list.remove();
        assertEquals("Expected null", null, list.getElement());
        assertEquals("Expected list to be size 0", 0, list.length);
        list.addToEnd("Hello");
        assertEquals("Wrong element found.", "Hello", list.getFirst());
    }


    @Test
    public void traverseListTest() {
        list.addToEnd("Hello");
        list.addToEnd("World");
        list.addToEnd("Daniel");

        assertEquals("Wrong element found.", "Hello", list.getFirst());
        assertEquals("Wrong element found.", "World", list.getNext());
        assertEquals("Wrong element found.", "Daniel", list.getNext());
        assertEquals("Wrong element found.", "Daniel", list.getNext());
        assertEquals("Wrong element found.", "Daniel", list.getLast());

    }
}
