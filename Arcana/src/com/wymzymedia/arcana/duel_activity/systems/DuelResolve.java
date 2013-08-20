package com.wymzymedia.arcana.duel_activity.systems;

import android.util.Log;

import com.wymzymedia.arcana.duel_activity.components.VitalsC;
import com.wymzymedia.arcana.game_utils.GameEntity;
import com.wymzymedia.arcana.game_utils.GameState;
import com.wymzymedia.arcana.game_utils.GameSystem;

public class DuelResolve extends GameSystem {
	public static final String TAG = DuelResolve.class.getSimpleName();

	// Constructor
	public DuelResolve(GameState state) {
		super(state);

		// set required components
		addReqComponent("VitalsC");
	}

	// Execute logic on entity
	@Override
	protected void execSystem(GameEntity entity) {
		// TODO remove
		Log.d(TAG, "========== RESOLVE PHASE ==========");

		// retrieve components
		VitalsC vitals = (VitalsC) entity.getComponent("VitalsC");

		// abort if player phase does not match duel phase
		if (vitals.getPhase() != 4) {
			return;
		}

		// apply card effects

		// progress player to next phase
		vitals.setPhase(vitals.getPhase() + 1);
	}
}
