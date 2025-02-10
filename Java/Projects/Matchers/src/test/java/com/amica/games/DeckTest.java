package com.amica.games;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;

import com.amica.games.Card.Spot;
import com.amica.games.Card.Suit;

/**
 * An illustration of a custom matcher that aggregates built-in matchers.
 * 
 * @author Will Provost
 */
public class DeckTest {

	public static Matcher<Card> hasSuitAndSpot(Suit suit, Spot spot) {
		
//		Compiler sees this as a Matcher<Object>:
//		return both(hasProperty("suit", equalTo(suit)))
//				.and(hasProperty("spot", equalTo(spot)));
			
		return allOf(instanceOf(Card.class),
				hasProperty("suit", equalTo(suit)),
				hasProperty("spot", equalTo(spot)));
	}
	
	@Test
	public void testSporting() {
		Deck deck = new Deck();
		Card card = deck.deal(52).stream().sorted().findFirst().get();
		assertThat(card, hasSuitAndSpot(Suit.CLUBS, Spot._2));
		
	}
}
