package com.matt.game.world.Structure.Walls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.awt.*;

public class DungeonWallBottom extends Wall {
    public DungeonWallBottom(byte ID) {
        super(new Texture(Gdx.files.internal("tiles/wall_bottom.png")), ID, (byte) 1);
        //addGlobalHitBox(new Rectangle(0,0,10,10));
        addFeetHitBox(new Rectangle(0, 0, WallWidth, 28));
        addLightBox(new Rectangle(0, 0, WallWidth, 28));
    }
}
