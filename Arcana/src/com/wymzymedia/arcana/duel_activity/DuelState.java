package com.wymzymedia.arcana.duel_activity;

import java.util.ArrayList;

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
		playerDeck = new Deck(new ArrayList<Integer>(), true, "deck");
		playerHand = new Deck(new ArrayList<Integer>(), true, "hand");
		playerActive = new Deck(new ArrayList<Integer>(), true, "active");
		playerDiscard = new Deck(new ArrayList<Integer>(), true, "discard");
		enemyLife = 1;
		enemyPower = 1;
		enemyDeck = new Deck(new ArrayList<Integer>(), false, "deck");
		enemyHand = new Deck(new ArrayList<Integer>(), false, "hand");
		enemyActive = new Deck(new ArrayList<Integer>(), false, "active");
		enemyDiscard = new Deck(new ArrayList<Integer>(), false, "discard");

		// add entities
		addEntity(playerDeck);
		addEntity(playerHand);
		addEntity(playerActive);
		addEntity(playerDiscard);
		addEntity(enemyDeck);
		addEntity(enemyHand);
		addEntity(enemyActive);
		addEntity(enemyDiscard);
	}
}
