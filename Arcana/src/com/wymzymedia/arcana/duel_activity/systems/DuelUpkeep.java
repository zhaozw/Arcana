package com.wymzymedia.arcana.duel_activity.systems;

import android.util.Log;

import com.wymzymedia.arcana.duel_activity.components.DeckC;
import com.wymzymedia.arcana.duel_activity.components.VitalsC;
import com.wymzymedia.arcana.game_utils.GameEntity;
import com.wymzymedia.arcana.game_utils.GameState;
import com.wymzymedia.arcana.game_utils.GameSystem;

public class DuelUpkeep extends GameSystem {
	public static final String TAG = DuelUpkeep.class.getSimpleName();

	// Constructor
	public DuelUpkeep(GameState state) {
		super(state);

		// set required components
		addReqComponent("VitalsC");
		addReqComponent("DiscardDeckC");
	}

	// Execute logic on entity
	@Override
	protected void execSystem(GameEntity entity) {
		// TODO remove
		Log.d(TAG, "========== UPKEEP PHASE ==========");

		// retrieve components
		VitalsC vitals = (VitalsC) entity.getComponent("VitalsC");
		DeckC discard = (DeckC) entity.getComponent("DiscardDeckC");

		// abort if player phase does not match duel phase
		if (vitals.getPhase() != 4) {
			return;
		}

		// discard expired active cards

		// progress player to next phase
		vitals.setPhase(1);
	}
}
