package com.wymzymedia.arcana.duel_activity.components;

import com.wymzymedia.arcana.game_utils.GameComponent;

public class VitalsC extends GameComponent {
	public static final String TAG = VitalsC.class.getSimpleName();

	// Class variables
	private int card;
	private boolean human;
	private int life;
	private int power;

	// Constructor
	public VitalsC(boolean h, int l, int p) {
		card = -1;
		human = h;
		life = l;
		power = p;
	}

	// Return selected card
	public int getCard() {
		return card;
	}

	// Return human flag
	public boolean isHuman() {
		return human;
	}

	// Return life
	public int getLife() {
		return life;
	}

	// Return power
	public int getPower() {
		return power;
	}

	// Set selected card
	public void setCard(int c) {
		card = c;
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
}
