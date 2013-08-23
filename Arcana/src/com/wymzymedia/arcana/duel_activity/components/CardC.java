package com.wymzymedia.arcana.duel_activity.components;

import com.wymzymedia.arcana.game_utils.GameComponent;

public class CardC extends GameComponent {
	public static final String TAG = CardC.class.getSimpleName();

	// Class variables
	private int id;
	private int duration;

	// Constructor
	public CardC(int n) {
		setID(n);
	}

	// Return card id
	public int getID() {
		return id;
	}

	// Return card duration
	public int getDuration() {
		return duration;
	}

	// Set card id
	public void setID(int n) {
		id = n;
		if (id >= 0) {
			// load card data
			duration = 0;
		} else {
			// clear card data
			duration = 0;
		}
	}

	// Set card duration
	public void setDuration(int n) {
		duration = n;
	}
}
