package com.matt.game.world.Structure.Walls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class DungeonWallRight extends Wall {
    public DungeonWallRight(byte ID) {
        super(new Texture(Gdx.files.internal("tiles/wall_right.png")), ID, (byte) 1);
        addFeetHitBox(new Rectangle(36, 0, 28, 64));
    }
}
