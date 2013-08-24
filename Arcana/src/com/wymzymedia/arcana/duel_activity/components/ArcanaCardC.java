package com.wymzymedia.arcana.duel_activity.components;

public class ArcanaCardC extends CardC {
	public static final String TAG = ArcanaCardC.class.getSimpleName();

	// Class variables
	private String name;

	// Constructor
	public ArcanaCardC(int n) {
		super(n);
	}

	// Return name of card
	@Override
	public String getName() {
		return name;
	}

	// Load card data
	@Override
	public void loadCard(int n) {
		setID(n);
		if (n >= 0) {
			// set values
			// TODO implement DB interface
			switch (n) {
			case 1:
				name = "Zap";
				break;
			case 2:
				name = "Heal";
				break;
			case 3:
				name = "Shield";
				break;
			}
		} else {
			// clear values
			name = null;
		}
	}
}
