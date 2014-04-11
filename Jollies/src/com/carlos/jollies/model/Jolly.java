package com.carlos.jollies.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Jolly {
	
	public static final float SIZE = 1f;
	
	public Rectangle bounds;
	
	public enum EMOTION{
		GRUMPY(new Color(0f,08f,0f,1)),
		SLASH_FACE(new Color(0.9f,0f,0f,1)),
		POKER_FACE(new Color(0,0,1,1)),
		SMILE(new Color(0f,0f,0,1)),
		JOLLY(new Color(1,1,0,1));

	    private EMOTION(Color color){
	        this.color = color;
	    }

	    private final Color color;

	    public Color getColor(){return this.color;}
	}
	
	public EMOTION emotion; 
	
	public GridPosition position;
	
	public STATE state;
	
	public enum STATE{
		INACTIVE, ENLIGHTENED
	}
	
	public Jolly(Vector2 vector, EMOTION emotion){
		bounds = new Rectangle();
		bounds.height = SIZE;
		bounds.width = SIZE*2;
		position = new GridPosition(vector);
		this.emotion = emotion;
	}

	public GridPosition getPosition() {
		return position;
	}

	public void setPosition(GridPosition position) {
		this.position = position;
	}
	
}