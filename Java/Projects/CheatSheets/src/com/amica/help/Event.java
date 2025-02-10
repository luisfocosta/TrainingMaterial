package com.amica.help;

import java.util.Optional;

import com.amica.help.Ticket.Status;

import lombok.Getter;

/**
 * Represents an event in a ticket's history.
 *
 * @author Will Provost
 */
@Getter
public class Event {

	private long timestamp;
	private Optional<Status> newStatus;
	private String note;
  
	public Event(String note) {
		this(null, note);
	}
  
	public Event(Status newStatus, String note) {
		this.timestamp= Clock.getTime();
		this.newStatus = Optional.ofNullable(newStatus);
		this.note = note;
	}
	
	public Status getNewStatus() {
		return newStatus.orElse(null);
	}
	
	@Override
	public String toString() {
		String result = "Event: " + note;
		result += newStatus.map(ns -> " [" + ns + "]").orElse("");
		result += " (" + Clock.format(timestamp) + ")";
		return result;
	}
}
