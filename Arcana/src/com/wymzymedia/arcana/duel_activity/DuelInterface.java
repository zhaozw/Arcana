package com.wymzymedia.arcana.duel_activity;

import java.util.Arrays;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import com.wymzymedia.arcana.duel_activity.components.VitalsC;
import com.wymzymedia.arcana.game_utils.GameInterface;
import com.wymzymedia.arcana.game_utils.GameState;
import com.wymzymedia.arcana.game_utils.GameView;

public class DuelInterface extends GameInterface {
	public static final String TAG = DuelInterface.class.getSimpleName();

	// Class variables
	private final GameView view;
	private final int screenWidth;
	private final int screenHeight;
	private final int cardRows;
	private final int cardCols;
	private final float cellWidth;
	private final float cellHeight;
	private final String[] cellActionStr;

	// Constructor
	public DuelInterface(GameState s, int dispX, int dispY, int cols, int rows,
			GameView v) {
		super(s, dispX, dispY);

		// initialize variables
		view = v;
		screenWidth = dispX;
		screenHeight = dispY;
		cardCols = cols;
		cardRows = rows;
		cellWidth = dispX / cols;
		cellHeight = dispY / rows;
		cellActionStr = new String[cols * rows];
	}

	// Process touch event
	@Override
	public void processTouchEvent(String currDisplay, MotionEvent event) {
		if (currDisplay.equals("main")) {
			executeActionStr(getActionStr(event));
		} else if (currDisplay.equals("enemyActive")) {
			executeActionStr(getActionStr(event));
		} else if (currDisplay.equals("playerActive")) {
			executeActionStr(getActionStr(event));
		} else if (currDisplay.equals("card")) {
			// set display to main
			view.setCurrDisplay("main");
		} else {
			// log unknown interface type
			Log.d(TAG, "Unknown interface type: " + currDisplay);
		}
	}

	// Render specified interface
	@Override
	public void renderInterface(Canvas canvas, String type) {
		if (type.equals("main")) {
			// initialize interface actions
			Arrays.fill(cellActionStr, null);
			cellActionStr[1] = "display:enemyActive";
			cellActionStr[9] = "display:hand";
			cellActionStr[10] = "display:playerActive";
			cellActionStr[11] = "phase:4";
		} else if (type.equals("enemyActive")) {
			// initialize interface actions
			Arrays.fill(cellActionStr, null);
			cellActionStr[1] = "display:main";
			for (int i = 3; i <= 11; i++) {
				int cardID = -1;
				if (cardID >= 0) {
					cellActionStr[i] = "display:card:" + cardID;
				}
			}
		} else if (type.equals("playerActive")) {
			// initialize interface actions
			Arrays.fill(cellActionStr, null);
			for (int i = 8; i >= 0; i--) {
				int cardID = -1;
				if (cardID >= 0) {
					cellActionStr[i] = "display:card:" + cardID;
				}
			}
			cellActionStr[9] = "display:hand";
			cellActionStr[10] = "display:main";
			cellActionStr[11] = "phase:4";
		} else if (type.equals("card")) {
		} else {
			// log unknown interface type
			Log.d(TAG, "Unknown interface type: " + type);
		}
	}

	// Identify and return action string correlating to user touch
	public String getActionStr(MotionEvent event) {
		// TODO remove
		// Log.d(TAG, event.toString());

		// map touch event to card cell
		for (int i = 0; i < (cardRows * cardCols); i++) {
			int gridX = i % cardCols;
			int gridY = i / cardCols;
			if (event.getAction() == android.view.MotionEvent.ACTION_DOWN
					&& event.getX() >= gridX * cellWidth
					&& event.getX() <= (gridX + 1) * cellWidth
					&& event.getY() >= gridY * cellHeight
					&& event.getY() <= (gridY + 1) * cellHeight) {
				// return action string for cell
				return cellActionStr[i];
			}
		}
		// return null if no corresponding cell
		return null;
	}

	// Execute action string
	public void executeActionStr(String action) {
		if (action != null) {
			String[] actionElems = action.split(":");
			if (actionElems[0].equals("display")) {
				// set display
				view.setCurrDisplay(actionElems[1]);
			} else if (actionElems[0].equals("phase")) {
				// set player phase
				VitalsC vitals = (VitalsC) ((DuelState) state).getHuman()
						.getComponent("VitalsC");
				vitals.setPhase(Integer.valueOf(actionElems[1]));

				// set display to main
				view.setCurrDisplay("main");
			} else {
				// log unknown action type
				Log.d(TAG, "Unknown action type: " + actionElems[0]);
			}
		}
	}
}
