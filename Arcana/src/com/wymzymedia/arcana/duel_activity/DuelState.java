package com.wymzymedia.arcana.duel_activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.wymzymedia.arcana.duel_activity.components.VitalsC;
import com.wymzymedia.arcana.duel_activity.entities.Player;
import com.wymzymedia.arcana.game_utils.GameEntity;
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
		computerDeck.add(3);
		computerDeck.add(1);
		computerDeck.add(1);

		// initialize players
		Collections.shuffle(playerDeck);
		human = new Player(true, 5, 7, playerDeck);
		Collections.shuffle(computerDeck);
		computer = new Player(false, 5, 7, computerDeck);
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

	// Check card requirements
	public static final boolean checkReqs(GameEntity player, String reqStr) {
		// retrieve components
		VitalsC vitals = (VitalsC) player.getComponent("VitalsC");

		// parse and verify requirements if any
		if (reqStr != null) {
			String[] reqs = reqStr.split(";");
			for (String req : reqs) {
				String[] elems = req.split(":");
				if (elems[0].equals("power")) {
					if (vitals.getPower() < Integer.valueOf(elems[1])) {
						return false;
					}
				}
			}
		}
		return true;
	}

	// Apply card changes to player
	public final void applyChanges(GameEntity player, String changeStr) {
		// retrieve components
		VitalsC vitals = (VitalsC) player.getComponent("VitalsC");

		// parse and apply changes if any
		if (changeStr != null) {
			String[] changes = changeStr.split(";");
			for (String change : changes) {
				String[] elems = change.split(":");
				if (elems[0].equals("damage")) {
					// retrieve target components
					Player target = vitals.isHuman() ? computer : human;
					VitalsC targetVitals = (VitalsC) target
							.getComponent("VitalsC");

					// apply damage
					targetVitals.setLife(targetVitals.getLife()
							- Integer.valueOf(elems[1]));
				} else if (elems[0].equals("life")) {
					vitals.setLife(vitals.getLife() + Integer.valueOf(elems[1]));
				} else if (elems[0].equals("power")) {
					vitals.setPower(vitals.getPower()
							+ Integer.valueOf(elems[1]));
				} else if (elems[0].equals("shield")) {
					vitals.setShield(vitals.getShield()
							+ Integer.valueOf(elems[1]));
				}
			}
		}
	}
}
