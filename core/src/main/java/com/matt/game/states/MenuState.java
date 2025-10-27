package com.matt.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.matt.game.Global;
import com.matt.game.Handler;
import com.matt.game.input.UI.MenuButtonListener;

public class MenuState extends State implements Screen {

// Music Element
    private Music music;

// UI Elements
    private Table table;
    private TextButton playBTN, settingsBTN, quitBTN, testBTN1, testBTN2;
    private Label title;
    private TextField nameText, addressText, texttest1, texttest2, texttest3, texttest4;
    private Tree tree;
    private Slider slider, slider1, slider2, slider3, slider4;
    private ProgressBar prgBar;
    private Texture background;

    private BitmapFont consolas;

    OrthographicCamera camera;

    private Skin cleanSkin, cloudSkin, defaultSkin, plainJamesSkin, visSkin;


    public MenuState(final Handler handler){
        super(handler);

        music = handler.getRM().getAM().get("audio/menu_music.mp3", Music.class);
        music.setLooping(true);
        music.setVolume(0.1f);
        music.setOnCompletionListener(new Music.OnCompletionListener() {
            @Override
            public void onCompletion(Music music) {
                System.out.println("Finished Song!");
            }
        });




        cleanSkin = handler.getRM().getAM().get("ui/skins/clean/clean-crispy-ui.json", Skin.class);
        cloudSkin = handler.getRM().getAM().get("ui/skins/cloud/cloud-form-ui.json", Skin.class);
        defaultSkin = handler.getRM().getAM().get("ui/skins/default/uiskin.json", Skin.class);
        plainJamesSkin = handler.getRM().getAM().get("ui/skins/plainjames/plain-james-ui.json", Skin.class);
        visSkin = handler.getRM().getAM().get("ui/skins/vis/uiskin.json", Skin.class);

        table = new Table();
        table.setFillParent(true);

        title = new Label(Global.NAME + "  v" + Global.VERSION, cloudSkin);

        playBTN = new TextButton("Play", cloudSkin);
        settingsBTN = new TextButton("Settings", cloudSkin);
        quitBTN = new TextButton("Quit", cloudSkin);

        background = handler.getRM().getAM().get("background.jpg", Texture.class);


        playBTN.addListener(new MenuButtonListener(handler, new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                handler.getStateManager().setCurrentState(handler.getStateManager().getGameState());
            }
        }));
        settingsBTN.addListener(new MenuButtonListener(handler, new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                handler.getStateManager().setCurrentState(handler.getStateManager().getSettingsState());
            }
        }));
        quitBTN.addListener(new MenuButtonListener(handler, new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                handler.setGameStatus(false);//quits the game
            }
        }));




        title.setFontScale(3);
        table.add(title).row();
        table.columnDefaults(0).width(200f).height(40f).bottom().left().space(10f);
        table.add(playBTN).row();
        table.add(settingsBTN).row();
        table.add(quitBTN).row();



        table.setDebug(true);

        stage.addActor(table);
    }



    public void tick(){
        stage.act();
    }
    public void render(float delta) {
        Gdx.gl.glClearColor(.1f, .32f, .19f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Gdx.gl.glLineWidth(1);


        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.getBatch().end();


        stage.draw();
    }






    public void show() {
        Gdx.input.setInputProcessor(inputMultiplexer);
        //music.play();
    }
    public void hide() {

    }
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
    public void pause() {

    }
    public void resume() {

    }

    public void dispose() {
        stage.dispose();
        cloudSkin.dispose();
    }

}
