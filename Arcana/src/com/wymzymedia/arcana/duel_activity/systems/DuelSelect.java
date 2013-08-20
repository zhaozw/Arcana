package com.wymzymedia.arcana.duel_activity.systems;

import java.util.Random;

import android.util.Log;

import com.wymzymedia.arcana.duel_activity.components.DeckC;
import com.wymzymedia.arcana.duel_activity.components.VitalsC;
import com.wymzymedia.arcana.game_utils.GameEntity;
import com.wymzymedia.arcana.game_utils.GameState;
import com.wymzymedia.arcana.game_utils.GameSystem;

public class DuelSelect extends GameSystem {
	public static final String TAG = DuelSelect.class.getSimpleName();

	// Helper variables
	private final Random rng = new Random();

	// Constructor
	public DuelSelect(GameState state) {
		super(state);

		// set required components
		addReqComponent("VitalsC");
		addReqComponent("HandDeckC");
	}

	// Execute logic on entity
	@Override
	protected void execSystem(GameEntity entity) {
		// TODO remove
		Log.d(TAG, "========== SELECTING CARD ==========");

		// retrieve components
		VitalsC vitals = (VitalsC) entity.getComponent("VitalsC");
		DeckC hand = (DeckC) entity.getComponent("HandDeckC");

		// select card to play
		// TODO verify human selection is valid
		// if (vitals.isHuman()) {
		// // verify human selection is valid
		// } else {
		// perform computer selection
		// TODO implement AI strategy instead of random select
		if (hand.getCards().size() > 0) {
			vitals.setCard(hand.drawCard(rng.nextInt(hand.getCards().size())));
		}
		// }

		// progress player to next phase
		vitals.setPhase(vitals.getPhase() + 1);
	}
}
