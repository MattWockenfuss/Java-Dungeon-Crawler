package com.matt.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.matt.game.Game;
import com.matt.game.Handler;

public class LoadingState extends State implements Screen {


    private float progress = 0;
    private int timer = 0;

    private ShapeRenderer shapeRenderer;
    private OrthographicCamera gameCamera;

    public LoadingState(final Handler handler){
        super(handler);
        shapeRenderer = new ShapeRenderer();

        gameCamera = new OrthographicCamera();
        gameCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }


    public void tick(){
        stage.act();
        timer++;
        if(timer >= 10 && progress == 1.0){
            handler.getStateManager().createStates();
            //set the state to the game state
            System.out.println("Switching to Game State!");
            handler.getStateManager().setCurrentState(handler.getStateManager().getMenuState());
        }
    }
    public void render(float delta) {
        ScreenUtils.clear(53 / 256f,81 / 256f,92 / 256f,1);
        shapeRenderer.setProjectionMatrix(gameCamera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        Gdx.gl.glLineWidth(20);

        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(300, 300, 100 + (500 * progress), 400);
        shapeRenderer.end();

        if(progress == 1.0){
            //System.out.println("Done Loading!");
        }
    }






    public void show() {
        Gdx.input.setInputProcessor(inputMultiplexer);
    }
    public void hide() {
    }
    public void resize(int width, int height) {
    }
    public void pause() {
    }
    public void resume() {
    }

    public void dispose() {
        shapeRenderer.dispose();
    }


    public void setProgress(float progress){
        this.progress = progress;
    }



}
