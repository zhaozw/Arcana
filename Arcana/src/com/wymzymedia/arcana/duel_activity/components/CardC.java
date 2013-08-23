package com.wymzymedia.arcana.duel_activity.components;

import com.wymzymedia.arcana.game_utils.GameComponent;

public class CardC extends GameComponent {
	public static final String TAG = CardC.class.getSimpleName();

	// Class variables
	private int id;
	private int duration;

	// Constructor
	public CardC(int n) {
		loadCard(n);
	}

	// Return card id
	public int getID() {
		return id;
	}

	// Return card duration
	public int getDuration() {
		return duration;
	}

	// Return name of card
	// note: override in subclass to return readable name
	public String getName() {
		return String.valueOf(id);
	}

	// Set card id
	public void setID(int n) {
		id = n;
	}

	// Set card duration
	public void setDuration(int n) {
		duration = n;
	}

	// Load card data
	// note: override in subclass to handle db implementation and extra attribs
	public void loadCard(int n) {
		setID(n);
		if (n >= 0) {
			// set values
			duration = 0;
		} else {
			// clear values
			duration = 0;
		}
	}
}
