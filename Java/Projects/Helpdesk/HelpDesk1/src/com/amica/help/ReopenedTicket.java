package com.amica.help;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * A specialized ticket that represents a reopened ticket by holding a
 * reference to the prior ticket (which could itself be a reopened ticket)
 * and takes that ticket into account in some of its behaviors.
 */
public class ReopenedTicket extends Ticket {

	private Ticket priorTicket;
  
	public ReopenedTicket(int ID, Ticket priorTicket, 
			String reason, Priority priority) {
		super(ID, priorTicket.getOriginator(), reason, priority);
		this.priorTicket = priorTicket;
		assign(priorTicket.getTechnician());
	}
  
	public Ticket getPriorTicket() {
		return priorTicket;
	}
  
	@Override
	public List<Event> getHistory() {
		ArrayList<Event> history = new ArrayList<>();
		history.addAll(priorTicket.getHistory());
		history.addAll(super.getHistory());
		return history;
	}
  
	@Override
	public SortedSet<Tag> getTags() {
		SortedSet<Tag> result = new TreeSet<>();
		result.addAll(super.getTags());
		result.addAll(priorTicket.getTags());
		return result;
	}
  
	@Override
	public boolean includesText(String text) {
		return super.includesText(text) || priorTicket.includesText(text);
	}
}
