package com.wymzymedia.arcana;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.wymzymedia.arcana.duel_activity.DuelView;
import com.wymzymedia.arcana.game_utils.GameActivity;

public class DuelActivity extends GameActivity {
	public static final String TAG = DuelActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Hide notification bar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		// Hide title bar
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		// Lock orientation to portrait
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		// Set content view
		gameView = new DuelView(this);
		setContentView(gameView);
		Log.d(TAG, "View added");
	}
}
