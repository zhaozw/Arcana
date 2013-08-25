package com.wymzymedia.arcana.duel_activity.entities;

import java.util.ArrayList;
import java.util.List;

import com.wymzymedia.arcana.duel_activity.components.ArcanaCardC;
import com.wymzymedia.arcana.duel_activity.components.DeckC;
import com.wymzymedia.arcana.duel_activity.components.VitalsC;
import com.wymzymedia.arcana.game_utils.GameEntity;

public class Player extends GameEntity {
	public static final String TAG = Player.class.getSimpleName();

	// Constructor
	public Player(boolean human, int life, int power, List<Integer> cards) {
		addComponent("VitalsC", new VitalsC(human, life, power));
		addComponent("PlayCardC", new ArcanaCardC(-1));
		addComponent("DrawDeckC", new DeckC(cards));
		addComponent("HandDeckC", new DeckC(new ArrayList<Integer>()));
		addComponent("DiscardDeckC", new DeckC(new ArrayList<Integer>()));
	}
}
