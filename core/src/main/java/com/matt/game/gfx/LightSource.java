package com.matt.game.gfx;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.matt.game.world.Structure.Walls.ReturnableWall;
import com.matt.game.world.Structure.Walls.Wall;
import com.matt.game.world.World;

import java.awt.*;


public class LightSource {

    private Rectangle lightBox;
    private float intensity;
    private float angleStart;
    private float angleEnd;
    private Color color;

    private boolean isTicked = true;
    private boolean isRendered = true;

    private int x, y;//These are centered on the light source
    private float range;

    private float speed;

    public LightSource(int x, int y, float intensity, float angleStart, float angleEnd, Color color, int range, Rectangle bounds){
        this.x = x;
        this.y = y;
        this.intensity = intensity;
        this.angleStart = angleStart;
        this.angleEnd = angleEnd;
        this.color = color;
        this.range = range;
        this.lightBox = bounds;


        this.speed = (float)(Math.random() * 0.5f - 0.25f);
        System.out.println("New Light Source at (" + x + "," + y + ") with a speed of " + speed);
    }

    public void tick(){
        angleStart += speed;
        angleEnd += speed;

        normalizeAngles();

        if(color == Color.ORANGE){
            speed = 1.5f;
            range -= 0.05f;
            if(range <= 0){
                range = 100;
                isTicked = false;
            }
        }

    }

    public void normalizeAngles(){
        //come up with a method to keep the angles bounded to 0 -> 360
        if(angleStart < 0)
            angleStart += 360;
        if(angleStart > 360)
            angleStart -= 360;

        if(angleEnd < 0)
            angleEnd += 360;
        if(angleEnd > 360)
            angleEnd -= 360;
    }

    public void renderDebug(ShapeRenderer sr){
        sr.end();
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(color);

        if(isRendered){
            sr.rect(x + lightBox.x - (lightBox.width / 2), y + lightBox.y - (lightBox.height) / 2, lightBox.width, lightBox.height);
            float yDif = (float) (range * Math.sin(Math.toRadians(angleStart)));
            float xDif = (float) (range * Math.cos(Math.toRadians(angleStart)));
            sr.rectLine((float) x, (float) y, x + xDif, y + yDif, 2);
            yDif = (int) (range * Math.sin(Math.toRadians(angleEnd)));
            xDif = (int) (range * Math.cos(Math.toRadians(angleEnd)));
            sr.rectLine((float) x, (float) y, x + xDif, y + yDif, 2);
        }
        sr.end();
        sr.begin(ShapeRenderer.ShapeType.Line);
        if(isRendered)
            sr.circle(x, y, range);
    }
    public void renderCollision(ShapeRenderer sr, World world){
        //assuming sr on filled at start and end
        if(!isRendered) return;
        Point[][] points = world.getPoints(x, y, angleStart, angleEnd, (int) range);
        int pointWidth = 2;
        sr.end();
        sr.begin(ShapeRenderer.ShapeType.Filled);

        int numPoints = 0;

        for(int circle = 0; circle < points.length; circle++){
            for(int segment = 0; segment < points[circle].length; segment++){
                //okay now for every point, get the wall at that location
                ReturnableWall w = world.getWallAt(points[circle][segment].x, points[circle][segment].y, true);
                if(w != null && w.getWall() != null){
                    w.getWall().renderHitBox(sr, w.getX() * Wall.WallWidth, w.getY() * Wall.WallHeight, 8);
                }

                if(circle % 2 == 0)
                    sr.setColor(Color.BLUE);
                else
                    sr.setColor(Color.RED);

                sr.rect(points[circle][segment].x - pointWidth, points[circle][segment].y - pointWidth, pointWidth * 2 + 1, pointWidth * 2 + 1);
                numPoints++;
            }
        }

        //System.out.println("range/numPoints: " + range + " / " + numPoints);

        /*
                AVG FPS = 165

                circleDistance = 48
                arcDistance = 48

                200.3049 / 31
                220.0 / 49
                220.0 / 55
                700.0 / 87
                190.0 / 39
                2000.0 / 1480
                780.0 / 123
                10.0 / 4

         */

        /*
                AVG FPS = 165

                circleDistance = 32
                arcDistance = 32

                199.90488 / 54
                220.0 / 89
                220.0 / 99
                700.0 / 166
                190.0 / 76
                2000.0 / 3259
                780.0 / 243
                10.0 / 4

         */

        /*

                AVG FPS = 140

                circleDistance = 8
                arcDistance = 8

                206.45528 / 590
                220.0 / 1175
                220.0 / 1316
                700.0 / 2182
                190.0 / 978
                2000.0 / 50056
                780.0 / 3196
                10.0 / 18

         */

        /*

                AVG FPS = 14

                circleDistance = 2
                arcDistance = 2

                268.95514 / 14618
                220.0 / 17524
                220.0 / 19693
                700.0 / 32871
                190.0 / 14769
                2000.0 / 789241
                780.0 / 48738
                10.0 / 136

         */





    }


    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public void setRange(float range){
        this.range = range;
    }


    public float getAngleStart() {
        return angleStart;
    }
    public float getAngleEnd() {
        return angleEnd;
    }
    public void setAngleStart(float angle){
        this.angleStart = angle;
    }
    public void setAngleEnd(float angle){
        this.angleEnd = angle;
    }
    public float getIntensity() {
        return intensity;
    }
    public Color getColor() {
        return color;
    }
    public float getRange() {
        return range;
    }
    public void setTicked(boolean isTicked){
        this.isTicked = isTicked;
    }
    public void setRendered(boolean isRendered){
        this.isRendered = isRendered;
    }
    public boolean isTicked() {
        return isTicked;
    }
    public boolean isRendered() {
        return isRendered;
    }
}
