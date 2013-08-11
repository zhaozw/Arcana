package com.wymzymedia.arcana;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// Launch Duel activity when user clicks button
	public void startDuel(View view) {
		Intent intent = new Intent(this, DuelActivity.class);
		startActivity(intent);
	}

	// Launch Options activity when user clicks button
	public void startOptions(View view) {
		Intent intent = new Intent(this, OptionsActivity.class);
		startActivity(intent);
	}

	// Launch About activity when user clicks button
	public void startAbout(View view) {
		Intent intent = new Intent(this, AboutActivity.class);
		startActivity(intent);
	}
}
