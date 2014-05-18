package com.carlos.jollies.model;

import com.badlogic.gdx.math.Vector3;
import com.carlos.jollies.model.Jolly.EMOTION;

public class World {
	public int JOLLIES_ROW_NUMBER;
	
	public int JOLLIES_COLUMN_NUMBER;
	
	private Jolly[][] jolliesList = new Jolly[4][4];
	
	public enum OBJECTS_POSITIONS{
		STEPS_LABEL(35, 360),
		CHONOMETER(200, 360);
		
		private OBJECTS_POSITIONS(int x, int y){
			this.x = x;
			this.y = y;
		}

		public final int x;
		
		public final int y;
	}
	
	public World(int rows, int columns){
		JOLLIES_ROW_NUMBER = rows;
		JOLLIES_COLUMN_NUMBER = columns;
		createGameWorld();
	}
	
	public void createGameWorld(){
		for(int i = 0; i < JOLLIES_ROW_NUMBER; i++){
			for(int j = 0; j < JOLLIES_COLUMN_NUMBER;j++){
				EMOTION jollyEmotion = EMOTION.GRUMPY;
				if(j == 0 && i == 0){
					jollyEmotion = EMOTION.JOLLY;
				}
				Jolly jolly = new Jolly(new Vector3(0.7f + (Jolly.WIDTH_SIZE*1.2f*i), 0.5f + (Jolly.HEIGHT_SIZE*1.2f*j), 0), jollyEmotion, new GridPosition(i,  j));
				jolliesList[i][j] = jolly;
			}
		}
	}

	public Jolly[][] getJolliesList() {
		return jolliesList;
	}

	public void setJolliesList(Jolly[][] jolliesList) {
		this.jolliesList = jolliesList;
	}
}