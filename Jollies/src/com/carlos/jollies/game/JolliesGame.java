package com.carlos.jollies.game;

import com.badlogic.gdx.Game;

public class JolliesGame extends Game {

	@Override
	public void create() {
		this.setScreen(new GameScreen());
	}
	
	@Override
	public void render() {
		super.render();
	}
}