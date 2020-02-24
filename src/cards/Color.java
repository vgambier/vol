package cards;

import vol.Main.Sayable;

public enum Color {

	CLUBS("♣", Color::opponentPlaysTwice),
	DIAMONDS("♦", Color::pickedDiamond),
	HEARTS("♥", Color::pickedHeart),
	SPADES("♠", Color::giveCardtoOpponent);

	private String symbol;
	private Sayable effect;

	Color(String symbol, Sayable effect) {
		this.symbol = symbol;
		this.effect = effect;
	}

	@Override
	public String toString() {
		return symbol;
	}

	public Sayable getEffect() {
		return effect;
	}

	// Effects

	public static void opponentPlaysTwice() {
		System.out.println("The opponent will play twice next turn");
	}

	public static void pickedDiamond() {
		System.out.println("Picked a diamond");
	}

	public static void pickedHeart() {
		System.out.println("Picked a heart");
	}

	public static void giveCardtoOpponent() {
		System.out.println("Must give card to opponent");
	}

}
