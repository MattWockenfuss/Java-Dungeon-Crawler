package com.matt.game.world.Structure.Walls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class DungeonWallLeftCorner extends Wall {
    public DungeonWallLeftCorner(byte ID) {
        super(new Texture(Gdx.files.internal("tiles/wall_1_corner_left.png")), ID, (byte) 3);
        addRightArmBox(new Rectangle(0, 20, 50, 50));
        addFeetHitBox(new Rectangle(0, 0, WallWidth, 20));
    }
}
