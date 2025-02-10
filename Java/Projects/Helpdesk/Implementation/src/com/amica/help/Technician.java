package com.amica.help;
import java.util.ArrayList;
import com.amica.help.Ticket;

public class Technician {

    public String ID;
    public String name;
    public long extension;
    public int activeTicketCount;
    public int resolvedTicketCount;

    public Technician(String ID, String name, long extension, int activeTicketCount, int resolvedTicketCount) {
        this.ID = ID;
        this.name = name;
        this.extension = extension;
        this.activeTicketCount = activeTicketCount;
        this.resolvedTicketCount = resolvedTicketCount;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getExtension() {
        return extension;
    }

    public void setExtension(long extension) {
        this.extension = extension;
    }

    public int getActiveTicketCount() {
        return activeTicketCount;
    }

    public void setActiveTicketCount(int activeTicketCount) {
        this.activeTicketCount = activeTicketCount;
    }

    public int getResolvedTicketCount() {
        return resolvedTicketCount;
    }

    public void setResolvedTicketCount(int resolvedTicketCount) {
        this.resolvedTicketCount = resolvedTicketCount;
    }
}
