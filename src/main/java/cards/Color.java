package cards;

public enum Color {

  CLUBS("♣"),
  DIAMONDS("♦"),
  HEARTS("♥"),
  SPADES("♠");

  private String symbol;

  Color(String symbol) {
    this.symbol = symbol;
  }

  @Override
  public String toString() {
    return symbol;
  }

}
