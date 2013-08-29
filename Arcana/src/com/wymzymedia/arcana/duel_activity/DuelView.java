package com.wymzymedia.arcana.duel_activity;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

import com.wymzymedia.arcana.AppDefaults;
import com.wymzymedia.arcana.duel_activity.systems.DuelDiscard;
import com.wymzymedia.arcana.duel_activity.systems.DuelDraw;
import com.wymzymedia.arcana.duel_activity.systems.DuelPlay;
import com.wymzymedia.arcana.duel_activity.systems.DuelRender;
import com.wymzymedia.arcana.duel_activity.systems.DuelSelect;
import com.wymzymedia.arcana.duel_activity.systems.DuelUpkeep;
import com.wymzymedia.arcana.game_utils.GameEntity;
import com.wymzymedia.arcana.game_utils.GameThread;
import com.wymzymedia.arcana.game_utils.GameView;

public class DuelView extends GameView {
	public static final String TAG = DuelView.class.getSimpleName();

	// Class variables
	private final boolean showMetrics = true;
	private DuelRender renderSys;
	private final DuelDraw drawSys;
	private final DuelSelect selectSys;
	private final DuelPlay playSys;
	private final DuelUpkeep upkeepSys;
	private final DuelDiscard discardSys;

	// Constructor
	public DuelView(Context context) {
		super(context);

		// initialize game thread
		gameThread = new GameThread(this, AppDefaults.MAX_LPS);

		// initialize game state
		gameState = new DuelState();

		// initialize variables and systems
		currDisplay = "main";
		drawSys = new DuelDraw(gameState);
		selectSys = new DuelSelect(gameState);
		playSys = new DuelPlay(gameState);
		upkeepSys = new DuelUpkeep(gameState);
		discardSys = new DuelDiscard(gameState);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// determine number of display rows and columns
		int cols = 3;
		int rows = 4;

		// initialize render system
		renderSys = new DuelRender(gameState, getContext(), getWidth(),
				getHeight(), cols, rows);

		// initialize user interface
		gameUI = new DuelInterface(gameState, getWidth(), getHeight(), cols,
				rows);

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
			} else if (currDisplay.equals("playerActive")) {
				renderActive(canvas, ((DuelState) gameState).getHuman());
			} else if (currDisplay.equals("enemyActive")) {
				renderActive(canvas, ((DuelState) gameState).getComputer());
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

	// Render basic duel screen
	@Override
	protected void renderMain(Canvas canvas) {
		// render background
		renderSys.renderBackground(canvas);

		// render entities
		renderSys.process(canvas);

		// render user interface
		gameUI.renderInterface(canvas, currDisplay);
	}

	// Render active cards screen for given player entity
	protected void renderActive(Canvas canvas, GameEntity entity) {
		// render background
		renderSys.renderBackground(canvas);

		// render entities
		renderSys.renderActive(entity, canvas);

		// render user interface
		gameUI.renderInterface(canvas, currDisplay);
	}

	// Update game
	@Override
	protected void update() {
		Log.d(TAG, "Updating game");

		// processing systems
		if (((DuelState) gameState).getPhase() == 1) {
			drawSys.process();
		} else if (((DuelState) gameState).getPhase() == 2) {
			selectSys.process();
		} else if (((DuelState) gameState).getPhase() == 3) {
			playSys.process();
		} else if (((DuelState) gameState).getPhase() == 4) {
			upkeepSys.process();
		} else if (((DuelState) gameState).getPhase() == 5) {
			discardSys.process();
		}

		// update game phase
		((DuelState) gameState).updatePhase();
	}
}
