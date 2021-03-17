package cards;

import vol.Game;
import vol.Main.Sayable;

public enum Rank {

	TWO(2, "2", Rank::cancelOne),
	THREE(3, "3", Rank::cancelTwo),
	FOUR(4, "4", Rank::pickLotsOfCards),
	FIVE(5, "5", Rank::swap),
	SIX(6, "6", Rank::replayCard),
	SEVEN(7, "7", Rank::luckySeven),
	EIGHT(8, "8", Rank::pickedEight),
	NINE(9, "9", Rank::opponentReplayCards),
	TEN(10, "10", Rank::iForgot),
	JACK(11, "J", Rank::giveJackToOpponent),
	QUEEN(12, "Q", Rank::pickedQueen),
	KING(13, "K", Rank::cancelCardNoCounter),
	ACE(1, "A", Rank::cancelCard),
	JOKER(0, "!", Rank::jokersTrick);

	private int value;
	private String name;
	private Sayable effect;

	Rank(int value, String name, Sayable effect) {
		this.value = value;
		this.name = name;
		this.effect = effect;
	}

	@Override
	public String toString() {
		return name;
	}

	public int getValue() {
		return value;
	}

	public Sayable getEffect() {
		return effect;
	}

	// Effects

	public static void cancelOne(Game game) {
		System.out.println("Cancelled a one");
	}

	public static void cancelTwo(Game game) {
		System.out.println("Cancelled a two");
	}

	public static void pickLotsOfCards(Game game) {
		System.out.println("OP effect");
	}

	public static void swap(Game game) {
		System.out.println("Swapping cards");
	}

	public static void replayCard(Game game) {
		System.out.println("Must replay a card of your choice");
	}

	public static void luckySeven(Game game) {
		System.out.println("Wait until you get 3 of them");
	}

	public static void pickedEight(Game game) {
		System.out.println("Wait until you get 4 of them");
	}

	public static void opponentReplayCards(Game game) {
		System.out.println("Opponent must replay a card of their choice");
	}

	public static void iForgot(Game game) {
		System.out.println("What does a 10 do again?");
	}

	public static void giveJackToOpponent(Game game) {
		System.out.println("Immediately give this card to your opponent");
	}

	public static void pickedQueen(Game game) {
		System.out.println("Wait until you get 2, or check if you win");
	}

	public static void cancelCardNoCounter(Game game) {
		System.out.println("Cancel a card with no counter, or check if you win");
	}

	public static void cancelCard(Game game) {
		System.out.println("Cancel a card");
	}

	public static void jokersTrick(Game game) {
		System.out.println("Joker's trick");
	}

}
