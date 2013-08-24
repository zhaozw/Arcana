package com.wymzymedia.arcana.duel_activity.components;

public class ArcanaCardC extends CardC {
	public static final String TAG = ArcanaCardC.class.getSimpleName();

	// Class variables
	private String name;
	private String phase;
	private String type;

	// Constructor
	public ArcanaCardC(int n) {
		super(n);
	}

	// Return phase
	public String getPhase() {
		return phase;
	}

	// Return type
	public String getType() {
		return type;
	}

	// Set phase
	public void setPhase(String p) {
		phase = p;
	}

	// Set type
	public void setType(String t) {
		type = t;
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
				phase = "play";
				type = "damage";
				break;
			case 2:
				name = "Heal";
				phase = "play";
				type = "heal";
				break;
			case 3:
				name = "Shield";
				phase = "play";
				type = "shield";
				break;
			}
		} else {
			// clear values
			name = null;
			phase = null;
			type = null;
		}
	}
}
