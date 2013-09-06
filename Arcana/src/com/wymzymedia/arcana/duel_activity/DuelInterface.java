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
	private final int gridRows;
	private final int gridCols;
	private final float cellWidth;
	private final float cellHeight;
	private final String[] actions;

	// Constructor
	public DuelInterface(GameState s, int dispX, int dispY, int cols, int rows,
			GameView v) {
		super(s, dispX, dispY);

		// initialize variables
		view = v;
		gridCols = cols;
		gridRows = rows;
		cellWidth = dispX / cols;
		cellHeight = dispY / rows;
		actions = new String[cols * rows];
	}

	// Process touch event
	@Override
	public void processTouchEvent(String currDisplay, MotionEvent event) {
		// check for down touch
		if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
			// process interaction based on display type
			if (currDisplay.equals("main") || currDisplay.equals("active")
					|| currDisplay.equals("hand")) {
				// initialize cell actions
				initActions(currDisplay, ((DuelView) view).getDisplayTarget());

				// retrieve and execute action
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
		} else if (type.equals("active")) {
		} else if (type.equals("hand")) {
		} else if (type.equals("card")) {
		} else {
			// log unknown interface type
			Log.d(TAG, "Unknown interface type: " + type);
		}
	}

	// Execute action string
	public void executeActionStr(String actionStr) {
		if (actionStr != null) {
			String[] actionElems = actionStr.split(":");
			if (actionElems[0].equals("display")) {
				// set display
				view.setCurrDisplay(actionElems[1]);

				// set display target if available
				if (actionElems.length >= 3) {
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

	// Identify and return action string for cell matching user touch
	public String getActionStr(MotionEvent event) {
		// map touch event to grid cell
		for (int i = 0; i < (gridRows * gridCols); i++) {
			int gridX = i % gridCols;
			int gridY = i / gridCols;
			if (event.getX() >= gridX * cellWidth
					&& event.getX() <= (gridX + 1) * cellWidth
					&& event.getY() >= gridY * cellHeight
					&& event.getY() <= (gridY + 1) * cellHeight) {
				// return action string for cell
				return actions[i];
			}
		}
		// return null if no corresponding cell
		return null;
	}

	// Initialize action strings
	private void initActions(String type, String target) {
		// clear actions
		Arrays.fill(actions, null);

		// check interface type
		if (type.equals("main")) {
			// set actions
			actions[1] = "display:active:computer";
			actions[9] = "display:hand";
			actions[10] = "display:active:human";
			actions[11] = "phase:4";
		} else if (type.equals("active")) {
			// initialize variables
			ArcanaDeckC deck = null;
			int offset = -1;
			if (target.equals("human")) {
				deck = (ArcanaDeckC) ((DuelState) state).getHuman()
						.getComponent("ActiveDeckC");
				offset = 0;
				actions[9] = "display:hand";
				actions[10] = "display:main";
			} else if (target.equals("computer")) {
				deck = (ArcanaDeckC) ((DuelState) state).getComputer()
						.getComponent("ActiveDeckC");
				offset = 3;
				actions[1] = "display:main";
			} else {
				// log unknown target type
				Log.d(TAG, "Unknown target type: " + target);
			}

			// map cardIDs to actions
			for (int i = 0; i < 9 && i < deck.getCards().size(); i++) {
				int cardID = deck.getCard(i).getID();
				if (cardID >= 0) {
					actions[i + offset] = "display:card:" + cardID;
				}
			}
		} else if (type.equals("hand")) {
			// retrieve hand cards
			ArcanaDeckC deck = (ArcanaDeckC) ((DuelState) state).getHuman()
					.getComponent("HandDeckC");

			// map cardIDs to actions
			for (int i = 0; i < 9 && i < deck.getCards().size(); i++) {
				int cardID = deck.getCard(i).getID();
				if (cardID >= 0) {
					actions[i] = "select:card:" + cardID;
				}
			}
			actions[9] = "display:main";
			actions[10] = "display:active:human";
		} else if (type.equals("card")) {
		} else {
			// log unknown interface type
			Log.d(TAG, "Unknown interface type: " + type);
		}
	}
}
