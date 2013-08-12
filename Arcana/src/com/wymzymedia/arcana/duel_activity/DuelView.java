package com.wymzymedia.arcana.duel_activity;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

import com.wymzymedia.arcana.AppDefaults;
import com.wymzymedia.arcana.game_utils.GameThread;
import com.wymzymedia.arcana.game_utils.GameView;

public class DuelView extends GameView {
	public static final String TAG = DuelView.class.getSimpleName();

	// Class variables
	private final boolean showMetrics = true;
	private DuelRender renderSys;

	// Constructor
	public DuelView(Context context) {
		super(context);

		// initialize game thread
		gameThread = new GameThread(this, AppDefaults.MAX_LPS);

		// initialize game state
		gameState = new DuelState();

		// initialize variables and systems
		currDisplay = "main";
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// initialize render system
		renderSys = new DuelRender(gameState, getContext(), getWidth(),
				getHeight());

		// initialize user interface
		gameUI = new DuelInterface(getWidth(), getHeight(), gameState);

		super.surfaceCreated(holder);
	}

	// Render game
	@Override
	protected void render() {
		Log.d(TAG, "Rendering game");

		// lock canvas for drawing
		Canvas canvas = getHolder().lockCanvas();
		if (canvas != null) {
			// clear canvas
			canvas.drawColor(Defaults.BG_COLOR);

			// render current display
			if (currDisplay.equals("main")) {
				renderMain(canvas);
			} else {
				// log unknown display type
				Log.d(TAG, "Unknown display type: " + currDisplay);
			}

			// render "paused" prompt
			if (gameThread.getPaused() == true) {
				renderPause(canvas, Defaults.PAUSE_DARKEN_COLOR,
						Defaults.PAUSE_TEXT_COLOR);
			}

			// render thread metrics
			if (showMetrics) {
				gameUI.renderThreadMetrics(canvas, Defaults.TEXT_COLOR,
						gameThread.getAvgLPS(), gameThread.getLoopCounter());
			}

			// unlocking canvas and posting
			getHolder().unlockCanvasAndPost(canvas);
		} else {
			Log.d(TAG, "Unable to lock canvas");
		}
	}

	// Render main screen
	@Override
	protected void renderMain(Canvas canvas) {
		// render background
		renderSys.renderBackground(canvas);

		// render entities
		renderSys.process(canvas);

		// render user interface
		gameUI.renderInterface(canvas, currDisplay);
	}

	// Update game
	@Override
	protected void update() {
		Log.d(TAG, "Updating game");

		// processing systems
		// TODO remove
		// system.process();
	}
}