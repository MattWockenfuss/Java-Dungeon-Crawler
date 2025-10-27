package com.matt.game.states;

import com.matt.game.Game;
import com.matt.game.Handler;

public class StateManager {

    //this class controls which game state is currently being rendered and such

    private Handler handler;


    private State currentState;
    private State lastState;

    private GameState gameState;
    private LoadingState loadingState;
    private MenuState menuState;
    private SettingsState settingsState;

    public StateManager(Handler handler){
        this.handler = handler;
        this.handler.setStateFarm(this);
        loadingState = new LoadingState(handler);
        lastState = loadingState;
    }

    public void createStates(){
        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        settingsState = new SettingsState(handler);
    }

    public void tick(){
        if(currentState != null)
            currentState.tick();
    }
    public void render(){//dont think we need this because we have setScreen

    }





    //getters and setters

    public void setCurrentState(State s){
//        lastState = currentState;
//        if(lastState != null){
//            System.out.println("Set the Last State to " + lastState.getClass().getSimpleName());
//        }

        currentState = s;
        handler.getGame().setScreen(s);
    }
    public State getCurrentState() {
        return currentState;
    }
    public State getLastState() {
        return lastState;
    }
    public GameState getGameState() {
        return gameState;
    }
    public LoadingState getLoadingState() {
        return loadingState;
    }
    public MenuState getMenuState() {
        return menuState;
    }
    public SettingsState getSettingsState() {
        return settingsState;
    }

    public void setLoadingState(LoadingState loadingState) {
        this.loadingState = loadingState;
    }
}
