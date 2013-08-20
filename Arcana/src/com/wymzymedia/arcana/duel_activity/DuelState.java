package com.wymzymedia.arcana.duel_activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.wymzymedia.arcana.duel_activity.components.VitalsC;
import com.wymzymedia.arcana.duel_activity.entities.Player;
import com.wymzymedia.arcana.game_utils.GameState;

public class DuelState extends GameState {
	public static final String TAG = DuelState.class.getSimpleName();

	// Class variables
	Player human;
	Player computer;
	int phase;

	// Constructor
	public DuelState() {
		// initialize player decks
		// TODO remove poker deck implementation
		List<Integer> pokerDeck = new ArrayList<Integer>(52);
		for (int i = 0; i < 52; i++) {
			pokerDeck.add(i);
		}
		Collections.shuffle(pokerDeck);

		// initialize variables
		// TODO remove poker deck implementation
		human = new Player(true, 5, 1, pokerDeck.subList(0, 26));
		computer = new Player(false, 2, 2, pokerDeck.subList(26, 52));
		addEntity(human);
		addEntity(computer);
		phase = 1;

		// draw first hand
	}

	// Return duel phase
	public int getPhase() {
		return phase;
	}

	// Set duel phase
	public void setPhase(int n) {
		phase = n;
	}

	// Update duel phase based on player phases
	public void updatePhase() {
		VitalsC humanVitals = (VitalsC) human.getComponent("VitalsC");
		VitalsC compVitals = (VitalsC) computer.getComponent("VitalsC");

		// set phase to the lowest phase
		if (humanVitals.getPhase() < compVitals.getPhase()) {
			setPhase(humanVitals.getPhase());
		} else {
			setPhase(compVitals.getPhase());
		}
	}
}
