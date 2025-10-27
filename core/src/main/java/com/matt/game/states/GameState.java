package com.matt.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.matt.game.Game;
import com.matt.game.Handler;
import com.matt.game.gfx.LightManager;
import com.matt.game.input.Controls;
import com.matt.game.input.UI.LinkedButton;
import com.matt.game.input.UI.MenuButtonListener;
import com.matt.game.input.UI.ToggableButton;
import com.matt.game.world.Environment.Entities.Player;
import com.matt.game.world.World;

import java.awt.*;
import java.awt.peer.DesktopPeer;
import java.security.Key;

public class GameState extends State implements Screen{

    private Player player;
    private World world;//multiplayer players belong to the world entities

    private int ticksLastSecond = 0;
    private int ticks = 0;

    private OrthographicCamera playerCamera, hudCamera;
    private SpriteBatch batch;
    private ShapeRenderer sr;
    private ShaderProgram bwShader, randomShader;

    private LightManager lm;

    BitmapFont font;


    private int collisionCode = 8;




    private ToggableButton escape;
    private LinkedButton shadersBTN;


    public GameState(final Handler handler){
        super(handler);

        world = new World(handler,"BiggieBobRoss", 30, 30, "world/");
        lm = new LightManager(handler);
        player = new Player(handler, world, "Matt", world.getSpawnX(), world.getSpawnY());


        batch = new SpriteBatch();
        playerCamera = player.getPlayerCamera();
        hudCamera = player.getHUDCamera();



        font = handler.getRM().getAM().get("ui/fonts/consolas.fnt", BitmapFont.class);


        ShaderProgram.pedantic = false;
        //By Default if a varaible isnt used in shader, then it is removed by libgdx, by setting pedantic to false, this removes
        //LibGdx's check https://www.youtube.com/watch?v=gCR_ikOdaUc at 4:50


        String vertexShader = Gdx.files.internal("shaders/bw_vertex_shader.glsl").readString();
        String fragmentShader = Gdx.files.internal("shaders/bw_fragment_shader.glsl").readString();
        bwShader = new ShaderProgram(vertexShader, fragmentShader);

        vertexShader = Gdx.files.internal("shaders/random_vertex_shader.glsl").readString();
        fragmentShader = Gdx.files.internal("shaders/random_fragment_shader.glsl").readString();
        randomShader = new ShaderProgram(vertexShader, fragmentShader);

        if(!bwShader.isCompiled()){
            System.out.println("[ERROR] Loading Black and White Shader\n" + bwShader.getLog());
        }
        if(!randomShader.isCompiled()){
            System.out.println("[ERROR] Loading Random Shader\n" + randomShader.getLog());
        }
        sr = new ShapeRenderer();

        //Making the Escape Menu
        Skin cloudSkin = handler.getRM().getAM().get("ui/skins/cloud/cloud-form-ui.json", Skin.class);

        Table table = new Table();
        table.setFillParent(true);

        TextButton menuBTN = new TextButton("Menu", cloudSkin);
        TextButton settingsBTN = new TextButton("Settings", cloudSkin);

        menuBTN.addListener(new MenuButtonListener(handler, new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                handler.getStateManager().setCurrentState(handler.getStateManager().getMenuState());
            }
        }));

        settingsBTN.addListener(new MenuButtonListener(handler, new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                handler.getStateManager().setCurrentState(handler.getStateManager().getSettingsState());
            }
        }));

        table.columnDefaults(0).width(200f).height(40f).bottom().left().space(10f);
        table.add(menuBTN).row();
        table.add(settingsBTN).row();
        table.add(settingsBTN).row();

        stage.addActor(table);

        escape = new ToggableButton(Input.Keys.ESCAPE, stage);
        shadersBTN = new LinkedButton(new int[]{Input.Keys.I, Input.Keys.O, Input.Keys.P});
        shadersBTN.setValue(0, true);
    }






    public void tick(){
        ticks++;

        escape.tick();



        if(!escape.is()){
            shadersBTN.tick();
            player.tick();
            lm.tick();
        }


        if(escape.is()){
            stage.act();
        }
    }
    public void render(float delta) {
        ScreenUtils.clear(256 / 256f,256 / 256f,256 / 256f,1);
        playerCamera.update();
        batch.setProjectionMatrix(playerCamera.combined);
        sr.setProjectionMatrix(playerCamera.combined);

        if(Controls.LightingShader.isPressed())
            batch.setShader(lm.getShader());
        else
            batch.setShader(null);

    //    if(shadersBTN.is(0))
    //        batch.setShader(null);
    //    if(shadersBTN.is(1))
    //        batch.setShader(bwShader);
    //    if(shadersBTN.is(2)){
    //        randomShader.setUniformf("u_mouseCoords", Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
    //        randomShader.setUniformf("u_height", Gdx.graphics.getHeight());
    //        batch.setShader(randomShader);
    //    }

//        int count = 0;
//        if(Gdx.input.isKeyPressed(Input.Keys.H)){
//            for(int i = 0; i < 100000000; i++){//it does NOT cost a lot to set the shader, that is pretty easy
//                //do this 100 times
//                batch.setShader(null);
//                batch.setShader(bwShader);
//            }
//        }

        if(Controls.collisionDebug.isPressed()){
            Controls.collisionDebug.setIsPressed(false);
            collisionCode++;
            if(collisionCode > 8)
                collisionCode = 0;
            System.out.println("Code: " + collisionCode);
        }


        batch.begin();
        world.render(batch);
        player.render(batch);


        batch.end();
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.setColor(Color.RED);
        world.renderDebug(sr, collisionCode);
        sr.setColor(Color.BROWN);
        player.renderDebug(sr);
        lm.renderDebug(sr);

        sr.end();



        /////// RENDERING THE HUD
        batch.setProjectionMatrix(hudCamera.combined);
        batch.begin();
        font.setColor(Color.BLACK);
        font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond() + "  TPS: " + ticksLastSecond, 5, Gdx.graphics.getHeight() - 20);
        font.draw(batch, "(" + player.getX() + ", " + player.getY() + ")", 5, Gdx.graphics.getHeight() - 40);
        if(shadersBTN.is(0))
            font.draw(batch, "[Default]", 5, Gdx.graphics.getHeight() - 60);
        if(shadersBTN.is(1))
            font.draw(batch, "[GrayScale]", 5, Gdx.graphics.getHeight() - 60);
        if(shadersBTN.is(2))
            font.draw(batch, "[Dark]", 5, Gdx.graphics.getHeight() - 60);
        switch (collisionCode){
            case 0:
                font.draw(batch, "[Global Hitbox]", 5, Gdx.graphics.getHeight() - 80);
                break;
            case 1:
                font.draw(batch, "[Head Hitbox]", 5, Gdx.graphics.getHeight() - 80);
                break;
            case 2:
                font.draw(batch, "[Chest Hitbox]", 5, Gdx.graphics.getHeight() - 80);
                break;
            case 3:
                font.draw(batch, "[Left Arm Hitbox]", 5, Gdx.graphics.getHeight() - 80);
                break;
            case 4:
                font.draw(batch, "[Right Arm Hitbox]", 5, Gdx.graphics.getHeight() - 80);
                break;
            case 5:
                font.draw(batch, "[Legs Hitbox]", 5, Gdx.graphics.getHeight() - 80);
                break;
            case 6:
                font.draw(batch, "[Feet Hitbox]", 5, Gdx.graphics.getHeight() - 80);
                break;
            case 7:
                font.draw(batch, "[All Layers Hitbox]", 5, Gdx.graphics.getHeight() - 80);
                break;
        }
        font.draw(batch, "[" + lm.getLightSources().get(0).getColor().getClass().getSimpleName() + "] " + lm.getLightSources().get(0).getAngleStart() + "  ->  " + lm.getLightSources().get(0).getAngleEnd(), 5, Gdx.graphics.getHeight() - 240);
        font.draw(batch, getScreenOffsetToPlayerCam().x + ", " + getScreenOffsetToPlayerCam().y, 5, Gdx.graphics.getHeight() - 260);
        player.renderHUD(batch);
        batch.end();

        if(escape.is()){
            stage.draw();
        }

    }

    @Override
    public void onSecond() {
        ticksLastSecond = ticks;
        ticks = 0;
    }

    public void show() {
        Gdx.input.setInputProcessor(inputMultiplexer);
        escape.setValue(false);
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
        batch.dispose();
        font.dispose();
    }
    public Point getScreenOffsetToPlayerCam(){
        //okay so we get the coords of the mouse, BTW this only makes sense with a pair of coords and a camera
        Point p = player.getMouseCoords();
        Vector3 worldPosition = new Vector3(p.x, p.y, 0); // Your world coordinates
        Vector3 screenPosition = playerCamera.project(worldPosition);
        return new Point((int) (p.x - screenPosition.x), (int) (p.y - screenPosition.y));
    }
    public Player getPlayer(){
        return this.player;
    }
}
