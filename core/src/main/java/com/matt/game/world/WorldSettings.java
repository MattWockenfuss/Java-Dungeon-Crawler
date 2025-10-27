package com.matt.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class WorldSettings {

    private int minRooms, maxRooms;
    private int radius;


    public WorldSettings(String path){
        //path  = path to world generation settings file
        loadFile(path);


    }


    private void loadFile(String path){
        FileHandle file = Gdx.files.internal(path);

        String fileText = file.readString();
        String[] lines = fileText.split("\\r?\\n|\\r");

        int count = 1;
        for(String line : lines){
            System.out.println(count + ":" + line);
            count++;
            //okay now we are reading every line


            if(line.trim().isEmpty())
                continue;

            if(line.charAt(0) == '#')       //skip commented lines
                continue;

            //if there is no equal sign, also skip
            if(!line.contains("="))
                continue;


            //alright so we have a line that has an equal sign and is not a comment
            //read both sides
            String[] chunks = line.split("=");
            String token = chunks[0].trim();
            String value = chunks[1].trim();


            switch (token){
                case "numOfRooms":
                    minRooms = Integer.parseInt(value);
                    System.out.println("The Number of Rooms is " + minRooms);
                    break;
                case "Spawn Entities":
                    boolean spawnEntities = Boolean.parseBoolean(value);
                    if(spawnEntities)
                        System.out.println("We are spawning Entities!");
                    else
                        System.out.println("DONT SPAWN ENTITIES");
                    break;
                case "radius":
                    radius = Integer.parseInt(value);
                    System.out.println("The radius is " + radius);
                    break;
                default:
                    //do nothing
                    break;
            }




        }



    }


    public int getRadius() {
        return radius;
    }
}
