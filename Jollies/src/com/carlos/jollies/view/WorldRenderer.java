package com.carlos.jollies.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.carlos.jollies.model.Jolly;
import com.carlos.jollies.model.World;

public class WorldRenderer {

	private World world;
	private OrthographicCamera cam;
	
	ShapeRenderer debugRenderer = new ShapeRenderer();
	
	public WorldRenderer(World world){
		this.world = world;
		this.cam = new OrthographicCamera(10, 7);
		this.cam.position.set(5, 3.5f, 0);
		this.cam.update();
	}
	
	public void render(){
		debugRenderer.setProjectionMatrix(cam.combined);
		debugRenderer.begin(ShapeType.Line);
		Jolly[][] list = world.getJolliesList();
		for(int i =0; i < list.length;i++){
			for(int j = 0; j < list[i].length;j++){
				Rectangle rect = list[i][j].bounds;
				float x1 = list[i][j].getPosition().position.x + rect.x;
				float y1 = list[i][j].getPosition().position.y + rect.y;
				debugRenderer.setColor(new Color(0,0,1,1));
				debugRenderer.rect(x1, y1, rect.width, rect.height);
			}
		}
		debugRenderer.end();
	}
}