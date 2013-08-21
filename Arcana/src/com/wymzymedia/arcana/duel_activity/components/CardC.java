package com.wymzymedia.arcana.duel_activity.components;

import com.wymzymedia.arcana.game_utils.GameComponent;

public class CardC extends GameComponent {
	public static final String TAG = CardC.class.getSimpleName();

	// Class variables
	private int id;

	// Constructor
	public CardC(int n) {
		id = n;
	}

	// Return card id
	public int getID() {
		return id;
	}

	// Set card id
	public void setID(int n) {
		id = n;
		if (id >= 0) {
			// TODO load card data
		} else {
			// TODO clear card ddata
		}
	}
}
