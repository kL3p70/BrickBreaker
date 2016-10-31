package com.example.jay.brickbreaker;

/**
 * Created by Jay on 10/31/2016.
 */
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;
import android.util.Log;

public class bbGameRenderer  implements Renderer{

    private bbBackground background = new bbBackground();
    private bbPlayer player1 = new bbPlayer();
    private bbBall ball = new bbBall();
    private bbTextures textureLoader;
    private int[] spriteSheets = new int[3];
    private int numberOfRows = 4;
    private bbWall wall;
    private long loopStart = 0;
    private long loopEnd = 0;
    private long loopRunTime = 0 ;


    @Override
    public void onDrawFrame(GL10 gl) {
        // TODO Auto-generated method stub
        loopStart = System.currentTimeMillis();
        // TODO Auto-generated method stub
        try {
            if (loopRunTime < bbGameVars.GAME_THREAD_FPS_SLEEP){
                Thread.sleep(bbGameVars.GAME_THREAD_FPS_SLEEP - loopRunTime);
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        drawBackground1(gl);
        movePlayer1(gl);
        drawBricks(gl);
        moveBall(gl);
        detectCollisions();
        loopEnd = System.currentTimeMillis();
        loopRunTime = ((loopEnd - loopStart));
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        // TODO Auto-generated method stub
        gl.glViewport(0, 0, width,height);

        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();

        gl.glOrthof(0f, 1f, 0f, 1f, -1f, 1f);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig arg1) {
        // TODO Auto-generated method stub
        initializeBricks();
        textureLoader = new bbTextures(gl);
        spriteSheets = textureLoader.loadTexture(gl, bbGameVars.BRICK_SHEET, bbGameVars.context, 1);
        spriteSheets = textureLoader.loadTexture(gl, bbGameVars.NUMBER_SHEET, bbGameVars.context, 2);
        spriteSheets = textureLoader.loadTexture(gl, bbGameVars.BALL_SHEET, bbGameVars.context, 3);

        gl.glEnable(GL10.GL_TEXTURE_2D);
        gl.glClearDepthf(1.0f);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glDepthFunc(GL10.GL_LEQUAL);

        background.loadTexture(gl,bbGameVars.BACKGROUND, bbGameVars.context);
        player1.loadTexture(gl,bbGameVars.PADDLE, bbGameVars.context);
    }

    private void drawBackground1(GL10 gl){

        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glPushMatrix();
        gl.glScalef(1f, 1f, 1f);
        //gl.glTranslatef(0f, 0f, 0f);

        background.draw(gl);
        gl.glPopMatrix();
        gl.glLoadIdentity();


    }

    private void initializeBricks(){
        wall = new bbWall(numberOfRows);
    }

    private void drawBricks(GL10 gl){
        for (int x = 0; x < wall.rows.length; x++) {
            for(int y = 0; y < wall.rows[x].bricks.length; y++) {
                if(!wall.rows[x].bricks[y].isDestroyed) {
                    switch (wall.rows[x].bricks[y].brickType){
                        case bbGameVars.BRICK_BLUE:
                            gl.glMatrixMode(GL10.GL_MODELVIEW);
                            gl.glLoadIdentity();
                            gl.glPushMatrix();
                            gl.glScalef(.25f, .25f, 1f);
                            gl.glTranslatef(wall.rows[x].bricks[y].posX, wall.rows[x].bricks[y].posY, 0f);
                            gl.glMatrixMode(GL10.GL_TEXTURE);
                            gl.glLoadIdentity();
                            gl.glTranslatef(0.66f, 0.33f , 0.0f);
                            wall.rows[x].bricks[y].draw(gl, spriteSheets);
                            gl.glPopMatrix();
                            gl.glLoadIdentity();
                            break;
                        case bbGameVars.BRICK_BROWN:
                            gl.glMatrixMode(GL10.GL_MODELVIEW);
                            gl.glLoadIdentity();
                            gl.glPushMatrix();
                            gl.glScalef(.25f, .25f, 1f);
                            gl.glTranslatef(wall.rows[x].bricks[y].posX, wall.rows[x].bricks[y].posY, 0f);
                            gl.glMatrixMode(GL10.GL_TEXTURE);
                            gl.glLoadIdentity();
                            gl.glTranslatef(0.0f, 0.66f , 0.0f);
                            wall.rows[x].bricks[y].draw(gl, spriteSheets);
                            gl.glPopMatrix();
                            gl.glLoadIdentity();
                            break;
                        case bbGameVars.BRICK_DARK_GRAY:
                            gl.glMatrixMode(GL10.GL_MODELVIEW);
                            gl.glLoadIdentity();
                            gl.glPushMatrix();
                            gl.glScalef(.25f, .25f, 1f);
                            gl.glTranslatef(wall.rows[x].bricks[y].posX, wall.rows[x].bricks[y].posY, 0f);
                            gl.glMatrixMode(GL10.GL_TEXTURE);
                            gl.glLoadIdentity();
                            gl.glTranslatef(0.33f, 0.33f , 0.0f);
                            wall.rows[x].bricks[y].draw(gl, spriteSheets);
                            gl.glPopMatrix();
                            gl.glLoadIdentity();
                            break;
                        case bbGameVars.BRICK_GREEN:
                            gl.glMatrixMode(GL10.GL_MODELVIEW);
                            gl.glLoadIdentity();
                            gl.glPushMatrix();
                            gl.glScalef(.25f, .25f, 1f);
                            gl.glTranslatef(wall.rows[x].bricks[y].posX, wall.rows[x].bricks[y].posY, 0f);
                            gl.glMatrixMode(GL10.GL_TEXTURE);
                            gl.glLoadIdentity();
                            gl.glTranslatef(0.00f, 0.33f , 0.0f);
                            wall.rows[x].bricks[y].draw(gl, spriteSheets);
                            gl.glPopMatrix();
                            gl.glLoadIdentity();
                            break;
                        case bbGameVars.BRICK_LITE_GRAY:
                            gl.glMatrixMode(GL10.GL_MODELVIEW);
                            gl.glLoadIdentity();
                            gl.glPushMatrix();
                            gl.glScalef(.25f, .25f, 1f);
                            gl.glTranslatef(wall.rows[x].bricks[y].posX, wall.rows[x].bricks[y].posY, 0f);
                            gl.glMatrixMode(GL10.GL_TEXTURE);
                            gl.glLoadIdentity();
                            gl.glTranslatef(0.33f, 0.0f , 0.0f);
                            wall.rows[x].bricks[y].draw(gl, spriteSheets);
                            gl.glPopMatrix();
                            gl.glLoadIdentity();
                            break;
                        case bbGameVars.BRICK_PURPLE:
                            gl.glMatrixMode(GL10.GL_MODELVIEW);
                            gl.glLoadIdentity();
                            gl.glPushMatrix();
                            gl.glScalef(.25f, .25f, 1f);
                            gl.glTranslatef(wall.rows[x].bricks[y].posX, wall.rows[x].bricks[y].posY, 0f);
                            gl.glMatrixMode(GL10.GL_TEXTURE);
                            gl.glLoadIdentity();
                            gl.glTranslatef(0.66f, 0.0f , 0.0f);
                            wall.rows[x].bricks[y].draw(gl, spriteSheets);
                            gl.glPopMatrix();
                            gl.glLoadIdentity();
                            break;
                        case bbGameVars.BRICK_RED:
                            gl.glMatrixMode(GL10.GL_MODELVIEW);
                            gl.glLoadIdentity();
                            gl.glPushMatrix();
                            gl.glScalef(.25f, .25f, 1f);
                            gl.glTranslatef(wall.rows[x].bricks[y].posX, wall.rows[x].bricks[y].posY, 0f);
                            gl.glMatrixMode(GL10.GL_TEXTURE);
                            gl.glLoadIdentity();
                            gl.glTranslatef(0.0f, 0.0f , 0.0f);
                            wall.rows[x].bricks[y].draw(gl, spriteSheets);
                            gl.glPopMatrix();
                            gl.glLoadIdentity();
                            break;
                        default:
                            gl.glMatrixMode(GL10.GL_MODELVIEW);
                            gl.glLoadIdentity();
                            gl.glPushMatrix();
                            gl.glScalef(.25f, .25f, 1f);
                            gl.glTranslatef(wall.rows[x].bricks[y].posX, wall.rows[x].bricks[y].posY, 0f);
                            gl.glMatrixMode(GL10.GL_TEXTURE);
                            gl.glLoadIdentity();
                            gl.glTranslatef(0.0f, 0.0f , 0.0f);
                            wall.rows[x].bricks[y].draw(gl, spriteSheets);
                            gl.glPopMatrix();
                            gl.glLoadIdentity();
                            break;
                    }
                }
            }
        }
    }

    private void moveBall(GL10 gl){
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glPushMatrix();
        gl.glScalef(.25f, .25f, 1f);
        ball.posX += ((bbGameVars.ballTargetX - ball.posX )/ (ball.posY / (bbGameVars.ballTargetY )));
        ball.posY -= bbGameVars.ballTargetY * 3;
        gl.glTranslatef(ball.posX, ball.posY, 0f);
        gl.glMatrixMode(GL10.GL_TEXTURE);
        gl.glLoadIdentity();
        gl.glTranslatef(0.0f,0.0f, 0.0f);
        ball.draw(gl,spriteSheets);
        gl.glPopMatrix();
        gl.glLoadIdentity();
    }

    private void movePlayer1(GL10 gl){
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glPushMatrix();
        gl.glScalef(.25f, .25f, 1f);
        if (bbGameVars.playerAction == bbGameVars.PLAYER_MOVE_LEFT_1 && bbGameVars.playerBankPosX > 0) {
            bbGameVars.playerBankPosX = bbGameVars.playerBankPosX - bbGameVars.PLAYER_MOVE_SPEED;
        }
        else if(bbGameVars.playerAction == bbGameVars.PLAYER_MOVE_RIGHT_1 &&  bbGameVars.playerBankPosX < 2.5) {
            bbGameVars.playerBankPosX = bbGameVars.playerBankPosX + bbGameVars.PLAYER_MOVE_SPEED;
        }
        gl.glTranslatef(bbGameVars.playerBankPosX, .5f, 0f);
        gl.glMatrixMode(GL10.GL_TEXTURE);
        gl.glLoadIdentity();
        gl.glTranslatef(0.0f,0.0f, 0.0f);
        player1.draw(gl);
        gl.glPopMatrix();
        gl.glLoadIdentity();

    }

    private void detectCollisions(){
        if(ball.posY <= 0){
            //gameover
        }

        for (int x = 0; x < wall.rows.length; x++) {
            for(int y = 0; y < wall.rows[x].bricks.length; y++) {
                if(!wall.rows[x].bricks[y].isDestroyed) {
                    if (((ball.posY > wall.rows[x].bricks[y].posY - .25f) && (ball.posY < wall.rows[x].bricks[y].posY) && (ball.posX + .25f > wall.rows[x].bricks[y].posX) && (ball.posX < wall.rows[x].bricks[y].posX + 1.50f))) {
                        wall.rows[x].bricks[y].isDestroyed = true;
                        advanceScore();
                        bbGameVars.ballTargetY = bbGameVars.ballTargetY * -1f;
                        if(bbGameVars.ballTargetX == -2f){
                            bbGameVars.ballTargetX = 5f;
                        }
                        else{
                            bbGameVars.ballTargetX = -2f;
                        }
                    }
                }
            }
        }

        if((ball.posY - .25f <= .5f) && (ball.posX + .25f > bbGameVars.playerBankPosX ) && (ball.posX < bbGameVars.playerBankPosX  + 1.50f)){
            bbGameVars.ballTargetY = bbGameVars.ballTargetY * -1f;
            if(bbGameVars.ballTargetX == -2f){
                bbGameVars.ballTargetX = 5f;
            }
            else{
                bbGameVars.ballTargetX = -2f;
            }
        }
        if(ball.posX < 0 || ball.posX + .25f > 3.75f) {
            bbGameVars.ballTargetX = bbGameVars.ballTargetX * -1f;
        }
    }
    private void advanceScore(){
        Log.d("SCORE"," ++ ");//Score Method here
    }
}

