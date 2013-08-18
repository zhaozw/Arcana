package com.wymzymedia.arcana.duel_activity;

import java.util.Arrays;

import com.wymzymedia.arcana.duel_activity.entities.Player;
import com.wymzymedia.arcana.game_utils.GameState;

public class DuelState extends GameState {
	public static final String TAG = DuelState.class.getSimpleName();

	// Class variables
	Player human;
	Player computer;

	// Constructor
	public DuelState() {
		// initialize variables
		human = new Player(true, 5, 1,
				Arrays.asList(new Integer[] { 1, 2, 3, 4 }));
		computer = new Player(false, 2, 2, Arrays.asList(new Integer[] { 6, 7,
				8, 9 }));
		addEntity(human);
		addEntity(computer);

		// draw first hand
	}
}
