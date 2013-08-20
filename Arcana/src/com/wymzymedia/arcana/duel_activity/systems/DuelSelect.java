package com.wymzymedia.arcana.duel_activity.systems;

import android.util.Log;

import com.wymzymedia.arcana.duel_activity.components.VitalsC;
import com.wymzymedia.arcana.game_utils.GameEntity;
import com.wymzymedia.arcana.game_utils.GameState;
import com.wymzymedia.arcana.game_utils.GameSystem;

public class DuelSelect extends GameSystem {
	public static final String TAG = DuelSelect.class.getSimpleName();

	// Constructor
	public DuelSelect(GameState state) {
		super(state);
	}

	// Execute logic on entity
	@Override
	protected void execSystem(GameEntity entity) {
		// TODO remove
		Log.d(TAG, "========== SELECTING CARD ==========");

		// retrieve components
		VitalsC vitals = (VitalsC) entity.getComponent("VitalsC");

		// select card to play
		if (vitals.isHuman()) {
			// verify human selection
		} else {
			// perform computer selection
		}

		// progress player to next phase
		vitals.setPhase(vitals.getPhase() + 1);
	}
}
