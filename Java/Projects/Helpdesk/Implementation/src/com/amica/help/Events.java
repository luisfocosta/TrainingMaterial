package com.amica.help;

import com.amica.help.Ticket;
import java.util.ArrayList;
import com.amica.help.Clock;

import static com.amica.help.Clock.getTime;

public class Events {
    public long timestamp;
    public String note;

    public Events(long ticket, long timestamp, String note) {
        this.timestamp = timestamp;
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
