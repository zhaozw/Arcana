package com.wymzymedia.arcana.duel_activity.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.wymzymedia.arcana.game_utils.GameComponent;

public class DeckC extends GameComponent {
	public static final String TAG = DeckC.class.getSimpleName();

	// Class variables
	private List<Integer> cards;

	// Constructor
	public DeckC(List<Integer> c) {
		cards = new ArrayList<Integer>(c);
	}

	// Return card at given index
	public int getCard(int i) {
		return cards.get(i);
	}

	// Return cards
	public List<Integer> getCards() {
		return cards;
	}

	// Set cards
	public void setCards(List<Integer> c) {
		cards = c;
	}

	// Add card to bottom of deck
	public void addCard(int n) {
		cards.add(n);
	}

	// Add list of cards to bottom of deck
	public void addCards(List<Integer> c) {
		cards.addAll(c);
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
