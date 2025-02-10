package com.amica.help;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import com.amica.help.Ticket.Priority;
import com.amica.help.Ticket.Status;

/**
 * Implementation of the primary API. The help desk collects technicians
 * and tickets, cooreindates creation and lifecycle of tickets, and offers
 * query methods to get one or more tickets by a few useful criteria.
 * It also holds a tag manager, so that keyword tags assigned to tickets
 * are unique objects within the scope of this help desk instance.
 * 
 * @author Will Provost
 */
public class HelpDesk implements HelpDeskAPI {

	private int nextID = 0;
	private SortedSet<Technician> technicians = new TreeSet<>();
	private SortedSet<Ticket> tickets = new TreeSet<>();
	private Tags tags;

	public HelpDesk() {
		this(new Tags());
	}
	
	public HelpDesk(Tags tags) {
		this.tags = tags;
	}
	
	public Tags getTags() {
		return tags;
	}

	public void createTechnician(String ID, String name, int extension) {
		technicians.add(new Technician(ID, name, extension));
	}

	public int createTicket(String originator, String description, 
			Priority priority) {
		
		if (technicians.isEmpty()) {
			throw new IllegalStateException("No technicians available yet.");
		}
		Ticket ticket = new Ticket(++nextID, originator, description, priority);
		tickets.add(ticket);
		Technician assignee = null;
		for (Technician candidate : technicians) {
			if (assignee == null ||candidate.getActiveTickets().size() < 
					assignee.getActiveTickets().size()) {
				assignee = candidate;
			}
		}
		ticket.assign(assignee);
		return ticket.getID();
	}

	public void addNoteToTicket(int ID, String note) {
		getTicketByID(ID).addNote(note);
	}

	public void resolveTicket(int ID, String note) {
		getTicketByID(ID).resolve(note);
	}

	public void addTags(int ID, String... tagValues) {
		Ticket ticket = getTicketByID(ID);
		if (ticket != null) {
			for (String tagValue : tagValues) {
				ticket.addTag(tags.getTag(tagValue));
			}
		} else {
			throw new IllegalArgumentException("No ticket with ID " + ID);
		}
	}

	public SortedSet<Technician> getTechnicians() {
		return Collections.unmodifiableSortedSet(technicians);
	}

	public int reopenTicket(int priorTicketID, String reason, Priority priority) {
		if (technicians.isEmpty()) {
			throw new IllegalStateException("No technicians available yet.");
		}
		Ticket ticket = new ReopenedTicket(++nextID, 
				getTicketByID(priorTicketID), reason, priority);
		tickets.add(ticket);
		return ticket.getID();
	}

	public SortedSet<Ticket> getTickets() {
		return Collections.unmodifiableSortedSet(tickets);
	}

	public Ticket getTicketByID(int ID) {
		for (Ticket ticket : tickets) {
			if (ticket.getID() == ID) {
				return ticket;
			}
		}
		return null;
	}

	public List<Ticket> getTicketsByStatus(Status status) {
		List<Ticket> result = new ArrayList<>();
		for (Ticket ticket : tickets) {
			if (ticket.getStatus() == status) {
				result.add(ticket);
			}
		}
		return result;
	}

	public List<Ticket> getTicketsByNotStatus(Status status) {
		List<Ticket> result = new ArrayList<>();
		for (Ticket ticket : tickets) {
			if (ticket.getStatus() != status) {
				result.add(ticket);
			}
		}
		return result;
	}

	public List<Ticket> getTicketsByTechnician(String techID) {
		List<Ticket> result = new ArrayList<>();
		for (Ticket ticket : tickets) {
			if (ticket.getTechnician().getID().equals(techID)) {
				result.add(ticket);
			}
		}
		return result;
	}

	public List<Ticket> getTicketsWithAnyTag(String... tagValues) {
		List<Ticket> result = new ArrayList<>();
		for (Ticket ticket : tickets) {
			for (String tagValue : tagValues) {
				Tag tag = tags.getTag(tagValue);
				if (ticket.getTags().contains(tag)) {
					result.add(ticket);
					break;
				}
			}
		}
		return result;
	}

	public int getAverageMinutesToResolve() {
		List<Ticket> resolvedTickets = getTicketsByStatus(Status.RESOLVED);
		long total = 0;
		for (Ticket ticket : resolvedTickets) {
			total += ticket.getMinutesToResolve();
		}
		return (int) (total / resolvedTickets.size());
	}

	public Map<String, Integer> getAverageMinutesToResolvePerTechnician() {
		Map<String, Integer> result = new HashMap<>();
		for (Technician technician : technicians) {
			long total = 0;
			int count = 0;
			List<Ticket> tickets = getTicketsByTechnician(technician.getID());
			for (Ticket ticket : tickets) {
				if (ticket.getStatus() == Status.RESOLVED) {
					total += ticket.getMinutesToResolve();
					++count;
				}
			}
			if (count != 0) {
				result.put(technician.getID(), (int) (total / count));
			}
		}
		return result;
	}

	public List<Ticket> getTicketsByText(String text) {
		List<Ticket> result = new ArrayList<>();
		for (Ticket ticket : tickets) {
			if (ticket.includesText(text)) {
				result.add(ticket);
			}
		}
		return result;
	}
}
