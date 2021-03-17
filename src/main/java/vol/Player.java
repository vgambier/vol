package vol;

import java.util.ArrayList;
import cards.Card;
import cards.CardDeck;

public class Player {

  private String name;
  private CardDeck hand; // Active part of the deck
  private CardDeck inactiveCards; // Inactive part of the deck

  private boolean canPlayTwice;
  private boolean isSilenced;
  private int nbHearts;
  private int nbSeven; // TODO it might be smart for some of these to NOT be stored as variables?
  private int nbEight;
  private int nbUseableDiamonds;
  private int nbUseableQueens;

  /**
   * The constructor.
   * 
   * @param name
   */
  public Player(String name) {
    this.name = name;
    this.hand = new CardDeck(new ArrayList<Card>()); // Empty hand
    this.inactiveCards = new CardDeck(new ArrayList<Card>()); // Ditto

    this.canPlayTwice = false;
    this.isSilenced = false;
    this.nbHearts = 0;
    this.nbUseableDiamonds = 0;
    this.nbUseableQueens = 0;
  }

  /**
   * Picks n cards from a bigger deck and adds them to the player's hand
   * 
   * @param n the number of cards to pick
   * @param biggerDeck the deck to pick from
   * @throws Exception if the bigger deck doesn't have enough cards
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

  public boolean canPlayTwice() {
    return canPlayTwice;
  }

  public void setCanPlayTwice(boolean canPlayTwice) {
    this.canPlayTwice = canPlayTwice;
  }

  @Override
  public String toString() {
    return name;
  }

}
