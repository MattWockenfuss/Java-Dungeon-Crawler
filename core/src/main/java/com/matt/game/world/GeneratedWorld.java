package com.matt.game.world;

import com.badlogic.gdx.math.MathUtils;
import com.matt.game.world.Structure.Tiles.Tile;
import com.matt.game.world.Structure.Walls.Wall;

import java.awt.*;

public class GeneratedWorld {

    private WorldSettings ws;

    protected int WIDTH, HEIGHT;

    protected byte[][] tileIDs;
    protected byte[][] wallIDs;

    protected Point playerSpawn;

    public GeneratedWorld(WorldSettings ws){
        this.ws = ws;
        generateWorld();
    }

    private void generateWorld(){
        //this method generated the tileID and wallID arrays given the world settings ws


        //this is going to generated a world of 7x7 squares based on the radius read by the file

        System.out.println("GENERATING WORLD: radius: " + ws.getRadius());


        WIDTH = 101;
        HEIGHT = 101;

        tileIDs = new byte[WIDTH][HEIGHT];
        wallIDs = new byte[WIDTH][HEIGHT];

        for(int y = 0; y < HEIGHT; y++){
            for(int x = 0; x < WIDTH; x++){

                int random = 0;

                if(tileIDs[x][y] == 0)
                    random = MathUtils.random(1,100); // generate a random number between 1 and 100

                if(1 <= random && random < 30){
                    tileIDs[x][y] = 5;
                }else if(30 <= random && random < 60){
                    tileIDs[x][y] = 6;
                }else if(60 <= random && random < 84){
                    tileIDs[x][y] = 7;
                }else if(84 <= random && random < 92){
                    tileIDs[x][y] = 8;
                }else if(92 <= random && random < 99){
                    tileIDs[x][y] = 9;
                }else if(99 <= random && random <= 100){
                    if(x < (WIDTH - 1)){
                        //then this one can be left and next row can be right
                        tileIDs[x][y] = Tile.dungeonTile6Left.getID();
                        tileIDs[x + 1][y] = Tile.dungeonTile6Right.getID();
                        System.out.println("Placed Big Brick! at " + "(" + x + "," + y + ")");
                    }else{
                        tileIDs[x][y] = 4; // failed placed big brick
                    }

                }else{
                    System.out.println(random + " not handled!");
                }




                wallIDs[x][y] = 0;
            }
        }

        //now we are going to pick a random point inside the middle 30

        Point randomPoint = new Point(MathUtils.random(ws.getRadius() * 2) + (50 - ws.getRadius()),
                                      MathUtils.random(ws.getRadius() * 2) + (50 - ws.getRadius()));

        tileIDs[randomPoint.x][randomPoint.y] = Tile.waterTile.getID();
        tileIDs[50][50] = Tile.spacesandTile.getID();

        // now based on radius we want to make a radius x radius square of houses
        int houseWidth = 11;

        for(int i = - ws.getRadius(); i < ws.getRadius() + 1; i++){
            for(int j = - ws.getRadius(); j < ws.getRadius() + 1; j++){
                generateHouse(new Point(50 + (i * houseWidth), 50 + (j * (houseWidth + 3))), houseWidth);
            }
        }




        playerSpawn = new Point(3168, 3234);

    }

    private void generateHouse(Point p, int width){
        //this function builds a square room centered on Point p provided

        int branch = (width - 1) / 2;

        for(int y = (p.y - branch); y < (p.y + branch + 1); y++){
            for(int x = (p.x - branch); x < (p.x + branch + 1); x++){//we have to add 1 in both directions, it starts at bottom left
                if(x == (p.x - branch))
                    wallIDs[x][y] = Wall.dungeonWallLeft.getID();
                if(x == (p.x + branch))
                    wallIDs[x][y] = Wall.dungeonWallRight.getID();
                if(y == (p.y + branch))
                    wallIDs[x][y] = Wall.dungeonWall.getID();
                if(y == (p.y - branch))
                    wallIDs[x][y] = Wall.dungeonWallBottom.getID();
            }
        }

        //now pick the 4 corners
        wallIDs[p.x - branch][p.y + branch] = Wall.dungeonWallLeftCorner.getID(); //top left
        wallIDs[p.x + branch][p.y + branch] = Wall.dungeonWallRightCorner.getID(); //top right
        wallIDs[p.x - branch][p.y - branch] = Wall.dungeonWallBottomLeftCorner.getID(); //bottom left
        wallIDs[p.x + branch][p.y - branch] = Wall.dungeonWallBottomRightCorner.getID(); //bottom left

        wallIDs[p.x][p.y + branch] = 10;


        //now cut hole in side
        wallIDs[p.x - branch][p.y] = 0;
        wallIDs[p.x - branch][p.y - 1] = 9;

        wallIDs[p.x + branch][p.y] = 0;
        wallIDs[p.x + branch][p.y - 1] = 9;

    }


    /*
           int numberOf1 = 0;
        int numberOf2 = 0;
        int numberOf3 = 0;
        int numberOf4 = 0;
        int numberOf5 = 0;
        int numberOf6 = 0;
        int numberOf7 = 0;
        int numberOf8 = 0;
        int numberOf9 = 0;
        int numberOf10 = 0;
        for(int i = 0; i < 10000; i++){
            //System.out.println("RANDOM: " + (MathUtils.random(9) + 1)); //with a range 10, it generated a random number between 0 and 10 both inclusive
            int random = MathUtils.random(9) + 1; //generates a random number between 0 and 9 inclusive, then adds 1 to get 1 -> 10

            if(random == 1)
                numberOf1++;
            if(random == 2)
                numberOf2++;
            if(random == 3)
                numberOf3++;
            if(random == 4)
                numberOf4++;
            if(random == 5)
                numberOf5++;
            if(random == 6)
                numberOf6++;
            if(random == 7)
                numberOf7++;
            if(random == 8)
                numberOf8++;
            if(random == 9)
                numberOf9++;
            if(random == 10)
                numberOf10++;
        }

        //now print out results
        System.out.println("Here are the random distribution results!\n");
        System.out.println("1: " + numberOf1);
        System.out.println("2: " + numberOf2);
        System.out.println("3: " + numberOf3);
        System.out.println("4: " + numberOf4);
        System.out.println("5: " + numberOf5);
        System.out.println("6: " + numberOf6);
        System.out.println("7: " + numberOf7);
        System.out.println("8: " + numberOf8);
        System.out.println("9: " + numberOf9);
        System.out.println("10: " + numberOf10);
     */




}
