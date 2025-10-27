package com.matt.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.matt.game.Handler;
import com.matt.game.world.Structure.Tiles.Tile;
import com.matt.game.world.Structure.Walls.ReturnableWall;
import com.matt.game.world.Structure.Walls.Wall;

import java.awt.*;
import java.util.ArrayList;

public class World {
    private Handler handler;
    private String name;
    private GeneratedWorld world;


    public World(Handler handler, String name, int width, int height, String path){
        this.name = name;
        this.handler = handler;
        handler.setCurrentWorld(this);

        WorldSettings worldSettings = new WorldSettings("world/config.txt");
        world = new GeneratedWorld(worldSettings);
    }

//    private byte[][] generateWorld(){
//
//        //to generate the world, lets just place sand tiles everywhere
//        byte[][] tiles = new byte[WIDTH][HEIGHT];
//
//        for(int y = 0; y < tilePixelsMap.getHeight(); y++){
//            for(int x = 0; x < tilePixelsMap.getWidth(); x++){
//                String hexColor = Integer.toHexString(tilePixelsMap.getPixel(x, tilePixelsMap.getHeight() - 1 - y)).toUpperCase().substring(0,6);
//                String hexWallColor = Integer.toHexString(wallPixelsMap.getPixel(x, wallPixelsMap.getHeight() - 1 - y)).toUpperCase().substring(0,6);
//                //water is 218DEEFF because FF is 1 cause opaque
//                switch (hexColor){
//                    case "218DEE"://Water
//                        tiles[x][y] = Tile.waterTile.getID();
//                        break;
//                    case "E2D695"://Sand
//                        tiles[x][y] = Tile.sandTile.getID();
//                        break;
//                    case "282828"://Darkstone
//                        tiles[x][y] = Tile.darkStoneTile.getID();
//                        break;
//                    case "E4A672"://Spacesand
//                        tiles[x][y] = Tile.spacesandTile.getID();
//                        break;
//                    case "7F4A35"://Coarse Soil
//                        tiles[x][y] = Tile.coarseSoilTile.getID();
//                        break;
//                    case "3F3645"://SpookyDungeonTile
//                        tiles[x][y] = Tile.dungeonTile.getID();
//                        break;
//                    default:
//                        System.out.println("[World] Error loading tile at (" + x + "," + y + ")");
//                        tiles[x][y] = Tile.waterTile.getID();
//                }
//
//                switch (hexWallColor){
//                    case "3F3645":
//                        wallIDs[x][y] = Wall.dungeonWall.getID();
//                        break;
//                    case "3F364E":
//                        wallIDs[x][y] = Wall.dungeonWallRight.getID();
//                        break;
//                    case "4E4360":
//                        wallIDs[x][y] = Wall.dungeonWallRightCorner.getID();
//                        break;
//                    case "3F364F":
//                        wallIDs[x][y] = Wall.dungeonWallLeft.getID();
//                        break;
//                    case "3F365A":
//                        wallIDs[x][y] = Wall.dungeonWallLeftCorner.getID();
//                        break;
//                    case "3F366A":
//                        wallIDs[x][y] = Wall.dungeonWallBottomLeftCorner.getID();
//                        break;
//                    case "3F367A":
//                        wallIDs[x][y] = Wall.dungeonWallBottom.getID();
//                        break;
//                    case "3F368A":
//                        wallIDs[x][y] = Wall.dungeonWallBottomRightCorner.getID();
//                        break;
//                    default:
//                        wallIDs[x][y] = 0;
//                        break;
//                }
//
//
//
//            }
//        }
//
//
////        for(int y = 0; y < HEIGHT; y++){
////            for(int x = 0; x < WIDTH; x++){
////                tiles[x][y] = random.nextInt(5);
////            }
////        }
//
//        return tiles;
//
//    }

    public void render(SpriteBatch batch){
        //loop through every tile and render at appropriate x and y
        for(int y = 0; y < world.HEIGHT; y++){
            for(int x = 0; x < world.WIDTH; x++){
                Tile.tiles[world.tileIDs[x][y]].render(batch, x * Tile.TileWidth, y * Tile.TileHeight);
            }
        }

        //once we render all of the tiles, now lets render all of the walls
        for(int y = 0; y < world.HEIGHT; y++){
            for(int x = 0; x < world.WIDTH; x++){

                if(world.wallIDs[x][y] != 0){
                    Wall.walls[world.wallIDs[x][y]].render(batch, x * Tile.TileWidth, y * Tile.TileHeight);
                }
            }
        }



    }
    public void renderDebug(ShapeRenderer sr, int code){

        sr.end();
        sr.begin(ShapeRenderer.ShapeType.Filled);

        for(int y = 0; y < world.HEIGHT; y++){
            for(int x = 0; x < world.WIDTH; x++){
                if(world.wallIDs[x][y] != 0){
                    Wall.walls[world.wallIDs[x][y]].renderHitBox(sr, x * Tile.TileWidth, y * Tile.TileHeight, code);
                }
            }
        }

        sr.end();
        sr.begin(ShapeRenderer.ShapeType.Line);

    }

