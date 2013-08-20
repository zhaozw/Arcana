package com.wymzymedia.arcana.duel_activity.systems;

import android.util.Log;

import com.wymzymedia.arcana.duel_activity.components.DeckC;
import com.wymzymedia.arcana.duel_activity.components.VitalsC;
import com.wymzymedia.arcana.game_utils.GameEntity;
import com.wymzymedia.arcana.game_utils.GameState;
import com.wymzymedia.arcana.game_utils.GameSystem;

public class DuelDraw extends GameSystem {
	public static final String TAG = DuelDraw.class.getSimpleName();

	// Constructor
	public DuelDraw(GameState state) {
		super(state);

		// set required components
		addReqComponent("VitalsC");
		addReqComponent("DrawDeckC");
		addReqComponent("HandDeckC");
	}

	// Execute logic on entity
	@Override
	protected void execSystem(GameEntity entity) {
		// TODO remove
		Log.d(TAG, "========== DRAWING CARD ==========");

		// retrieve components
		VitalsC vitals = (VitalsC) entity.getComponent("VitalsC");
		DeckC draw = (DeckC) entity.getComponent("DrawDeckC");
		DeckC hand = (DeckC) entity.getComponent("HandDeckC");

		// abort if player phase does not match duel phase
		if (vitals.getPhase() != 1) {
			return;
		}

		// draw from new deck and add to hand deck
		int drawNum = vitals.getDrawNum();
		if (draw.getCards().size() < drawNum) {
			drawNum = draw.getCards().size();
		}
		if (drawNum > 0) {
			hand.addCards(draw.drawTop(drawNum));
		}

		// progress player to next phase
		vitals.setPhase(vitals.getPhase() + 1);
	}
}
