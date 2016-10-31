package com.example.jay.brickbreaker;

/**
 * Created by Jay on 10/31/2016.
 */

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;


public class bbGame extends Activity {
    final bbGameVars gameEngine = new bbGameVars();
    private bbGameView gameView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new bbGameView(this);
        setContentView(gameView);
        MediaPlayer bgm = MediaPlayer.create(this, R.raw.diggingmypotato);
        bgm.start();
    }
    @Override
    protected void onResume() {
        super.onResume();
        gameView.onResume();

        //bgm.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.onPause();
        //bgm.pause();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //

        //bbGameVars.getWindowManager().getDefaultDisplay().getMetrics(bbGameVars.displaymetrics);
        //int height = bbGameVars.displaymetrics.heightPixels;
        //int width = displaymetrics.widthPixels

        float x = event.getX();
        float y = event.getY();


        int height = bbGameVars.display.getHeight() / 4;
        int playableArea = bbGameVars.display.getHeight() - height;
        //int height = bbGameVars.displaymetrics.heightPixels / 4;
        //int playableArea = bbGameVars.displaymetrics.heightPixels - height;


        if (y > playableArea){
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:


                    //if(x < bbGameVars.displaymetrics.widthPixels / 2){
                    if(x < bbGameVars.display.getWidth() / 2){
                        Log.d("XVAL","getx()=="+x+"  "+bbGameVars.display.getWidth()+" mythain ");
                        bbGameVars.playerAction = bbGameVars.PLAYER_MOVE_LEFT_1;
                    }else{
                        bbGameVars.playerAction = bbGameVars.PLAYER_MOVE_RIGHT_1;
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    bbGameVars.playerAction = bbGameVars.PLAYER_RELEASE;
                    break;
            }

        }

        return false;
    }
}
