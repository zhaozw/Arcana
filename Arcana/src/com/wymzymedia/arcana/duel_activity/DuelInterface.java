package com.wymzymedia.arcana.duel_activity;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import com.wymzymedia.arcana.game_utils.GameInterface;
import com.wymzymedia.arcana.game_utils.GameState;

public class DuelInterface extends GameInterface {
	public static final String TAG = DuelInterface.class.getSimpleName();

	// Class variables
	private final int screenWidth;
	private final int screenHeight;
	private final int cardRows;
	private final int cardCols;
	private final float cellWidth;
	private final float cellHeight;

	// Constructor
	public DuelInterface(GameState s, int dispX, int dispY, int cols, int rows) {
		super(s, dispX, dispY);

		// initialize variables
		screenWidth = dispX;
		screenHeight = dispY;
		cardCols = cols;
		cardRows = rows;
		cellWidth = dispX / cols;
		cellHeight = dispY / rows;
	}

	// Process touch event
	@Override
	public void processTouchEvent(String currDisplay, MotionEvent event) {
		if (currDisplay.equals("main")) {
			handleMainTouch(event);
		} else {
			// log unknown interface type
			Log.d(TAG, "Unknown interface type: " + currDisplay);
		}
	}

	// Render specified interface
	@Override
	public void renderInterface(Canvas canvas, String type) {
		if (type.equals("main")) {
			// render status
			renderStatus(canvas);

		} else {
			// log unknown interface type
			Log.d(TAG, "Unknown interface type: " + type);
		}
	}

	// Process interaction with main display
	public void handleMainTouch(MotionEvent event) {
		// TODO remove
		// Log.d(TAG, event.toString());

	}

	// Render status display
	private void renderStatus(Canvas canvas) {
		// initialize display variables

		// draw energy counter

		// draw crystal counter
	}
}
