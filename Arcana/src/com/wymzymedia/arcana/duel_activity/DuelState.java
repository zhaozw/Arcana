package com.wymzymedia.arcana.duel_activity;

import java.util.ArrayList;
import java.util.Arrays;

import com.wymzymedia.arcana.duel_activity.entities.Deck;
import com.wymzymedia.arcana.game_utils.GameState;

public class DuelState extends GameState {
	public static final String TAG = DuelState.class.getSimpleName();

	// Class variables
	int playerLife;
	int playerPower;
	Deck playerDeck;
	Deck playerHand;
	Deck playerActive;
	Deck playerDiscard;
	int enemyLife;
	int enemyPower;
	Deck enemyDeck;
	Deck enemyHand;
	Deck enemyActive;
	Deck enemyDiscard;

	// Constructor
	public DuelState() {
		// initialize variables
		playerLife = 1;
		playerPower = 1;
		playerDeck = new Deck(Arrays.asList(new Integer[] { 1, 2, 3, 4 }),
				true, "deck");
		addEntity(playerDeck);
		enemyLife = 1;
		enemyPower = 1;
		enemyDeck = new Deck(Arrays.asList(new Integer[] { 6, 7, 8, 9 }),
				false, "deck");
		addEntity(enemyDeck);

		// initialize first hand
		playerHand = new Deck(new ArrayList<Integer>(), true, "hand");
		addEntity(playerHand);
		enemyHand = new Deck(new ArrayList<Integer>(), false, "hand");
		addEntity(enemyHand);

		// initialize empty decks
		playerActive = new Deck(new ArrayList<Integer>(), true, "active");
		addEntity(playerActive);
		enemyActive = new Deck(new ArrayList<Integer>(), false, "active");
		addEntity(enemyActive);
		playerDiscard = new Deck(new ArrayList<Integer>(), true, "discard");
		addEntity(playerDiscard);
		enemyDiscard = new Deck(new ArrayList<Integer>(), false, "discard");
		addEntity(enemyDiscard);
	}
}
