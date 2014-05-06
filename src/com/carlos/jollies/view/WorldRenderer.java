package com.carlos.jollies.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.carlos.jollies.model.Jolly;
import com.carlos.jollies.model.World;

public class WorldRenderer {

	private World world;
	public OrthographicCamera cam;

	ShapeRenderer debugRenderer = new ShapeRenderer();
	private SpriteBatch spriteBatch;
	private AssetManager manager = new AssetManager();

	private int width;
	private int height;
	private float ppuX; // pixels per unit on the X axis
	private float ppuY; // pixels per unit on the Y axis

	private static final float CAMERA_WIDTH = 10f;
	private static final float CAMERA_HEIGHT = 7f;


	public WorldRenderer(World world){
		this.world = world;
		this.cam = new OrthographicCamera(10, 7);
		this.cam.position.set(5.5f, 3.5f, 0);
		this.cam.update();
		this.spriteBatch = new SpriteBatch();
		manager.load("images/grumpy2.png", Texture.class);
		manager.load("images/smiley2.png", Texture.class);
		manager.load("images/jolly.png", Texture.class);
		manager.finishLoading();
	}

	public void render(){
		spriteBatch.begin();
		Jolly[][] list = world.getJolliesList();
		drawJollies(list, spriteBatch);
		spriteBatch.end();
	}

	private void drawJollies(Jolly[][] list, SpriteBatch renderer){
		for(int i =0; i < list.length;i++){
			for(int j = 0; j < list[i].length;j++){
				Jolly jolly = list[i][j];
				Rectangle rect = jolly.bounds;
				float x1 = jolly.bounds.x;
				float y1 = jolly.bounds.y;
				Texture texture = (Texture)manager.get(jolly.emotion.getTextureName());
				spriteBatch.draw(texture, x1 * ppuX, y1 * ppuY, rect.width * ppuX, rect.height * ppuY);
				/*if(jolly.equals(this.)){

				spriteBatch.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE);
				spriteBatch.setColor(0.3f, 0.3f, 0.3f, 1f);
				spriteBatch.draw(texture, x1 * ppuX, y1 * ppuY, rect.width * ppuX, rect.height * ppuY);
				spriteBatch.setColor(1f, 1f, 1f, 1f);
				spriteBatch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
				}*/

			}
		}
	}
	
	public void setSize (int w, int h) {
		this.width = w;
		this.height = h;
		ppuX = (float)width / CAMERA_WIDTH;
		ppuY = (float)height / CAMERA_HEIGHT;
	}

}