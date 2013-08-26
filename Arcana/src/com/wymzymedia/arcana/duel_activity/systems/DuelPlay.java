package com.wymzymedia.arcana.duel_activity.systems;

import android.util.Log;

import com.wymzymedia.arcana.duel_activity.DuelState;
import com.wymzymedia.arcana.duel_activity.components.ArcanaCardC;
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
		addReqComponent("PlayCardC");
		addReqComponent("DiscardDeckC");
	}

	// Execute logic on entity
	@Override
	protected void execSystem(GameEntity entity) {
		// TODO remove
		Log.d(TAG, "========== PLAY PHASE ==========");

		// retrieve components
		VitalsC vitals = (VitalsC) entity.getComponent("VitalsC");
		ArcanaCardC card = (ArcanaCardC) entity.getComponent("PlayCardC");
		DeckC discard = (DeckC) entity.getComponent("DiscardDeckC");

		// abort if player phase does not match duel phase
		if (vitals.getPhase() != 3) {
			return;
		}

		// apply card effects
		((DuelState) getState()).applyChanges(entity, card.getEffectStr());

		// check card duration
		if (card.getDuration() == 0) {
			// move card to discard deck
			discard.addCard(card.getID());
			card.loadCard(-1);
		} else {
			// move card to active deck
		}

		// progress player to next phase
		vitals.setPhase(vitals.getPhase() + 1);
	}
}
