package com.matt.game.input;

public class ToggleControls extends Controls{

    protected boolean isToggle = false;
    private boolean alreadyCounted = false;

    public ToggleControls(int ID, int keyCode) {
        super(ID, keyCode);
    }

    public boolean isPressed(){             //this method is used when we want to see if the button is pressed
        return isPressed;
    }
    public void setIsPressed(boolean pressed){//this method is used when we want to set it isPressed
        this.isPressed = pressed;
    }
    public void toggle(){
        if(!alreadyCounted){
            isPressed = !isPressed;
            alreadyCounted = true;
        }
    }
    public void setAlreadyCounted(boolean alreadyCounted){
        this.alreadyCounted = alreadyCounted;
    }
    public void setKeyCode(int keyCode){       //here we set the keycode
        this.keyCode = keyCode;
    }



}
