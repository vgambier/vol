package cards;

import vol.Game;
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

	public static void opponentPlaysTwice(Game game) {
		System.out.println("The opponent will play twice next turn");
	}

	public static void pickedDiamond(Game game) {
		System.out.println("Picked a diamond");
	}

	public static void pickedHeart(Game game) {
		System.out.println("Picked a heart");
	}

	public static void giveCardtoOpponent(Game game) {
		System.out.println("Must give card to opponent");
	}

}
