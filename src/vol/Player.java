package vol;

import java.util.ArrayList;

import cards.Card;
import cards.CardDeck;

public class Player {

	private String name;
	private CardDeck hand;

	/**
	 * The constructor.
	 * 
	 * @param name
	 */
	public Player(String name) {
		this.name = name;
		this.hand = new CardDeck(new ArrayList<Card>()); // Empty hand
	}

	/**
	 * Picks n cards from a bigger deck and adds them to the player's hand
	 * 
	 * @param n
	 *            the number of cards to pick
	 * @param biggerDeck
	 *            the deck to pick from
	 * @throws Exception
	 *             if the bigger deck doesn't have enough cards
	 */
	public void pickNCards(int n, CardDeck biggerDeck) throws Exception {
		hand.addCards(biggerDeck.pickNCards(n));
	}

	public CardDeck getHand() {
		return hand;
	}

	public String getName() {
		return name;
	}

}
