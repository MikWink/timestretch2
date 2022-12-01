package org.example;

import java.sql.Timestamp;

public class Time {

    private int Task_ID;

   private Timestamp Timestamp_start;

    private Timestamp Timestamp_end;

    public Time(int Task_ID, Timestamp Timestamp_start, Timestamp Timestamp_end) {
        this.Task_ID = Task_ID;
        this.Timestamp_start = Timestamp_start;
        this.Timestamp_end = Timestamp_end;
    }

    public int getTask_ID() {
        return Task_ID;
    }

    public Timestamp getTimestamp_start() {
        return Timestamp_start;
    }

    public Timestamp getTimestamp_end() {
        return Timestamp_end;
    }
}
