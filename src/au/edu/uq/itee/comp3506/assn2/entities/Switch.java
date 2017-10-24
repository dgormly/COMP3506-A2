package au.edu.uq.itee.comp3506.assn2.entities;


/**
 * Custom switch class used to store basic ID and connection count information.
 *
 * Memory Runtime: O(n), where n is the number of switches.
 *
 * @author Daniel Gormly
 *
 */
public class Switch {

    private int id;
    private int count = 0;

    public Switch(int id) {
        this.id = id;
    }

    /**
     * Returns the ID associated with a switch.
     *
     * @return
     *      ID of switch.
     */
    public int getId() {
        return id;
    }


    /**
     * Returns the number if connections a switch has.
     *
     * @return
     *      Number of connections.
     */
    public int getCount() {
        return count;
    }

    /**
     * Increments the number of switches associated with a switch.
     */
    public void incrementCount() {
        count++;
    }
}
