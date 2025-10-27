package com.matt.game.world;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import java.awt.*;
import java.util.ArrayList;

public class Collidable {

    /*
        This class is extended wen ever an object needs to define collision bounds
        then whenever something moves, it can check if the area its moving into is an instance of collidable, if it is, get its collision bounuds
        for the appropriate layer

     */

    private ArrayList<Rectangle> global = new ArrayList<>();
    private ArrayList<Rectangle> head = new ArrayList<>();
    private ArrayList<Rectangle> chest = new ArrayList<>();
    private ArrayList<Rectangle> leftArm = new ArrayList<>();
    private ArrayList<Rectangle> rightArm = new ArrayList<>();
    private ArrayList<Rectangle> legs = new ArrayList<>();
    private ArrayList<Rectangle> feet = new ArrayList<>();
    private ArrayList<Rectangle> lightBox = new ArrayList<>();

    private int numOfHitBoxes = 0;

    public void addGlobalHitBox(Rectangle hitbox){
        global.add(hitbox);
        numOfHitBoxes++;
    }
    public void addHeadHitBox(Rectangle hitbox){
        head.add(hitbox);
        numOfHitBoxes++;
    }
    public void addChestHitBox(Rectangle hitbox){
        chest.add(hitbox);
        numOfHitBoxes++;
    }
    public void addLeftArmHitBox(Rectangle hitbox){
        leftArm.add(hitbox);
        numOfHitBoxes++;
    }
    public void addRightArmBox(Rectangle hitbox){
        rightArm.add(hitbox);
        numOfHitBoxes++;
    }
    public void addLegsBox(Rectangle hitbox){
        legs.add(hitbox);
        numOfHitBoxes++;
    }
    public void addFeetHitBox(Rectangle hitbox){
        feet.add(hitbox);
        numOfHitBoxes++;
    }
    public void addLightBox(Rectangle hitbox){
        lightBox.add(hitbox);
        numOfHitBoxes++;
    }

    public void printNumOfHitboxes(String objectName){
        System.out.println(objectName + " has " + numOfHitBoxes);
    }

    public void renderHitBox(ShapeRenderer sr, int mapx, int mapy, int code){
        //let us render the hitboxes
        //the code indicates which layer, 0 for global, 1 for head, 2 for chest, ... 7 for all

        switch (code){
            case 0:
                sr.setColor(Color.RED);
                for(Rectangle hitbox : global){
                    sr.rect(mapx + hitbox.x, mapy + hitbox.y, hitbox.width, hitbox.height);
                }
                break;
            case 1:
                sr.setColor(Color.GREEN);
                for(Rectangle hitbox : head){
                    sr.rect(mapx + hitbox.x, mapy + hitbox.y, hitbox.width, hitbox.height);
                }
                break;
            case 2:
                sr.setColor(Color.BLUE);
                for(Rectangle hitbox : chest){
                    sr.rect(mapx + hitbox.x, mapy + hitbox.y, hitbox.width, hitbox.height);
                }
                break;
            case 3:
                sr.setColor(Color.CYAN);
                for(Rectangle hitbox : leftArm){
                    sr.rect(mapx + hitbox.x, mapy + hitbox.y, hitbox.width, hitbox.height);
                }
                break;
            case 4:
                sr.setColor(Color.ROYAL);
                for(Rectangle hitbox : rightArm){
                    sr.rect(mapx + hitbox.x, mapy + hitbox.y, hitbox.width, hitbox.height);
                }
                break;
            case 5:
                sr.setColor(Color.FIREBRICK);
                for(Rectangle hitbox : legs){
                    sr.rect(mapx + hitbox.x, mapy + hitbox.y, hitbox.width, hitbox.height);
                }
                break;
            case 6:
                sr.setColor(Color.SALMON);
                for(Rectangle hitbox : feet){
                    sr.rect(mapx + hitbox.x, mapy + hitbox.y, hitbox.width, hitbox.height);
                }
                break;
            case 7:
                sr.setColor(Color.YELLOW);
                for(Rectangle hitbox : lightBox){
                    sr.rect(mapx + hitbox.x, mapy + hitbox.y, hitbox.width, hitbox.height);
                }
                break;
            case 8:
                sr.setColor(Color.RED);
                for(Rectangle hitbox : global){
                    sr.rect(mapx + hitbox.x, mapy + hitbox.y, hitbox.width, hitbox.height);
                }
                sr.setColor(Color.GREEN);
                for(Rectangle hitbox : head){
                    sr.rect(mapx + hitbox.x, mapy + hitbox.y, hitbox.width, hitbox.height);
                }
                sr.setColor(Color.BLUE);
                for(Rectangle hitbox : chest){
                    sr.rect(mapx + hitbox.x, mapy + hitbox.y, hitbox.width, hitbox.height);
                }
                sr.setColor(Color.CYAN);
                for(Rectangle hitbox : leftArm){
                    sr.rect(mapx + hitbox.x, mapy + hitbox.y, hitbox.width, hitbox.height);
                }
                sr.setColor(Color.ROYAL);
                for(Rectangle hitbox : rightArm){
                    sr.rect(mapx + hitbox.x, mapy + hitbox.y, hitbox.width, hitbox.height);
                }
                sr.setColor(Color.FIREBRICK);
                for(Rectangle hitbox : legs){
                    sr.rect(mapx + hitbox.x, mapy + hitbox.y, hitbox.width, hitbox.height);
                }
                sr.setColor(Color.SALMON);
                for(Rectangle hitbox : feet){
                    sr.rect(mapx + hitbox.x, mapy + hitbox.y, hitbox.width, hitbox.height);
                }
                sr.setColor(Color.YELLOW);
                for(Rectangle hitbox : lightBox){
                    sr.rect(mapx + hitbox.x, mapy + hitbox.y, hitbox.width, hitbox.height);
                }
                break;
        }





    }


    public void move(float deltaX, float deltaY){
        //calculate perfect collision
    }






    public ArrayList<Rectangle> getGlobalHitboxes() {
        return global;
    }
    public ArrayList<Rectangle> getHeadHitboxes() {
        return head;
    }
    public ArrayList<Rectangle> getChestHitboxes() {
        return chest;
    }
    public ArrayList<Rectangle> getLeftArmHitboxes() {
        return leftArm;
    }
    public ArrayList<Rectangle> getRightArmHitboxes() {
        return rightArm;
    }
    public ArrayList<Rectangle> getLegsHitboxes() {
        return legs;
    }
    public ArrayList<Rectangle> getFeetHitboxes() {
        return feet;
    }
    public ArrayList<Rectangle> getLightBoxes(){return lightBox;}

}
