package com.wymzymedia.arcana.game_utils;

import android.util.Log;

public final class GameThread extends Thread {
	public static final String TAG = GameThread.class.getSimpleName();

	// Class variables
	private volatile boolean running = false;
	private volatile boolean paused = true;
	private long loopPeriod;
	private GameView view;

	// Loop metrics variables
	private int loopCounter = 0;
	private float avgLPS;
	private int[] loopCounts = new int[11];
	private int lastLoopIndex = 0;

	// Constructor
	public GameThread(GameView v, int lps) {
		super();

		// initialize variables
		// loopPeriod = milliseconds per loop required to equal given lps
		loopPeriod = 1000 / lps;
		avgLPS = lps;
		view = v;
	}

	// Execute render/update cycle
	@Override
	public void run() {
		Log.d(TAG, "Enter game loop");

		// timing vars
		long loopStartTime;
		long loopTimeDiff;
		long excessTime;

		while (running) {
			Log.d(TAG, "Running game loop");

			while (paused) {
				Log.d(TAG, "Pausing game loop");
				synchronized (this) {
					try {
						wait();
					} catch (InterruptedException e) {
						Log.d(TAG, "Wait interrupted");
						e.printStackTrace();
						Thread.currentThread().interrupt();
						return;
					}
				}
			}

			if (running && !paused) {
				// set loop start time
				loopStartTime = System.currentTimeMillis();

				// calculate thread metrics
				calcThreadMetrics(loopStartTime);

				// TODO streamline update to skip excessive calculations
				// update game state
				view.update();

				// TODO consider separate update and render threads
				// render game state
				view.render();

				// calculate time used to perform loop
				loopTimeDiff = System.currentTimeMillis() - loopStartTime;

				// calculate excess time
				excessTime = loopPeriod - loopTimeDiff;

				// TODO refine loop to achieve independent UPS and FPS
				// burn off excess time
				if (excessTime > 0) {
					try {
						sleep(excessTime);
					} catch (InterruptedException e) {
						Log.d(TAG, "Sleep interrupted");
						e.printStackTrace();
						Thread.currentThread().interrupt();
						return;
					}
				}
			} else {
				Log.d(TAG, "Skipping game loop");
			}
		}
		Log.d(TAG, "Exit game loop");
	}

	// Calculate thread metrics
	private void calcThreadMetrics(long time) {
		incrementLoopCount(time);
		float arrayAvg = GameUtils.calcArrayAvg(loopCounts, lastLoopIndex);
		if (arrayAvg != 0) {
			avgLPS = arrayAvg;
		}
	}

	// Return average loops per second
	public float getAvgLPS() {
		return avgLPS;
	}

	// Return total loops counter
	public int getLoopCounter() {
		return loopCounter;
	}

	// Return paused flag
	public boolean getPaused() {
		return paused;
	}

	// Increment loop count
	private void incrementLoopCount(long time) {
		// calculate index
		int countIndex = (int) (time / 1000) % loopCounts.length;

		// TODO zero out multiple elements if index jump > 1
		// reset count when moving to new second
		if (countIndex != lastLoopIndex) {
			loopCounts[countIndex] = 0;
		}

		// increment count and update last index
		loopCounter++;
		loopCounts[countIndex]++;
		lastLoopIndex = countIndex;
	}

	// Set paused flag
	public void setPaused(boolean p) {
		paused = p;
	}

	// Set running flag
	public void setRunning(boolean r) {
		running = r;
	}
}
