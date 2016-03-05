package com.test.platformer;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class LevelActivity extends AppCompatActivity {

    Environment environment = new Environment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        // get the bundle of saved stuff
        Bundle savedStuff = getIntent().getExtras();
        // get the level and character info from the bundle
        int savedLevelInfo = (int) savedStuff.getSerializable("levelID");
        if (savedLevelInfo == 1){
            environment.initialize(environment.l1, environment.player);
        }

    }


}
