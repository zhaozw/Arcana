package com.wymzymedia.arcana.game_utils;

import java.util.HashSet;
import java.util.Set;

public abstract class GameSystem {
	public static final String TAG = GameSystem.class.getSimpleName();

	// Class variables
	private GameState state;
	private Set<String> reqComponents = new HashSet<String>();

	// Constructor
	protected GameSystem(GameState state) {
		this.state = state;
		// specify required components for system
	}

	// Process entities
	public void process() {
		for (GameEntity entity : state.getEntitiesContaining(reqComponents)) {
			execSystem(entity);
		}
	}

	// Execute logic on entity
	// note: override method in subclass
	protected void execSystem(GameEntity entity) {
	}

	// Add type to required components list
	protected final void addReqComponent(String type) {
		reqComponents.add(type);
	}

	// Return required components list
	protected final Set<String> getReqComponents() {
		return reqComponents;
	}

	// Return entity manager
	protected final GameState getState() {
		return state;
	}
}
