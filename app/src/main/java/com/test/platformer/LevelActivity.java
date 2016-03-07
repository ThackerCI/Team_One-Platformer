package com.test.platformer;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class LevelActivity extends AppCompatActivity {
    int value = 0;
    Environment environment = new Environment();
    boolean started = false;
    boolean running = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        // get the bundle of saved stuff
        Bundle savedStuff = getIntent().getExtras();
        // get the level and character info from the bundle
        int savedLevelInfo = (int) savedStuff.getSerializable("levelID");
        if (savedLevelInfo == 1) {
            initLevel(savedLevelInfo);
        }

    }

    public void initLevel(int i) {
        // load the level and the player character into the environment
        environment.initialize(environment.l1, environment.player);
        // signal that the level has started
        started = true;
        // initialize the ImageViews
        initView();
        // signal that the level is not paused
        running = true;
    }

    public void initView() {
        initBlocksView();
        initRecordsView();
        initCharacterView();
    }

    public void initBlocksView() {
        // iterate through the blocks in the environment
        for (int i = 0; i < environment.getBlocks().size(); ++i) {
            Block tempBlock = environment.getBlocks().get(i); // get the current block
            ImageView imageView = new ImageView(LevelActivity.this); // create a new ImageView
            imageView.setImageResource(R.drawable.block);            // set the "block" sprite to it
            // get the level layout
            RelativeLayout RL = (RelativeLayout) findViewById(R.id.level_layout);
            // get the dimensions for the sprite and convert them for the device's screen
            int dimX = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    tempBlock.getDimensions().x, getResources().getDisplayMetrics());
            int dimY = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    tempBlock.getDimensions().y, getResources().getDisplayMetrics());
            // create new layout parameters for the sprite
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(dimX, dimY);
            // get the location of the block and convert the coordinates for the device's screen
            int newX = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, tempBlock.getLocation().x, getResources().getDisplayMetrics());
            int newY = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, tempBlock.getLocation().y, getResources().getDisplayMetrics());
            // set the margins for the ImageView (i.e. position on the screen)
            layoutParams.setMargins(newX, newY, 0, 0);
            // add the ImageView to the layout
            RL.addView(imageView, layoutParams);

        }
    }

    public void initRecordsView() {

    }

    public void initCharacterView() {

    }


}
