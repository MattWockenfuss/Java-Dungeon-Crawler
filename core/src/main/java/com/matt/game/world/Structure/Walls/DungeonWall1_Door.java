package com.matt.game.world.Structure.Walls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class DungeonWall1_Door extends Wall {
    public DungeonWall1_Door(byte ID) {
        super(new Texture(Gdx.files.internal("tiles/wall_1_door.png")), ID, (byte) 3);
        addFeetHitBox(new Rectangle(0, 0, WallWidth, 20));
    }
}
