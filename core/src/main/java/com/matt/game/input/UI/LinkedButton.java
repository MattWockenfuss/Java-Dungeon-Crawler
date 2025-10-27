package com.matt.game.input.UI;

import com.badlogic.gdx.Gdx;

public class LinkedButton {

    private int[] keycodes;
    private boolean[] values;

    public LinkedButton(int[] keycodes){
        this.keycodes = keycodes;
        values = new boolean[keycodes.length];
    }

    public void tick(){
        boolean[] values = new boolean[keycodes.length];

        for(int i = 0; i < keycodes.length; i++){
            values[i] = Gdx.input.isKeyPressed(keycodes[i]);
        }

        int firstTrue = -1;

        for(int i = 0; i < keycodes.length; i++){
            if(values[i]){
                firstTrue = i;
                break;
            }
        }

        if(firstTrue != -1){
            //okay than we found a value that is true.
            //okay set all values to false accept that one

            for(int i = 0; i < values.length; i++){
                if(i == firstTrue)
                    this.values[i] = true;
                else
                    this.values[i] = false;
            }


        }





    }

    public boolean[] is(){
        return this.values;
    }
    public boolean is(int index){
        return values[index];
    }
    public void setValue(int index, boolean value){
        this.values[index] = value;
    }

}
