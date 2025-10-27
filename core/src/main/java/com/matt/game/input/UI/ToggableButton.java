package com.matt.game.input.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class ToggableButton {

    //this class contains the logic to toggle a button and get its current state

    private int KeyCode;

    private boolean Value = false;
    private boolean alreadyCounted = false;

    private Stage stage;

    public ToggableButton(int KeyCode){
        this.KeyCode = KeyCode;
    }

    public ToggableButton(int KeyCode, Stage s){
        this.KeyCode = KeyCode;
        this.stage = s;
    }

    public void tick(){
        if(Gdx.input.isKeyPressed(this.KeyCode)){
            if(!alreadyCounted){
                Value = !Value;
                alreadyCounted = true;
                if(stage != null){
                    if(Value){
                        Gdx.input.setInputProcessor(stage);
                    }else{
                        Gdx.input.setInputProcessor(null);
                    }
                }
            }
        }else{
            alreadyCounted = false;
        }
    }
    public int getKeyCode(){
        return this.KeyCode;
    }
    public boolean is(){
        return this.Value;
    }
    public void setValue(boolean val){
        this.Value = val;
    }



}
