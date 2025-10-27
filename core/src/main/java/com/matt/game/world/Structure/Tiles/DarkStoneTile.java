package com.matt.game.world.Structure.Tiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class DarkStoneTile extends Tile{
    public DarkStoneTile(byte ID) {
        super(new Texture(Gdx.files.internal("tiles/dark_stone.png")), ID);
    }
}
