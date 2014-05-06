package com.carlos.jollies.model;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Jolly {

	public static final float WIDTH_SIZE = 1.8f;

	public static final float HEIGHT_SIZE = 0.9f;

	public Rectangle bounds;

	private ArrayList<Sound> sounds;

	public enum EMOTION{
		GRUMPY("images/grumpy2.png"),
		SMILE("images/smiley2.png"),
		JOLLY("images/jolly.png");

		private EMOTION(String paramName){
			this.textureName = paramName;
		}

		private final String textureName;

		public String getTextureName(){
			return this.textureName;
		}
	}

	public EMOTION emotion; 


	public STATE state;

	public GridPosition gridPosition;

	public boolean selected;

	public enum STATE{
		INACTIVE, ENLIGHTENED
	}

	public Jolly(Vector3 vector, EMOTION emotion, GridPosition gridPos){
		bounds = new Rectangle();
		bounds.height = HEIGHT_SIZE;
		bounds.width = WIDTH_SIZE;
		bounds.x = vector.x;
		bounds.y = vector.y;
		this.emotion = emotion;
		this.gridPosition = gridPos;
		initJollySound();
	}

	public void SwapEmotion(EMOTION emotion){
		this.emotion = emotion;
		initJollySound();
	}

	private void initJollySound(){
		this.sounds = new ArrayList<Sound>();
		if(this.emotion == EMOTION.GRUMPY){
			sounds.add(Gdx.audio.newSound(Gdx.files.internal("data/grumpy/dont.mp3")));
			sounds.add(Gdx.audio.newSound(Gdx.files.internal("data/grumpy/please_stop.mp3")));
			sounds.add(Gdx.audio.newSound(Gdx.files.internal("data/grumpy/sarcastic.mp3")));
			sounds.add(Gdx.audio.newSound(Gdx.files.internal("data/grumpy/will_not_move.mp3")));
		}else if(this.emotion == EMOTION.JOLLY){
			sounds.add(Gdx.audio.newSound(Gdx.files.internal("data/jolly/glorious.mp3")));
			sounds.add(Gdx.audio.newSound(Gdx.files.internal("data/jolly/spreading.mp3")));
			sounds.add(Gdx.audio.newSound(Gdx.files.internal("data/jolly/yeah.mp3")));
		}
	}

	public void playSound(){
		if(this.sounds.size() > 0){
			Random r = new Random();
			int index = r.nextInt(this.sounds.size());
			this.sounds.get(index).play();
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (!(obj instanceof Jolly))
            return false;

        Jolly jolly = (Jolly) obj;
        if(this.emotion == jolly.emotion &&
        		this.gridPosition.x == jolly.gridPosition.x &&
        		this.gridPosition.y == jolly.gridPosition.y){
        	return true;
        }else{
        	return false;
        }
	}
}