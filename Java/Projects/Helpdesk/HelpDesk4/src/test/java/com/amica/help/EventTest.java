package com.amica.help;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.amica.help.Ticket.Status;
	
/**
 * Unit test for the {@link Event} class.
 * 
 * @author Will Provost
 */
public class EventTest {

	public static final int TICKET_ID = 1;
	
	public static final Status EVENT1_STATUS = Status.RESOLVED;
	public static final String EVENT1_NOTE = "EVENT1_NOTE";
	
	public static final Status EVENT2_STATUS = null;
	public static final String EVENT2_NOTE = "EVENT2_NOTE";
	
	private Event event1;
	private Event event2;
	private long timestamp1;
	private long timestamp2;

	/**
	 * Helper to assert all state on an Event object.
	 */
	public static void assertEventHas(Event event, 
			int ID, long timestamp, Status status, String note) {
		assertThat(event.getTicketID(), equalTo(ID));
		assertThat(event.getTimestamp(), equalTo(timestamp));
		assertThat(event.getNewStatus(), equalTo(status));
		assertThat(event.getNote(), equalTo(note));
	}
	
	/**
	 * Set up mock ticket and two test events.
	 */
	@BeforeEach
	public void setUp() {
		
		Ticket ticket = mock(Ticket.class);
		when(ticket.getID()).thenReturn(TICKET_ID);
		
		Clock.setTime("1/6/22 8:00");
		timestamp1 = Clock.getTime();
		event1 = new Event(ticket, EVENT1_STATUS, EVENT1_NOTE);
		
		Clock.setTime("1/6/22 8:01");
		timestamp2 = Clock.getTime();
		event2 = new Event(ticket, EVENT2_NOTE);
	}
	
	/**
	 * Test initialization and getters.
	 */
	@Test
	public void testInitialization() {
		assertEventHas(event1, TICKET_ID, timestamp1, EVENT1_STATUS, EVENT1_NOTE);
		assertEventHas(event2, TICKET_ID, timestamp2, EVENT2_STATUS, EVENT2_NOTE);
	}
	
	/**
	 * Test null handling.
	 */
	@Test
	public void testInitialization_NullTicket() {
		assertThrows(IllegalArgumentException.class, 
				() -> new Event(null, EVENT1_STATUS, EVENT1_NOTE));
	}
	
	/**
	 * Test null handling.
	 */
	@Test
	public void testInitialization_NullNote() {
		assertThrows(IllegalArgumentException.class, 
				() -> new Event(mock(Ticket.class), EVENT1_STATUS, null));
	}
	
	/**
	 * Test comparison by timestamp.
	 */
	@Test
	public void testComparison() {
		assertThat(event1, lessThan(event2));
	}
	
}
