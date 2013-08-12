package com.wymzymedia.arcana.game_utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class GameEntity {
	public static final String TAG = GameEntity.class.getSimpleName();

	// Class variables
	private Map<String, GameComponent> components = new HashMap<String, GameComponent>();

	// Constructor
	protected GameEntity() {
	}

	// Add component to entity
	protected final void addComponent(String type, GameComponent component) {
		components.put(type, component);
	}

	// Return specified component
	public final GameComponent getComponent(String type) {
		return components.get(type);
	}

	// Check for required component
	public final boolean hasComponent(String type) {
		if (getComponent(type) == null) {
			return false;
		} else {
			return true;
		}
	}

	// Check for required components
	public final boolean hasComponent(Set<String> reqComponents) {
		for (String type : reqComponents) {
			if (getComponent(type) == null) {
				return false;
			}
		}
		return true;
	}

	// Check whether entity is active
	public boolean isActive() {
		return true;
	}
}
