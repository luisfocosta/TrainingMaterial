package com.amica.help;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.amica.HasKeys;
import com.amica.help.Ticket.Priority;
import com.amica.help.Ticket.Status;

/**
 * Unit test for the {@link HelpDesk} class.
 * 
 * @author Will Provost
 */
public class HelpDeskTest {

	public static final String TECH1 = "TECH1";
	public static final String TECH2 = "TECH2";
	public static final String TECH3 = "TECH3";

	public static final int TICKET1_ID = 1;
	public static final String TICKET1_ORIGINATOR = "TICKET1_ORIGINATOR";
	public static final String TICKET1_DESCRIPTION = "TICKET1_DESCRIPTION";
	public static final Priority TICKET1_PRIORITY = Priority.LOW;
	public static final int TICKET2_ID = 2;
	public static final String TICKET2_ORIGINATOR = "TICKET2_ORIGINATOR";
	public static final String TICKET2_DESCRIPTION = "TICKET2_DESCRIPTION";
	public static final Priority TICKET2_PRIORITY = Priority.HIGH;
	
	public static final String TAG1 = "TAG1";
	public static final String TAG2 = "TAG2";
	public static final String TAG3 = "TAG3";
	
	private HelpDesk helpDesk = new HelpDesk();
	private Technician tech1;
	private Technician tech2;
	private Technician tech3;

	/**
	 * Set up three technicians, and grab references to them.
	 * Set the clock -- the time doesn't always matter, but it must be set.
	 */
	@BeforeEach
	public void addTechnicians() {
		
		helpDesk.createTechnician(TECH1, TECH1, 1);
		helpDesk.createTechnician(TECH2, TECH2, 2);
		helpDesk.createTechnician(TECH3, TECH3, 3);
		
		Iterator<Technician> iterator = helpDesk.getTechnicians().iterator();
		tech1 = iterator.next();
		tech2 = iterator.next();
		tech3 = iterator.next();

		Clock.setTime(100);
	}
	
	private void createTicket1() {
		helpDesk.createTicket
				(TICKET1_ORIGINATOR, TICKET1_DESCRIPTION, TICKET1_PRIORITY);
	}
	
	private void createTicket2() {
		helpDesk.createTicket
				(TICKET2_ORIGINATOR, TICKET2_DESCRIPTION, TICKET2_PRIORITY);
	}
	
	/**
	 * Custom matcher that checks the contents of a stream of tickets
	 * against expected IDs, in exact order;
	 */
	public static class HasIDs extends TypeSafeMatcher<Stream<? extends Ticket>> {

		private String expected;
		private String was;
		
		public HasIDs(int... IDs) {
			int[] expectedIDs = IDs;
			expected = Arrays.stream(expectedIDs)
					.mapToObj(Integer::toString)
					.collect(Collectors.joining(", ", "[ ", " ]"));		
		}
		
		public void describeTo(Description description) {
			
			description.appendText("tickets with IDs ");
			description.appendText(expected);
		}
		
		@Override
		public void describeMismatchSafely
				(Stream<? extends Ticket> tickets, Description description) {
			description.appendText("was: tickets with IDs ");
			description.appendText(was);
		}

		protected boolean matchesSafely(Stream<? extends Ticket> tickets) {
			was = tickets.mapToInt(Ticket::getID)
					.mapToObj(Integer::toString)
					.collect(Collectors.joining(", ", "[ ", " ]"));
			return expected.equals(was);
		}
		
	}
// Step4 uses the simpler, Ticket-specific matcher defined above:
//	public static Matcher<Stream<? extends Ticket>> hasIDs(int... IDs) {
//		return new HasIDs(IDs);
//	}
	public static Matcher<Stream<? extends Ticket>> hasIDs(Integer... IDs) {
		return HasKeys.hasKeys(Ticket::getID, IDs);
	}
	
	/**
	 * Test that help desk rejects tickets until it has a staff to handle them.
	 * Note that we don't use the helpDesk field for this, because every other
	 * method on this class expects technicians to be present.
	 */
	@Test
	public void testNoTechnicians() {
		assertThrows(IllegalStateException.class, 
				() -> new HelpDesk().createTicket(null, null, null));
	}
	
	@Test
	public void testCreateTicketsAndGetByID() {
		createTicket1();
		createTicket2();
		assertThat(helpDesk.getTicketByID(TICKET2_ID).getDescription(),
				equalTo(TICKET2_DESCRIPTION));
	}
	
	@Test
	public void testGetByID_NotFound() {
		assertThat(helpDesk.getTicketByID(2), nullValue());
	}

