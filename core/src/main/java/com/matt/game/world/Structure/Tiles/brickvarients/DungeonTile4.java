package com.matt.game.world.Structure.Tiles.brickvarients;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.matt.game.world.Structure.Tiles.Tile;

public class DungeonTile4 extends Tile {
    public DungeonTile4(byte ID) {
        super(new Texture(Gdx.files.internal("tiles/brickvarients/dungeonTile4.png")), ID);
    }
}
