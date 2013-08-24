package com.wymzymedia.arcana.duel_activity.components;

public class ArcanaCardC extends CardC {
	public static final String TAG = ArcanaCardC.class.getSimpleName();

	// Class variables

	// Constructor
	public ArcanaCardC(int n) {
		super(n);
	}

	// Return name of card
	@Override
	public String getName() {
		if (getID() >= 0) {
			return "Arcana:" + getID();
		} else {
			return "";
		}
	}

	// Load card data
	@Override
	public void loadCard(int n) {
		setID(n);
		if (n >= 0) {
			// set values
		} else {
			// clear values
		}
	}
}
