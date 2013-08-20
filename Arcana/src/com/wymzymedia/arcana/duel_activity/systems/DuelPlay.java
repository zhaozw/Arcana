package com.wymzymedia.arcana.duel_activity.systems;

import android.util.Log;

import com.wymzymedia.arcana.duel_activity.components.VitalsC;
import com.wymzymedia.arcana.game_utils.GameEntity;
import com.wymzymedia.arcana.game_utils.GameState;
import com.wymzymedia.arcana.game_utils.GameSystem;

public class DuelPlay extends GameSystem {
	public static final String TAG = DuelPlay.class.getSimpleName();

	// Constructor
	public DuelPlay(GameState state) {
		super(state);
	}

	// Execute logic on entity
	@Override
	protected void execSystem(GameEntity entity) {
		// TODO remove
		Log.d(TAG, "========== PLAYING CARD ==========");

		// retrieve components
		VitalsC vitals = (VitalsC) entity.getComponent("VitalsC");

		// process card effects
	}

}
