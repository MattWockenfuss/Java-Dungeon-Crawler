package com.matt.game.world.Environment.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.matt.game.Game;
import com.matt.game.Handler;
import com.matt.game.gfx.LightSource;
import com.matt.game.input.Controls;
import com.matt.game.world.Structure.Walls.ReturnableWall;
import com.matt.game.world.Structure.Walls.Wall;
import com.badlogic.gdx.math.Rectangle;
import com.matt.game.world.World;
import jdk.javadoc.internal.doclets.toolkit.taglets.ReturnTaglet;

import java.awt.*;
import java.util.ArrayList;

public class Player extends Entity{

    private String name;
    private OrthographicCamera playerCamera, hudCamera;

    private float TEMP_imageScale = 6f;

    private TextureRegion[] animationFramesRIGHT;
    private TextureRegion[] animationFramesLEFT;
    private int numOfFrames = 8;
    private int ticksPerFrame = 5;
    private int animationCount = 0;//the current animation counter
    private int currentFrame = 0;
    private int animationLength = numOfFrames * ticksPerFrame;//remember 60 ticks per second


    //alright so we have a flashlight texture
    private Sprite flashlightTexture;
    private float angle = 0;
    private int mdwX = 0;
    private int mdwY = 0;
    private int hypotenuse = 0;

    private float range;
    private float narrowness = 18.0f;


    private LightSource flashlightLight;
    private LightSource characterLight;

    private Handler handler;

    private BitmapFont font;



    private int boxWidth = 130;
    public Player(Handler handler, World world, String name, int x, int y){
        super(world, x, y);
        this.name = name;
        this.handler = handler;

        flashlightTexture = new Sprite(handler.getRM().getAM().get("flashlight.png", Texture.class));
        flashlightTexture.setScale(4,4);
        flashlightTexture.setPosition(x - flashlightTexture.getWidth() / 2, y - flashlightTexture.getHeight() / 2);
        flashlightTexture.setOriginCenter();
        flashlightLight = new LightSource(x, y, 1.0f, 0f, 90f, Color.PURPLE,
                780, new Rectangle(0,0,10,10));
        characterLight = new LightSource(x, y, 1.0f, 0f, 360f, Color.YELLOW,
                120, new Rectangle(0,0,1,1));
        handler.getLightManager().addLightSource(flashlightLight);
        handler.getLightManager().addLightSource(characterLight);
        flashlightLight.setTicked(false);
        characterLight.setTicked(false);


        Texture sheet = handler.getRM().getAM().get("character-Sheet.png", Texture.class);

        animationFramesRIGHT = new TextureRegion[numOfFrames];
        for(int i = 0; i < numOfFrames; i++){
            animationFramesRIGHT[i] = new TextureRegion(sheet, 16 * i, 0, 16, 32);
        }


        animationFramesLEFT = new TextureRegion[numOfFrames];
        for(int i = 0; i < numOfFrames; i++){
            TextureRegion tr = new TextureRegion(sheet, 16 * i, 0, 16, 32);
            tr.flip(true, false);
            animationFramesLEFT[i] = tr;
        }
        //sheet.dispose();//no longer needed
        texWidth = (int)(animationFramesRIGHT[0].getRegionWidth() * TEMP_imageScale);
        texHeight = (int)(animationFramesRIGHT[0].getRegionHeight() * TEMP_imageScale);

        playerCamera = new OrthographicCamera();
        playerCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        hudCamera = new OrthographicCamera();
        hudCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

//        addLeftArmHitBox(new Rectangle(4, 31, 11, 26));
//        addRightArmBox(new Rectangle(44, 31, 11, 26));
        addLegsBox(new Rectangle(22, 0, 52, 40));
        addFeetHitBox(new Rectangle(22, 0, 52, 10));
        font = handler.getRM().getAM().get("ui/fonts/consolas.fnt", BitmapFont.class);
    }

