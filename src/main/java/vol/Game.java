package vol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import cards.Card;
import cards.CardDeck;
import cards.Color;
import cards.Rank;

public class Game {

  private static Scanner scanner = new Scanner(System.in);

  private CardDeck cardDeck;
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

    cardDeck = new CardDeck();
    cardDeck.shuffle();

    boolean isOver = false;

    while (!isOver) {

      for (Player player : players) {

        currentPlayer = player;
        boolean canPlay = true;

        while (canPlay) {

          // Pick a card
          player.pickNCardsFromDeck(1, cardDeck);

          // Show hand
          System.out.printf("%s's turn\n", player.getName());
          System.out.printf("Available cards: %s\n", player.getHand().toString());
          CardDeck inactiveCards = player.getInactiveCards();
          if (!inactiveCards.getCards().isEmpty()) {
            System.out.printf("Unavailable cards: %s\n", inactiveCards.toString());
          }

          // Play cards
          for (int i = 0; i < player.getHand().getCards().size(); i++) {

            Card card = player.getHand().getCards().get(i);

            // TODO let players choose the order of their plays

            Rank rank = card.getRank();
            System.out.print(rank + ": ");
            rankEffect(rank);

            Color color = card.getColor();
            System.out.print(color + ": ");
            colorEffect(color);

            // Move the used card from the player's hand to the player's inactive cards
            player.getHand().getCards().remove(card);
            player.getInactiveCards().addCards(Collections.singletonList(card));

          }

          optionalActions(); // TODO technically the player should be able to do this at any time
          // when the "let the player choose order" feature is implemented, change this as well

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

  private void optionalActions() throws Exception {

    // TODO what if the player has 4 queens and want to use the queen power twice
    // (similar issue for the other powers)

    if (currentPlayer.getNbSeven() >= 4 && askUser("You have 4 sevens. Would you like to peek at the deck?")) {
      // TODO: peek at the deck
      // TODO: decrease the number of sevens (substract by 3)
      // TODO: make it impossible for this to happen again, according to the rules
    }

    if (currentPlayer.getNbEight() >= 4 && askUser("You have 4 eights. Would you like to win?")) {
      // TODO: win
    }

    if (currentPlayer.getNbUseableQueens() >= 4 && askUser("You have 2 queens. Would you like to pick 4 cards?")) {
      currentPlayer.pickNCardsFromDeck(4, cardDeck);
      // TODO make the player play them
    }

    if (currentPlayer.getNbHearts() > 0) {
      // TODO: heart behavior
    }

    if (currentPlayer.getNbUseableDiamonds() >= 4 && askUser("You have 4 diamonds. Would you like to pick 4 cards?")) {
      currentPlayer.pickNCardsFromDeck(4, cardDeck);
      // TODO make the player play them
    }

  }

  /**
   * Asks the user the input question. Only accepted answers are "Y" and "N".
   * 
   * @param message
   * @return true if the user replied "Y", false if the they replied "N"
   */
  private boolean askUser(String message) {
    String userInput = null;
    while ("Y".equals(userInput) || "N".equals(userInput)) {

      if (userInput != null) {
        System.out.print("Invalid input"); // This only shows up after the second time.
      }
      userInput = "";

      System.out.println(message + " (Y/N)");
      userInput = scanner.nextLine();
    }
    return "Y".equals(userInput);
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
    currentPlayer.incrementNbSeven();
    System.out.printf("You now have %s seven(s) at your disposal.\n", currentPlayer.getNbSeven());
  }

  private void pickedEight() {
    currentPlayer.incrementNbEight();
    System.out.printf("You now have %s eight(s) at your disposal.\n", currentPlayer.getNbEight());
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
    // TODO check if you win somewhere
    currentPlayer.incrementNbUseableQueens();
    System.out.printf("You now have %s queen(s) at your disposal.\n", currentPlayer.getNbUseableQueens());
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
    System.out.printf("%s will play twice when it is their turn.\n", nextPlayer);
    nextPlayer.setCanPlayTwice(true);
  }

  private void pickedDiamond() {
    currentPlayer.incrementNbUseableDiamonds();
    System.out.printf("You now have %s diamond(s) at your disposal.\n", currentPlayer.getNbUseableDiamonds());
  }

  private void pickedHeart() {
    currentPlayer.incrementNbHearts();
    System.out.printf("You now have %s heart(s) at your disposal.\n", currentPlayer.getNbHearts());
  }

  private void giveCardtoOpponent() {
    // TODO implement giveCardtoOpponent
    System.out.println("Must give card to opponent");
  }

}
