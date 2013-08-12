package com.wymzymedia.arcana.game_utils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public abstract class GameState {
	public static final String TAG = GameState.class.getSimpleName();

	// Class variables
	private Map<Integer, GameEntity> entities = new ConcurrentHashMap<Integer, GameEntity>();

	// Constructor
	protected GameState() {
	}

	// Add entity
	public final void addEntity(GameEntity entity) {
		// register entity
		entities.put(entity.hashCode(), entity);
	}

	// Return collection of entities
	public final Collection<GameEntity> getAllEntities() {
		return entities.values();
	}

	// Return collection of entities by entity class
	public final Collection<GameEntity> getEntitiesByClass(String name) {
		Set<GameEntity> verifiedEntities = new HashSet<GameEntity>();
		for (GameEntity entity : getAllEntities()) {
			if (entity.getClass().getSimpleName().equals(name)) {
				verifiedEntities.add(entity);
			}
		}
		return verifiedEntities;
	}

	// Return collection of entities containing single given component
	public final Collection<GameEntity> getEntitiesContaining(String component) {
		Set<GameEntity> verifiedEntities = new HashSet<GameEntity>();
		for (GameEntity entity : getAllEntities()) {
			if (entity.hasComponent(component)) {
				verifiedEntities.add(entity);
			}
		}
		return verifiedEntities;
	}

	// Return collection of entities containing multiple given components
	public final Collection<GameEntity> getEntitiesContaining(
			Set<String> components) {
		Set<GameEntity> verifiedEntities = new HashSet<GameEntity>();
		for (GameEntity entity : getAllEntities()) {
			if (entity.hasComponent(components)) {
				verifiedEntities.add(entity);
			}
		}
		return verifiedEntities;
	}

	// Remove entity
	public final void removeEntity(GameEntity entity) {
		// unregister entity
		entities.remove(entity.hashCode());
	}
}