    public void tick(){
        float speed = 5f;

        if(Controls.flashLight.isPressed()){
            flashlightLight.setRendered(false);
        }else{
            flashlightLight.setRendered(true);
        }

        if(handler.getIM().shift) speed = 100f;
        if(handler.getIM().y){
            x = 6000;
            y = 19500;
        }

        if(handler.getIM().k){
            //System.out.println("REMAPPED WALKING CONTROLS TO ARROWS instead of W-A-S-D");
            Controls.WALK_LEFT.setKeyCode(Input.Keys.LEFT);
            Controls.WALK_UP.setKeyCode(Input.Keys.UP);
            Controls.WALK_RIGHT.setKeyCode(Input.Keys.RIGHT);
            Controls.WALK_DOWN.setKeyCode(Input.Keys.DOWN);

        }

        if(Controls.WALK_UP.isPressed()){
            yMove = speed;
        }
        if(Controls.WALK_DOWN.isPressed()){
            yMove = -speed;
        }

        if(Controls.WALK_LEFT.isPressed()){
            xMove = -speed;
            animationCount++;
        }

        if(Controls.WALK_RIGHT.isPressed()){
            xMove = speed;
            animationCount++;
        }
        if(animationCount > ticksPerFrame){
            animationCount = 0;
            currentFrame++;
            if(currentFrame > numOfFrames - 1){
                currentFrame = 0;
            }
        }

        if(!Controls.WALK_LEFT.isPressed() && !Controls.WALK_RIGHT.isPressed())
            xMove = 0;
        if(!Controls.WALK_UP.isPressed() && !Controls.WALK_DOWN.isPressed())
            yMove = 0;
        move();
        playerCamera.position.set(x, y, 0);

        range = flashlightLight.getRange();
        if(handler.getIM().z){
            range += 1f;
        }else if(handler.getIM().x){
            range -= 1f;
        }

        flashlightLight.setRange(range);



        if(handler.getIM().mouseScroll == -1)
            narrowness += 0.8f;
        else if(handler.getIM().mouseScroll == 1)
            narrowness -= 0.8f;
        handler.getIM().mouseScroll = 0;





        Point p = getMouseCoords();

        //okay, store horizontal and vertical mouse Distances
        mdwX = (Gdx.input.getX() - Gdx.graphics.getWidth() / 2);
        mdwY = (Gdx.input.getY() - Gdx.graphics.getHeight() / 2);
        hypotenuse = (int) Math.sqrt(Math.pow(mdwX, 2) + Math.pow(mdwY, 2));

        float radAngle = (float)(Math.atan2(-mdwY, mdwX));
        angle = (float) Math.toDegrees(radAngle) - 45;

        int radiusOfFlashCircle = 14;

        int flashXOffset = (int) (radiusOfFlashCircle * Math.cos(radAngle));
        int flashYOffset = (int) (radiusOfFlashCircle * Math.sin(radAngle));

        //now lets calculate the angles to set our lightsource

        flashlightLight.setAngleStart((angle + 45) - narrowness);
        flashlightLight.setAngleEnd((angle + 45) + narrowness);
        flashlightLight.normalizeAngles();


        flashlightLight.setX(x + flashXOffset);
        flashlightLight.setY(y + flashYOffset);
        characterLight.setX(x);
        characterLight.setY(y);


        flashlightTexture.setPosition(x - flashlightTexture.getWidth() / 2, y - flashlightTexture.getHeight() / 2);
        flashlightTexture.setRotation(angle);
        if((angle + 45) < 0) angle += 360;
        if((angle + 45) > 360) angle -= 360;

        //okay now we need to figure out what angle to draw the flashlight at







    }
    public void render(SpriteBatch batch){
        if(Controls.WALK_RIGHT.isPressed())
            batch.draw(animationFramesRIGHT[currentFrame], x - (texWidth / 2), y - (texHeight / 2), texWidth, texHeight);
        if(Controls.WALK_LEFT.isPressed()){
//            for(int yT = -5; yT < 6; yT++){
//                for(int xT = -5; xT < 6; xT++){
//                    batch.draw(animationFramesLEFT[currentFrame], x - (texWidth / 2) + (xT * 100), y - (texHeight / 2) + (yT * 100), texWidth, texHeight);
//                }
//            }
            batch.draw(animationFramesLEFT[currentFrame], x - (texWidth / 2), y - (texHeight / 2), texWidth, texHeight);
        }else{
            batch.draw(animationFramesLEFT[currentFrame], x - (texWidth / 2), y - (texHeight / 2), texWidth, texHeight);
        }
        Point p = getMouseCoords();
        //batch.draw(flashlightTexture, , , flashlightWidth, flashlightHeight);

        flashlightTexture.draw(batch);



    }
    public void renderHUD(SpriteBatch batch){
        //this function is drawn irrevalent of the player position

        font.draw(batch, "mdw X: " + mdwX + " , y: " +  mdwY, 5, Gdx.graphics.getHeight() - 100);
        font.draw(batch, "Hyponetuse: " +  hypotenuse, 5, Gdx.graphics.getHeight() - 120);

        font.draw(batch, "[Flashlight]: isOn: "  + flashlightLight.isRendered(), 5, Gdx.graphics.getHeight() - 140);
        font.draw(batch, "Angle: " + Math.round((angle + 45) * 100.0) / 100.0, 5, Gdx.graphics.getHeight() - 160);
        font.draw(batch, "Narrowness: " + narrowness, 5, Gdx.graphics.getHeight() - 180);
        font.draw(batch, "Range: " + flashlightLight.getRange(), 5, Gdx.graphics.getHeight() - 200);

        Point p = getMouseCoords();
        font.draw(batch, "(" + p.x + "," + p.y + "): ", Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());

    }
    public void renderDebug(ShapeRenderer sr){
        //sr.rect(x - (texWidth / 2), y - (texHeight / 2), texWidth, texHeight);
        //entity x and y are in relation to center of entity?


        renderHitBox(sr, x - (texWidth / 2), y - (texHeight / 2), 7);

        Point p = getMouseCoords();//this function returns mouse normalized to the world



        if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){

            //if i scroll increase/decrease box size
            if(Gdx.input.isKeyPressed(Input.Keys.X)) boxWidth++;
            else if(Gdx.input.isKeyPressed(Input.Keys.Z)) if(boxWidth > 0) boxWidth--;

            ReturnableWall[][] walls = world.getWallsIn(new Rectangle(p.x - boxWidth / 2, p.y - boxWidth / 2, boxWidth, boxWidth));

            for(int yi = 0; yi < walls[0].length; yi++){
                for(int xi = 0; xi < walls.length; xi++){
                    if(walls[xi][yi] != null && walls[xi][yi].getWall() != null){
                        walls[xi][yi].getWall().renderOutline(sr, walls[xi][yi].getX() * Wall.WallWidth, walls[xi][yi].getY() * Wall.WallHeight);
                        walls[xi][yi].getWall().renderHitBox(sr, walls[xi][yi].getX() * Wall.WallWidth, walls[xi][yi].getY() * Wall.WallHeight, 7);
                    }

                }
            }
            sr.setColor(Color.FIREBRICK);
            sr.rect(p.x - boxWidth / 2, p.y - boxWidth / 2, boxWidth, boxWidth);
            //instead lets just render the points
            Point[][] points = world.getPoints(new Rectangle(p.x - boxWidth / 2, p.y - boxWidth / 2, boxWidth, boxWidth));
            int pointRadius = 10;
            for(int yi = 0; yi < points[0].length; yi++){
                for(int xi = 0; xi < points.length; xi++){
                    if(walls[xi][yi] != null){
                        if(walls[xi][yi].getWall() != null)
                            sr.setColor(Color.BLUE);
                        else
                            sr.setColor(Color.ORANGE);
                    }else{
                        sr.setColor(Color.RED);
                    }
                    sr.rect((float) (points[xi][yi].getX() - pointRadius), (float) (points[xi][yi].getY() - pointRadius), pointRadius * 2 + 1, pointRadius * 2 + 1);
                }
            }
        }else{
            ReturnableWall w = world.getWallAt(p.x, p.y, true);
            if(w != null && w.getWall() != null){
                w.getWall().renderOutline(sr, w.getX() * Wall.WallWidth, w.getY() * Wall.WallHeight);
                w.getWall().renderHitBox(sr, w.getX() * Wall.WallWidth, w.getY() * Wall.WallHeight, 7);
                if(handler.getIM().isMouseLeft){
                    w.getWall().printNumOfHitboxes(w.getWall().getClass().getSimpleName());
                }
            }
        }

