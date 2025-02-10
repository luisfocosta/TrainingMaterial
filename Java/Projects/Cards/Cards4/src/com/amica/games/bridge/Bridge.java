package com.amica.games.bridge;

import static com.amica.games.CardFormatter.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.amica.games.Card;
import com.amica.games.Deck;

/**
 * Runs a single hand of contract bridge and judges the results.
 * Currently, we can shuffle the deck, deal out the hands, and then run play
 * assuming that the first player is declarer of a 1NT contract.
 * (The players have some bidding logic but it's not sufficiently complete
 * in order to support a complete round of bidding.)
 *
 * @author Will Provost
 */
public class Bridge {

	public static final int PLAYERS = 4;
	public static final int TRICKS = 13;

	private List<Player> players;
	private List<Integer> tricksPerTeam;
	private int leader;

	/**
	 * Accessor for the results of the hand.
	 */
	public List<Integer> getTricksPerTeam() {
		return tricksPerTeam;
	}
	
	/**
	 * We initialize the game by creating a list of four players,
	 * and a list of tricks taken by each team.
	 */
	public Bridge() {
		players = new ArrayList<>(PLAYERS);
		for (int seat = 0; seat < PLAYERS; ++seat) {
			players.add(new Player());
		}

		tricksPerTeam = new ArrayList<>(Collections.nCopies(PLAYERS / 2, 0));
	}

	/**
	 * Helper to advance any 0-3 index and rotate from 3 back to 0.
	 */
	private int next(int current) {
		return (current + 1) % PLAYERS;
	}

	/**
	 * Shuffle a {@ Deck} and deal out all four hands.
	 * Tell all players when the deal is done.
	 * Print out the contents of all four hands.
	 */
	public void deal() {
		
		Deck deck = new Deck();
		
		int seat = 0;
		while (deck.isCardAvailable()) {
			players.get(seat).acceptCard(deck.deal());
			seat = next(seat);
		}

		System.out.println("The deal:");
		for (int p = 0; p < PLAYERS; ++p) {
			System.out.format("Player %d: %s%n", p, players.get(p));
		}
		System.out.println();
	}

	/**
	 * Play out the hand: we start with the index of the player who leads;
	 * we run each trick by having the players {@link Player#play play}
	 * in turn; we judge the results of the trick and set that player to be
	 * the leader of the next trick; we print the trick contents,
	 * and count up the winners by team as we go.
	 */
	public void play() {

		for (int t = 0; t < TRICKS; ++t) {
			System.out.format("Player %d leads ... ", leader);
			int player = leader;
			Trick trick = new Trick();
			for (int c = 0; c < PLAYERS; ++c) {
				Card played = players.get(player).play(trick);
				trick.add(played);
				player = next(player);

				System.out.print(nameOf(played));
				if (c < 3) {
					System.out.print(", ");
				}
			}
			System.out.println();

			int winner = (trick.getWinner() + leader) % PLAYERS;
			tricksPerTeam.set(winner % 2, tricksPerTeam.get(winner % 2) + 1);
			leader = winner;
		}

		System.out.println();
	}

	/**
	 * Run a game by instantiating the class and calling the main lifecycle
	 * methods to deal, bid, play, and judge results.
	 * @param args
	 */
	public static void main(String[] args) {
		Bridge game = new Bridge();
		game.deal();
		game.play();
		
		List<Integer> results = game.getTricksPerTeam();
		for (int t = 0; t < 2; ++t) {
			System.out.format("Team %d won %d tricks.%n", t + 1, results.get(t));
		}

	}
}
