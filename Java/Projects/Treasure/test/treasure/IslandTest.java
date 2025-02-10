package treasure;

import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static treasure.Island.Contents.COIN;
import static treasure.Island.Contents.EMPTY;
import static treasure.Island.Contents.TREASURE;
import static treasure.Island.Contents.WIZARD;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import treasure.Island.Contents;

/**
 * Unit test for the {@link Island} class.
 *
 * @author Will Provost
 */
public class IslandTest {

	public static final int COINS = 2;
	public static final String SPELL = "SPELL";
	
	/**
	 * Creates a test target with hard-coded configuration.
	 */
	public static Island createTestIsland() {
		Contents[][] space = {
			{ EMPTY, COIN,  EMPTY,    WIZARD },
			{ EMPTY, EMPTY, TREASURE, EMPTY  },
			{ COIN, EMPTY,  EMPTY,    COIN   }
		};
		return new Island(space, COINS, SPELL);
	}
	
	private Island island = createTestIsland();
	
	/**
	 * Check that the class initializes fields correctly.
	 */
	@Test
	public void testInitialization() {
		assertThat(island.getHeight(), equalTo(3));
		assertThat(island.getWidth(), equalTo(4));
		assertThat(island.getCoinsForWizard(), equalTo(COINS));
		assertThat(island.getContents(0, 0), equalTo(EMPTY));
		assertThat(island.getContents(2, 3), equalTo(COIN));
	}
	
	/**
	 * Check that taking a coin clears the square.
	 */
	@Test
	public void testTakeCoin() {
		island.takeCoin(0, 1);
		assertThat(island.getContents(0, 1), equalTo(EMPTY));
	}
	
	/**
	 * Check that paying the wizard returns the configured spell.
	 */
	@Test
	public void testPayWizard() {
		assertThat(island.payWizard(0, 3, COINS), equalTo(SPELL));
	}
	
	/**
	 * Check that claiming the trasure clears the square.
	 */
	@Test
	public void testClaimTreasure() {
		island.claimTreasure(1, 2, SPELL);
		assertThat(island.getContents(1, 2), equalTo(EMPTY));
	}
	
	/**
	 * Helper to assert that a given behavior throws an IllegalArgumentException.
	 */
	public static void assertThrowsIAE(Runnable call) {
		try {
			call.run();
			fail("IllegalArgumentException expected.");
		} catch (IllegalArgumentException ex) {
		}
	}
	
	/**
	 * Check error handling over out-of-bounds coordinates.
	 */
	@Test
	public void testGetContentsErrorHandling() {
		assertThrowsIAE(() -> island.getContents(-1, 0));
		assertThrowsIAE(() -> island.getContents(0, -1));
		assertThrowsIAE(() -> island.getContents(3, 0));
		assertThrowsIAE(() -> island.getContents(0, 4));
	}
	
	/**
	 * Check error handling over coin-collecting at out-of-bounds coordinates 
	 * and squares that don't have coins. 
	 */
	@Test
	public void testTakeCoinErrorHandling() {
		assertThrowsIAE(() -> island.takeCoin(-1, 0));
		assertThrowsIAE(() -> island.takeCoin(0, -1));
		assertThrowsIAE(() -> island.takeCoin(3, 0));
		assertThrowsIAE(() -> island.takeCoin(0, 4));
		assertThrowsIAE(() -> island.takeCoin(0, 0));
	}
	
	/**
	 * Check error handling over wizard-paying at out-of-bounds coordinates 
	 * and squares that don't have a wizard. 
	 */
	@Test
	public void testPayWizardErrorHandling() {
		assertThrowsIAE(() -> island.payWizard(-1, 0, 0));
		assertThrowsIAE(() -> island.payWizard(0, -1, 0));
		assertThrowsIAE(() -> island.payWizard(3, 0, 0));
		assertThrowsIAE(() -> island.payWizard(0, 4, 0));
		assertThrowsIAE(() -> island.payWizard(0, 0, 0));
		assertThrowsIAE(() -> island.payWizard(0, 3, 0));
	}
	
	/**
	 * Check error handling over treasure-claiming at out-of-bounds coordinates 
	 * and squares that don't have treasure. 
	 */
	@Test
	public void testClaimTreasureErrorHandling() {
		assertThrowsIAE(() -> island.claimTreasure(-1, 0, SPELL));
		assertThrowsIAE(() -> island.claimTreasure(0, -1, SPELL));
		assertThrowsIAE(() -> island.claimTreasure(3, 0, SPELL));
		assertThrowsIAE(() -> island.claimTreasure(0, 4, SPELL));
		assertThrowsIAE(() -> island.claimTreasure(0, 0, SPELL));
		assertThrowsIAE(() -> island.claimTreasure(0, 3, "Not the spell"));
	}
	
	/**
	 * Assert what we can about a randomly-initialized island:
	 * it has a total of N coins, 1 sizard, and 1 treasure, and a non-null,
	 * non-empty magic spell.
	 */
	@Test
	public void testRandomIsland() {
		Island island = new Island();
		assertThat(island.getCoinsForWizard(), equalTo(3));
		
		Map<Contents, Integer> counts = new HashMap<>();
		for (Contents value : Contents.values()) {
			counts.put(value, 0);
		}
		for (int row = 0; row < island.getHeight(); ++row) {
			for (int col = 0; col < island.getWidth(); ++col) {
				Contents contents = island.getContents(row,  col);
				counts.put(contents, counts.get(contents) + 1);
				
				if (contents == WIZARD) {
					assertThat(island.payWizard(row, col, 3), 
							both(notNullValue()).and(not(equalTo(""))));
				}
			}
		}
		
		assertThat(counts.get(COIN), equalTo(5));
		assertThat(counts.get(WIZARD), equalTo(1));
		assertThat(counts.get(TREASURE), equalTo(1));
	}
}
