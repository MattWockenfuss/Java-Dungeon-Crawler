package com.matt.game.world.Structure.Walls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class DungeonWallBottomLeft extends Wall {
    public DungeonWallBottomLeft(byte ID) {
        super(new Texture(Gdx.files.internal("tiles/wall_bottom_left_corner.png")), ID, (byte) 1);
        addFeetHitBox(new Rectangle(0, 0, WallWidth, 28));
        addFeetHitBox(new Rectangle(0, 28, 28, 36));
    }
}
