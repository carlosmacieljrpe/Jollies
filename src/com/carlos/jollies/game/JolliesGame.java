package com.carlos.jollies.game;

import android.content.Context;

import com.badlogic.gdx.Game;

public class JolliesGame extends Game {

	public Context context;
	
	public JolliesGame(Context ctx){
		this.context = ctx;
	}
	
	@Override
	public void create() {
		this.setScreen(new GameScreen());
	}
	
	@Override
	public void render() {
		super.render();
	}
}