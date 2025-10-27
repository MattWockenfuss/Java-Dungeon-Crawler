package com.matt.game.world.Structure.Tiles.brickvarients;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.matt.game.world.Structure.Tiles.Tile;

public class DungeonTile6Left extends Tile {
    public DungeonTile6Left(byte ID) {
        super(new Texture(Gdx.files.internal("tiles/brickvarients/dungeonTile6_left.png")), ID);
    }
}
