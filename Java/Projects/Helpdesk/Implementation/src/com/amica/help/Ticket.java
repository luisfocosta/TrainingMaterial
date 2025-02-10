package com.amica.help;
import com.amica.help.Clock;
import com.amica.help.Event;
import com.amica.help.Technician;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

import static com.amica.help.Clock.getTime;

/**
 * Class representing a problem ticket for a help desk.
 *
 * @author Will Provost
 */
public class Ticket {

	public enum Status { CREATED, ASSIGNED, RESOLVED }
	public enum Priority { LOW, MEDIUM, HIGH, URGENT }

	public int ID;

	public String originator;
	public String description;
	public Ticket.Priority priority;
	public Ticket.Status status;
	public String technicianID;
	public long created;
	public long resolved;
	public int minutesResolved;
	ArrayList<Events> eventsList = new ArrayList<Events>();
	SortedSet<String> tagList = new TreeSet<>();


	int previousTicketID;

	public Ticket(int ID, String originator, String description, long created, Priority priority) {
		this.ID = ID;
		this.originator = originator;
		this.description = description;
		this.technicianID = technicianID;
		this.created = created;
		this.priority = priority;
	}

	public long getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}
	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getTechnicianID() {
		return technicianID;
	}

	public void setTechnicianID(String technicianID) {
		this.technicianID = technicianID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public long getResolved() {
		return resolved;
	}

	public void setResolved(long resolved) {
		this.resolved = resolved;
	}

	public int getMinutesResolved() {
		return minutesResolved;
	}

	public long getCreated() {
		return created;
	}

	public void setCreated(long created) {
		this.created = created;
	}

	public void setMinutesResolved(long minutesResolved) {
		this.minutesResolved = (int) minutesResolved;
	}
	public ArrayList<Events> getEventsList() {
		return eventsList;
	}

	public void setEventsList(ArrayList<Events> eventsList) {
		this.eventsList = eventsList;
	}

	public SortedSet<String> getTagList() {
		return tagList;
	}

	public void setTagList(SortedSet<String> tagList) {
		this.tagList = tagList;
	}

	public int getPreviousTicketID() {
		return previousTicketID;
	}

	public void setPreviousTicketID(int previousTicketID) {
		this.previousTicketID = previousTicketID;
	}

	public String getOriginator() {
		return originator;
	}

	public void setOriginator(String originator) {
		this.originator = originator;
	}

	public void setMinutesResolved(int minutesResolved) {
		this.minutesResolved = minutesResolved;
	}
}
