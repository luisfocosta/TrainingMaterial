package treasure;

/**
 * Application that drives the Island and Game APIs through a random game.
 * You must fill in the setup and turn-playing logic so that you win the game,
 * even without knowing the island's layout in advance.
 * 
 * @author Will Provost
 */
public class RandomGame {

	/**
	 * Creates a random 6x6 island, and sets up the game viewer.
	 * You take it from there ...
	 */
	public static void main(String[] args) {
		Island island = new Island();
		Game game = new Game(island);
		
		//TODO Initialize your game-playing logic here

		game.onEachTurnCall(() -> {
				//TODO Call a function of your own here;
				//     return true to keep playing and false to quit.
				System.out.println("TICK ...");
				return true;
			});
	}
}
