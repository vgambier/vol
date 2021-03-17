package vol;

import java.util.ArrayList;
import cards.Card;
import cards.CardDeck;
import cards.Color;
import cards.Rank;

public class Game {

  private ArrayList<Player> players;
  private Player currentPlayer;

  public Game(ArrayList<Player> players) throws Exception {

    if (players.size() < 2)
      throw new Exception("There must be at least 2 players.");
    // TODO check that all the players are different (use an ordered set?)

    this.players = players;
    this.currentPlayer = players.get(0); // TODO nose rule

  }

  /**
   * TODO write this javadoc
   */
  public void start() throws Exception {

    // TODO: two modes, one is silent with no instructions on what is going on

    CardDeck cardDeck = new CardDeck();
    cardDeck.shuffle();

    boolean isOver = false;

    while (!isOver) {

      for (Player player : players) {

        currentPlayer = player;

        boolean canPlay = true;
        while (canPlay) {

          // Pick a card
          player.pickNCards(1, cardDeck);

          // Show hand
          System.out.print("Current hand of " + player.getName() + ": ");
          System.out.println(player.getHand().toString());

          // Play cards
          for (Card card : player.getHand().getCards()) {

            // TODO let players choose the order of their plays

            Rank rank = card.getRank();
            System.out.print(rank + ": ");
            rankEffect(rank);

            Color color = card.getColor();
            System.out.print(color + ": ");
            colorEffect(color);

            // TODO use up the card by moving it to another array
            // TODO how to distinguish between the used up color and used up rank? the whole must be
            // remembered
          }

          // Don't let the player play again if they're not under the effect of a club
          if (!player.canPlayTwice()) {
            canPlay = false;
          } else {
            player.setCanPlayTwice(false); // The player gets only one extra turn
          }

        }
      }

      isOver = true; // TODO temp

    }

  }

  /**
   * Find the player that will play or attempt to play after the current player has finished their
   * turn or turns
   * 
   * @return the player that will play next
   */
  private Player nextPlayer() {

    int currentPlayerIndex = players.indexOf(currentPlayer);
    return players.get(currentPlayerIndex == players.size() - 1 ? 0 : currentPlayerIndex + 1);
  }

  // TODO maybe write javadoc for all effects

  private void rankEffect(Rank rank) throws Exception {

    switch (rank) {
      case TWO:
        cancelOne();
        break;
      case THREE:
        cancelTwo();
        break;
      case FOUR:
        pickLotsOfCards();
        break;
      case FIVE:
        swap();
        break;
      case SIX:
        replayCard();
        break;
      case SEVEN:
        luckySeven();
        break;
      case EIGHT:
        pickedEight();
        break;
      case NINE:
        opponentReplayCards();
        break;
      case TEN:
        becomeSilenced();
        break;
      case JACK:
        giveJackToOpponent();
        break;
      case QUEEN:
        pickedQueen();
        break;
      case KING:
        cancelCardNoCounter();
        break;
      case ACE:
        cancelCard();
        break;
      case JOKER:
        jokersTrick();
        break;
      default:
        throw new Exception("Unexpected rank!"); // TODO better exception handling
    }
  }

  private void colorEffect(Color color) throws Exception {

    switch (color) {
      case CLUBS:
        opponentPlaysTwice();
        break;
      case DIAMONDS:
        pickedDiamond();
        break;
      case HEARTS:
        pickedHeart();
        break;
      case SPADES:
        giveCardtoOpponent();
        break;
      default:
        throw new Exception("Unexpected color!");
    }
  }

  // Rank effects

  private void cancelOne() {
    // TODO implement cancelOne
    System.out.println("Cancelled a one");
  }

  private void cancelTwo() {
    // TODO implement cancelTwo
    System.out.println("Cancelled a two");
  }

  private void pickLotsOfCards() {
    // TODO implement pickLotsOfCards
    // TODO implement secondary 8 4 effect
    System.out.println("OP effect");
  }

  private void swap() {
    // TODO implement swap
    System.out.println("Swapping cards");
  }

  private void replayCard() {
    // TODO implement replayCard
    System.out.println("Must replay a card of your choice");
  }

  private void luckySeven() {
    // TODO implement luckySeven
    System.out.println("Wait until you get 3 of them");
    // nbSeven++;
  }

  private void pickedEight() {
    // TODO implement pickedEight
    System.out.println("Wait until you get 4 of them");
    // nbEight++;
  }

  private void opponentReplayCards() {
    // TODO implement opponentReplayCards
    System.out.println("Opponent must replay a card of their choice");
  }

  private void becomeSilenced() {
    // TODO implement becomeSilenced
    System.out.println("You cannot play until you make a correct prediction.");
    // isSilenced = true;
  }

  private void giveJackToOpponent() {
    // TODO implement giveJackToOpponent
    System.out.println("Immediately give this card to your opponent");
  }

  private void pickedQueen() {
    // TODO implement pickedQueen
    System.out.println("Wait until you get 2, or check if you win");
    // nbUseableQueens++;
  }

  private void cancelCardNoCounter() {
    // TODO implement cancelCardNoCounter
    System.out.println("Cancel a card with no counter, or check if you win");
  }

  private void cancelCard() {
    // TODO implement cancelCard
    System.out.println("Cancel a card");
  }

  private void jokersTrick() {
    // TODO implement jokersTrick
    System.out.println("Joker's trick");
  }

  // Color effects

  private void opponentPlaysTwice() {
    Player nextPlayer = nextPlayer();
    System.out.printf("%s will play twice when it is their turn\n", nextPlayer);
    nextPlayer.setCanPlayTwice(true);
  }

  private void pickedDiamond() {
    // TODO implement pickedDiamond
    System.out.println("Picked a diamond");
    // nbUseableDiamonds++;
  }

  private void pickedHeart() {
    // TODO implement pickedHeart
    System.out.println("Picked a heart");
    // nbHearts++;
  }

  private void giveCardtoOpponent() {
    // TODO implement giveCardtoOpponent
    System.out.println("Must give card to opponent");
  }

}
