package vol;

import java.util.ArrayList;
import cards.CardDeck;

public class Main {

  public static void main(String[] args) throws Exception {

    CardDeck cardDeck = new CardDeck();
    System.out.println(cardDeck);
    cardDeck.shuffle(); // Everyday I'm suffling
    System.out.println(cardDeck);
    System.out.println();

    ArrayList<Player> players = new ArrayList<Player>();
    players.add(new Player("Victor"));
    players.add(new Player("Gabriel"));

    Game game = new Game(players);
    game.start();

  }

}
