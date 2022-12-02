package org.example;


import java.util.Date;

public class Time {

    private int Task_ID;

   private Date Timestamp_start;

    private Date Timestamp_end;

    public Time(int Task_ID, Date Timestamp_start, Date Timestamp_end) {
        this.Task_ID = Task_ID;
        this.Timestamp_start = Timestamp_start;
        this.Timestamp_end = Timestamp_end;
    }

    public int getTask_ID() {
        return Task_ID;
    }

    public Date getTimestamp_start() {
        return Timestamp_start;
    }

    public Date getTimestamp_end() {
        return Timestamp_end;
    }
}
