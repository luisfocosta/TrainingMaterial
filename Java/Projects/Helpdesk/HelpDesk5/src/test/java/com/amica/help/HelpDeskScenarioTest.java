package com.amica.help;

import static com.amica.help.HelpDeskTest.hasIDs;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.amica.help.Ticket.Priority;
import com.amica.help.Ticket.Status;
import com.amica.help.data.DataSet;
import com.amica.help.data.OriginalDataSet;

/**
 * Test for the {@link HelpDesk} class, based on the main-method style of
 * testing in the original {@link TestProgram}. This is not a typical unit test,
 * as it builds a fairly complex set of test data and then runs batteries
 * of tests against that data. But it is isolated and runs quickly,
 * and could reasonably be included in automated builds.
 * 
 * @author Will Provost
 */
public class HelpDeskScenarioTest {

	public static final String TECH1_ID = "A05589";
	public static final String TECH2_ID = "A12312";
	public static final String TECH3_ID = "A17440";
	public static final String TECH4_ID = "A20265";
	
	public HelpDesk helpDesk;
	public Tags tags;
	
	private void addTags(int ticketID, String... tags) {
		helpDesk.addTags(ticketID, tags);
	}
	
	private Stream<Ticket> getTicketsWithAnyTag(String ... tagValues) {
		return helpDesk.getTicketsWithAnyTag(tagValues);
	}
	
	public void addNote(int ticketID, String note) {
		helpDesk.getTicketByID(ticketID).addNote(note);
	}
	
	public void suspend(int ticketID, String reason) {
		helpDesk.getTicketByID(ticketID).suspend(reason);
	}
	
	public void resume(int ticketID, String reason) {
		helpDesk.getTicketByID(ticketID).resume(reason);
	}
	
	public void resolve(int ticketID, String reason) {
		helpDesk.getTicketByID(ticketID).resolve(reason);
	}
	
	@BeforeEach
	public void populate() {
		DataSet ds = new OriginalDataSet();
		ds.build();
		helpDesk = ds.getHelpDesk();
		tags = ds.getTags();
	}

	public static void assertTrue(boolean condition, String error) {
		assertThat(error, condition, equalTo(true));
	}

	public static void assertEqual(Object actual, Object expected, String error) {
		assertThat(String.format(error, actual), actual, equalTo(expected));
	}
	
	public static void assertCount(Stream<?> results,
			long expectedCount, String error) {
		assertEqual(results.count(), expectedCount, error);
	}
	
	public static long getCount(Stream<?> stream) {
		return stream.count();
	}

	/**
	 * This method tests that the ijmplementation meets the following requirements.
	 * <ul>
	 * <li>We can't re-assign a resolved ticket.</li>
	 * <li>Each ticket has a history that includes events for assignment and
	 * resolution, with timestamps taken from the {@link Clock}.</li>
	 * <li>Notes added to the ticket appear in the event history as well.</li>
	 * </ul>
	 */
	@Test
	public void test2_History() {
		
		Iterator<Event> history = helpDesk.getTicketByID(4).getHistory().iterator();
		Event created4 = history.next();
		assertEqual(Clock.format(created4.getTimestamp()), "11/1/21 9:05",
				"Ticket 4 should have been created at 9:05, was %s.");
		assertEqual(created4.getNewStatus(), Status.CREATED, "Ticket 4's first event should be CREATED, was %s.");
		assertEqual(created4.getNote(), "Created ticket.", "Ticket 4 creation note is wrong: %s.");
		Event assigned4 = history.next();
		assertEqual(Clock.format(assigned4.getTimestamp()), "11/1/21 9:05",
				"Ticket 4 should have been assigned at 9:05, was %s.");
		assertEqual(assigned4.getNewStatus(), Status.ASSIGNED, "Ticket 4's second event should be ASSIGNED, was %s.");
		assertEqual(assigned4.getNote(), "Assigned to Technician A20265, Dineh.",
				"Ticket 4 assignment note is wrong: %s.");
		Event resolved4 = history.next();
		assertEqual(Clock.format(resolved4.getTimestamp()), "11/1/21 9:07",
				"Ticket 4 should have been resolved at 9:07, was %s.");
		assertEqual(resolved4.getNewStatus(), Status.RESOLVED, "Ticket 4's second event should be RESOLVED, was %s.");
		assertEqual(resolved4.getNote(), "Explained that this is not a feature we support right now.",
				"Ticket 4 resolution note is wrong: %s.");

		history = helpDesk.getTicketByID(2).getHistory().iterator();
		history.next();
		history.next();
		history.next();
		Event note7 = history.next();
		assertEqual(Clock.format(note7.getTimestamp()), "11/1/21 14:14",
				"Ticket 2's 2nd note should be stamped 14:14, was %s.");
		assertTrue(note7.getNewStatus() == Status.ASSIGNED,
				"Ticket 2's second note status should be ASSIGNED, was " + note7.getNewStatus() + ".");
		assertEqual(note7.getNote(), "User: Yes, I can connect from other desktop machines at Amica.",
				"Ticket 2's 2nd note is wrong: %s.");
	}

