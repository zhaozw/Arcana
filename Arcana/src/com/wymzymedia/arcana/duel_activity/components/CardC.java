package com.wymzymedia.arcana.duel_activity.components;

import com.wymzymedia.arcana.game_utils.GameComponent;

public class CardC extends GameComponent {
	public static final String TAG = CardC.class.getSimpleName();

	// Class variables
	private int id;
	private int duration;
	private String name;

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
	public String getName() {
		return name;
	}

	// Set card id
	public void setID(int n) {
		id = n;
	}

	// Set card duration
	public void setDuration(int n) {
		duration = n;
	}

	// Set card name
	public void setName(String s) {
		name = s;
	}

	// Load card data
	// note: override in subclass to handle db implementation and extra attribs
	public void loadCard(int n) {
		// clear values
		id = -1;
		duration = 0;
		name = String.valueOf(-1);

		// set values
		if (n >= 0) {
			id = n;
			duration = 0;
			name = String.valueOf(id);
		}
	}
}
