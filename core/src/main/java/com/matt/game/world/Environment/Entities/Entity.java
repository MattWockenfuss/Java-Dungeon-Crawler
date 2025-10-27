package com.matt.game.world.Environment.Entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.matt.game.world.Collidable;
import com.matt.game.world.Structure.Walls.ReturnableWall;
import com.matt.game.world.Structure.Walls.Wall;
import com.matt.game.world.World;

import java.util.ArrayList;

public class Entity extends Collidable {
    //what does every entity have in our world

    protected World world;
    protected int x, y;
    protected float xMove, yMove;
    protected int texWidth, texHeight;



    public Entity(World world, int x, int y){
        this.world = world;
        this.x = x;
        this.y = y;
        xMove = 0;
        yMove = 0;
    }

    public void tick(){

    }
    public void render(SpriteBatch sb){

    }

    public void move(){
        checkEntityCollision();
        x += (int) xMove;
        y += (int) yMove;
    }

    public void checkEntityCollision() {

        //this function reassigns xMove and yMove to appropriate values



        int relX = (x - (texWidth / 2));//in relation to center of entity
        int relY = (y - (texHeight / 2));


        for(Rectangle box : getFeetHitboxes()){
            Rectangle myHitbox = null;
            if(xMove > 0){
                myHitbox = new Rectangle(relX + box.x + box.width, relY + box.y, xMove, box.height);
                ReturnableWall[][] walls = world.getWallsIn(myHitbox);

                //now for each one of these walls we want to get the feet hitboxes, because thats what we are checking, and see if we intersect
                ArrayList<Rectangle> boxesWeWillHit = new ArrayList<>();

                for(int yi = 0; yi < walls[0].length; yi++){
                    for(int xi = 0; xi < walls.length; xi++){
                        if(walls[xi][yi].getWall() != null){
                            for(Rectangle wallFootBox : walls[xi][yi].getWall().getFeetHitboxes()){
                                Rectangle normalizedWallFootBox = new Rectangle(wallFootBox.x + walls[xi][yi].getX() * Wall.WallWidth, wallFootBox.y + walls[xi][yi].getY() * Wall.WallHeight, wallFootBox.width, wallFootBox.height);
                                if(myHitbox.overlaps(normalizedWallFootBox)){
                                    boxesWeWillHit.add(normalizedWallFootBox);
                                }
                            }
                        }
                    }
                }

                if(!boxesWeWillHit.isEmpty()){
                    Rectangle closestRect = boxesWeWillHit.get(0);

                    for(Rectangle boxWeWillHit : boxesWeWillHit){
                        if(boxWeWillHit.x < closestRect.x) closestRect = boxWeWillHit;
                    }
                    xMove = closestRect.x - (myHitbox.x + myHitbox.width - xMove);
                }
            }
            if(xMove < 0){
                myHitbox = new Rectangle(relX + box.x + xMove, relY + box.y, -xMove, box.height);
                ReturnableWall[][] walls = world.getWallsIn(myHitbox);

                //now for each one of these walls we want to get the feet hitboxes, because thats what we are checking, and see if we intersect
                ArrayList<Rectangle> boxesWeWillHit = new ArrayList<>();

                for(int yi = 0; yi < walls[0].length; yi++){
                    for(int xi = 0; xi < walls.length; xi++){
                        if(walls[xi][yi].getWall() != null){
                            for(Rectangle wallFootBox : walls[xi][yi].getWall().getFeetHitboxes()){
                                Rectangle normalizedWallFootBox = new Rectangle(wallFootBox.x + walls[xi][yi].getX() * Wall.WallWidth, wallFootBox.y + walls[xi][yi].getY() * Wall.WallHeight, wallFootBox.width, wallFootBox.height);
                                if(myHitbox.overlaps(normalizedWallFootBox)){
                                    boxesWeWillHit.add(normalizedWallFootBox);
                                }
                            }
                        }
                    }
                }

                if(!boxesWeWillHit.isEmpty()){
                    Rectangle closestRect = boxesWeWillHit.get(0);

                    for(Rectangle boxWeWillHit : boxesWeWillHit){
                        if((boxWeWillHit.x + boxWeWillHit.width) > (closestRect.x) + closestRect.width) closestRect = boxWeWillHit;
                    }
                    xMove = xMove - ((myHitbox.x) - (closestRect.x + closestRect.width));
                }
            }
            if(yMove > 0){//we are going up
                myHitbox = new Rectangle(relX + box.x, relY + box.y + box.height, box.width, yMove);
                ReturnableWall[][] walls = world.getWallsIn(myHitbox);

                //now for each one of these walls we want to get the feet hitboxes, because thats what we are checking, and see if we intersect
                ArrayList<Rectangle> boxesWeWillHit = new ArrayList<>();

                for(int yi = 0; yi < walls[0].length; yi++){
                    for(int xi = 0; xi < walls.length; xi++){
                        if(walls[xi][yi].getWall() != null){
                            for(Rectangle wallFootBox : walls[xi][yi].getWall().getFeetHitboxes()){
                                Rectangle normalizedWallFootBox = new Rectangle(wallFootBox.x + walls[xi][yi].getX() * Wall.WallWidth, wallFootBox.y + walls[xi][yi].getY() * Wall.WallHeight, wallFootBox.width, wallFootBox.height);
                                if(myHitbox.overlaps(normalizedWallFootBox)){
                                    boxesWeWillHit.add(normalizedWallFootBox);
                                }
                            }
                        }
                    }
                }

                if(!boxesWeWillHit.isEmpty()){
                    Rectangle closestRect = boxesWeWillHit.get(0);

                    for(Rectangle boxWeWillHit : boxesWeWillHit){
                        if(boxWeWillHit.y < closestRect.y) closestRect = boxWeWillHit;
                    }
                    //r.x and r.y are bottom left corner
                    yMove = (closestRect.y - (myHitbox.y + myHitbox.height) + yMove);
                }
            }
            if(yMove < 0){//we are going up
                myHitbox = new Rectangle(relX + box.x, relY + box.y + yMove, box.width, -yMove);
                ReturnableWall[][] walls = world.getWallsIn(myHitbox);

                //now for each one of these walls we want to get the feet hitboxes, because thats what we are checking, and see if we intersect
                ArrayList<Rectangle> boxesWeWillHit = new ArrayList<>();

                for(int yi = 0; yi < walls[0].length; yi++){
                    for(int xi = 0; xi < walls.length; xi++){
                        if(walls[xi][yi].getWall() != null){
                            for(Rectangle wallFootBox : walls[xi][yi].getWall().getFeetHitboxes()){
                                Rectangle normalizedWallFootBox = new Rectangle(wallFootBox.x + walls[xi][yi].getX() * Wall.WallWidth, wallFootBox.y + walls[xi][yi].getY() * Wall.WallHeight, wallFootBox.width, wallFootBox.height);
                                if(myHitbox.overlaps(normalizedWallFootBox)){
                                    boxesWeWillHit.add(normalizedWallFootBox);
                                }
                            }
                        }
                    }
                }

                if(!boxesWeWillHit.isEmpty()){
                    Rectangle closestRect = boxesWeWillHit.get(0);

                    for(Rectangle boxWeWillHit : boxesWeWillHit){
                        if(boxWeWillHit.y < closestRect.y) closestRect = boxWeWillHit;
                    }
                    //r.x and r.y are bottom left corner
                    yMove = -(myHitbox.y - (closestRect.y + closestRect.height) - yMove);

                }
            }
        }
    }






    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public float getxMove(){return this.xMove;}
    public float getyMove(){return this.yMove;}

}
