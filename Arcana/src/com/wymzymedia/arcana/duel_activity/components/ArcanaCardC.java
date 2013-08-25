package com.wymzymedia.arcana.duel_activity.components;

public class ArcanaCardC extends CardC {
	public static final String TAG = ArcanaCardC.class.getSimpleName();

	// Class variables
	private String name;
	private String reqStr;
	private String costStr;
	private String effectStr;

	// Constructor
	public ArcanaCardC(int n) {
		super(n);
	}

	// Return req string
	public String getReqStr() {
		return reqStr;
	}

	// Return cost string
	public String getCostStr() {
		return costStr;
	}

	// Return effect string
	public String getEffectStr() {
		return effectStr;
	}

	// Set req string
	public void setReqStr(String r) {
		reqStr = r;
	}

	// Set cost string
	public void setCostStr(String c) {
		costStr = c;
	}

	// Set effect string
	public void setEffectStr(String e) {
		effectStr = e;
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
				reqStr = "power:1";
				costStr = "power:-1";
				effectStr = "damage:1";
				break;
			case 2:
				name = "Heal";
				reqStr = "power:2";
				costStr = "power:-2";
				effectStr = "life:1";
				break;
			case 3:
				name = "Shield";
				reqStr = "power:3";
				costStr = "power:-3";
				effectStr = "shield:1";
				break;
			}
		} else {
			// clear values
			name = null;
			reqStr = null;
			costStr = null;
		}
	}
}
