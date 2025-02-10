package com.amica.help;

import com.amica.help.Ticket;
import java.util.ArrayList;
import com.amica.help.Clock;

import static com.amica.help.Clock.getTime;

public class Event {
    public long ticket;
    public long timestamp;
    public String note;

    public Event(long ticket, long timestamp, String note) {
        this.ticket = ticket;
        this.timestamp = timestamp;
        this.note = note;
    }
}
