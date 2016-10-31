package com.example.jay.brickbreaker;

import android.app.Activity;
        import android.content.Context;
        import android.content.Intent;
        import android.os.Bundle;
        import android.os.Handler;
        import android.view.WindowManager;

public class bbActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        bbGameVars.display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        super.onCreate(savedInstanceState);
/*display the splash screen image*/
        setContentView(R.layout.splashscreen);
/*start up the splash screen and main menu in a time delayed
thread*/
        bbGameVars.context = this;
        new Handler().postDelayed(new Thread() {
            @Override
            public void run() {
                Intent mainMenu = new Intent(bbActivity.this, bbMainMenu.class);
                bbActivity.this.startActivity(mainMenu);
                bbActivity.this.finish();
                overridePendingTransition(R.layout.fadein,R.layout.fadeout);
            }
        }, bbGameVars.GAME_THREAD_DELAY);
    }
}
