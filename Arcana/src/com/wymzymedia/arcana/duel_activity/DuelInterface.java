package com.wymzymedia.arcana.duel_activity;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import com.wymzymedia.arcana.game_utils.GameInterface;
import com.wymzymedia.arcana.game_utils.GameState;

public class DuelInterface extends GameInterface {
	public static final String TAG = DuelInterface.class.getSimpleName();

	// Class variables
	private final Rect mainDisplay;

	// Constructor
	public DuelInterface(int x, int y, GameState s) {
		super(x, y, s);
		mainDisplay = new Rect(0, 0, screenWidth, screenHeight);
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

	// Return main display rectangle
	public Rect getMainDisplay() {
		return mainDisplay;
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
