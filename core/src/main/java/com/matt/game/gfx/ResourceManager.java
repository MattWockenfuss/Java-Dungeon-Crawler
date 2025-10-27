package com.matt.game.gfx;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.matt.game.Handler;

public class ResourceManager {

    //This class is my AssetManager it will be passed with the handler so we can keep track of assets anywhere
    private Handler handler;
    private AssetManager am;

    public ResourceManager(Handler handler){
        this.handler = handler;
        this.handler.setRM(this);

        am = new AssetManager();

        loadTextures();
        loadFonts();
        loadSkins();
        loadAudio();
    }

    public void tick(){
        am.update(17);
    }

    private void loadTextures(){
        am.load("tiles/coarse_soil.png", Texture.class);
        am.load("tiles/dark_stone.png", Texture.class);
        am.load("tiles/sand.png", Texture.class);
        am.load("tiles/spacesand.png", Texture.class);
        am.load("cop.png", Texture.class);
        am.load("magicwand.png", Texture.class);
        am.load("skeleton_stand_right.png", Texture.class);




        am.load("flashlight.png", Texture.class);
        am.load("character-Sheet.png", Texture.class);

        am.load("background.jpg", Texture.class);
    }
    private void loadSkins(){
        am.load("ui/skins/clean/clean-crispy-ui.json", Skin.class);
        am.load("ui/skins/cloud/cloud-form-ui.json", Skin.class);
        am.load("ui/skins/default/uiskin.json", Skin.class);
        am.load("ui/skins/plainjames/plain-james-ui.json", Skin.class);
        am.load("ui/skins/vis/uiskin.json", Skin.class);
    }
    private void loadFonts(){
        am.load("ui/fonts/segoeUI.fnt", BitmapFont.class);
        am.load("ui/fonts/consolas.fnt", BitmapFont.class);
    }
    private void loadAudio(){
        am.load("audio/menu_music.mp3", Music.class);
        am.load("audio/button-hover2.wav", Sound.class);
    }

    public AssetManager getAM(){
        return am;
    }

    public float getProgress(){
        return am.getProgress();
    }


    public void dispose(){
        //called when the game is being closed to free up resources
        am.dispose();
    }



}
