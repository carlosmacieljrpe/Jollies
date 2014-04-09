package com.carlos.jollies.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class GridPosition {

	public static final float SIZE = 1f;
	public Vector2 position;
	public Rectangle bounds;
	
	public GridPosition(Vector2 vector){
		this.position = vector;
		bounds = new Rectangle();
		bounds.height = SIZE;
		bounds.width = SIZE;
	}
}