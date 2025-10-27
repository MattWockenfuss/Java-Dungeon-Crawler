package com.matt.game.world.Structure.Walls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class DungeonWallLeft extends Wall {
    public DungeonWallLeft(byte ID) {
        super(new Texture(Gdx.files.internal("tiles/wall_left.png")), ID, (byte) 1);
        addFeetHitBox(new Rectangle(0, 0, 28, 64));
    }
}
