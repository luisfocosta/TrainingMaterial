package treasure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import treasure.Island.Coordinates;

/**
 * An implementation of logic to play the treasure-hunt game.
 * We create two {@link Player}s, placing them at opposite corners.
 * We then carry out the game in two phases:
 * 
 * 1. We set a search pattern for each player so that we cover the whole
 * island. As the players find coins, wizard, and treasure, we track
 * locations.
 * 
 * 2. When we have found both wizard and treasure, and any player has
 * collected enough coins to pay the wizard, that player moves directly
 * to the wizard while the other player moves to the treasure. Once reaching
 * the wizard, the player pays for the spell, and once reaching the treasure
 * and the spell is collected the other claims the treasure.
 * (It's also possible that the same player will pay the wizard and
 * claim the treasure, if very close to both things when this phase begins.)
 *
 * @author Will Provost
 */
public class Team {

	private Game game;
	private Island island;
	private List<Player> players = new ArrayList<>();
	private Coordinates wizardLocation;
	private Coordinates treasureLocation;
	private String magicSpell;
	private boolean stillPlaying = true;
	
	/**
	 * The team stores references to the island and the game, and then
	 * builds players and their search patterns. Each player is instructed
	 * to {@link treasure.
	 */
	public Team(Game game, Island island) {
		this.game = game;
		this.island = island;
		
		int maxRow = island.getHeight() - 1;
		int maxCol = island.getWidth() - 1;
		
		Player player1 = new Player(island, this, 0, 0);
		Queue<Coordinates> plan1 = new LinkedList<>();
		int otherEnd = maxCol;
		for (int row = 0; row <= maxRow / 2 + 1; ++row) {
			plan1.add(new Coordinates(row, otherEnd));
			plan1.add(new Coordinates(row + 1, otherEnd));
			otherEnd = maxCol - otherEnd;
		}
		player1.setPlan(plan1);
		players.add(player1);
		
		Player player2 = new Player(island, this, maxRow, maxCol);
		Queue<Coordinates> plan2 = new LinkedList<>();
		otherEnd = 0;
		for (int row = maxRow; row >= maxRow / 2; --row) {
			plan2.add(new Coordinates(row, otherEnd));
			plan2.add(new Coordinates(row - 1, otherEnd));
			otherEnd = maxCol - otherEnd;
		}
		player2.setPlan(plan2);
		players.add(player2);

		for (Player player : players) {
			game.playerEnters(player.getRow(), player.getCol());
			game.showContents(player.getRow(), player.getCol());
		}
		
		game.showMessage("Searching ...");
	}
	
	/**
	 * Helper to build a movement plan to a single destination.
	 */
	private static Queue<Coordinates> goToOneDestination(Coordinates coordinates) {
		Queue<Coordinates> plan = new LinkedList<>();
		plan.add(coordinates);
		return plan;
	}
	
	/**
	 * First, all players search their current locations; this can trigger
	 * event notifications from the payers back to us, which can modify our
	 * state: maybe we found the wizard or treasure, learned the spell, etc.
	 * Then, based on our current state, we might (a) claim the treasure,
	 * (b) move to phase two and go to the wizard and the treasure, or
	 * (c) just keep searching for stuff.
	 *  
	 */
	public boolean playOneTurn() {
		for (Player player : players) {
			player.search();
		}
		
		if (stillPlaying) {
			
			// If we've learned the spell, all players to the treasure!
			// One player was already headed there, but if the wizard and the
			// treasure are close to each other, the player at the wizard
			// may get to the treasure first.
			if (magicSpell != null) {
				for (Player player : players) {
					player.setPlan(goToOneDestination(treasureLocation));
				}
				
			// If we have located both the wizard and the the treasure, 
			// AND at least one player has enough coins to pay the wizard,
			// then we enter phase two, directing the coin-laden player to the
			// wizard and the other one to the treasure.
			} else {
				if (wizardLocation != null && treasureLocation != null) {
					for (int p = 0; p < players.size(); ++p) {
						if (players.get(p).getCoins() >= island.getCoinsForWizard()) {
							game.showMessage("Phase two: pay the wizard, claim the treasure ...");
							players.get(p).setPlan(goToOneDestination(wizardLocation));
							players.get(1-p).setPlan(goToOneDestination(treasureLocation));
						}
					}
				}
			}

			// All players move according to their current plans,
			// and we update the UI with player icons and square contents.
			for (Player player : players) {
				game.playerLeaves(player.getRow(), player.getCol());
				player.move();
				game.playerEnters(player.getRow(), player.getCol());
				if (stillPlaying) { // just so treasure doesn't disappear at end of game
					game.showContents(player.getRow(), player.getCol());
				}
			}
		}
		
		return stillPlaying;
	}
	
	/**
	 * Update the UI to show the taken coin and a fresh message.
	 */
	public void tookCoin(int row, int col) {
		game.showMessage("Found a coin.");
		game.showContents(row, col);
	}
	
	/**
	 * Record the wizard's location, and update the UI message.
	 */
	public void foundWizard(int row, int col) {
		wizardLocation = new Coordinates(row, col);
		game.showMessage("Found the wizard.");
	}
	
	/**
	 * Record the treasure location, and update the UI message. 
	 */
	public void foundTreasure(int row, int col) {
		treasureLocation = new Coordinates(row, col);
		game.showMessage("Found the treasure!");
	}
	
	/**
	 * Accessor for the magic spell -- returns null if not yet known.
	 */
	public String getMagicSpell() {
		return magicSpell;
	}
	
	/**
	 * Record the magic spell, and update the UI message
	 */
	public void setMagicSpell(String magicSpell) {
		game.showMessage("Paid the wizard, learned the magic spell.");
		this.magicSpell = magicSpell;
	}
	
	/**
	 * Set a flag saying that we're done playing, and update the UI message.
	 */
	public void claimedTreasure() {
		game.showMessage("Claimed the treasure!");
		stillPlaying = false;
	}
}
