package com.wymzymedia.arcana.duel_activity.systems;

import android.util.Log;

import com.wymzymedia.arcana.duel_activity.components.DeckC;
import com.wymzymedia.arcana.duel_activity.components.VitalsC;
import com.wymzymedia.arcana.game_utils.GameEntity;
import com.wymzymedia.arcana.game_utils.GameState;
import com.wymzymedia.arcana.game_utils.GameSystem;

public class DuelPlay extends GameSystem {
	public static final String TAG = DuelPlay.class.getSimpleName();

	// Constructor
	public DuelPlay(GameState state) {
		super(state);

		// set required components
		addReqComponent("VitalsC");
		addReqComponent("ActiveDeckC");
	}

	// Execute logic on entity
	@Override
	protected void execSystem(GameEntity entity) {
		// TODO remove
		Log.d(TAG, "========== PLAYING CARD ==========");

		// retrieve components
		VitalsC vitals = (VitalsC) entity.getComponent("VitalsC");
		DeckC active = (DeckC) entity.getComponent("ActiveDeckC");

		// abort if player phase does not match duel phase
		if (vitals.getPhase() != 3) {
			return;
		}

		// apply card effects
		// TODO may need to split play phase into calculate and apply phases

		// progress player to next phase
		vitals.setPhase(vitals.getPhase() + 1);
	}
}
