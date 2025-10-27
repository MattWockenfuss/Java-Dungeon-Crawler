package com.matt.game.world.Structure.Tiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.matt.game.world.Structure.Tiles.brickvarients.*;

public class Tile {
    public static Tile[] tiles = new Tile[256];

    public static Tile sandTile = new SandTile((byte) 0);
    public static Tile spacesandTile = new SpaceSandTile((byte) 1);
    public static Tile coarseSoilTile = new CoarseSoilTile((byte) 2);
    public static Tile darkStoneTile = new DarkStoneTile((byte) 3);
    public static Tile waterTile = new WaterTile((byte) 4);

    public static Tile dungeonTile1 = new DungeonTile((byte) 5);
    public static Tile dungeonTile2 = new DungeonTile2((byte) 6);
    public static Tile dungeonTile3 = new DungeonTile3((byte) 7);
    public static Tile dungeonTile4 = new DungeonTile4((byte) 8);
    public static Tile dungeonTile5 = new DungeonTile5((byte) 9);
    public static Tile dungeonTile6Left = new DungeonTile6Left((byte) 10);
    public static Tile dungeonTile6Right = new DungeonTile6Right((byte) 11);




    public static final int TileWidth = 64;
    public static final int TileHeight = 64;

    protected Texture texture;
    protected final byte ID;

    public Tile(Texture texture, byte ID){
        this.texture = texture;
        this.ID = ID;
        tiles[ID] = this;
    }

    public void render(SpriteBatch batch, int x, int y){
        batch.draw(texture, x, y, Tile.TileWidth, Tile.TileHeight);
    }
    public void renderGrid(ShapeRenderer sr, int x, int y){
        sr.rect(x, y, Tile.TileWidth, Tile.TileHeight);
    }
    public byte getID(){
        return ID;
    }
}
