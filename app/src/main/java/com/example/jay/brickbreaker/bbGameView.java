package com.example.jay.brickbreaker;

/**
 * Created by Jay on 10/31/2016.
 */
import android.content.Context;
import android.opengl.GLSurfaceView;
public class bbGameView extends GLSurfaceView {

    private bbGameRenderer renderer;
    public bbGameView(Context context) {
        super(context);
        renderer = new bbGameRenderer();
        this.setRenderer(renderer);
    }
}