package com.wymzymedia.arcana.game_utils;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

public abstract class GameInterface {
	public static final String TAG = GameInterface.class.getSimpleName();

	// Class variables
	protected GameState state;
	protected int screenWidth;
	protected int screenHeight;

	// Shared helper variables
	protected Paint paint = new Paint();
	protected Rect rect = new Rect();

	// Constructor
	public GameInterface(GameState s, int x, int y) {
		state = s;
		screenWidth = x;
		screenHeight = y;
	}

	// Process touch event
	public void processTouchEvent(String currDisplay, MotionEvent event) {
		if (currDisplay.equals("main")) {
			// handle main interface touch event
		} else {
			// log unknown interface type
			Log.d(TAG, "Unknown interface type: " + currDisplay);
		}
	}

	// Render controls onto given canvas
	private void renderControls(Canvas canvas) {
		// initialize display vars
		paint.setColor(Color.BLACK);
		paint.setStyle(Paint.Style.STROKE);

		// render move circle
		float circleRadius = 100;
		float circleX = circleRadius * 1.5f;
		float circleY = screenHeight - circleRadius * 1.5f;
		canvas.drawCircle(circleX, circleY, circleRadius, paint);
	}

	// Render specified interface
	public void renderInterface(Canvas canvas, String type) {
		if (type.equals("main")) {
			// render controls
			renderControls(canvas);
		} else {
			// log unknown interface type
			Log.d(TAG, "Unknown interface type: " + type);
		}
	}

	// Render thread metrics
	public void renderThreadMetrics(Canvas canvas, int textColor, double lps,
			int loopCnt) {
		// initialize display vars
		paint.setColor(textColor);
		paint.setTextAlign(Paint.Align.LEFT);
		// TODO calculate text size instead of fixed value
		paint.setTextSize(25);

		// draw average LPS metric
		String avgLPS = "Avg LPS: " + String.format("%.2f", lps);
		paint.getTextBounds(avgLPS, 0, avgLPS.length(), rect);
		canvas.drawText(avgLPS, 0, rect.height(), paint);

		// draw loop counter metric
		String countStr = "Total loops: " + loopCnt;
		paint.getTextBounds(countStr, 0, countStr.length(), rect);
		canvas.drawText(countStr, 0, rect.height() * 2, paint);
	}
}
