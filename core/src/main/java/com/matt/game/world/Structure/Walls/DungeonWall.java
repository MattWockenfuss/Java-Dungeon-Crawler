package com.matt.game.world.Structure.Walls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class DungeonWall extends Wall {
    public DungeonWall(byte ID) {
        super(new Texture(Gdx.files.internal("tiles/wall_1.png")), ID, (byte) 3);
        addHeadHitBox(new Rectangle(9, 150, WallWidth - 18, 42));
        addChestHitBox(new Rectangle(0, 120, WallWidth, 30));
        addLeftArmHitBox(new Rectangle(0, 48, 16, 10));
        addRightArmBox(new Rectangle(10, 10, 10, 50));
        addLegsBox(new Rectangle(10, 10, 10, 50));
        addFeetHitBox(new Rectangle(0, 0, WallWidth, 20));
    }
}
