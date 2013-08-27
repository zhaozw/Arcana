package com.wymzymedia.arcana.duel_activity.systems;

import android.util.Log;

import com.wymzymedia.arcana.duel_activity.DuelState;
import com.wymzymedia.arcana.duel_activity.components.ArcanaDeckC;
import com.wymzymedia.arcana.duel_activity.components.ArcanaCardC;
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
		addReqComponent("ActiveDeckC");
	}

	// Execute logic on entity
	@Override
	protected void execSystem(GameEntity entity) {
		// TODO remove
		Log.d(TAG, "========== UPKEEP PHASE ==========");

		// retrieve components
		VitalsC vitals = (VitalsC) entity.getComponent("VitalsC");
		ArcanaDeckC active = (ArcanaDeckC) entity.getComponent("ActiveDeckC");

		// abort if player phase does not match duel phase
		if (vitals.getPhase() != 4) {
			return;
		}

		// apply card upkeep
		for (int i = 0; i < active.getCards().size(); i++) {
			// retrieve card
			ArcanaCardC card = active.getCard(i);

			// apply card upkeep
			((DuelState) getState()).applyChanges(entity, card.getUpkeepStr());
		}

		// progress player to next phase
		vitals.setPhase(vitals.getPhase() + 1);
	}
}