    public ReturnableWall getWallAt(int x, int y, boolean searchDown){
        if(x < 0 || (world.WIDTH * Wall.WallWidth) <= x){
            return null;
        }
        if(y < 0 || (world.HEIGHT * Wall.WallHeight) <= y){
            return null;
        }
        int searchX = x / Wall.WallWidth;
        int searchY = y / Wall.WallHeight;

        int maxSearchDown = 3; //max wall is 4 tiles tall


        Wall w = Wall.walls[world.wallIDs[searchX][searchY]];
        if(w != null){
            //System.out.println("returning " + w.getID() + " at " + "(" + searchX + "," + searchY + ")");
        }else{
            //okay so now we want to implement a search down algorithm
            //okay so the current wall is null, now we want to check further down
            if(searchDown){
                //now we want to keeping checkingAA until we found a tile thats false or we didnt find one
                Wall found = null;
                int tilesSearched = 0;

                while(found == null && tilesSearched < maxSearchDown){
                    tilesSearched++;
                    if(searchY - tilesSearched < 0)
                        break;
                    //System.out.println("Attempting to search tile at (" + searchX + "," + ((searchY) - (tilesSearched + 1)) + ") TilesSearched: " + tilesSearched);
                    Wall nextSearch = Wall.walls[world.wallIDs[searchX][searchY - tilesSearched]];
                    if(nextSearch != null){
                        found = nextSearch;
                    }
                }

                //now if we found a tile, see if its height reaches to where we are, if so, then we are in that tile
                if(found != null){
                    if(found.getRealHeight() >= tilesSearched + 1){
                        //System.out.println("returning " + found.getID() + " at " + "(" + searchX + "," + (searchY - tilesSearched) + ")");
                        return new ReturnableWall(found, searchX, searchY - tilesSearched);
                    }
                }


            }
        }


        return new ReturnableWall(w, searchX, searchY);
    }
    public ReturnableWall[][] getWallsIn(Rectangle r){
        //r's coordinates are normalized to the world

        Point[][] points = getPoints(r);

        ReturnableWall[][] walls = new ReturnableWall[points.length][points[0].length];

        for(int y = 0; y < points[0].length; y++){
            for(int x = 0; x < points.length; x++){
                ReturnableWall w = getWallAt(points[x][y].x, points[x][y].y, true);
                walls[x][y] = w;

//                if(w != null && w.getWall() != null)
//                    System.out.println("walls[" + w.getX() + "][" + w.getY() + "] (" + points[x][y].x +", " + points[x][y].y + ") FOUND!");
//                else
//                    System.out.println("walls[" + x + "][" + y + "] (" + points[x][y].x +", " + points[x][y].y + ")");
            }
        }




        return walls;
    }

    public Point[][] getPoints(int startX, int endX, float angleStart, float angleEnd, int range){
        //given a circle with the following parameters, this method with return the minimum number of points to check
        //and find walls inside of the cone up to a certain level of accuracy
        //maybe also if it finds a wall in the beginning dont check all the way to the end

        //we are going to spread out radially
        //for now lets just get every 32 px

        int circleDistance = 48;//distance between concentric circles
        int arcDistance = 48;   //distance between points on Arcs

        int NumOfCircles = (int) (Math.ceil(range / circleDistance) + 1);
        Point[][] points = new Point[NumOfCircles + 1][];

        if(angleStart > angleEnd)
            angleEnd += 360;

        double startAngleRadians = Math.toRadians(angleStart);
        double endAngleRadians = Math.toRadians(angleEnd);


        for(int circle = 0; circle <= NumOfCircles; circle++){
            //okay so we are looping through each circle

            //first figure out how many points are in this arc
            if(circle == 0){
                //then this is only 1
                points[circle] = new Point[1];
                points[0][0] = new Point(startX, endX);
                continue;
            }

            int radius = (circleDistance * circle);
            //if this is the last circle, then then make it on range/border
            if(circle == NumOfCircles){
                radius = range;
            }
            //alright. now how many points do the rest of the arcs get, well it depends on arc length

            double arcLength = (endAngleRadians - startAngleRadians) * radius;
            //this is the arc length of the current circle

            int numPoints = (int) (Math.ceil(arcLength / arcDistance) + 1);
            double angleStep = (endAngleRadians - startAngleRadians) / (numPoints - 1);
            points[circle] = new Point[numPoints];
            //now for each concentric circle, loop through arc, place points
            for (int i = 0; i < numPoints; i++) {
                double angle = startAngleRadians + i * angleStep;
                int x = (int) (startX + radius * Math.cos(angle));
                int y = (int) (endX + radius * Math.sin(angle));
                points[circle][i] = new Point(x, y);
            }
        }

        return points;



    }
    public Point[][] getPoints(Rectangle r){
        int xLength = (int) Math.ceil(r.width / Wall.WallWidth) + 1;
        int yLength = (int) Math.ceil(r.height / Wall.WallHeight) + 1;

        Point[][] points = new Point[xLength][yLength];

        for(int y = 0; y < yLength; y++){
            for(int x = 0; x < xLength; x++){

                int pointX = (int) (r.x + (x * Wall.WallWidth));
                int pointY = (int) (r.y + (y * Wall.WallHeight));

                if (pointX > r.x + r.width) {
                    pointX = (int) (r.x + r.width);
                }
                if (pointY > r.y + r.height) {
                    pointY = (int) (r.y + r.height);
                }



                points[x][y] = new Point(pointX, pointY);
            }
        }

        return points;



    }

    public int getSpawnX(){return world.playerSpawn.x;}
    public int getSpawnY(){return world.playerSpawn.y;}

}
