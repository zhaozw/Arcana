package com.wymzymedia.arcana.duel_activity.entities;

import com.wymzymedia.arcana.duel_activity.components.DeckC;
import com.wymzymedia.arcana.game_utils.GameEntity;

public class Deck extends GameEntity {
	public static final String TAG = Deck.class.getSimpleName();

	// Constructor
	public Deck() {
		addComponent("DeckC", new DeckC());
	}
}