        //draw bars with this
        if(Controls.trigOlverlay.isPressed()){
            sr.end();
            sr.begin(ShapeRenderer.ShapeType.Filled);
            sr.setColor(Color.RED);
            sr.rect(x, y, mdwX, 4);
            sr.setColor(Color.GREEN);
            sr.rect(x + mdwX, y, 4, -mdwY);
            sr.setColor(Color.BLUE);
            sr.rectLine(x,y,x + mdwX, y + -mdwY, 4);

            sr.end();
            sr.begin(ShapeRenderer.ShapeType.Line);
            sr.setColor(Color.YELLOW);
            sr.circle(x, y, hypotenuse);

            int radiusOfFlashCircle = 25;

            int flashXOffset = 0;
            int flashYOffset = 0;
            sr.setColor(Color.GOLD);
            sr.circle(x, y, radiusOfFlashCircle);


        }

        if(Controls.trigLightingOlverlay.isPressed()) {
            flashlightLight.setRendered(true);
        }else{
            flashlightLight.setRendered(false);
        }



    }

    public OrthographicCamera getPlayerCamera(){
        return playerCamera;
    }
    public OrthographicCamera getHUDCamera(){
        return hudCamera;
    }

    public Point getMouseCoords(){
        Point p = new Point(x - (Gdx.graphics.getWidth() / 2) + Gdx.input.getX(),
                            y - (Gdx.graphics.getHeight() / 2) + (Gdx.graphics.getHeight() - Gdx.input.getY()));
        return p;
    }


}
