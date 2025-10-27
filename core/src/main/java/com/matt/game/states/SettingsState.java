package com.matt.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.matt.game.Game;
import com.matt.game.Handler;
import com.matt.game.input.UI.MenuButtonListener;

public class SettingsState extends State implements Screen {

    private Texture coarseSoil, darkStone, sand, spaceSand;

    private Table table;
    private TextButton backBTN;
    private Skin cloudSkin;

    public SettingsState(final Handler handler){
        super(handler);

        cloudSkin = handler.getRM().getAM().get("ui/skins/cloud/cloud-form-ui.json", Skin.class);

        table = new Table();
        table.setFillParent(true);

        backBTN = new TextButton("Back", cloudSkin);
        backBTN.addListener(new MenuButtonListener(handler, new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                handler.getStateManager().setCurrentState(handler.getStateManager().getMenuState());
            }
        }));

        table.add(backBTN).width(200f).height(40f);
        table.bottom().left().pad(100f);
        table.setDebug(false);
        stage.addActor(table);

        coarseSoil = handler.getRM().getAM().get("tiles/coarse_soil.png", Texture.class);
        darkStone = handler.getRM().getAM().get("tiles/dark_stone.png", Texture.class);
        sand = handler.getRM().getAM().get("tiles/sand.png", Texture.class);
        spaceSand = handler.getRM().getAM().get("tiles/spacesand.png", Texture.class);
    }









    public void tick(){
        stage.act();
    }
    public void render(float delta) {
        Gdx.gl.glClearColor(.1f, .32f, .19f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        for(int y = 0; y < Gdx.graphics.getHeight() / 16; y++){
            for(int x = 0; x < Gdx.graphics.getWidth() / 16; x++){

            }
        }

        stage.draw();


    }






    public void show() {
        Gdx.input.setInputProcessor(inputMultiplexer);
    }
    public void hide() {
        System.out.println("Hide SettingsState!");
    }
    public void resize(int width, int height) {

    }
    public void pause() {

    }
    public void resume() {

    }
    public void dispose() {

    }
}
