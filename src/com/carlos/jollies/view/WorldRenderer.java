package com.carlos.jollies.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
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

	public Jolly selectedJolly = null;

	public int steps = -1;

	BitmapFont font;

	private int counter;

	private float timeCounter;
	
	public static final String FONT_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>";

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
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("data/roboto.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 20;
		parameter.flip = false;
		font = generator.generateFont(parameter);
		
		font.setColor(Color.BLACK);
		generator.dispose();
	}

	public void render(){
		spriteBatch.begin();
		Jolly[][] list = world.getJolliesList();
		drawJollies(list, spriteBatch);
		drawChronometer();
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

				if(this.selectedJolly != null && jolly.equals(this.selectedJolly)){
					spriteBatch.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE);
					spriteBatch.setColor(0.6f, 0.6f, 1f, 1f);
					spriteBatch.draw(texture, x1 * ppuX, y1 * ppuY, rect.width * ppuX, rect.height * ppuY);
					spriteBatch.setColor(1f, 1f, 1f, 1f);
					spriteBatch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
				}

			}
		}
		if(steps != -1){
			drawSteps();
		}
	}

	public void setSize (int w, int h) {
		this.width = w;
		this.height = h;
		ppuX = (float)width / CAMERA_WIDTH;
		ppuY = (float)height / CAMERA_HEIGHT;
	}

	private void drawSteps(){
		font.setColor(Color.BLACK);
		font.draw(spriteBatch, this.steps + "", World.OBJECTS_POSITIONS.STEPS_LABEL.x, World.OBJECTS_POSITIONS.STEPS_LABEL.y);
	}

	private void drawChronometer(){
		timeCounter += Gdx.graphics.getDeltaTime();
		if(timeCounter >= 1.0f){
			timeCounter = 0;
			counter++;
		}
		font.setColor(Color.RED);
		font.draw(spriteBatch, this.counter + "", World.OBJECTS_POSITIONS.CHONOMETER.x, World.OBJECTS_POSITIONS.CHONOMETER.y);
	}
}