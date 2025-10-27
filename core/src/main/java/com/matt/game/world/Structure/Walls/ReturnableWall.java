package com.matt.game.world.Structure.Walls;

public class ReturnableWall {

    //this class is merely used to return a wall and its coordinates
    private Wall w;
    private int x, y;

    public ReturnableWall(Wall w, int x, int y){
        this.w = w;
        this.x = x;
        this.y = y;
    }

    public Wall getWall() {
        return w;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}
