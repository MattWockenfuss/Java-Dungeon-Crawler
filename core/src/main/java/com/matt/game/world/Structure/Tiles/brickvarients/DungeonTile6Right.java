package com.matt.game.world.Structure.Tiles.brickvarients;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.matt.game.world.Structure.Tiles.Tile;

public class DungeonTile6Right extends Tile {
    public DungeonTile6Right(byte ID) {
        super(new Texture(Gdx.files.internal("tiles/brickvarients/dungeonTile6_right.png")), ID);
    }
}
