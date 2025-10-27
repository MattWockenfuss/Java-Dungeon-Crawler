package com.matt.game;

import com.badlogic.gdx.Gdx;
import com.matt.game.gfx.ResourceManager;
import com.matt.game.input.InputManager;
import com.matt.game.states.*;

public class Game extends com.badlogic.gdx.Game {

	public Handler handler;

	private ResourceManager resourceManager;
	private InputManager inputManager;
	private StateManager stateManager;


	public void create() {
		handler = new Handler();
		handler.setGame(this);

		resourceManager = new ResourceManager(handler);
		inputManager = new InputManager(handler);
		//Gdx.input.setInputProcessor(inputManager);

		stateManager = new StateManager(handler);



		stateManager.setLoadingState(new LoadingState(handler));
		stateManager.setCurrentState(stateManager.getLoadingState());
	}

	public void render(){
		resourceManager.tick();
		inputManager.tick();
		stateManager.getLoadingState().setProgress(resourceManager.getProgress());
		if(resourceManager.getProgress() == 1.0){//returns true if loaded, false otherwise
			shouldTick();
			super.render();
			stateManager.render();

		}

		if(!handler.isGameRunning()){
			dispose();
			Gdx.app.exit();
		}

	}

	int seconds = 0;
	int targetTPS = 60;
	double timePerTick = 1000000000 / targetTPS;
	double delta = 0;
	long now;
	long lastTime = System.nanoTime();
	long timer = 0; //every second clock
	int ticks = 0;

	public void shouldTick(){
		now = System.nanoTime();
		delta += (now - lastTime) / timePerTick;
		timer += now - lastTime;
		lastTime = now;
		if(delta >= 1) {
			ticks++;
			stateManager.tick();
			delta--;
		}

		if(timer >= 1000000000) {
			//System.out.println("Seconds Elapsed: " + seconds++);
			stateManager.getCurrentState().onSecond();
			timer = 0;
		}

	}













	public void dispose(){
		resourceManager.dispose();
	}

}
