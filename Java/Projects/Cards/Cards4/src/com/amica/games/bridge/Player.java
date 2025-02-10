package com.amica.games.bridge;

import static com.amica.games.CardFormatter.abbreviationOf;

import java.util.Collections;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import com.amica.games.Card;
import com.amica.games.Card.Suit;

/**
 * Represents one player in the game of contract bridge.
 * Can accept cards when dealt, and make decisions about what card to play
 * when its our turn.
 *
 * @author Will Provost
 */
public class Player {

	private Map<Suit,SortedSet<Card>> hand;

	/**
	 * Create the player by setting up a map of empty lists of cards,
	 * one list for each of the four suits.
	 */
	public Player() {
		hand = new TreeMap<>();
		for (Suit suit : Suit.values()) {
			hand.put(suit, new TreeSet<>(Collections.reverseOrder()));
		}
	}

	/**
	 * Add the new card to the appropriate suit.
	 */
	public void acceptCard(Card card) {
		hand.get(card.getSuit()).add(card);
	}

	/**
	 * Helper that evaluates a card's value when counting points for bidding.
	 */
    private static int getValue(Card card) {
    	return Math.max(card.getSpot().ordinal() - 9, 0);
    }

    /**
     * Count up the points in one of our suits.
     */
	private int countPoints(Suit suit) {
		int points = 0;
		for (Card card : hand.get(suit)) {
			points += getValue(card);
		}
		return points;
	}

	/**
	 * Determine our current "longest and strongest" suit, rating each card of
	 * "length" as worth about 3 high-card points.
	 */
	private Suit getLongestAndStrongest() {
		Suit bestSuit = null;
		int bestRating = 0;
		for (Suit suit : Suit.values()) {
			int length = hand.get(suit).size();
			int points = countPoints(suit);
			int rating = length + points / 3;
			if (rating > bestRating) {
				bestRating = rating;
				bestSuit = suit;
			}
		}

		return bestSuit;
	}

	/**
	 * Play a card when asked. If the trick is empty, we must be leading, so we
	 * lead the best card from our current best suit -- naive, but it will win
	 * us some tricks. If there are cards in the trick, then we try to follow suit,
	 * and we win if it if we can, play low if we can't win it. If we can't
	 * follow suit, we search the whole hand for our current lowest card,
	 * and throw that.
	 */
	public Card play(Trick trick) {

		Card toPlay = null;

		// Lead highest from our best suit
		if (trick.isEmpty()) {
			SortedSet<Card> suit = hand.get(getLongestAndStrongest()); 
			toPlay = suit.first();

		} else {
			Suit suit = trick.get(0).getSuit();
			SortedSet<Card> cardsInSuit = hand.get(suit);

			// We can't follow suit: play our lowest other card
			if (cardsInSuit.isEmpty()) {
				int lowest = 13;
				for (Suit possibleSuit : Suit.values()) {
					if (!hand.get(possibleSuit).isEmpty()) {
						Card lowestInSuit = hand.get(possibleSuit).last();
						if (lowestInSuit.getSpot().ordinal() < lowest) {
							lowest = lowestInSuit.getSpot().ordinal();
							toPlay = lowestInSuit;
						}
					}
				}
			} else {

			// If we can win it, win it, and if not, play low
				if (hand.get(suit).first().compareTo(trick.getWinningCard()) > 0) {
					toPlay = hand.get(suit).first();
				} else {
					toPlay = hand.get(suit).last();
				}
			}
		}

		hand.get(toPlay.getSuit()).remove(toPlay);
		return toPlay;
	}

	/**
	 * We represent the hand by listing all contents, sorted by suit.
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (Suit suit : Suit.values()) {
			SortedSet<Card> cardsInSuit = hand.get(suit);
			if (!cardsInSuit.isEmpty()) {
				for (Card card : cardsInSuit) {
					builder.append(abbreviationOf(card.getSpot())).append(" ");
				}
				builder.append("of ").append(suit.toString().toLowerCase()).append(" -- ");
			}
		}

		return builder.toString();
	}
}
