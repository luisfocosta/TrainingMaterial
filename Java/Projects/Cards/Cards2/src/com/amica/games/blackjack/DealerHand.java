package com.amica.games.blackjack;

import static com.amica.games.CardFormatter.abbreviationOf;

import java.util.ArrayList;
import java.util.List;

import com.amica.games.Card;
import com.amica.games.Card.Spot;

/**
 * A blackjack hand that only evaluates points and hits on anything under 17.
 *
 * @author Will Provost
 */
public class DealerHand {

	private List<Card> cards = new ArrayList<>();
	
	public DealerHand(Card card1, Card card2) {
		cards.add(card1);
		cards.add(card2);
	}
	
	public Card getFaceUpCard() {
		return cards.get(0);
	}
	
	/**
	 * Count up points, with caes worth 1 point; and separately count aces.
	 * Then optimize by counting as many aces as 11 as we can.
	 */
	public int getPoints() {
		int points = 0;
		int aces = 0;
		
		// Count up the minimum points, with aces valued at 1
		for (Card card : cards) {
			Card.Spot spot = card.getSpot();
			if (spot == Card.Spot.ACE) {
				++points;
				++aces;
			} else if (spot.ordinal() > 8) {
				points += 10;
			} else {
				points += spot.ordinal() + 2;
			}
		}
		
		// Now take advantage of any and all aces, if it doesn't bust us
		while (aces-- > 0) {
			if (points < 12) {
				points += 10;
			}
		}
		
		return points;
	}
	
	/**
	 * Two cards totaling 21 can only be an ace and a 10 -- blackjack.
	 */
	public boolean isBlackjack() {
		return cards.size() == 2 && getPoints() == 21;		
	}

	/**
	 * Anything over 21 is bust.
	 * @return
	 */
	public boolean isBust() {
		return getPoints() > 21;
	}
	
	public boolean shouldHit() {
		return getPoints() < 17;
	}

	/**
	 * Accept the given card into the hand.
	 */
	public void hit(Card card) {
		cards.add(card);
	}
	
	/**
	 * Represent the spot values of all of our cards. 
	 */
	@Override
	public String toString() {
		String result = "";
		for (Card card : cards) {
			result += " " + abbreviationOf(card.getSpot());
		}
		
		return result.substring(1);
	}
}
