package com.matt.game.world.Structure.Tiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class SandTile extends Tile{
    public SandTile(byte ID) {
        super(new Texture(Gdx.files.internal("tiles/sand.png")), ID);
    }
}