	/**
	 * This method tests that the ijmplementation meets the following requirements.
	 * <li>We can find all tickets assigned to a given technician.</li>
	 * <li>Tickets are always assigned to the technician with the fewest active
	 * (i.e. unresolved) tickets.</li>
	 * <li>Tickets are sorted from highest priority to lowest, in the master data
	 * set and for each technician.</li>
	 * </ul>
	 */
	@Test
	public void test3_Assignment() {
		
		assertCount(helpDesk.getTicketsByTechnician(TECH1_ID), 5,
				"Andree should have been assigned 5 tickets, but has %s.");
		assertCount(helpDesk.getTicketsByTechnician(TECH2_ID), 3,
				"Boris should have been assigned 3 tickets, but has %s.");
		assertCount(helpDesk.getTicketsByTechnician(TECH3_ID), 3,
				"Caelem should have been assigned 3 tickets, but has %s.");
		assertCount(helpDesk.getTicketsByTechnician(TECH4_ID), 3,
				"Dineh should have been assigned 3 tickets, but has %s.");
		
		assertThat(helpDesk.getTickets(), 
				hasIDs(7, 11, 1, 2, 5, 8, 9, 10, 14, 3, 4, 6, 12, 13));

		Iterator<Technician> techs = ((HelpDesk) helpDesk).getTechnicians().iterator();
		assertThat(techs.next().getActiveTickets(), hasIDs(8, 12, 13));
		assertThat(techs.next().getActiveTickets(), hasIDs(7, 2, 14));
		assertThat(techs.next().getActiveTickets(), hasIDs(9));
		assertThat(techs.next().getActiveTickets(), hasIDs());
	}

	/**
	 * This method tests that the ijmplementation meets the following requirements.
	 * <ul>
	 * <li>We can tag tickets and find them by tags.</li>
	 * <li>A ticket will only appear once in these results, even if it has multiple
	 * requested tags.</li>
	 * <li>Tags are considered equal on a case-insensitive basis.</li>
	 * </ul>
	 */
	@Test
	public void test4_Tags() {
		assertCount(getTicketsWithAnyTag("laptop"), 3,
				"There should be 3 tickets with the 'laptop' tag, was %s.");

		assertCount(getTicketsWithAnyTag("VM"), 2,
				"There should be 2 tickets with the 'vm' tag, was %s.");

		assertCount(getTicketsWithAnyTag("permissions", "CMA"), 3,
				"There should be 3 tickets with the 'permissions' and/or 'CMA' tags, was %s.");
	}

	/**
	 * This method tests that the ijmplementation meets the following requirements.
	 * All of the test logic uses the {@link HelpDeskAPI} and so is pre-written.
	 * <ul>
	 * <li>We can correctly calculate our average time to resolve a ticket.</li>
	 * <li>We can also see the average time per technician.</li>
	 * </ul>
	 */
	@Test
	public void test5_TimeToResolve() {
		int minutes = helpDesk.getAverageMinutesToResolve();
		int hours = minutes / 60;
		minutes %= 60;
		assertEqual(hours, 24, "Average hours to resolve should be 24, was %s.");
		assertEqual(minutes, 29, "Average minutes to resolve should be 29, was %s.");

		Map<String, ? extends Number> byTech = helpDesk.getAverageMinutesToResolvePerTechnician();
		assertEqual(byTech.get("A05589").intValue(), 1396, "Andree's average should be 1396, was %s.");
		assertTrue(!byTech.containsKey("A12312"), "Boris shouldn't have an average time to resolve.");
		assertEqual(byTech.get("A17440").intValue(), 1557, "Caelem average should be 1557, was %s.");
		assertEqual(byTech.get("A20265").intValue(), 1458, "Dineh's average should be 1458, was %s.");
	}

