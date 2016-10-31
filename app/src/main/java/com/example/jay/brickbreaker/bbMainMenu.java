package com.example.jay.brickbreaker;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
public class bbMainMenu extends Activity {
    /** Called when the activity is first created. */
    final bbGameVars engine = new bbGameVars();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        bbGameVars.context = getApplicationContext();
/** Set menu button options */

        ImageButton start = (ImageButton)findViewById(R.id.btnStart);
        ImageButton exit = (ImageButton)findViewById(R.id.btnExit);
        start.getBackground().setAlpha(bbGameVars.MENU_BUTTON_ALPHA);
        start.setHapticFeedbackEnabled(bbGameVars.HAPTIC_BUTTON_FEEDBACK);
        exit.getBackground().setAlpha(bbGameVars.MENU_BUTTON_ALPHA);
        exit.setHapticFeedbackEnabled(bbGameVars.HAPTIC_BUTTON_FEEDBACK);
        start.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
/** Start Game!!!! */
                Intent game = new Intent(getApplicationContext(),bbGame.class);
                bbMainMenu.this.startActivity(game);
            }
        });
        exit.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
                int pid= android.os.Process.myPid();
                android.os.Process.killProcess(pid);
            }
        });
    }
}