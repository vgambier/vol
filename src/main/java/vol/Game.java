package vol;

import java.util.ArrayList;

import cards.Card;
import cards.CardDeck;

public class Game {

	private ArrayList<Player> players;

	public Game(ArrayList<Player> players) throws Exception {

		if (players.size() < 2)
			throw new Exception("There must be at least 2 players.");

		this.players = players;
	}

	public void start() throws Exception {

		CardDeck cardDeck = new CardDeck();
		cardDeck.shuffle();

		boolean isOver = false;

		while (!isOver) {

			for (Player player : players) {

				// Pick a card
				player.pickNCards(1, cardDeck);

				// Show hand
				System.out.print("Current hand of " + player.getName() + ": ");
				System.out.println(player.getHand().toString());

				// Play cards
				for (Card card : player.getHand().getCards()) {
					card.getRank().getEffect().say(); // TODO
					card.getColor().getEffect().say();
				}

			}

			isOver = true;

		}

	}
}
