package au.edu.uq.itee.comp3506.assn2.entities;

import java.time.LocalDateTime;

public class Switch {

    private int id;
    private int Count = 0;
    private LocalDateTime timeStamp;

    public Switch(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }
}
