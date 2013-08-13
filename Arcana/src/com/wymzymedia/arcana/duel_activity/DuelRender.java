package com.wymzymedia.arcana.duel_activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;

import com.wymzymedia.arcana.game_utils.GameEntity;
import com.wymzymedia.arcana.game_utils.GameState;
import com.wymzymedia.arcana.game_utils.GameSystem;

public class DuelRender extends GameSystem {
	public static final String TAG = DuelRender.class.getSimpleName();

	// Class variables
	private final Context context;
	private final Rect mainDisplay;
	// TODO allow user to change dispFactor w/ pinch zoom
	private final float dispFactor;
	// TODO recenter view on player for each frame
	private final float viewCenterX;
	private final float viewCenterY;

	// Shared helper variables
	Paint paint = new Paint();
	Rect rect = new Rect();
	Matrix matrix = new Matrix();

	// Constructor
	public DuelRender(GameState state, Context c, int dispX, int dispY) {
		super(state);

		// initialize variables
		context = c;
		mainDisplay = new Rect(0, 0, dispX, dispY);
		dispFactor = 1;
		viewCenterX = mainDisplay.exactCenterX();
		viewCenterY = mainDisplay.exactCenterX();

		// set required components
		addReqComponent("IdentityC");
		addReqComponent("PositionC");
	}

	// Process entities
	public void process(Canvas canvas) {
		for (GameEntity entity : getState().getEntitiesContaining(
				getReqComponents())) {
			execSystem(entity, canvas);
		}
	}

	// Execute logic on entity
	protected void execSystem(GameEntity entity, Canvas canvas) {
		// retrieve components

		// initialize vars

		// draw entity

	}

	// Render battle background
	public void renderBackground(Canvas canvas) {
		// render crosshair
		renderCrosshair(canvas);

		// render grid
		renderGrid(canvas);
	}

	// Render cross hair
	private void renderCrosshair(Canvas canvas) {
		// initialize display variables
		paint.setColor(Defaults.TEXT_COLOR);

		// draw cross hair
		canvas.drawLine(mainDisplay.centerX(), 0, mainDisplay.centerX(),
				mainDisplay.height(), paint);
		canvas.drawLine(0, mainDisplay.centerY(), mainDisplay.width(),
				mainDisplay.centerY(), paint);
	}

	// Render battle grid
	private void renderGrid(Canvas canvas) {
		// initialize display variables
		paint.setColor(Defaults.TEXT_COLOR);

		// draw grid

	}
}
