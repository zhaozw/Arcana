package com.wymzymedia.arcana.game_utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public abstract class GameView extends SurfaceView implements
		SurfaceHolder.Callback {
	public static final String TAG = GameView.class.getSimpleName();

	// Class variables
	protected String currDisplay = "main";
	protected GameThread gameThread;
	protected GameInterface gameUI;
	protected GameState gameState;

	// Constructor
	protected GameView(Context context) {
		super(context);

		// add callback to intercept surface holder events
		getHolder().addCallback(this);

		// set focusable attribute to allow event handling
		setFocusable(true);

		// note: initialize gameThread in subclass
		// initialize game thread
		// gameThread = new GameThread(this, 99);

		// note: initialize gameState in subclass
		// initialize game state
		// gameState = new GameState();

		// note: initialize variables and systems in subclass
		// gameVar = val;
		// gameSys = new GameSystem();
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		Log.d(TAG, "Surface created ...");

		// note: initialize render system in subclass
		// initialize render system
		// renderSys = new RenderSystem();

		// note: initialize gameUI in subclass
		// initialize user interface
		// gameUI = new GameInterface(getWidth() - 1, getHeight() - 1);

		// start "paused" game thread
		if (gameThread.getState() == Thread.State.NEW) {
			Log.d(TAG, "Thread starting ...");
			gameThread.setRunning(true);
			gameThread.start();
		}

		// manually render first frame of "paused" thread
		render();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		Log.d(TAG, "Surface changed ...");
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.d(TAG, "Surface destroyed ...");
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.d(TAG, "Touch Coords: x=" + event.getX() + ", y=" + event.getY());
		if (gameThread.getPaused() == true) {
			// unpause on user touch if game "paused"
			unpauseGame();
			return false;
		} else {
			// process interaction with user interface
			gameUI.processTouchEvent(currDisplay, event);
			return true;
		}
	}

	// Set current display
	public void setCurrDisplay(String s) {
		currDisplay = s;
	}

	// Pause game by setting "paused" thread flag
	public void pauseGame() {
		Log.d(TAG, "Pausing game thread");
		gameThread.setPaused(true);
	}

	// Unpause game by setting "paused" thread flag and notifying to clear wait
	protected void unpauseGame() {
		Log.d(TAG, "Unpausing game thread");
		gameThread.setPaused(false);
		synchronized (gameThread) {
			gameThread.notify();
		}
	}

	// Shutdown game by setting "running" flag, unpausing, and joining
	public void shutdownGame() {
		Log.d(TAG, "Shutting down game thread");
		gameThread.setRunning(false);
		unpauseGame();
		try {
			gameThread.join();
		} catch (InterruptedException e) {
			Log.d(TAG, "Join interrupted");
			e.printStackTrace();
			Thread.currentThread().interrupt();
			return;
		}
	}

	// Render game
	protected void render() {
		Log.d(TAG, "Rendering game");

		// lock canvas for drawing
		Canvas canvas = getHolder().lockCanvas();
		if (canvas != null) {
			// clear canvas
			canvas.drawColor(Color.WHITE);

			// render current display
			if (currDisplay.equals("main")) {
				renderMain(canvas);
			} else {
				// log unknown display type
				Log.d(TAG, "Unknown display type: " + currDisplay);
			}

			// unlocking canvas and posting
			getHolder().unlockCanvasAndPost(canvas);
		} else {
			Log.d(TAG, "Unable to lock canvas");
		}
	}

	// Render main screen
	protected void renderMain(Canvas canvas) {
		// render background

		// render entities

		// render user interface

	}

	// Render "paused" screen
	protected void renderPause(Canvas canvas, int darkenColor, int textColor) {
		// gray out canvas
		canvas.drawColor(darkenColor, PorterDuff.Mode.DARKEN);

		// draw "paused" text
		Paint paint = new Paint();
		String str = "Tap to Start";
		paint.setColor(textColor);
		paint.setTextAlign(Paint.Align.CENTER);
		// TODO calculate text size instead of fixed value
		paint.setTextSize(75);
		canvas.drawText(str, getWidth() / 2, getHeight() / 2, paint);
	}

	// Update game
	protected void update() {
		Log.d(TAG, "Updating game");
	}
}
