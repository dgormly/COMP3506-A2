package au.edu.uq.itee.comp3506.assn2.entities;


public class Switch {

    private int id;
    private int count = 0;

    public Switch(int id, int count) {
        this.id = id;
        this.count = count;
    }

    public Switch(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getCount() {
        return count;
    }


    public void setCount(int count) {
        this.count = count;
    }

    public void incrementCount() {
        count++;
    }
}
