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
	}

	// Execute logic on entity
	@Override
	protected void execSystem(GameEntity entity) {
		// TODO remove
		Log.d(TAG, "========== DRAWING CARD ==========");

		// retrieve components
		VitalsC vitals = (VitalsC) entity.getComponent("VitalsC");
		DeckC newDeck = (DeckC) entity.getComponent("NewDeckC");
		DeckC handDeck = (DeckC) entity.getComponent("HandDeckC");

		// draw from new deck and add to hand deck
		int drawNum = vitals.getDrawNum();
		if (newDeck.getCards().size() < drawNum) {
			drawNum = newDeck.getCards().size();
		}
		if (drawNum > 0) {
			handDeck.addCards(newDeck.drawTop(drawNum));
		}
	}
}
