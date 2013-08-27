package com.wymzymedia.arcana.duel_activity.systems;

import java.util.Random;

import android.util.Log;

import com.wymzymedia.arcana.duel_activity.DuelState;
import com.wymzymedia.arcana.duel_activity.components.ArcanaCardC;
import com.wymzymedia.arcana.duel_activity.components.ArcanaDeckC;
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
		Log.d(TAG, "========== SELECT PHASE ==========");

		// retrieve components
		VitalsC vitals = (VitalsC) entity.getComponent("VitalsC");
		ArcanaCardC card = (ArcanaCardC) entity.getComponent("PlayCardC");
		ArcanaDeckC hand = (ArcanaDeckC) entity.getComponent("HandDeckC");

		// abort if player phase does not match duel phase
		if (vitals.getPhase() != 2) {
			return;
		}

		// select computer card to play
		// TODO uncomment isHuman() after card select interface implemented
		// if (!vitals.isHuman()) {
		// TODO implement AI strategy instead of random select
		if (hand.getCards().size() > 0) {
			card = hand.drawCard(rng.nextInt(hand.getCards().size()));
		}
		// }

		// check if card selected
		if (card.getID() >= 0) {
			// verify card requirements
			if (DuelState.checkReqs(entity, card.getReqStr())) {
				// apply card costs
				((DuelState) getState())
						.applyChanges(entity, card.getCostStr());

				// apply card modifiers
				((DuelState) getState()).applyChanges(entity, card.getModStr());

				// progress player to next phase
				vitals.setPhase(vitals.getPhase() + 1);
			} else {
				// return card to hand
				hand.addCard(card.getID());
				card.loadCard(-1);
			}
		}
	}
}
