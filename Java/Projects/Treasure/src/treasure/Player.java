package treasure;

import java.util.Queue;

import treasure.Island.Contents;
import treasure.Island.Coordinates;

/**
 * Manages state and activity of one player on a {@link Team}.
 * We know where we are on the island, and we are given a plan in the form
 * of a series of destination coordinates. As long as we have a plan,
 * we follow it; and if no plan or the plan is completed, we're idle.
 * We can also search our current square and report findings back to our team.
 * We do some things on our own initiative, too, such as collecting coins,
 * paying the wizard if we can, and claiming the treasure if we can.
 *
 * @author Will Provost
 */
public class Player {

	private Island island;
	private Team team;
	private int row;
	private int col;
	private Queue<Coordinates> plan;
	private int coins;
	
	/**
	 * Store off our location, island reference, and team reference. 
	 */
	public Player(Island island, Team team, int row, int col) {
		this.island = island;
		this.team = team;
		this.row = row;
		this.col = col;
	}
	
	/**
	 * Accessor for the row index.
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Accessor for the column index.
	 */
	public int getCol() {
		return col;
	}

	public int getCoins() {
		return coins;
	}

	/**
	 * Accessor for the movement plan, or null if no plan.
	 */
	public Queue<Coordinates> getPlan() {
		return plan;
	}

	/**
	 * Mutator for the movement plan.
	 */
	public void setPlan(Queue<Coordinates> plan) {
		
		// No point traveling to a location we already occupy:
		while (!plan.isEmpty() && plan.peek().row == row && plan.peek().col == col) {
			plan.poll();
		}
		
		this.plan = plan;
	}
	
	/**
	 * See what's in our current square. If there's a coin, take it, and
	 * notify the team. If there's a wizard, notify the team of its location;
	 * and, if we have enough money to pay the wizard, do so, and report the
	 * magic spell to the team. If there's treasure, report its location
	 * to the team; and if the team knows the magic spell, use it  
	 * to claim the treasure, and tell the team.
	 * 
	 */
	public void search() {
		Contents contents = island.getContents(row, col);
		if (contents == Contents.COIN) {
			island.takeCoin(row, col);
			++coins;
			team.tookCoin(row, col);
		} else if (contents == Contents.WIZARD) {
			team.foundWizard(row, col);
			if (coins >= island.getCoinsForWizard()) {
				team.setMagicSpell(island.payWizard(row, col, coins));
			}
		} else if (contents == Contents.TREASURE) {
			team.foundTreasure(row, col);
			if (team.getMagicSpell() != null) {
				island.claimTreasure(row, col, team.getMagicSpell());
				team.claimedTreasure();
			}
		}
	}
	
	/**
	 * If we have a plan, move one row or column toward the first destination
	 * in the plan. If we reach that destination, remove it so that either
	 * (a) the next destination becomes the first one, or (b) we're done
	 * and will sit idle until told a new plan.
	 */
	public void move() {
		if(plan == null || plan.size() == 0) {
			return;
		}
		
		Coordinates destination = plan.peek();
		if (destination.row > row) {
			row += 1;
		} else if (destination.row < row) {
			row -= 1;
		} else if (destination.col > col) {
			col += 1;
		} else if (destination.col < col) {
			col -= 1;
		}
		
		if (destination.row == row && destination.col == col) {
			plan.poll();
		}
	}
}
