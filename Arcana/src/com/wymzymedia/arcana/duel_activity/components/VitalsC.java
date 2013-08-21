package com.wymzymedia.arcana.duel_activity.components;

import com.wymzymedia.arcana.game_utils.GameComponent;

public class VitalsC extends GameComponent {
	public static final String TAG = VitalsC.class.getSimpleName();

	// Class variables
	private boolean human;
	private int phase;
	private int life;
	private int power;
	private int drawNum;

	// Constructor
	public VitalsC(boolean h, int l, int p) {
		human = h;
		phase = 1;
		life = l;
		power = p;
		drawNum = 1;
	}

	// Return human flag
	public boolean isHuman() {
		return human;
	}

	// Return player phase
	public int getPhase() {
		return phase;
	}

	// Return life
	public int getLife() {
		return life;
	}

	// Return power
	public int getPower() {
		return power;
	}

	// Return number of cards to draw
	public int getDrawNum() {
		return drawNum;
	}

	// Set player phase
	public void setPhase(int n) {
		phase = n;
	}

	// Set human flag
	public void setHuman(boolean h) {
		human = h;
	}

	// Set life
	public void setLife(int l) {
		life = l;
	}

	// Set power
	public void setPower(int p) {
		power = p;
	}

	// Set number of cards to draw
	public void setDrawNum(int n) {
		drawNum = n;
	}
}
