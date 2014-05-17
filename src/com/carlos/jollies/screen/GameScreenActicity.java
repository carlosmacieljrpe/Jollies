package com.carlos.jollies.screen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.carlos.jollies.MainActivity;
import com.carlos.jollies.R;

public class GameScreenActicity extends Activity implements OnClickListener {

	private Button start_button;
	private Button exit_button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.menu_screen);
		this.start_button = (Button) findViewById(R.id.textViewStartGame);
		this.exit_button = (Button) findViewById(R.id.textViewQuitGame);
		this.start_button.setOnClickListener(this);
		this.exit_button.setOnClickListener(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.textViewQuitGame:
				this.finish();
			case R.id.textViewStartGame:
				Intent intent = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(intent);
			default:
				break;
		}
	}
}