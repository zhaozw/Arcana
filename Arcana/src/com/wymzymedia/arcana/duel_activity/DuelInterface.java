package com.wymzymedia.arcana.duel_activity;

import java.util.Arrays;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import com.wymzymedia.arcana.duel_activity.components.ArcanaDeckC;
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
		// TODO remove
		Log.d(TAG, ">>>>> PROCESSING TOUCH <<<<<");
		if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
			if (currDisplay.equals("main")) {
				executeActionStr(getActionStr(event));
			} else if (currDisplay.equals("enemyActive")) {
				executeActionStr(getActionStr(event));
			} else if (currDisplay.equals("playerActive")) {
				executeActionStr(getActionStr(event));
			} else if (currDisplay.equals("hand")) {
				executeActionStr(getActionStr(event));
			} else if (currDisplay.equals("card")) {
				// set display to main
				view.setCurrDisplay("main");
			} else {
				// log unknown interface type
				Log.d(TAG, "Unknown interface type: " + currDisplay);
			}
		}
	}

	// Render specified interface
	@Override
	public void renderInterface(Canvas canvas, String type) {
		if (type.equals("main")) {
			// set interface actions
			Arrays.fill(cellActionStr, null);
			cellActionStr[1] = "display:active:enemy";
			cellActionStr[9] = "display:hand";
			cellActionStr[10] = "display:active:player";
			cellActionStr[11] = "phase:4";
		} else if (type.equals("enemyActive")) {
			// retrieve active cards
			ArcanaDeckC active = (ArcanaDeckC) ((DuelState) state)
					.getComputer().getComponent("ActiveDeckC");

			// set interface actions
			Arrays.fill(cellActionStr, null);
			cellActionStr[1] = "display:main";
			for (int i = 3; i <= 11; i++) {
				// retrieve card ID at given position
				if (i - 3 < active.getCards().size()) {
					int cardID = active.getCard(i - 3).getID();
					if (cardID >= 0) {
						cellActionStr[i] = "display:card:" + cardID;
					}
				}
			}
		} else if (type.equals("playerActive")) {
			// retrieve active cards
			ArcanaDeckC active = (ArcanaDeckC) ((DuelState) state).getHuman()
					.getComponent("ActiveDeckC");

			// set interface actions
			Arrays.fill(cellActionStr, null);
			for (int i = 8; i >= 0; i--) {
				// TODO fix action initialization for player active display
			}
			cellActionStr[9] = "display:hand";
			cellActionStr[10] = "display:main";
			cellActionStr[11] = "phase:4";
		} else if (type.equals("hand")) {
			// retrieve hand cards
			ArcanaDeckC hand = (ArcanaDeckC) ((DuelState) state).getHuman()
					.getComponent("HandDeckC");

			// set interface actions
			Arrays.fill(cellActionStr, null);
			for (int i = 0; i <= 8; i++) {
				// retrieve card ID at given position
				if (i < hand.getCards().size()) {
					int cardID = hand.getCard(i).getID();
					if (cardID >= 0) {
						cellActionStr[i] = "select:card:" + cardID;
					}
				}
			}
			cellActionStr[9] = "display:main";
			cellActionStr[10] = "display:playerActive";
			cellActionStr[11] = "phase:4";
		} else if (type.equals("card")) {
			// set interface actions
			Arrays.fill(cellActionStr, null);
		} else {
			// log unknown interface type
			Log.d(TAG, "Unknown interface type: " + type);
		}
	}

	// Identify and return action string correlating to user touch
	public String getActionStr(MotionEvent event) {
		// map touch event to card cell
		for (int i = 0; i < (cardRows * cardCols); i++) {
			int gridX = i % cardCols;
			int gridY = i / cardCols;
			if (event.getX() >= gridX * cellWidth
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
	public void executeActionStr(String actionStr) {
		if (actionStr != null) {
			String[] actionElems = actionStr.split(":");
			if (actionElems[0].equals("display")) {
				// set display
				view.setCurrDisplay(actionElems[1]);

				// set display target if available
				if (actionElems[2] != null) {
					((DuelView) view).setDisplayTarget(actionElems[2]);
				}
			} else if (actionElems[0].equals("select")) {
				if (actionElems[1].equals("card")) {
					// set player play card
					((DuelView) view).setPlayCard(Integer
							.valueOf(actionElems[2]));

					// set display to main
					view.setCurrDisplay("main");
				}
			} else if (actionElems[0].equals("skip")) {
				// set player phase
				VitalsC vitals = (VitalsC) ((DuelState) state).getHuman()
						.getComponent("VitalsC");
				vitals.setPhase(4);

				// set display to main
				view.setCurrDisplay("main");
			} else {
				// log unknown action type
				Log.d(TAG, "Unknown action type: " + actionElems[0]);
			}
		}
	}
}
