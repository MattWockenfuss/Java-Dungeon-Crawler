package com.matt.game.world;

public class WorldGenerator {

    private WorldSettings worldSettings;


    public WorldGenerator(WorldSettings worldSettings){
        this.worldSettings = worldSettings;
    }

    public void generateWorld(){
        if(worldSettings == null){
            System.out.println("World Settings is null for world!");
            return;
        }
    }



}
