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
		List<Integer> playerDeck = new ArrayList<Integer>();
		playerDeck.add(1);
		playerDeck.add(1);
		playerDeck.add(1);
		List<Integer> computerDeck = new ArrayList<Integer>();
		computerDeck.add(2);
		computerDeck.add(2);
		computerDeck.add(3);

		// initialize players
		Collections.shuffle(playerDeck);
		human = new Player(true, 10, 10, playerDeck);
		Collections.shuffle(computerDeck);
		computer = new Player(false, 10, 10, computerDeck);
		addEntity(human);
		addEntity(computer);
		updatePhase();

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
