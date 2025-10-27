package com.matt.game.input.UI;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.matt.game.Game;
import com.matt.game.Handler;

public class MenuButtonListener extends ClickListener {
    private Handler handler;
    public static Sound hover_sound;
    private boolean soundPlayed = false;
    private ClickListener clickListener;

    public MenuButtonListener(Handler handler, ClickListener c){
        hover_sound = handler.getRM().getAM().get("audio/button-hover2.wav", Sound.class);
        this.handler = handler;
        clickListener = c;
    }

    public void clicked(InputEvent e, float x, float y){
        clickListener.clicked(e, x, y);
    }

    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
        super.enter(event, x, y, pointer, fromActor);

        Actor actor = event.getListenerActor();
        Actor from = fromActor;
        while(from != null){
            if(from == actor) return;
            from = from.getParent();
        }

        if(!soundPlayed){
            hover_sound.play(0.25f);
            soundPlayed = true;
        }
    }
    public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor){
        super.exit(event, x, y, pointer, fromActor);

        Actor actor = event.getListenerActor();
        Actor from = fromActor;
        while(from != null){
            if(from == actor) return;
            from = from.getParent();
        }

        soundPlayed = false;
    }
}
