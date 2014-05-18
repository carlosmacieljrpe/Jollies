package com.carlos.jollies.game;

import javax.microedition.khronos.opengles.GL10;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.carlos.jollies.model.GridPosition;
import com.carlos.jollies.model.Jolly;
import com.carlos.jollies.model.Jolly.EMOTION;
import com.carlos.jollies.model.World;
import com.carlos.jollies.view.WorldRenderer;

public class GameScreen implements Screen, InputProcessor {
	
	private World world;
	private WorldRenderer worldRenderer;
	
	private Jolly touchDownJolly = null;;
	private Jolly touchUpJolly = null;
	
	private int steps = 0;
	
	public GameScreen(){
	}

	@Override
	public void dispose() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void render(float arg0) {
		Gdx.gl.glClearColor(1f, 1f, 1f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		worldRenderer.render();
	}

	@Override
	public void resize(int width, int height) {
		worldRenderer.setSize(width, height);
	}

	@Override
	public void resume() {
		worldRenderer.render();
	}

	@Override
	public void show() {
		world = new World(4,4);
		worldRenderer = new WorldRenderer(world);
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public boolean keyDown(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		Vector3 touchPos = new Vector3();
	    touchPos.set(Gdx.input.getX(), Gdx.input.getY(),0);
	    worldRenderer.cam.unproject(touchPos);
		
	    touchDownJolly = detectTouchObject(touchPos.x, touchPos.y);
	    if(touchDownJolly != null){
	    	touchDownJolly.playSound();
	    	worldRenderer.selectedJolly = touchDownJolly;
	    }
		return true;
	}

	private Jolly detectTouchObject(float x, float y) {
		Jolly[][] list = world.getJolliesList();
		for(int i = 0; i < list.length; i++){
			for(int j = 0; j < list[i].length;j++){
				Jolly jolly = list[i][j];
				if(pointInRectangle(jolly.bounds, x,y)){
					return jolly;
				}
			}
		}
		return null;
	}
	
	public static boolean pointInRectangle (Rectangle r, float x, float y) {
	    boolean insideRectangle = false;
		if((x > r.x && x < r.x + r.width)&&(y > r.y && y < r.y + r.height)){
			insideRectangle = true;
		}
		return insideRectangle;
	}

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
		Vector3 touchPos = new Vector3();
	    touchPos.set(Gdx.input.getX(), Gdx.input.getY(),0);
	    worldRenderer.cam.unproject(touchPos);
		
		touchUpJolly = detectTouchObject(touchPos.x, touchPos.y);
		trySwapPositions(touchDownJolly, touchUpJolly);
		return true;
	}
	
	public void trySwapPositions(Jolly jollyDown, Jolly jollyUp){
		if(jollyDown != null && jollyUp != null){
			if(jollyDown.emotion == EMOTION.JOLLY && areGridNeighbors(jollyDown.gridPosition, jollyUp.gridPosition)){
				jollyUp.SwapEmotion(EMOTION.JOLLY);
				jollyDown.SwapEmotion(EMOTION.SMILE);
				this.worldRenderer.selectedJolly = jollyUp;
				steps++;
		    	worldRenderer.steps = steps;
			}
		}
	}
	
	public boolean areGridNeighbors(GridPosition grid1, GridPosition grid2){
		boolean areNeighboors = false;
		int x1 = grid1.x;
		int y1 = grid1.y;
		
		int x2 = grid2.x;
		int y2 = grid2.y;
		
		if(x2 == x1 && (y2-1 == y1)){		//UP neighbor
			areNeighboors = true;
		}else if(x2-1 == x1 && y2 == y1){	//RIGHT neighbor
			areNeighboors = true;
		}else if(x2+1 == x1 && y2==y1){		//LEFT neighbor
			areNeighboors = true;
		}else if(x2 == x1 && y2+1 == y1){	//DOWN neighbor
			areNeighboors = true;
		}
		
		return areNeighboors;
	}
}