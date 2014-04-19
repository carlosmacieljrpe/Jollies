package com.carlos.jollies.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Jolly {
	
	public static final float WIDTH_SIZE = 1.4f;
	
	public static final float HEIGHT_SIZE = 0.7f;
	
	public Rectangle bounds;
	
	public enum EMOTION{
		GRUMPY(new Color(0.8f,0.8f,0.8f,1)),
		SMILE(new Color(0.9f,0.9f,0,1)),
		JOLLY(new Color(1,1,0,1));

	    private EMOTION(Color color){
	        this.color = color;
	    }

	    private final Color color;

	    public Color getColor(){return this.color;}
	}
	
	public EMOTION emotion; 
	
	
	public STATE state;

	public GridPosition gridPosition;
	
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
	}

	
	
}