package com.wymzymedia.arcana.duel_activity.components;

public class ArcanaCardC extends CardC {
	public static final String TAG = ArcanaCardC.class.getSimpleName();

	// Class variables
	private String name;
	private String reqStr;

	// Constructor
	public ArcanaCardC(int n) {
		super(n);
	}

	// Return req string
	public String getReqStr() {
		return reqStr;
	}

	// Set req string
	public void setReqStr(String r) {
		reqStr = r;
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
				reqStr = "power:1;life:1";
				break;
			case 2:
				name = "Heal";
				reqStr = "power:1;life:2";
				break;
			case 3:
				name = "Shield";
				reqStr = "power:1;life:3";
				break;
			}
		} else {
			// clear values
			name = null;
		}
	}
}
