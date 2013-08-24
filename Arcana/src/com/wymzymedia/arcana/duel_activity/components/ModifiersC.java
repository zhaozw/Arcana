package com.wymzymedia.arcana.duel_activity.components;

import com.wymzymedia.arcana.game_utils.GameComponent;

public class ModifiersC extends GameComponent {
	public static final String TAG = ModifiersC.class.getSimpleName();

	// Class variables
	private int drawNum;

	// Constructor
	public ModifiersC() {
		drawNum = 1;

	}

	// Return number of cards to draw
	public int getDrawNum() {
		return drawNum;
	}

	// Set number of cards to draw
	public void setDrawNum(int n) {
		drawNum = n;
	}
}
