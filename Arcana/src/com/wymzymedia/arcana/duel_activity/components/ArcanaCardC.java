package com.wymzymedia.arcana.duel_activity.components;

public class ArcanaCardC extends CardC {
	public static final String TAG = ArcanaCardC.class.getSimpleName();

	// Class variables
	private String name;
	private String reqStr;
	private String costStr;
	private String modStr;
	private String effectStr;
	private String upkeepStr;
	private String discardStr;

	// Constructor
	public ArcanaCardC(int n) {
		super(n);
	}

	// Return requirement string of status needed to play card
	public String getReqStr() {
		return reqStr;
	}

	// Return cost string of changes to make when activating card
	public String getCostStr() {
		return costStr;
	}

	// Return mod string of changes to make during select phase
	public String getModStr() {
		return modStr;
	}

	// Return effect string of changes to make during play phase
	public String getEffectStr() {
		return effectStr;
	}

	// Return upkeep string of changes to make during upkeep phase
	public String getUpkeepStr() {
		return upkeepStr;
	}

	// Return discard string of changes to make when discarding card
	public String getDiscardStr() {
		return discardStr;
	}

	// Set requirements string
	public void setReqStr(String s) {
		reqStr = s;
	}

	// Set cost string
	public void setCostStr(String s) {
		costStr = s;
	}

	// Set mod string
	public void setModStr(String s) {
		modStr = s;
	}

	// Set effect string
	public void setEffectStr(String s) {
		effectStr = s;
	}

	// Set upkeep string
	public void setUpkeepStr(String s) {
		upkeepStr = s;
	}

	// Set discard string
	public void setDiscardStr(String s) {
		discardStr = s;
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

		// clear values
		reqStr = null;
		costStr = null;
		modStr = null;
		effectStr = null;
		upkeepStr = null;
		discardStr = null;

		// set values
		if (n >= 0) {
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
				modStr = "shield:1";
				discardStr = "shield:-1";
				break;
			}
		}
	}
}
