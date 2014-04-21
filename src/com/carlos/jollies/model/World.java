package com.carlos.jollies.model;

import com.badlogic.gdx.math.Vector3;
import com.carlos.jollies.model.Jolly.EMOTION;

public class World {
	Jolly[][] jolliesList = new Jolly[4][4];
	
	public World(){
		createGameWorld();
	}
	
	public void createGameWorld(){
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4;j++){
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