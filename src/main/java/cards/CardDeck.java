package cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class CardDeck {

  private ArrayList<Card> cards;

  /**
   * Constructor
   * 
   * @param cards the list of cards that constitute the deck
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
        if (rank == Rank.JOKER)
          break; // exclude jokers for now
        cards.add(new Card(color, rank));
      }
    }

    cards.add(new Card(Color.HEARTS, Rank.JOKER)); // TODO Joker is neither HEARTS nor SPADES...
    cards.add(new Card(Color.SPADES, Rank.JOKER)); // inheritance with Joker being a separate class?
                                                   // Card > StandardCard + JokerCard

    this.cards = cards;
  }

  /**
   * TODO write this javadoc
   */
  public void shuffle() {
    Collections.shuffle(cards);
  }

  /**
   * TODO write this javadoc
   */
  public List<Card> pickNCards(int n) throws Exception {

    if (n > cards.size())
      throw new Exception("Cannot pick more cards than there are in the deck");

    ArrayList<Card> pickedCards = new ArrayList<Card>();

    for (int i = 0; i < n; i++) {
      int randomIndex = ThreadLocalRandom.current().nextInt(0, cards.size());
      pickedCards.add(cards.remove(randomIndex));
    }

    return pickedCards;
  }

  /**
   * TODO write this javadoc
   */
  public void addCards(List<Card> addedCards) {
    cards.addAll(addedCards);
  }

  /**
   * Getter for cards.
   * 
   * @return cards
   */
  public ArrayList<Card> getCards() {
    return cards;
  }

  @Override
  public String toString() {

    // TODO stream magic

    StringBuilder str = new StringBuilder();

    for (Card card : cards) {
      str.append(card.toString());
    }

    return str.toString();
  }

}
