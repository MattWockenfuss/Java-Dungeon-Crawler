package com.matt.game.world.Structure.Tiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class SpaceSandTile extends Tile{
    public SpaceSandTile(byte ID) {
        super(new Texture(Gdx.files.internal("tiles/spacesand.png")), ID);
    }
}
