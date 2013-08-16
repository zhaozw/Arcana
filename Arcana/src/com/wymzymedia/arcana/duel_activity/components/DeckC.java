package com.wymzymedia.arcana.duel_activity.components;

import java.util.Collections;
import java.util.List;

import android.util.Log;

import com.wymzymedia.arcana.game_utils.GameComponent;

public class DeckC extends GameComponent {
	public static final String TAG = DeckC.class.getSimpleName();

	// Class variables
	private List<Integer> cards;
	private boolean playerFlag;
	private String type;

	// Constructor
	public DeckC(List<Integer> c, boolean f, String t) {
		cards = c;
		playerFlag = f;
		type = t;
	}

	// Return cards
	public List<Integer> getCards() {
		return cards;
	}

	// Return player flag
	public boolean getPlayerFlag() {
		return playerFlag;
	}

	// Return deck type
	public String getType() {
		return type;
	}

	// Set cards
	public void setCards(List<Integer> c) {
		cards = c;
	}

	// Set player flag
	public void setPlayerFlag(boolean f) {
		playerFlag = f;
	}

	// Set deck type
	public void setType(String t) {
		if (t.equals("deck") || t.equals("hand") || t.equals("active")
				|| t.equals("discard")) {
			type = t;
		} else {
			// log unknown deck type
			Log.d(TAG, "Unknown deck type: " + t);
		}
	}

	// Add card to bottom of deck
	public void addCard(int n) {
		cards.add(n);
	}

	// Remove and return card at index n
	public int drawCard(int n) {
		int card = cards.get(n - 1);
		cards.remove(n - 1);
		return card;
	}

	// Remove and return n cards from top of deck
	public List<Integer> drawTop(int n) {
		List<Integer> top = cards.subList(0, n);
		cards = cards.subList(n, cards.size());
		return top;
	}

	// Reorder card from "old" position to "new" position
	public void reorderCard(int oldP, int newP) {
		int card = cards.get(oldP - 1);
		cards.remove(oldP - 1);
		if (oldP < newP) {
			cards.add(newP - 1, card);
		} else if (oldP > newP) {
			cards.add(newP, card);
		}
	}

	// Shuffle cards
	public void shuffleCards() {
		Collections.shuffle(cards);
	}
}
