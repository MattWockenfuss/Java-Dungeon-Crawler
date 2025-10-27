package com.matt.game.world.Structure.Tiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class CoarseSoilTile extends Tile {
    public CoarseSoilTile(byte ID) {
        super(new Texture(Gdx.files.internal("tiles/coarse_soil.png")), ID);
    }
}
