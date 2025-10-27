package com.matt.game.gfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.matt.game.Handler;
import com.matt.game.input.Controls;

import java.util.ArrayList;


public class LightManager {

    private Handler handler;
    private ArrayList<LightSource> lightSources;

    private ShaderProgram shader;

    public LightManager(Handler handler){
        this.handler = handler;
        handler.setLightManager(this);
        lightSources = new ArrayList<>();

        lightSources.add(new LightSource(6300, 19700, 1.0f, 0f, 90f, Color.ORANGE, 290, new Rectangle(0,0,20,20)));
        lightSources.add(new LightSource(7300, 19500, 1.0f, 10f, 170f, Color.YELLOW, 220, new Rectangle(0,0,30,30)));
        lightSources.add(new LightSource(6800, 19300, 1.0f, 0f, 180f, Color.RED, 220, new Rectangle(0,0,80,80)));
        lightSources.add(new LightSource(5000, 20200, 1.0f, 0f, 30f, Color.LIME, 700, new Rectangle(0,0,80,80)));
        lightSources.add(new LightSource(5600, 19200, 1.0f, 0f, 180f, Color.SKY, 190, new Rectangle(0,0,80,80)));
        lightSources.add(new LightSource(9500, 19200, 1.0f, 0f, 90f, Color.SCARLET, 2000, new Rectangle(0,0,80,80)));

        String vertexShader = Gdx.files.internal("shaders/light_vertex_shader.glsl").readString();
        String fragmentShader = Gdx.files.internal("shaders/light_fragment_shader.glsl").readString();
        shader = new ShaderProgram(vertexShader, fragmentShader);

        if(!shader.isCompiled()){
            System.out.println("Light Shader Did Not Compile Correctly!\n" + shader.getLog());
        }
    }

    public void tick(){
        for(LightSource lightSource : lightSources){
            if(lightSource.isTicked())
                lightSource.tick();
        }
        //after we loop through all of the light sources, then we set the uniforms of the shader
        shader.setUniformi("numLights", lightSources.size());
        //this works for now but I would like to find a better way to do this, maybe a more concrete way of keeping the x and y offset of the player in the handler
        shader.setUniformf("u_worldOffset", handler.getStateManager().getGameState().getScreenOffsetToPlayerCam().x + handler.getStateManager().getGameState().getPlayer().getxMove(),
                handler.getStateManager().getGameState().getScreenOffsetToPlayerCam().y + handler.getStateManager().getGameState().getPlayer().getyMove());
        int count = 0;
        for(LightSource lightSource : lightSources){
            //how can i convert the position of the lights to screen coords?
            shader.setUniformf("lights[" + count + "].position", lightSource.getX(), lightSource.getY());
            shader.setUniformf("lights[" + count + "].color", lightSource.getColor().r, lightSource.getColor().g, lightSource.getColor().b);
            shader.setUniformf("lights[" + count + "].intensity", lightSource.getIntensity());
            shader.setUniformf("lights[" + count + "].range", lightSource.getRange());
            shader.setUniformf("lights[" + count + "].angleStart", lightSource.getAngleStart());
            shader.setUniformf("lights[" + count + "].angleEnd", lightSource.getAngleEnd());
            if(lightSource.isRendered())
                shader.setUniformi("lights[" + count + "].isActive", 1);
            else
                shader.setUniformi("lights[" + count + "].isActive", 0);
            count++;
        }
    }

    public void renderDebug(ShapeRenderer sr){
        if(Controls.trigLightingOlverlay.isPressed()){
            for(LightSource lightSource : lightSources){
                lightSource.renderDebug(sr);
                lightSource.renderCollision(sr, handler.getCurrentWorld());
            }
        }
    }


    public ArrayList<LightSource> getLightSources(){
        return lightSources;
    }

    public void addLightSource(LightSource ls){
        lightSources.add(ls);
    }
    public ShaderProgram getShader(){
        return shader;
    }


}
