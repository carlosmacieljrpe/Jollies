package com.carlos.jollies.model;

import com.badlogic.gdx.math.Vector2;
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
				if(i % 2 == 0 && j % 2 == 0){
					jollyEmotion = EMOTION.SLASH_FACE;
				}
				else if(i % 3 == 0){
					jollyEmotion = EMOTION.POKER_FACE;
				}else if(j%3 == 0){
					jollyEmotion = EMOTION.SMILE;
				}else if(j% 2== 0){
					jollyEmotion = EMOTION.JOLLY;
				}
				Jolly jolly = new Jolly(new Vector2(1f + i, 1f + j), jollyEmotion);
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