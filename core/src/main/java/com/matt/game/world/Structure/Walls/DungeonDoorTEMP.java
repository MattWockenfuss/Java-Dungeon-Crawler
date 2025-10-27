package com.matt.game.world.Structure.Walls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class DungeonDoorTEMP extends Wall {
    public DungeonDoorTEMP(byte ID) {
        super(new Texture(Gdx.files.internal("tiles/brickvarients/door_right.png")), ID, (byte) 2);
        addHeadHitBox(new Rectangle(9, 20, WallWidth - 18, 42));
    }
}
