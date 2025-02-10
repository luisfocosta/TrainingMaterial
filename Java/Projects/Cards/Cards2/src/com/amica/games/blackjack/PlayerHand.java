package com.amica.games.blackjack;

import static com.amica.games.CardFormatter.abbreviationOf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.amica.games.Card;
import com.amica.games.Card.Spot;
import com.amica.games.blackjack.Blackjack.Action;

/**
 * Models the cards in a player's hand, keeping reference to an owning
 * {@link Player}. This is distinct from the player, especially because we can
 * split a hand into two new ones, so there's a one-to-many relationship there.
 * But the "hand" really acts as the player's agent, making decisions about
 * surrender, double, split, and hit/stand.
 * 
 * @author Will Provost
 */
public class PlayerHand {

	private Player owner;
	private int wager;
	private List<Card> cards = new ArrayList<>();
	private Card dealerCard;
	private int ID;
	private boolean doubled;

	/**
	 * Create a hand with reference to the owner, a wager, the first card,
	 * and reference to the dealer's up card.
	 */
	public PlayerHand(Player owner, int wager, Card firstCard, Card dealerCard) {
		this.owner = owner;
		this.wager = wager;
		cards.add(firstCard);
		this.dealerCard = dealerCard;
	}

	/**
	 * Accessor for the owning player.
	 */
	public Player getOwner() {
		return owner;
	}
	
	/**
	 * Accessor for the wager.
	 */
	public int getWager() {
		return wager;
	}
	
	/**
	 * Accessor for the hand ID.
	 */
	public int getID() {
		return ID;
	}
	
	/**
	 * Mutator for the hand ID -- this is set and then used by the 
	 * {@link Blackjack} game to distinguish between split hands in its logging. 
	 */
	public void setID(int ID) {
		this.ID = ID;
	}

	/**
	 * Accessor for our "doubled" flag.
	 */
	public boolean isDoubled() {
		return doubled;
	}
	
	public boolean dealerHasAtLeast(Spot spot) {
		return dealerCard.getSpot().compareTo(spot) >= 0;
	}

	/**
	 * Receives the second of the initial cards, and makes a decision to
	 * surreneder, double, split, or play on. Note that this method does not
	 * effect any of these actions: the dealer/game that calls this method
	 * will follow up by calling e.g. {@link #doble()} or {@link #hit(Card)}.
	 */
	public Action secondCard(Card card) {
		
		cards.add(card);
		
		final ArrayList<Spot> splits = new ArrayList<>();
		Collections.addAll(splits, Spot._2, Spot._3, 
				Spot._6, Spot._7, Spot._8, Spot._9, Spot.ACE);
		
		int points = getPoints();
		if (points == 10 || points == 11) {
			return Action.DOUBLE;
		} else if (points == 16 && dealerHasAtLeast(Spot._9)) {
			return Action.SURRENDER;
		} else if (cards.get(0).getSpot() == cards.get(1).getSpot() &&
				splits.contains(cards.get(0).getSpot())) {
			return Action.SPLIT;
		}

		return Action.NONE;
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
	
	/**
	 * Sets the hand as doubled and provides the third and final card.
	 */
	public void dubble(Card lastCard) {
		cards.add(lastCard);
		doubled = true;
	}
	
	/**
	 * Create two new hands, each getting one of our initial cards.
	 * Return a list of these two hands, which will replace this object
	 * in the ongoing game.
	 * @return
	 */
	public List<PlayerHand> split() {
		List<PlayerHand> splitHands = new ArrayList<>();
		splitHands.add(new PlayerHand(owner, wager, cards.get(0), dealerCard));
		splitHands.add(new PlayerHand(owner, wager, cards.get(1), dealerCard));
		return splitHands;
	}
	
	/**
	 * We choose to hit on a total less than 21, or on a total less than 17
	 * if the dealer is showing a 7 or better.
	 */
	public boolean shouldHit() {
		int points = getPoints();
		return points < 12 || (points < 17 && dealerHasAtLeast(Spot._7));
	}
	
	/**
	 * Accept the given card into the hand.
	 */
	public void hit(Card card) {
		cards.add(card);
	}
	
	/**
	 * Report our gain or loss to the owner.
	 */
	public void winOrLose(int gainOrLoss) {
		owner.winOrLose(gainOrLoss);
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
