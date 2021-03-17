package cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class CardDeck {

	private ArrayList<Card> cards;

	/**
	 * Constructor
	 * 
	 * @param cards
	 *            the list of cards that constitute the deck
	 */
	public CardDeck(ArrayList<Card> cards) {
		this.cards = cards;
	}

	/**
	 * Default constructor. Creates a deck with all 54 cards.
	 */
	public CardDeck() {

		ArrayList<Card> cards = new ArrayList<Card>();

		for (Color color : Color.values()) {
			for (Rank rank : Rank.values()) {
				if (rank.getValue() == 0)
					break; // exclude jokers for now
				cards.add(new Card(color, rank));
			}
		}

		cards.add(new Card(Color.HEARTS, Rank.JOKER));
		cards.add(new Card(Color.SPADES, Rank.JOKER));

		this.cards = cards;
	}

	public void sortByRank() {
		cards.sort((Card c1, Card c2) -> (Integer.compare(c1.getRank().getValue(), c2.getRank().getValue())));
	}

	public void shuffle() {
		Collections.shuffle(cards);
	}

	public ArrayList<Card> pickNCards(int n) throws Exception {

		if (n > cards.size())
			throw new Exception("Cannot pick more cards than there are in the deck");

		ArrayList<Card> pickedCards = new ArrayList<Card>();

		for (int i = 0; i < n; i++) {
			int randomIndex = ThreadLocalRandom.current().nextInt(0, cards.size());
			pickedCards.add(cards.remove(randomIndex));
		}

		return pickedCards;
	}

	public void addCards(ArrayList<Card> addedCards) {
		cards.addAll(addedCards);
	}
	
	public ArrayList<Card> getCards() {
		return cards;
	}

	@Override
	public String toString() {

		StringBuilder str = new StringBuilder();

		for (Card card : cards) {
			str.append(card.toString());
		}

		return str.toString();
	}

}
