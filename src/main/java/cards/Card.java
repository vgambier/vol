package cards;

public class Card {

  private Color color;
  private Rank rank;

  public Card(Color color, Rank rank) {
    this.color = color;
    this.rank = rank;
  }

  @Override
  public String toString() {
    return color.toString() + rank.toString();
  }

  public Rank getRank() {
    return rank;
  }

  public Color getColor() {
    return color;
  }

}
