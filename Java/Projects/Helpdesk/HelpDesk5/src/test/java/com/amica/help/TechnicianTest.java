package com.amica.help;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.amica.help.Ticket.Priority;

/**
 * Unit test for the {@link Technician} class.
 * 
 * @author Will Provost
 */
public class TechnicianTest {
	
	public static final String ID =  "ID";
	public static final String NAME = "NAME";
	public static final int EXTENSION = 12345;
	
	public static final String ORIGINATOR = "ROGINATOR";
	public static final String DESCRIPTION = "DESCRIPTION";
	
	private Technician technician;
	private Ticket ticket1;
	private Ticket ticket2;

	private List<Integer> activeTickets() {
		return technician.getActiveTickets().map(Ticket::getID).toList();
	}
	
	@BeforeEach
	public void setUp() {
		technician = new Technician(ID, NAME, EXTENSION);

		Clock.setTime("1/6/22 8:00");
		ticket1 = new Ticket(1, ORIGINATOR, DESCRIPTION, Priority.LOW);
		Clock.setTime("1/6/22 7:00");
		ticket2 = new Ticket(2, ORIGINATOR, DESCRIPTION, Priority.HIGH);

//		We could support the same range of tests with a mock object:
//		
//		ticket1 =mock(Ticket.class);
//		when(ticket1.getID()).thenReturn(1);
//		
//		ticket2 =mock(Ticket.class);
//		when(ticket2.getID()).thenReturn(2);
//
//		when(ticket1.compareTo(ticket2)).thenReturn(1);
//		when(ticket2.compareTo(ticket1)).thenReturn(-1);
	}
	
	@Test
	public void testInitialization() {
		assertThat(technician.getID(), equalTo(ID));
		assertThat(technician.getName(), equalTo(NAME));
		assertThat(technician.getExtension(), equalTo(EXTENSION));
		assertThat(activeTickets(), empty());
	}
	
	@Test
	public void testAddActiveTicket() {
		technician.addActiveTicket(ticket1);
		assertThat(activeTickets(), contains(1));
	}
	
	public void testAddActiveTicket_Created() {
		assertThrows(IllegalArgumentException.class, 
				() ->technician.addActiveTicket(ticket1));
	}
	
	@Test
	public void testAddActiveTickets() {
		technician.addActiveTicket(ticket1);
		technician.addActiveTicket(ticket2);
		assertThat(activeTickets(), contains(2, 1));
	}
	
	@Test
	public void testAddActiveTicket_Duplicate() {
		technician.addActiveTicket(ticket1);
		technician.addActiveTicket(ticket1);
		assertThat(activeTickets(), contains(1));
	}
	
	@Test
	public void testTicketOrdering() {
		technician.addActiveTicket(ticket1);
		technician.addActiveTicket(ticket2);
		assertThat(activeTickets(), contains(2, 1));
	}
	
	@Test
	public void testRemoveActiveTicket() {
		technician.addActiveTicket(ticket1);
		technician.addActiveTicket(ticket2);
		technician.removeActiveTicket(ticket1);
		assertThat(activeTickets(), contains(2));
	}
	
	@Test
	public void testRemoveActiveTicket_Missing() {
		assertThrows(IllegalArgumentException.class, 
				() ->technician.removeActiveTicket(ticket1));
	}
}