	/**
	 * This method tests that the ijmplementation meets the following requirements.
	 * All of the test logic uses the {@link HelpDeskAPI} and so is pre-written.
	 * <ul>
	 * <li>We can find tickets whose descriptions or notes include a given
	 * substring.</li>
	 * </ul>
	 */
	@Test
	public void test6_TextSearch() {
		assertCount(helpDesk.getTicketsByText("audio"), 1, 
				"There should be 1 ticket with the text 'audio', was %s.");
		assertCount(helpDesk.getTicketsByText("permission"), 3, 
				"There should be 2 ticket with the text 'permission', was %s.");
	}

	/**
	 * This method tests that the ijmplementation meets the following requirements.
	 * <ul>
	 * <li>We can reopen a resolved ticket, with a new reason and priority.</li>
	 * <li>We can't reopen an un-resolved ticket.</li>
	 * <li>Reopened tickets take on the originator of the prior ticket, and are
	 * assigned to the original technician.</li>
	 * <li>Reopened tickets compile their own history and that of the prior
	 * ticket.</li>
	 * <li>Reopened tickets compile their own tags and those of the prior
	 * ticket.</li>
	 * <li>Reopened tags search their own and the prior ticket's text.</li>
	 */
	@Test
	public void test7_ReopenedTickets() {
		Clock.setTime("11/3/21 14:01");
		helpDesk.reopenTicket(6, "Still can't connect.", Priority.MEDIUM);
		Clock.setTime("11/3/21 14:12");
		helpDesk.reopenTicket(3, "Still can't log in.", Priority.HIGH);
		Clock.setTime("11/3/21 14:59");
		addTags(3, "VPN");

		long expectedSize = getCount(helpDesk.getTicketByID(6).getHistory()) + 2;
		long actualSize = getCount(helpDesk.getTicketByID(15).getHistory());
		assertEqual(actualSize, expectedSize, 
				"Reopened ticket should have " + expectedSize + " events, was %s");

		assertTrue(helpDesk.getTicketsWithAnyTag("GitHub")
			.anyMatch(helpDesk.getTicketByID(16)::equals),
				"Reopened ticket not found by prior ticket's tag;");
		assertTrue(helpDesk.getTicketsWithAnyTag("VPN")
			.anyMatch(helpDesk.getTicketByID(16)::equals),
				"Reopened ticket not found by its own tag;");

		assertTrue(helpDesk.getTicketsByText("access")
			.anyMatch(helpDesk.getTicketByID(15)::equals),
				"Reopened ticked should be found by original description.");
		
		assertThat(helpDesk.getReopenedTickets(), hasIDs(16, 15));
	}

	/**
	 * This method tests that the ijmplementation meets the following requirements.
	 * <ul>
	 * <li>We can pre-define synonyms for tags, and see them consolidated to a
	 * chosen master tag.</li>
	 * <li>We can pre-define preferred capitalization for tags, instead of having
	 * everything pushed to lower case.</li>
	 * </ul>
	 * This test relies on additional setup, to be performed in the
	 * {@link #runSimulation runSimulation} method, before creating and tagging
	 * tickets:
	 * <ul>
	 * <li>Create synonyms for "remoting": "RDP" and "remote desktop".</li>
	 * <li>Pre-set the following capitalizations: "VM", "VPN", "CMA", and
	 * "GitHub".</li>
	 * </ul>
	 */
	@Test
	public void test8_Synonyms() {
		assertCount(getTicketsWithAnyTag("remoting"), 6,
				"There should be 6 tickets with the 'remoting' tag, was %s.");

		assertEqual(tags.getTag("github").getValue(), "GitHub", "The tag capitalization GitHub should be used, was %s");

		Iterator<Tag> allTags = tags.getTags().iterator();
		assertEqual(allTags.next().getValue(), "audio", "Unexpected tag: %s.");
		assertEqual(allTags.next().getValue(), "CMA", "Unexpected tag: %s.");
		assertEqual(allTags.next().getValue(), "GitHub", "Unexpected tag: %s.");
		assertEqual(allTags.next().getValue(), "laptop", "Unexpected tag: %s.");
		assertEqual(allTags.next().getValue(), "permissions", "Unexpected tag: %s.");
		assertEqual(allTags.next().getValue(), "remoting", "Unexpected tag: %s.");
		assertEqual(allTags.next().getValue(), "VM", "Unexpected tag: %s.");
		assertEqual(allTags.next().getValue(), "VPN", "Unexpected tag: %s.");
	}

	/**
	 * Tests the latest-activity query.
	 */
	@Test
	public void test9_LatestActivity() {
		assertCount(helpDesk.getLatestActivity(10)
				.filter(e -> e.getTicketID() == 5), 3,
			"3 of the events in the latest 10 should relate to ticket 5; was %d.");
	}
}