	@Test
	public void testAssignment_AllEven() {
		createTicket1();
		assertThat(helpDesk.getTicketByID(TICKET1_ID).getTechnician(), 
				equalTo(tech1));
		assertThat(tech1.getActiveTickets().count(), equalTo(1L));
	}
	
	@Test
	public void testAssignment_FirstLowest() {
		createTicket1();
		createTicket2();
		assertThat(helpDesk.getTicketByID(TICKET2_ID).getTechnician(), 
				equalTo(tech2));
		assertThat(tech2.getActiveTickets().count(), equalTo(1L));
	}

	@Test
	public void testGetTicketsByStatus() {
		createTicket1();
		createTicket2();
		helpDesk.getTicketByID(TICKET1_ID).resolve("");
		
		assertThat(helpDesk.getTicketsByStatus(Status.ASSIGNED).count(),
				equalTo(1L));
		assertThat(helpDesk.getTicketsByStatus(Status.RESOLVED).count(),
				equalTo(1L));
	}
	
	/**
	 * This test also checks ticket sorting by priority.
	 */
	@Test
	public void testGetTicketsByNotStatus() {
		createTicket1();
		createTicket2();
		helpDesk.getTicketByID(TICKET1_ID).resolve("");
		
		assertThat(helpDesk.getTicketsByNotStatus(Status.WAITING), hasIDs(2, 1));
	}
	
	@Test
	public void testGetTicketsWithAnyTag() {
		createTicket1();
		createTicket2();
		helpDesk.addTags(1, TAG1, TAG3);
		helpDesk.addTags(2, TAG2, TAG3);
		
		assertThat(helpDesk.getTicketsWithAnyTag(TAG1), hasIDs(1));
		assertThat(helpDesk.getTicketsWithAnyTag(TAG2), hasIDs(2));
		assertThat(helpDesk.getTicketsWithAnyTag(TAG3), hasIDs(2, 1));
	}
	
	@Test
	public void testGetTicketsWithAnyTag_ProvidedTagRepo() {
		
		Tags tags = new Tags();
		tags.addSynonym("A", "B");
		HelpDesk ourHelpDesk = new HelpDesk(tags);
		ourHelpDesk.createTechnician(TECH1, TECH1, 1);

		ourHelpDesk.createTicket("", "", Priority.HIGH);
		ourHelpDesk.addTags(1, "B");
		
		assertThat(ourHelpDesk.getTicketsWithAnyTag("A"), hasIDs(1));
	}
	
	@Test
	public void testGetTicketsByText() {
		final String NOTE = "NOTE";
		
		createTicket1();
		createTicket2();
		helpDesk.getTicketByID(TICKET1_ID).addNote(NOTE);
		
		assertThat(helpDesk.getTicketsByText(TICKET1_DESCRIPTION), hasIDs(1));
		assertThat(helpDesk.getTicketsByText(TICKET2_DESCRIPTION.substring(3)), 
				hasIDs(2));
		assertThat(helpDesk.getTicketsByText(NOTE), hasIDs(1));
		assertThat(helpDesk.getTicketsByText("Assigned to"), hasIDs(2, 1));

	}
	
	@Test
	public void testGetLatestActivity() {
		createTicket1();
		createTicket2();
		Ticket ticket1 = helpDesk.getTicketByID(TICKET1_ID);
		Ticket ticket2 = helpDesk.getTicketByID(TICKET2_ID);
		
		Clock.setTime(200);
		ticket1.addNote("A");
		Clock.setTime(201);
		ticket1.suspend("B");
		Clock.setTime(202);
		ticket2.addNote("C");
		Clock.setTime(203);
		ticket1.resume("D");
		
		Iterator<Event> events = helpDesk.getLatestActivity(3).iterator();
		assertThat(events.next().getNote(), equalTo("D"));
		assertThat(events.next().getNote(), equalTo("C"));
		assertThat(events.next().getNote(), equalTo("B"));
		assertThat(events.hasNext(), equalTo(false));
	}

	@Test
	public void testGetLatestActivity_TooFewEvents() {
		createTicket1();
		assertThat(helpDesk.getLatestActivity(5).count(), equalTo(2L));
	}
	
	@Test
	public void testGetReopenedTickets() {
		createTicket1();
		createTicket2();
		helpDesk.reopenTicket(TICKET1_ID, "", Priority.URGENT);
		
		assertThat(helpDesk.getTicketByID(3), isA(ReopenedTicket.class));
		
		assertThat(helpDesk.getReopenedTickets(), hasIDs(3));
	}
}
