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
import com.wymzymedia.arcana.duel_activity.components.ArcanaCardC;
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
	private final int cardCols;
	private final int cardRows;
	private final float cellWidth;
	private final float cellHeight;
	private final int textFactor;
	private final int cardWidth;
	private final int cardHeight;
	private final Rect[] gridPosition;
	private final Rect zoomPosition;

	// Shared helper variables
	Paint paint = new Paint();
	Rect rect = new Rect();
	RectF rectF = new RectF();
	Matrix matrix = new Matrix();

	// Constructor
	public DuelRender(GameState state, Context c, int dispX, int dispY,
			int cols, int rows) {
		super(state);

		// initialize variables
		context = c;
		cardCols = cols;
		cardRows = rows;
		cellWidth = dispX / cols;
		cellHeight = dispY / rows;

		// initialize card width and height
		int widthRatio = 150;
		int heightRatio = 210;
		textFactor = 6;
		int dimenFactor = (cellWidth / widthRatio < cellHeight / heightRatio) ? (int) cellWidth
				/ widthRatio
				: (int) cellHeight / heightRatio;
		cardWidth = widthRatio * dimenFactor;
		cardHeight = heightRatio * dimenFactor;

		// initialize position rectangles
		gridPosition = new Rect[cols * rows];
		for (int i = 0; i < cols * rows; i++) {
			int startX = (int) (cellWidth / 2 - cardWidth / 2 + (cellWidth * (i % cols)));
			int startY = (int) (cellHeight / 2 - cardHeight / 2 + (cellHeight * (i / (rows - 1))));
			int stopX = (int) (cellWidth / 2 + cardWidth / 2 + (cellWidth * (i % cols)));
			int stopY = (int) (cellHeight / 2 + cardHeight / 2 + (cellHeight * (i / (rows - 1))));
			gridPosition[i] = new Rect(startX, startY, stopX, stopY);
		}
		zoomPosition = new Rect(dispX / 2 - cardWidth * 2, dispY / 2
				- cardHeight * 2, dispX / 2 + cardWidth * 2, dispY / 2
				+ cardHeight * 2);

		// set required components
		addReqComponent("VitalsC");
		addReqComponent("PlayCardC");
		addReqComponent("DrawDeckC");
		addReqComponent("HandDeckC");
		addReqComponent("ActiveDeckC");
		addReqComponent("DiscardDeckC");
	}

	// Process entities
	public void process(Canvas canvas) {
		for (GameEntity entity : getState().getEntitiesContaining(
				getReqComponents())) {
			execSystem(entity, canvas);
		}
	}

	// TODO rework logic to allow variable rows/columns layouts
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
		renderCard(canvas, gridPosition[1 + offsetFlag * 9], -1);

		// render player vitals
		renderBars(canvas, cellWidth, cellHeight * (1 + offsetFlag * 3),
				vitals.getLife(), Color.GREEN);
		renderBars(canvas, cellWidth * 1.85f,
				cellHeight * (1 + offsetFlag * 3), vitals.getPower(),
				Color.BLUE);

		// render draw deck
		renderCard(canvas, gridPosition[3 + offsetFlag * 3], -1);
		renderNumber(canvas, gridPosition[3 + offsetFlag * 3], draw.getCards()
				.size());

		// render play card
		if (card.getID() >= 0) {
			renderCard(canvas, gridPosition[4 + offsetFlag * 3], card.getID());
		}

		// render discard deck
		renderCard(canvas, gridPosition[5 + offsetFlag * 3], -1);
		renderNumber(canvas, gridPosition[5 + offsetFlag * 3], discard
				.getCards().size());
	}

	// Render duel background
	public void renderBackground(Canvas canvas) {
		// TODO add background image
	}

	// TODO rework logic into more general implementation
	// Render vertically stacking bars at given starting coordinate
	public void renderBars(Canvas canvas, float startX, float startY, int bars,
			int color) {
		float setHeight = cellHeight * 0.1f;
		float barWidth = cellWidth * 0.15f;
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

	// Render card at given position
	public void renderCard(Canvas canvas, Rect position, int cardID) {
		// initialize variables
		ArcanaCardC card = new ArcanaCardC(cardID);
		Bitmap cardBitmap = null;
		// TODO retrieve image from resources
		// cardBitmap = BitmapFactory.decodeResource(
		// context.getResources(), card.getID());

		// render card
		if (cardBitmap != null) {
			// render image
			// TODO refine bitmap rotation and rendering
			// Bitmap entityBitmap = BitmapFactory.decodeResource(
			// context.getResources(), identity.getImageKey());
			// Bitmap scaledBitmap = Bitmap.createScaledBitmap(entityBitmap,
			// Math.round(size * 2), Math.round(size * 2), true);
			// matrix.setRotate(position.getFacing(), size, size);
			// matrix.postTranslate(dispCoord[0] - size, dispCoord[1] - size);
			// canvas.drawBitmap(scaledBitmap, matrix, null);
		} else {
			// draw card shape
			paint.setColor(Color.GRAY);
			paint.setStyle(Paint.Style.FILL_AND_STROKE);
			canvas.drawRect(position, paint);

			// draw card name
			if (cardID >= 0) {
				// initialize text variables
				String nameStr = card.getName();
				int textSize = position.height() / textFactor;
				paint.setTextSize(textSize);
				paint.getTextBounds(nameStr, 0, nameStr.length(), rect);

				// calculate text coordinate
				float textX = position.exactCenterX();
				float textY = position.exactCenterY() + rect.height() / 2;

				// draw name text
				paint.setTextAlign(Paint.Align.CENTER);
				paint.setColor(Defaults.TEXT_COLOR);
				canvas.drawText(nameStr, textX, textY, paint);
			}
		}
	}

	// Render card grid
	public void renderGrid(Canvas canvas) {
		// initialize display variables
		paint.setColor(Defaults.TEXT_COLOR);

		// render grid
		for (int x = 0; x < cardCols; x++) {
			float[] lineStart = { cellWidth * x, 0 };
			float[] lineStop = { cellWidth * x, cellHeight * cardRows };
			canvas.drawLine(lineStart[0], lineStart[1], lineStop[0],
					lineStop[1], paint);
		}
		for (int y = 0; y < cardRows; y++) {
			float[] lineStart = { 0, cellHeight * y };
			float[] lineStop = { cellWidth * cardCols, cellHeight * y };
			canvas.drawLine(lineStart[0], lineStart[1], lineStop[0],
					lineStop[1], paint);
		}
	}

	// Render number text at given coordinate
	public void renderNumber(Canvas canvas, Rect position, int count) {
		// render card count
		if (count >= 0) {
			// initialize text variables
			String countStr = String.valueOf(count);
			int textSize = position.height() / textFactor;
			paint.setTextSize(textSize);
			paint.getTextBounds(countStr, 0, countStr.length(), rect);

			// calculate text coordinate
			float textX = position.right - rect.width() - textSize / 2;
			float textY = position.bottom - textSize / 2;

			// render card count
			paint.setTextAlign(Paint.Align.LEFT);
			paint.setColor(Defaults.TEXT_COLOR);
			canvas.drawText(countStr, textX, textY, paint);
		}
	}

	// TODO rework logic to allow variable rows/columns layouts
	// Render set view of given cards for given player entity
	public void renderSet(GameEntity entity, Canvas canvas, ArcanaDeckC deck) {
		// retrieve components
		VitalsC vitals = (VitalsC) entity.getComponent("VitalsC");

		// initialize variables
		int offsetFlag = vitals.isHuman() ? 1 : 0;

		// render player portrait
		renderCard(canvas, gridPosition[1 + offsetFlag * 9], -1);

		// render player vitals
		renderBars(canvas, cellWidth, cellHeight * (1 + offsetFlag * 3),
				vitals.getLife(), Color.GREEN);
		renderBars(canvas, cellWidth * 1.85f,
				cellHeight * (1 + offsetFlag * 3), vitals.getPower(),
				Color.BLUE);

		// TODO add card offset to handle more than nine active cards
		// render set of nine active cards
		if (vitals.isHuman()) {
			for (int i = 0; i < deck.getCards().size() && i < 9; i++) {
				renderCard(canvas, gridPosition[i], deck.getCard(i).getID());
			}
		} else {
			for (int i = 0; i < deck.getCards().size() && i < 9; i++) {
				renderCard(canvas, gridPosition[i + 3], deck.getCard(i).getID());
			}
		}
	}

	// Render zoomed view of single card
	public void renderZoomCard(int cardID, Canvas canvas) {
		renderCard(canvas, zoomPosition, cardID);
	}
}
