package com.amica.games.poker;

import static com.amica.games.CardFormatter.abbreviationOf;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.amica.games.Card;
import com.amica.games.Card.Spot;
import com.amica.games.Card.Suit;

/**
 * Models a player and that player's hand, in draw poker.
 * We can evaluate the rank of the hand (pair, two pair, etc.)
 * and we can make good decisions about what cards to draw to improve the hand.
 * 
 * @author Will Provost
 */
public class Player extends TreeSet<Card> {

	public enum Rank { 
		NO_PAIR,
		ONE_PAIR, 
		TWO_PAIR, 
		THREE_OF_A_KIND, 
		STRAIGHT,
		FLUSH,
		FULL_HOUSE,
		FOUR_OF_A_KIND,
		STRAIGHT_FLUSH
	}
	
	/**
	 * Create a player with 5 initial cards.
	 * We sort them into our hand by spot value. 
	 */
	public Player(Collection<Card> cards) {
		super(Comparator.comparing
				(c -> c.getSpot().ordinal() * 4 + c.getSuit().ordinal()));
		addAll(cards);
	}
	
	/**
	 * Compile a map of spot values found in the hand and the counts of each.
	 */
	private Map<Spot,Integer> getCounts() {
		Map<Spot,Integer> result = new HashMap<>();
		for (Card card : this) {
			Spot spot = card.getSpot();
			if (!result.containsKey(spot)) {
				result.put(spot, 0);
			}
			result.put(spot, result.get(spot) + 1);
		}
		return result;
	}
	
	/**
	 * Evaluate the hand. First, look for spot counts higher than one,
	 * and regognize based on count combinations as one pair, two pair,
	 * three of a kind, full house, or four of a kind.
	 * Then look for straights and flushes.
	 * @return
	 */
	public Rank getRank() {
		
		Map<Spot,Integer> spotsAndCounts = getCounts();
		
		Collection<Integer> counts = spotsAndCounts.values(); 
		if (counts.contains(4)) {
			return Rank.FOUR_OF_A_KIND;
		}
		
		if (counts.contains(3)) {
			return counts.contains(2) 
					? Rank.FULL_HOUSE 
					: Rank.THREE_OF_A_KIND;
		}
		
		if (counts.contains(2)) {
			return counts.size() == 3 // would have to be 2, 2, 1
					? Rank.TWO_PAIR
					: Rank.ONE_PAIR;
		}
		
		boolean straight = last().getSpot().ordinal() == first().getSpot().ordinal() + 4;
		
		boolean flush = true;
		Card.Suit suit = first().getSuit();
		for (Card card : this) {
			if (card.getSuit() != suit) {
				flush = false;
				break;
			}
		}
		
		return straight
			? (flush ? Rank.STRAIGHT_FLUSH : Rank.STRAIGHT)
			: (flush ? Rank.FLUSH : Rank.NO_PAIR); 
	}
	
	private enum StraightChance { OUTSIDE, INSIDE, NONE }
	
	/**
	 * Helper to determine if a given four cards (the current hand, excluding
	 * the given card) form an inside or outside straight. 
	 * @return
	 */
	private StraightChance getStraightChance(Card toExclude) {
		
		StraightChance result = StraightChance.NONE;
		remove(toExclude);
		
		int span = last().getSpot().ordinal() - first().getSpot().ordinal();
		if (span < 5) {
			result = span == 3 ? StraightChance.OUTSIDE : StraightChance.INSIDE;
		}
		
		add(toExclude);
		return result;
	}
	
	/**
	 * Determine which cards to draw, based on current rank and which cards
	 * form the basis of that rank. 
	 */
	private Set<Card> getCardsToDrop() {
		Set<Card> result = new HashSet<>();
		Map<Spot,Integer> counts = getCounts();
		Rank rank = getRank();
		
		if (rank == Rank.THREE_OF_A_KIND || 
				rank == Rank.TWO_PAIR || rank == Rank.ONE_PAIR) {
			for (Card card : this) {
				if (counts.get(card.getSpot()) == 1) {
					result.add(card);
				}
			}
		}
		if (rank == Rank.NO_PAIR) {
			
			// Look for a possible flush
			Map<Suit,Set<Card>> bySuit = new HashMap<>();
			for (Suit suit : Suit.values()) {
				bySuit.put(suit, new HashSet<>());
			}
			int mostInASuit = 0;
			for (Card card : this) {
				Suit suit = card.getSuit();
				bySuit.get(suit).add(card);
				if (bySuit.get(suit).size() > mostInASuit) {
					++mostInASuit;
				}
			}
			
			// Look for a possible straight
			StraightChance straightChance = getStraightChance(first());
			Card straightCandidate = null;
			if (straightChance != StraightChance.NONE) {
				straightCandidate = first();
			} else {
				straightChance = getStraightChance(last());
				if (straightChance != StraightChance.NONE) {
					straightCandidate = last();
				}
			}
			
			// Best  chances first: one card to a flush ...
			if (mostInASuit == 4) {
				for (Card card : this) {
					if (bySuit.get(card.getSuit()).size() != 4) {
						result.add(card);
					}
				}
			// ... one card to a straight ...
			} else if (straightChance != StraightChance.NONE) {
				result.add(straightCandidate);
			// ...two cards to a flush ... 
			} else if (mostInASuit == 3) {
				for (Card card : this) {
					if (bySuit.get(card.getSuit()).size() != 3) {
						result.add(card);
					}
				}
			// ... or just throw the three lowest ...
			} else {
				int i = 0;
				for (Card card : this) {
					if (i++ < 3) {
						result.add(card);
					}
				}
			}		}
		
		return result;
	}
	
	/**
	 * Get the number of cards to draw. 
	 */
	public int getNumberToDraw() {
		return getCardsToDrop().size();
	}
	
	/**
	 * Carry out the draw, by dropping cards and accepting an equal number
	 * of new ones. 
	 */
	public void draw(Collection<Card> newCards) {
		removeAll(getCardsToDrop());
		addAll(newCards);
	}
	
	/**
	 * Show the cards int he hand.
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (Card card : this) {
			builder.append(abbreviationOf(card)).append(" ");
		}
		return builder.toString();
	}
}
