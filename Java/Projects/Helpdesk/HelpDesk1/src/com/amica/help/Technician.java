package com.amica.help;

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Represents a technician ont he staff of the help desk.
 *
 * @author Will Provost
 */
public class Technician implements Comparable<Technician> {

	private String ID;
	private String name;
	private int extension;
	private SortedSet<Ticket> activeTickets = new TreeSet<>();

	public Technician(String ID, String name, int extension) {
		this.ID = ID;
		this.name = name;
		this.extension = extension;
	}
  
	public String getID() {
		return ID;
	}

	public String getName() {
		return name;
	}

	public int getExtension() {
		return extension;
	}

	public SortedSet<Ticket> getActiveTickets() {
		return Collections.unmodifiableSortedSet(activeTickets);
	}
  
	public boolean addActiveTicket(Ticket ticket) {
		return activeTickets.add(ticket);
	}
	
	public boolean removeActiveTicket(Ticket ticket) {
		return activeTickets.remove(ticket);
	}
	
	@Override
	public String toString() {
		return String.format("Technician %s, %s", ID, name);
	}
  
	@Override
	public boolean equals(Object other) {
		if (other instanceof Technician) {
			return ID.equals(((Technician) other).getID());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return ID.hashCode();
	}
  
	public int compareTo(Technician other) {
		return ID.compareTo(other.getID());
	}
}
