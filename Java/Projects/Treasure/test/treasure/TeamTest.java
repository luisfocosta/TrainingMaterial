package treasure;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;

/**
 * Unit test for the {@link Teaem} class.
 *
 * @author Will Provost
 */
public class TeamTest {

	/**
	 * Team logic is highly stateful, so we run one, coarse-grained test
	 * that progresses through a simple game: get coins, pay wizard, claim
	 * treasure. We verify player location, square contents, and progress
	 * via verification of outbound calls to a mock {@link Game}.
	 */
	@Test
	public void testGame1() {
		Island island = IslandTest.createTestIsland();
		Game game = mock(Game.class);
		Team team = new Team(game, island);
		verify(game).showContents(0, 0);
		verify(game).showContents(2, 3);
		verify(game).showMessage("Searching ...");
		
		assertThat(team.playOneTurn(), equalTo(true));
		verify(game).showMessage("Found a coin.");
		verify(game).showContents(0, 1);
		verify(game).showContents(2, 2);
		
		assertThat(team.playOneTurn(), equalTo(true));
		verify(game, times(2)).showMessage("Found a coin.");
		verify(game, times(2)).showContents(0, 1); // took the coin
		verify(game).showContents(0, 2);
		verify(game).showContents(2, 1);
		
		assertThat(team.playOneTurn(), equalTo(true));
		verify(game).showContents(0, 3);
		verify(game).showContents(2, 0);
		
		assertThat(team.playOneTurn(), equalTo(true));
		verify(game).showMessage("Found the wizard.");
		verify(game, times(3)).showMessage("Found a coin.");
		verify(game).showContents(1, 3);
		verify(game).showContents(1, 0);
		
		assertThat(team.playOneTurn(), equalTo(true));
		verify(game).showContents(1, 2);
		verify(game).showContents(1, 1);
		
		assertThat(team.playOneTurn(), equalTo(true));
		verify(game).showMessage("Found the treasure!");
		verify(game).showMessage("Phase two: pay the wizard, claim the treasure ...");
		verify(game, times(3)).showContents(0, 1);
		
		assertThat(team.playOneTurn(), equalTo(true));
		verify(game, times(2)).showContents(0, 2);
		
		assertThat(team.playOneTurn(), equalTo(true));
		verify(game, times(2)).showContents(0, 3);
		
		assertThat(team.playOneTurn(), equalTo(true));
		verify(game).showMessage("Paid the wizard, learned the magic spell.");
		
		assertThat(team.playOneTurn(), equalTo(false));
		verify(game).showMessage("Claimed the treasure!");
	}
}
