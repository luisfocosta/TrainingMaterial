package treasure;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static treasure.Island.Contents.EMPTY;
import static treasure.Island.Contents.TREASURE;
import static treasure.Island.Contents.WIZARD;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

import treasure.Island.Coordinates;

/**
 * Unit test for the {@link Player} class.
 *
 * @author Will Provost
 */
public class PlayerTest {

	private Island island = IslandTest.createTestIsland();
	private Team team = mock(Team.class);
	private Player player;
	
	/**
	 * Check that the class initializes fields correctly.
	 */
	@Test
	public void testInitialization() {
		player = new Player(island, team, 2, 4);
		
		assertThat(player.getRow(), equalTo(2));
		assertThat(player.getCol(), equalTo(4));
		assertThat(player.getCoins(), equalTo(0));
	}
	
	/**
	 * Check that the player follows a single-destination movement plan.
	 */
	@Test
	public void testOneDestination() {
		Queue<Coordinates> plan = new LinkedList<>();
		plan.add(new Coordinates(2, 0));
		
		player = new Player(island, team, 0, 0);
		player.setPlan(plan);

		player.move();
		assertThat(player.getRow(), equalTo(1));
		assertThat(player.getCol(), equalTo(0));

		player.move();
		assertThat(player.getRow(), equalTo(2));
		assertThat(player.getCol(), equalTo(0));

		player.move();
		assertThat(player.getRow(), equalTo(2));
		assertThat(player.getCol(), equalTo(0));
	}
	
	/**
	 * Check that the player follows a three-destination movement plan.
	 */
	@Test
	public void testThreeDestinations() {
		Queue<Coordinates> plan = new LinkedList<>();
		plan.add(new Coordinates(2, 0));
		plan.add(new Coordinates(2, 2));
		plan.add(new Coordinates(0, 2));
		
		player = new Player(island, team, 0, 0);
		player.setPlan(plan);

		player.move();
		assertThat(player.getRow(), equalTo(1));
		assertThat(player.getCol(), equalTo(0));

		player.move();
		assertThat(player.getRow(), equalTo(2));
		assertThat(player.getCol(), equalTo(0));

		player.move();
		assertThat(player.getRow(), equalTo(2));
		assertThat(player.getCol(), equalTo(1));

		player.move();
		assertThat(player.getRow(), equalTo(2));
		assertThat(player.getCol(), equalTo(2));

		player.move();
		assertThat(player.getRow(), equalTo(1));
		assertThat(player.getCol(), equalTo(2));

		player.move();
		assertThat(player.getRow(), equalTo(0));
		assertThat(player.getCol(), equalTo(2));

		player.move();
		assertThat(player.getRow(), equalTo(0));
		assertThat(player.getCol(), equalTo(2));
	}
	
	/**
	 * Check that the player sits idle if given a plan to move to its
	 * own, current location.
	 */
	@Test
	public void testGoWhereWeAre() {
		Queue<Coordinates> plan = new LinkedList<>();
		plan.add(new Coordinates(2, 0));
		
		player = new Player(island, team, 2, 0);
		player.setPlan(plan);

		player.move();
		assertThat(player.getRow(), equalTo(2));
		assertThat(player.getCol(), equalTo(0));
	}
	
	/**
	 * Check that the player takes a coin when found.
	 */
	@Test
	public void testFindCoin() {
		player = new Player(island, team, 0, 1);
		player.search();
		
		assertThat(island.getContents(0,  1), equalTo(EMPTY));
		assertThat(player.getCoins(), equalTo(1));
		verify(team).tookCoin(0, 1);
	}
	
	/**
	 * Check that the player reports the wizard's location when found. 
	 */
	@Test
	public void testFindWizard() {
		player = new Player(island, team, 0, 3);
		player.search();
		
		assertThat(island.getContents(0,  3), equalTo(WIZARD));
		verify(team).foundWizard(0, 3);
		verify(team, never()).setMagicSpell(any());
	}
	
	/**
	 * Check that the player reports the wizard's location when found,
	 * and pays for the spell if the player has enough coins. 
	 */
	@Test
	public void testPayWizard() {
		Queue<Coordinates> plan = new LinkedList<>();
		plan.add(new Coordinates(2, 0));
		plan.add(new Coordinates(0, 2));
		plan.add(new Coordinates(0, 3));

		player = new Player(island, team, 2, 3);
		player.setPlan(plan);
		player.search();

		for (int i = 0; i < 8; ++i) {
			player.move();
			player.search();
		}
		
		verify(team).foundWizard(0, 3);
		verify(team).setMagicSpell(IslandTest.SPELL);
	}
	
	/**
	 * Check that the player reports the treasure's location when found 
	 */
	@Test
	public void testFindTreasure() {
		player = new Player(island, team, 1, 2);
		player.search();
		
		assertThat(island.getContents(1, 2), equalTo(TREASURE));
		verify(team).foundTreasure(1, 2);
		verify(team, never()).claimedTreasure();
	}
	
	/**
	 * Check that the player reports the treasure's location when found,
	 * and claims the treasure if the magic spell is known. 
	 */
	@Test
	public void testClaimTreasure() {
		when(team.getMagicSpell()).thenReturn(IslandTest.SPELL);
		
		player = new Player(island, team, 1, 2);
		player.search();
		
		assertThat(island.getContents(1, 2), equalTo(EMPTY));
		verify(team).foundTreasure(1, 2);
		verify(team).claimedTreasure();
	}
}
