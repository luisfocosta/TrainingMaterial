package com.amica.help;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.amica.help.Ticket.Priority;
import com.amica.help.Ticket.Status;

/**
 * Unit test for the {@link ReopenedTicket} class. We set up a distinct prior
 * ticket, and then create a reopened ticket that looks as much like the ticket
 * used by the base class, so most of the test cases are satisfied by the
 * derived-type object under test. Then we do have to adjust a few things,
 * mostly because reopened tickets automatically assign themselves to the prior
 * ticket's technician. We disable some tests that no longer make sense, such as
 * those that check how the ticket handles state transitions from the CREATED
 * state, since the ticket is effectively never in that state. And we add a few
 * tests specific to reopened tickets.
 * 
 * @author Will Provost
 */
public class ReopenedTicketTest extends TicketTest {

	public static final int PRIOR_ID = 2;
	public static final String PRIOR_DESCRIPTION = "PRIOR_DESCRIPTION";
	public static final Priority PRIOR_PRIORITY = Priority.LOW;
	public static final String PRIOR_NOTE = "PRIOR_NOTE";

	public static final Tag PRIOR_TAG1 = new Tag("PRIOR_TAG1");
	public static final Tag PRIOR_TAG2 = new Tag("PRIOR_TAG2");

	private Ticket priorTicket;

	/**
	 * Adjust the index at which we expect to find an event, by adding the number of
	 * events in the prior ticket's history.
	 */
	@Override
	public void assertHasEvent(int index, Status status, String note) {
		super.assertHasEvent((int) priorTicket.getHistory().count() + index, status, note);
	}

	/**
	 * Don't assign if already assigned. This would be leval, but for our test logic
	 * it would throw a few things off-track, including timestamps.
	 */
	@Override
	public void assign() {
		if (ticket.getStatus() != Status.ASSIGNED) {
			super.assign();
		}
	}

	/**
	 * Create the prior ticket, assign and resolve it. Then create the object under
	 * test: a reopened ticket based on that prior ticket.
	 */
	@Override
	@BeforeEach
	public void setUp() {
		init();
		
		Clock.setTime(START_TIME);
		priorTicket = ticket = new Ticket(PRIOR_ID, ORIGINATOR, PRIOR_DESCRIPTION, PRIOR_PRIORITY);
		assign();
		resolve();

		passOneMinute(); // Base class expects one more minute to assign the ticket`
		ticket = new ReopenedTicket(ID, priorTicket, DESCRIPTION, PRIORITY);
	}

	/**
	 * Some expectations are different for reopened tickets, including its status,
	 * assigned technician, and length of history.
	 */
	@Override
	@Test
	public void testInitialization() {
		assertThat(ticket.getID(), equalTo(ID));
		assertThat(ticket.getOriginator(), equalTo(ORIGINATOR));
		assertThat(ticket.getDescription(), equalTo(DESCRIPTION));
		assertThat(ticket.getPriority(), equalTo(PRIORITY));

		assertThat(ticket.getStatus(), equalTo(Status.ASSIGNED));
		assertThat(ticket.getTechnician(), equalTo(tech1));
		assertThat(ticket.getTags().count(), equalTo(0L));

		assertHasEvent(1, Status.ASSIGNED,
				String.format("Assigned to Technician %s, %s.", TECHNICIAN1_ID, TECHNICIAN1_NAME));
		verify(tech1).addActiveTicket(ticket);
	}

	/**
	 * We disable this test because the reopened ticket moves out of the CREATED
	 * status during construction, so we can't simulate how it would handle
	 * incorrect state transitions from the CREATED state.
	 */
	@Override
	@Test
	@Disabled
	public void testWait_Created() {
	}

	/**
	 * We disable this test because the reopened ticket moves out of the CREATED
	 * status during construction, so we can't simulate how it would handle
	 * incorrect state transitions from the CREATED state.
	 */
	@Override
	@Test
	public void testResume_Created() {
	}

	/**
	 * We disable this test because the reopened ticket moves out of the CREATED
	 * status during construction, so we can't simulate how it would handle
	 * incorrect state transitions from the CREATED state.
	 */
	@Override
	@Test
	public void testResolve_Created() {
	}

	/**
	 * We expect a shorter time to resolve, because our ticket is created one minute
	 * later than the base class' one would be.
	 */
	@Override
	@Test
	public void testGetMinutesToResolve() {
		resolve();
		assertThat(ticket.getMinutesToResolve(), equalTo(1));
	}

	/**
	 * Test that we can see a prior ticket's tag in th reopened ticket's tags.
	 */
	@Test
	public void testGetPriorTags1() {
		priorTicket.addTag(PRIOR_TAG1);
		assertThat(ticket.getTags().count(), equalTo(1L));
		assertThat(ticket.getTags().toList().contains(PRIOR_TAG1), equalTo(true));
	}

	/**
	 * Test that we won't see duplicate tags, even if added to different tickets in
	 * the chain.
	 */
	@Test
	public void testGetPriorTags2() {
		priorTicket.addTag(PRIOR_TAG1);
		ticket.addTag(PRIOR_TAG1);
		assertThat(ticket.getTags().count(), equalTo(1L));
		assertThat(ticket.getTags().toList().contains(PRIOR_TAG1), equalTo(true));
	}

	/**
	 * Test that the reopened ticket includes the prior ticket's description for
	 * purposes of text searching.
	 */
	@Test
	public void testIncludesText_PriorDescription() {
		assertThat(ticket.includesText(PRIOR_DESCRIPTION), equalTo(true));
	}

	/**
	 * Test that the reopened ticket includes the prior ticket's note for purposes
	 * of text searching.
	 */
	@Test
	public void testIncludesText_PriorNote() {
		priorTicket.addNote(PRIOR_NOTE);
		assertThat(ticket.includesText(PRIOR_DESCRIPTION), equalTo(true));
	}
}
