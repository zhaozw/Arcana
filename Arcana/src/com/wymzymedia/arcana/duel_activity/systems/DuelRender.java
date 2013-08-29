package com.wymzymedia.arcana.duel_activity.systems;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import com.wymzymedia.arcana.duel_activity.Defaults;
import com.wymzymedia.arcana.duel_activity.components.ArcanaDeckC;
import com.wymzymedia.arcana.duel_activity.components.CardC;
import com.wymzymedia.arcana.duel_activity.components.DeckC;
import com.wymzymedia.arcana.duel_activity.components.VitalsC;
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

	// Shared helper variables
	Paint paint = new Paint();
	Rect rect = new Rect();
	RectF rectF = new RectF();
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

		// set required components
		addReqComponent("VitalsC");
		addReqComponent("PlayCardC");
		addReqComponent("DrawDeckC");
		addReqComponent("DiscardDeckC");
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
		// retrieve components
		VitalsC vitals = (VitalsC) entity.getComponent("VitalsC");
		CardC card = (CardC) entity.getComponent("PlayCardC");
		DeckC draw = (DeckC) entity.getComponent("DrawDeckC");
		DeckC discard = (DeckC) entity.getComponent("DiscardDeckC");

		// initialize variables
		int offsetFlag = vitals.isHuman() ? 1 : 0;

		// render player portrait
		renderCard(canvas, 1 + offsetFlag * 9, null, -1);

		// render player vitals
		renderBars(canvas, vitals.getLife(), gridWidth, gridHeight
				* (1 + offsetFlag * 3), Color.GREEN);
		renderBars(canvas, vitals.getPower(), gridWidth * 1.85f, gridHeight
				* (1 + offsetFlag * 3), Color.BLUE);

		// render draw deck
		renderCard(canvas, 3 + offsetFlag * 3, null, draw.getCards().size());

		// render play card
		if (card.getID() >= 0) {
			renderCard(canvas, 4 + offsetFlag * 3, card, -1);
		}

		// render discard deck
		renderCard(canvas, 5 + offsetFlag * 3, null, discard.getCards().size());
	}

	// Render active cards view for given player entity
	public void renderActive(GameEntity entity, Canvas canvas) {
		// retrieve components
		VitalsC vitals = (VitalsC) entity.getComponent("VitalsC");
		ArcanaDeckC active = (ArcanaDeckC) entity.getComponent("ActiveDeckC");

		// initialize variables
		int offsetFlag = vitals.isHuman() ? 1 : 0;

		// render player portrait
		renderCard(canvas, 1 + offsetFlag * 9, null, -1);

		// render player vitals
		renderBars(canvas, vitals.getLife(), gridWidth, gridHeight
				* (1 + offsetFlag * 3), Color.GREEN);
		renderBars(canvas, vitals.getPower(), gridWidth * 1.85f, gridHeight
				* (1 + offsetFlag * 3), Color.BLUE);

		// TODO add card offset to handle more than nine active cards
		// render set of nine active cards
		for (int i = 0; i < active.getCards().size() && i < 9; i++) {
			int posNum = (i + 3) + (offsetFlag * (3 - (6 * (i / 3))));
			renderCard(canvas, posNum, active.getCard(i), -1);
		}
	}

	// Render duel background
	public void renderBackground(Canvas canvas) {
		// render grid
		renderGrid(canvas);
	}

	// Render life or power bars
	public void renderBars(Canvas canvas, int bars, float startX, float startY,
			int color) {
		float setHeight = gridHeight * 0.1f;
		float barWidth = gridWidth * 0.15f;
		float barHeight = setHeight * 0.9f;
		float barX = startX;
		float barY = startY - (setHeight * 0.1f) - barHeight;
		paint.setColor(color);
		rectF.set(0, 0, barWidth, barHeight);
		for (int i = 0; i < bars; i++) {
			rectF.offsetTo(barX, barY);
			canvas.drawRect(rectF, paint);
			barY -= setHeight;
		}
	}

	// Render card at given position with optional card count
	public void renderCard(Canvas canvas, int position, CardC card, int count) {
		// initialize card coordinate
		int offsetX = position % 3;
		int offsetY = position / 3;
		float posX = gridWidth / 2 + gridWidth * offsetX;
		float posY = gridHeight / 2 + gridHeight * offsetY;

		// retrieve card image
		Bitmap cardBitmap = null;
		// TODO retrieve image from resources
		// Bitmap cardBitmap = BitmapFactory.decodeResource(
		// context.getResources(), card.getID());

		// render card
		int cardWidth = 150;
		int cardHeight = 210;
		if (cardBitmap != null) {
			// render card
			// TODO refine bitmap rotation and rendering
			// Bitmap entityBitmap = BitmapFactory.decodeResource(
			// context.getResources(), identity.getImageKey());
			// Bitmap scaledBitmap = Bitmap.createScaledBitmap(entityBitmap,
			// Math.round(size * 2), Math.round(size * 2), true);
			// matrix.setRotate(position.getFacing(), size, size);
			// matrix.postTranslate(dispCoord[0] - size, dispCoord[1] - size);
			// canvas.drawBitmap(scaledBitmap, matrix, null);
		} else {
			// render card shape
			paint.setColor(Color.GRAY);
			paint.setStyle(Paint.Style.FILL_AND_STROKE);
			canvas.drawRect(posX - cardWidth / 2, posY - cardHeight / 2, posX
					+ cardWidth / 2, posY + cardHeight / 2, paint);

			// render card name
			if (card != null) {
				// initialize name string
				String nameStr = card.getName();
				// TODO calculate text size instead of fixed value
				int textSize = 50;
				paint.setTextSize(textSize);
				paint.getTextBounds(nameStr, 0, nameStr.length(), rect);

				// initialize text coordinate
				paint.setTextAlign(Paint.Align.CENTER);
				float textX = posX;
				float textY = posY + rect.height() / 2;

				// render card name
				paint.setColor(Defaults.TEXT_COLOR);
				canvas.drawText(nameStr, textX, textY, paint);
			}
		}

		// render card count
		if (count >= 0) {
			// initialize card count string
			String countStr = String.valueOf(count);
			// TODO calculate text size instead of fixed value
			int textSize = 35;
			paint.setTextSize(textSize);
			paint.getTextBounds(countStr, 0, countStr.length(), rect);

			// initialize card count coordinate
			paint.setTextAlign(Paint.Align.LEFT);
			float textX = posX + gridWidth / 2;
			textX = textX - rect.width() - textSize / 2;
			float textY = posY + gridHeight / 2;
			textY = textY - textSize / 2;

			// render card count
			paint.setColor(Defaults.TEXT_COLOR);
			canvas.drawText(countStr, textX, textY, paint);
		}
	}

	// Render card grid
	private void renderGrid(Canvas canvas) {
		// initialize display variables
		paint.setColor(Defaults.TEXT_COLOR);

		// render grid
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
