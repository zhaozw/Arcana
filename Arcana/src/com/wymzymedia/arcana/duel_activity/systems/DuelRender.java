package com.wymzymedia.arcana.duel_activity.systems;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.wymzymedia.arcana.duel_activity.Defaults;
import com.wymzymedia.arcana.duel_activity.components.DeckC;
import com.wymzymedia.arcana.game_utils.GameEntity;
import com.wymzymedia.arcana.game_utils.GameState;
import com.wymzymedia.arcana.game_utils.GameSystem;

public class DuelRender extends GameSystem {
	public static final String TAG = DuelRender.class.getSimpleName();

	// Class variables
	private final Context context;
	private final Rect mainDisplay;
	private final int cardCols;
	private final int cardRows;
	private final float gridWidth;
	private final float gridHeight;
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
		cardCols = 3;
		cardRows = 4;
		gridWidth = mainDisplay.width() / cardCols;
		gridHeight = mainDisplay.height() / cardRows;
		dispFactor = 1;
		viewCenterX = mainDisplay.exactCenterX();
		viewCenterY = mainDisplay.exactCenterX();

		// set required components
		addReqComponent("DeckC");
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
		// TODO remove
		Log.d(TAG, "===== HELLO!!! =====");

		// retrieve components
		DeckC deck = (DeckC) entity.getComponent("DeckC");

		// draw entity
		int posOffset = (deck.getPlayerFlag() ? 1 : 0) * 3;
		if (deck.getType().equals("deck")) {
			renderCard(canvas, 3 + posOffset, 0, deck.getCards().size());
		} else if (deck.getType().equals("hand")) {

		} else if (deck.getType().equals("active")) {
			for (int i = 0; i < deck.getCards().size(); i++) {
				if (i == 0) {
					renderCard(canvas, 4 + posOffset, 0, -1);
				} else if (i < 4) {
					renderCard(canvas, 0 + i - 1 + posOffset * 3, 0, -1);
				} else {
					// log excess active cards
					Log.d(TAG, "Too many active cards");
				}
			}
		} else if (deck.getType().equals("discard")) {
			renderCard(canvas, 5 + posOffset, 0, deck.getCards().size());
		} else {
			// log unknown deck type
			Log.d(TAG, "Unknown deck type: " + deck.getType());
		}
	}

	// Render battle background
	public void renderBackground(Canvas canvas) {
		// render grid
		renderGrid(canvas);
	}

	// Render card at given position with optional card count
	public void renderCard(Canvas canvas, int position, int id, int count) {
		// initialize card coordinate
		int offsetX = position % 3;
		int offsetY = position / 3;
		float posX = gridWidth / 2 + gridWidth * offsetX;
		float posY = gridHeight / 2 + gridHeight * offsetY;

		// retrieve card image
		Bitmap cardBitmap = null;
		// TODO replace image flag with retrieved image or null
		// Bitmap cardBitmap = BitmapFactory.decodeResource(
		// context.getResources(), id);

		// draw card
		int cardWidth = 150;
		int cardHeight = 210;
		if (cardBitmap == null) {
			// draw card outline
			paint.setColor(Color.GRAY);
			paint.setStyle(Paint.Style.FILL_AND_STROKE);
			canvas.drawRect(posX - cardWidth / 2, posY - cardHeight / 2, posX
					+ cardWidth / 2, posY + cardHeight / 2, paint);

			// draw card ID unless 0
			if (id != 0) {
				// initialize card ID string
				String idStr = String.valueOf(id);
				// TODO calculate text size instead of fixed value
				int textSize = 50;
				paint.setTextSize(textSize);
				paint.getTextBounds(idStr, 0, idStr.length(), rect);

				// initialize card ID coordinate
				paint.setTextAlign(Paint.Align.CENTER);
				float textX = posX;
				float textY = posY + rect.height() / 2;

				// draw card ID
				paint.setColor(Defaults.TEXT_COLOR);
				canvas.drawText(idStr, textX, textY, paint);
			}
		} else {
			// draw card
			// TODO refine bitmap rotation and rendering
			// Bitmap entityBitmap = BitmapFactory.decodeResource(
			// context.getResources(), identity.getImageKey());
			// Bitmap scaledBitmap = Bitmap.createScaledBitmap(entityBitmap,
			// Math.round(size * 2), Math.round(size * 2), true);
			// matrix.setRotate(position.getFacing(), size, size);
			// matrix.postTranslate(dispCoord[0] - size, dispCoord[1] - size);
			// canvas.drawBitmap(scaledBitmap, matrix, null);
		}

		// draw card count
		if (count >= 0) {
			// initialize card count string
			String countStr = String.valueOf(count);
			// TODO calculate text size instead of fixed value
			int textSize = 50;
			paint.setTextSize(textSize);
			paint.getTextBounds(countStr, 0, countStr.length(), rect);

			// initialize card count coordinate
			paint.setTextAlign(Paint.Align.LEFT);
			float textX = posX + gridWidth / 2;
			textX = textX - rect.width() - textSize / 2;
			float textY = posY + gridHeight / 2;
			textY = textY - textSize / 2;

			// draw card count
			paint.setColor(Defaults.TEXT_COLOR);
			canvas.drawText(countStr, textX, textY, paint);
		}
	}

	// Render card grid
	private void renderGrid(Canvas canvas) {
		// initialize display variables
		paint.setColor(Defaults.TEXT_COLOR);

		// draw grid
		for (int x = 0; x < cardCols; x++) {
			float[] lineStart = { gridWidth * x, 0 };
			float[] lineStop = { gridWidth * x, mainDisplay.height() - 1 };
			canvas.drawLine(lineStart[0], lineStart[1], lineStop[0],
					lineStop[1], paint);
		}
		for (int y = 0; y < cardRows; y++) {
			float[] lineStart = { 0, gridHeight * y };
			float[] lineStop = { mainDisplay.width() - 1, gridHeight * y };
			canvas.drawLine(lineStart[0], lineStart[1], lineStop[0],
					lineStop[1], paint);
		}
	}
}
