package com.carlos.jollies;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.carlos.jollies.game.JolliesGame;

public class MainActivity extends AndroidApplication {

	private static final String SHOULD_RECREATE = "SHOULD_RECREATE";
	public SharedPreferences prefs;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		prefs = this.getSharedPreferences(
			      "com.carlos.jollies", Context.MODE_PRIVATE);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = false;
		config.useCompass = false;
		config.useWakelock = false;
		initialize(new JolliesGame(this), config);
	}
	
	@Override
	protected void onPause() {
		prefs.edit().putBoolean(SHOULD_RECREATE, true).commit();
		super.onPause();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		if(prefs.getBoolean(SHOULD_RECREATE, false)){
			prefs.edit().putBoolean(SHOULD_RECREATE, false).commit();
			//this.finish();
		}
	}
	
	@Override
	protected void onStop() {
		prefs.edit().putBoolean(SHOULD_RECREATE, true).commit();
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		prefs.edit().putBoolean(SHOULD_RECREATE, true).commit();
		super.onDestroy();
	}
}