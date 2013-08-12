package com.wymzymedia.arcana.game_utils;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;

public abstract class GameActivity extends Activity {
	public static final String TAG = GameActivity.class.getSimpleName();

	// Class variables
	protected GameView gameView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "Creating ...");
		super.onCreate(savedInstanceState);

		// note: initialize gameView in subclass
		// initialize and set content view
		// gameView = new GameView(this);
		// setContentView(gameView);
		// Log.d(TAG, "View added");
	}

	@Override
	protected void onStart() {
		Log.d(TAG, "Starting ...");
		super.onStart();
	}

	@Override
	protected void onResume() {
		Log.d(TAG, "Resuming ...");
		super.onResume();
	}

	@Override
	protected void onPause() {
		Log.d(TAG, "Pausing ...");
		gameView.pauseGame();
		super.onPause();
	}

	@Override
	protected void onStop() {
		Log.d(TAG, "Stopping ...");
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		Log.d(TAG, "Destroying ...");
		gameView.shutdownGame();
		super.onDestroy();
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		Log.d(TAG, "Saving instance state ...");
		super.onSaveInstanceState(savedInstanceState);

	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		Log.d(TAG, "Restoring instance state ...");
		super.onRestoreInstanceState(savedInstanceState);
	}
}
