package com.wymzymedia.arcana.duel_activity;

import com.wymzymedia.arcana.duel_activity.entities.Deck;
import com.wymzymedia.arcana.game_utils.GameState;

public class DuelState extends GameState {
	public static final String TAG = DuelState.class.getSimpleName();

	// Class variables
	int playerLife;
	int playerPower;
	Deck playerDeck;
	Deck playerHand;
	Deck playerActives;
	Deck playerDiscards;
	int enemyLife;
	int enemyPower;
	Deck enemyDeck;
	Deck enemyHand;
	Deck enemyActives;
	Deck enemyDiscards;

	// Constructor
	public DuelState() {
		playerLife = 1;
		playerPower = 1;
		playerDeck = new Deck();
		playerHand = new Deck();
		playerActives = new Deck();
		playerDiscards = new Deck();
		enemyLife = 1;
		enemyPower = 1;
		enemyDeck = new Deck();
		enemyHand = new Deck();
		enemyActives = new Deck();
		enemyDiscards = new Deck();
	}
}
