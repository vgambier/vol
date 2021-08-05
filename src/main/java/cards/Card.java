package cards; // TODO rename packages

public class Card {

  private Color color;
  private Rank rank;
  // TODO: 2 attributes that let you know if the corresponding effect has been used up

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
