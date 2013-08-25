package com.wymzymedia.arcana.duel_activity.systems;

import java.util.Random;

import android.util.Log;

import com.wymzymedia.arcana.duel_activity.components.ArcanaCardC;
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
		Log.d(TAG, "========== SELECT PHASE ==========");

		// retrieve components
		VitalsC vitals = (VitalsC) entity.getComponent("VitalsC");
		ArcanaCardC card = (ArcanaCardC) entity.getComponent("PlayCardC");
		DeckC hand = (DeckC) entity.getComponent("HandDeckC");

		// abort if player phase does not match duel phase
		if (vitals.getPhase() != 2) {
			return;
		}

		// select computer card to play
		// TODO uncomment isHuman() after card select interface implemented
		// if (!vitals.isHuman()) {
		// TODO implement AI strategy instead of random select
		if (hand.getCards().size() > 0) {
			card.loadCard(hand.drawCard(rng.nextInt(hand.getCards().size())));
		}
		// }

		// check if card selected
		if (card.getID() >= 0) {
			// verify card requirements
			if (checkReqs(vitals, card.getReqStr())) {
				// apply card costs
				applyCosts(vitals, card.getCostStr());

				// apply card modifiers

				// progress player to next phase
				vitals.setPhase(vitals.getPhase() + 1);
			} else {
				// return card to hand
				hand.addCard(card.getID());
				card.loadCard(-1);
			}
		}
	}

	// Check card reqs
	public boolean checkReqs(VitalsC vitals, String reqStr) {
		String[] reqs = reqStr.split(";");
		for (String req : reqs) {
			if (!checkReq(vitals, req)) {
				return false;
			}
		}
		return true;
	}

	// Verify individual req
	public boolean checkReq(VitalsC vitals, String req) {
		String[] elems = req.split(":");
		if (elems[0].equals("power")) {
			if (vitals.getPower() < Integer.valueOf(elems[1])) {
				return false;
			}
		} else if (elems[0].equals("life")) {
			if (vitals.getPower() < Integer.valueOf(elems[1])) {
				return false;
			}
		}
		return true;
	}

	// Apply card costs
	public void applyCosts(VitalsC vitals, String costStr) {
		String[] costs = costStr.split(";");
		for (String cost : costs) {
			String[] elems = cost.split(":");
			if (elems[0].equals("power")) {
				vitals.setPower(vitals.getPower() + Integer.valueOf(elems[1]));
			}
		}
	}
}
