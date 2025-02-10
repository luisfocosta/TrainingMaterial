package com.amica.games;

import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.amica.games.Card.Spot;
import com.amica.games.Card.Suit;

/**
 * A deck of 52 {@link Card}s which are randomly shuffled and
 * which then can be dealt out, one at a time.
 *
 * @author wprovost
 */
public class Deck implements Supplier<Card>
{
    protected List<Card> cards;

    /**
     * Fill a list with one of each possible card suit-spot combination.
     * Then randomly "suffle the deck." Set an iterator to the start of
     * the list, such that "dealing" is just taking the next card in the
     * iteration.
     */
    public Deck() {
    	cards = new ArrayList<>(Suit.values().length * Spot.values().length);
        for (Suit suit : Suit.values()) {
            for (Spot spot : Spot.values()) {
                cards.add(new Card(suit, spot));
            }
        }
        shuffle();
    }

    /**
     * Shuffle the deck randomly.
     */
    public void shuffle() {
    	Collections.shuffle(cards);
    }


    /**
     * Remove the top card from the deck, and hand it over.
     */
    public Card deal() {
    	return cards.remove(0);
    }

    @Override
    public Card get(){
        return deal();
    }

    public Stream<Card> stream() {
        return Stream.generate(this).takeWhile(c -> isCardAvailable());
    }

    /**
     * Remove N cards from the top of the deck and return them in a list.
     */
    public List<Card> deal(int howMany) {
    	List<Card> results = new ArrayList<>(howMany);
    	for (int i = 0; i < howMany; ++i) {
    		results.add(deal());
    	}

    	return results;
    }

    /**
     * Returns the number of cards left.
     */
    public int cardsLeft() {
    	return cards.size();
    }

	public static Stream<Card> bridgeHand() {
		return new Deck3().stream().limit(13);
	}
	
	public static void printCards(Stream<Card> cards) {
		cards.map(CardFormatter::nameOf)
		.forEach(System.out::println);
	}
	
    /**
     * Lets the caller know if there is still at least one card in the deck.
     */
    public boolean isCardAvailable() {
    	return !cards.isEmpty();
    }

	public static void main(String[] args) {
        System.out.println("Whole deck output: ");
        printCards(new Deck().stream());

        System.out.println();

        System.out.println("Bridge hand output: ");
        printCards(bridgeHand().sorted());

        System.out.println();

        System.out.println("Only hearts: ");
        printCards(bridgeHand()
                  .filter(c -> c.getSuit() == Suit.HEARTS)
                  .sorted());

        System.out.println("\nPoint count: ");
        int points = bridgeHand()
                               .peek(System.out::println)
                               .map(Card::getSpot)
                               .mapToInt(Spot::ordinal)
                               .filter(ord -> ord > 8)
                               .map(ord -> ord - 8)
                               .sum();
        System.out.format("%d points%n%n", points);

        // Provide a comparator for purposes of finding the max:
        System.out.println("Card with highest value in hand");
        Optional<Card> maxCard = bridgeHand().filter(c -> c.getSpot().ordinal() > 8)
                                           .max((c1, c2) -> c1.getSpot().compareTo(c2.getSpot()));

        if(maxCard.isPresent()){
            System.out.println(CardFormatter.nameOf(maxCard.get()));
        }else{
            System.out.println("No max card found");
        }
		System.out.println();

        // Alternate approach to the comparator, 
        // and note that Optionals can be mapped, safely:
		System.out.println("Highest card:");
		System.out.println(bridgeHand()
				.peek(System.out::println)
				.max(Comparator.comparing(Card::getSpot))
				.map(CardFormatter::nameOf)
				.map(s -> "Highest: " + s)
				.orElse("No cards!"));
		System.out.println();

        //Stream to unmodifiable list
        List<Card> cardList = new Deck().stream().toList();

        // Stream to list
        cardList = new Deck().stream().collect(Collectors.toList());

        // List to stream
        Stream<Card> cardStream = cardList.stream();

        Card[] cardArray = cardStream.toArray(Card[]::new);

        //Array to Stream
        cardStream = Arrays.stream(cardArray);

        //Create a stream from some values
        cardStream = Stream.of(new Card(Suit.HEARTS, Spot.ACE), new Card(Suit.CLUBS, Spot.KING));

        //Stream to Map
        Map<Suit, String> suitNames = Arrays.stream(Suit.values()).collect(Collectors.toMap(Function.identity(), CardFormatter::nameOf));

        //Map to Streams
        Stream<Map.Entry<Suit,String>> suitAndNameStream = suitNames.entrySet().stream();

        //Grouping with aggregate values
        Map<Suit,Long> counts =  bridgeHand()
                                          .collect(Collectors.groupingBy(Card::getSuit, Collectors.counting()));

       for( Map.Entry<Suit,Long> entry : counts.entrySet()){
           System.out.println("Suit: " + entry.getKey());
           System.out.println("Value: " + entry.getValue());
       }

       //Grouping with a collection of cards
       Map<Suit, List<Card>> cardsBySuit = bridgeHand()
                                                     .collect(Collectors.groupingBy(Card::getSuit, Collectors.toList()));

        for( Map.Entry<Suit, List<Card>> entry : cardsBySuit.entrySet()){
            System.out.println("\nSuit: " + entry.getKey());
            for(Card c : entry.getValue()){
                System.out.println(CardFormatter.nameOf(c));
            }
        }

		String names = "Alice Bob Carol Dierdre";
		System.out.println("Names with 'e' in them: " + 
			Arrays.stream(names.split(" "))
				.filter(n -> n.contains("e"))
				.collect(Collectors.joining(", ")));

		System.out.println("Suits with 'e' in their names: " +
			Arrays.stream(Suit.values())
				.map(CardFormatter::nameOf)
				.filter(n -> n.contains("e"))
				.collect(Collectors.joining(", ")));
	}
}

