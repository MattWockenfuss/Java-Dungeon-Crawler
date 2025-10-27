package com.matt.game;

import com.matt.game.gfx.AudioManager;
import com.matt.game.gfx.LightManager;
import com.matt.game.gfx.ResourceManager;
import com.matt.game.input.InputManager;
import com.matt.game.states.StateManager;
import com.matt.game.world.World;

public class Handler {
    //the handler object handles a variety of commonly need functions or objects that we can get in our game.

    private World world;
    private boolean isGameRunning = true;

    private Game game;
    private ResourceManager resourceManager;
    private InputManager inputManager;
    private AudioManager audioManager;
    private LightManager lightManager;

    private StateManager stateManager;

    public Handler() {

    }
    public World getCurrentWorld(){
        return world;
    }

    public ResourceManager getRM(){
        return resourceManager;
    }
    public InputManager getIM(){
        return inputManager;
    }
    public AudioManager getAudioManager() {
        return audioManager;
    }
    public LightManager getLightManager() {
        return lightManager;
    }
    public StateManager getStateManager(){
        return stateManager;
    }

    ////////////////////////////////SETTERS////////////////////////////////////
    public void setCurrentWorld(World world){
        this.world = world;
    }

    public void setRM(ResourceManager resourceManager){
        this.resourceManager = resourceManager;
    }
    public void setIM(InputManager inputManager){
        this.inputManager = inputManager;
    }
    public void setAudioManager(AudioManager audioManager) {
        this.audioManager = audioManager;
    }
    public void setLightManager(LightManager lightManager) {
        this.lightManager = lightManager;
    }
    public void setStateFarm(StateManager stateManager){
        this.stateManager = stateManager;
    }

    public void setGameStatus(boolean running){
        isGameRunning = running;
    }
    public boolean isGameRunning(){
        return isGameRunning;
    }

    public void setGame(Game game) {
        this.game = game;
    }
    public Game getGame(){
        return game;
    }
}
