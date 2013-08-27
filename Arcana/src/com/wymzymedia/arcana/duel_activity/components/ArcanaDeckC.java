package com.wymzymedia.arcana.duel_activity.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.wymzymedia.arcana.game_utils.GameComponent;

public class ArcanaDeckC extends GameComponent {
	public static final String TAG = ArcanaDeckC.class.getSimpleName();

	// Class variables
	private List<ArcanaCardC> cards;

	// Constructor
	public ArcanaDeckC(List<Integer> c) {
		setCards(c);
	}

	// Return card at given index
	public ArcanaCardC getCard(int i) {
		return cards.get(i);
	}

	// Return cards
	public List<ArcanaCardC> getCards() {
		return cards;
	}

	// Set cards
	public void setCards(List<Integer> c) {
		cards = new ArrayList<ArcanaCardC>();
		for (Integer n : c) {
			ArcanaCardC card = new ArcanaCardC(n);
			cards.add(card);
		}
	}

	// Add card to bottom of deck
	public void addCard(int n) {
		ArcanaCardC card = new ArcanaCardC(n);
		cards.add(card);
	}

	// Add list of cards to bottom of deck
	public void addCards(List<Integer> c) {
		for (Integer n : c) {
			ArcanaCardC card = new ArcanaCardC(n);
			cards.add(card);
		}
	}

	// Remove and return card at index i
	public ArcanaCardC drawCard(int i) {
		ArcanaCardC card = cards.get(i);
		cards.remove(i);
		return card;
	}

	// Remove and return n cards from top of deck
	public List<ArcanaCardC> drawTop(int n) {
		List<ArcanaCardC> top = cards.subList(0, n);
		cards = cards.subList(n, cards.size());
		return top;
	}

	// Reorder card from "old" index to "new" index
	public void reorderCard(int oldI, int newI) {
		ArcanaCardC card = cards.get(oldI - 1);
		cards.remove(oldI - 1);
		if (oldI < newI) {
			cards.add(newI - 1, card);
		} else if (oldI > newI) {
			cards.add(newI, card);
		}
	}

	// Shuffle cards
	public void shuffleCards() {
		Collections.shuffle(cards);
	}
}
