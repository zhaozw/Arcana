package com.wymzymedia.arcana.duel_activity.components;

public class PokerCardC extends CardC {
	public static final String TAG = PokerCardC.class.getSimpleName();

	// Class variables
	private int suit;
	private int value;
	private final String[] suitMap = new String[] { "Clubs", "Diamonds",
			"Hearts", "Spades" };
	private final String[] valueMap = new String[] { "Ace", "2", "3", "4", "5",
			"6", "7", "8", "9", "10", "Jack", "Queen", "King" };

	// Constructor
	public PokerCardC(int n) {
		super(n);
		setDuration(0);
	}

	// Return suit
	public int getSuit() {
		return suit;
	}

	// Return value
	public int getValue() {
		return value;
	}

	// Return name of card
	@Override
	public String getName() {
		return valueMap[value] + " of " + suitMap[suit];
	}

	// Load card data
	@Override
	public void loadCard(int n) {
		setID(n);
		if (n >= 0) {
			// set values
			suit = n % 4;
			value = n % 13;
		} else {
			// clear values
			suit = -1;
			value = -1;
		}
	}
}
