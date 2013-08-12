package com.wymzymedia.arcana.game_utils;

import java.math.BigDecimal;

public final class GameUtils {
	public static final String TAG = GameUtils.class.getSimpleName();

	// Private constructor
	private GameUtils() {
	}

	// Calculate average value of integer array but skipping given index value
	public static float calcArrayAvg(int[] array, int skipIndex) {
		int sum = 0;
		int elements = 0;
		for (int i = 0; i < array.length; i++) {
			if (i != skipIndex && array[i] > 0) {
				sum += array[i];
				elements++;
			}
		}

		// avoid divide by zero
		if (elements > 0) {
			return (float) sum / elements;
		} else {
			return 0;
		}
	}

	// Calculate new coords based on starting point, heading, and distance
	public static float[] calcNewCoord(double x, double y, double heading,
			double distance) {
		double radianHeading = Math.toRadians(heading);
		double newX = x + distance * Math.cos(radianHeading);
		double newY = y + distance * Math.sin(radianHeading);
		return new float[] { (float) newX, (float) newY };
	}

	// Calculate distance between two points
	public static float distanceBetween(double x1, double y1, double x2,
			double y2) {
		double xDist = x1 - x2;
		double yDist = y1 - y2;
		return (float) Math.sqrt(xDist * xDist + yDist * yDist);
	}

	// Calculate heading from (x1, y1) to (x2, y2)
	public static float headingBetween(double x1, double y1, double x2,
			double y2) {
		double theta = Math.atan2(y2 - y1, x2 - x1);
		return (float) Math.toDegrees(theta);
	}

	// Round float to given number of decimal places
	public static float round(float number, int decimals) {
		BigDecimal bd = new BigDecimal(Float.toString(number));
		bd = bd.setScale(decimals, BigDecimal.ROUND_HALF_UP);
		return bd.floatValue();
	}
}
