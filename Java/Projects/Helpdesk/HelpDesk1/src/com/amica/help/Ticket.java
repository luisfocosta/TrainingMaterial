package com.amica.help;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Class representing a problem ticket for a help desk.
 *
 * @author Will Provost
 */
public class Ticket implements Comparable<Ticket> {

	public enum Status { CREATED, ASSIGNED, RESOLVED }
	public enum Priority { LOW, MEDIUM, HIGH, URGENT }

	private int ID;
	private Priority priority;
	private String originator;
	private String description;
	private Technician technician;
	private List<Event> history = new ArrayList<>();
	private SortedSet<Tag> tags = new TreeSet<>();

	public Ticket(int ID, String originator, String description, Priority priority) {
		this.ID = ID;
		this.priority = priority;
		this.originator = originator;
		this.description = description;
		this.history.add(new Event(Status.CREATED, "Created ticket."));
	}

	public Status getStatus() {
		ListIterator<Event> iterator = history.listIterator(history.size());
		while (iterator.hasPrevious()) {
			Status status = iterator.previous().getNewStatus();
			if (status != null) {
				return status;
			}
		}
		return null;
	}

	public int getID() {
		return ID;
	}

	public Priority getPriority() {
		return priority;
	}

	public String getOriginator() {
		return originator;
	}

	public String getDescription() {
		return description;
	}

	public Technician getTechnician() {
		return technician;
	}

	public List<Event> getHistory() {
		return Collections.unmodifiableList(history);
	}

	public SortedSet<Tag> getTags() {
		return Collections.unmodifiableSortedSet(tags);
	}

	public void assign(Technician technician) {
		if (this.technician != null) {
			this.technician.removeActiveTicket(this);
		}
			
		this.technician = technician;
		Status newStatus = Status.ASSIGNED;
		history.add(new Event(newStatus, "Assigned to " + technician + "."));
		technician.addActiveTicket(this);
	}

	public void addNote(String note) {
		history.add(new Event(note));
	}

	public void resolve(String reason) {
		if (getStatus() != Status.RESOLVED) {
			technician.removeActiveTicket(this);
			history.add(new Event(Status.RESOLVED, reason));
		} else {
			throw new IllegalStateException("Can't resolve a resolved ticket.");
		}
	}

	public boolean addTag(Tag tag) {
		return tags.add(tag);
	}

	public int getMinutesToResolve() {
		final int MILLISECONDS_PER_MINUTE = 60000;
		if (getStatus() == Status.RESOLVED) {
			long time = history.get(history.size() - 1).getTimestamp() - history.get(0).getTimestamp();
			return (int) time / MILLISECONDS_PER_MINUTE;
		} else {
			throw new IllegalStateException("The ticket is not yet resolved.");
		}
	}
    
	public boolean includesText(String text) {
		StringBuilder allText = new StringBuilder(getDescription());
		for (Event event : history) {
			allText.append(event.getNote());
		}
		return allText.toString().toLowerCase().contains(text.toLowerCase());
    }
    
	@Override
	public String toString() {
		return String.format("Ticket %d: %s priority, %s", 
				ID, priority.toString(), getStatus().toString());
	}
    
	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (other == null || !(other instanceof Ticket)) {
			return false;
		}
		return ID == ((Ticket) other).getID();
	}
	
	@Override
	public int hashCode() {
		return ID;
	}
    
	public int compareTo(Ticket other) {

	    if (equals(other)) {
	    	return 0;
	    }

		int result = -priority.compareTo(other.getPriority());
		if (result == 0) {
			result = Integer.compare(ID, other.getID());
		}
		return result;
	}
}
