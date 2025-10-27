package com.matt.game.world.Structure.Walls;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.matt.game.world.Collidable;

import java.awt.*;

public class Wall extends Collidable {
    public static Wall[] walls = new Wall[256];

    public static Wall dungeonWall = new DungeonWall((byte) 1);
    public static Wall dungeonWallRight = new DungeonWallRight((byte) 2);
    public static Wall dungeonWallRightCorner = new DungeonWallRightCorner((byte) 3);
    public static Wall dungeonWallLeft = new DungeonWallLeft((byte) 4);
    public static Wall dungeonWallLeftCorner = new DungeonWallLeftCorner((byte) 5);
    public static Wall dungeonWallBottom = new DungeonWallBottom((byte) 6);
    public static Wall dungeonWallBottomLeftCorner = new DungeonWallBottomLeft((byte) 7);
    public static Wall dungeonWallBottomRightCorner = new DungeonWallBottomRight((byte) 8);
    public static Wall dungeonDoor = new DungeonDoorTEMP((byte) 9);
    public static Wall dungeonWall1Door = new DungeonWall1_Door((byte) 10);




    public static final int WallWidth = 64;
    public static final int WallHeight = 64;

    protected Texture texture;
    protected final byte ID;
    protected final byte Height;

    public Wall(Texture texture, byte ID, byte Height){
        this.texture = texture;
        this.ID = ID;
        this.Height = Height;
        walls[ID] = this;
    }

    public void render(SpriteBatch batch, int x, int y){
        batch.draw(texture, x, y, WallWidth, WallHeight * Height);
    }
    public void renderOutline(ShapeRenderer sr, int x, int y){
        sr.setColor(Color.YELLOW);
        sr.rect(x, y, WallWidth, WallHeight * Height);
    }
    public byte getID(){
        return ID;
    }
    public int getRealHeight(){
        return (int) Height;
    }
}
