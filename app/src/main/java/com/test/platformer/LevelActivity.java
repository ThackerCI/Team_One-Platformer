package com.test.platformer;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

public class LevelActivity extends AppCompatActivity implements Controls.controlListener {
    Timer gameLoopTimer = new Timer();
    int value = 0;
    Environment environment = new Environment();
    boolean started = false;
    boolean running = false;
    int savedLevelInfo;
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
        savedLevelInfo = (int) savedStuff.getSerializable("levelID");


    }

    @Override
    protected void onStart() {
        super.onStart();
        if (savedLevelInfo == 1) {
            initLevel(savedLevelInfo);
        }

        TimerTask refresh = new TimerTask() {
            @Override
            public void run() {
                // run the update function. If the player hasn't reached the goal, update the views
                if(!environment.update(Environment.player, LevelActivity.this)) { //(RelativeLayout) findViewById(R.id.level_layout));
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            updateCharacterView();
                            updateBulletsView();
                        }
                    });
                } // else, return to the main menu
                else {
                    Intent mainIntent = new Intent(LevelActivity.this, MainActivity.class);
                    startActivity(mainIntent);  // go to MainActivity with intent mainIntent
                }
            }
        };
        // set refresh rate to once every 1/30th second, starting .5 seconds after creation.
        gameLoopTimer.schedule(refresh, 500, 100);
    }


    // set the cancel
    @Override
    protected void onStop() {
        super.onStop();
        gameLoopTimer.cancel();
    }

    public void initLevel(int i) {
        // load the level and the player character into the environment
        environment.initialize(environment.levelOne(), Environment.player);
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
        updateCharacterView();
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

    public void updateBulletsView() {
        // iterate through the blocks in the environment
        for (int i = 0; i < environment.getBullets().size(); ++i) {
            boolean flag = false;
            ImageView imageView;
            Bullet tempBullet = environment.getBullets().get(i); // get the current block
            if (tempBullet.getBulletView() == null) {
                flag = true;
                imageView = new ImageView(LevelActivity.this); // create a new ImageView
                tempBullet.setBulletView(imageView); // used for deleting said view on bullet despawn
                imageView.setImageResource(R.drawable.block);          // set the "bullet" sprite to it
            } else {
                imageView = (ImageView) tempBullet.getBulletView();
            }
            // get the level layout
            RelativeLayout RL = (RelativeLayout) findViewById(R.id.level_layout);
            // if the bullet is flagged for removal
            if (tempBullet.getFlag()){
                // destroy its view and remove it from the bullet list
                imageView.setVisibility(View.INVISIBLE);
                environment.getBullets().remove(i);
                --i;
                continue;
            }
            // get the dimensions for the sprite and convert them for the device's screen
            int dimX = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    tempBullet.getDimensions().x, getResources().getDisplayMetrics());
            int dimY = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    tempBullet.getDimensions().y, getResources().getDisplayMetrics());
            // create new layout parameters for the sprite
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(dimX, dimY);
            // get the location of the block and convert the coordinates for the device's screen
            int newX = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    tempBullet.getLocation().x, getResources().getDisplayMetrics());
            int newY = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    tempBullet.getLocation().y, getResources().getDisplayMetrics());
            // set the margins for the ImageView (i.e. position on the screen)
            layoutParams.setMargins(newX, newY, 0, 0);
            // add the ImageView to the layout, or update it if it's already there.
            if (flag) {
                RL.addView(imageView, layoutParams);
            } else {
                imageView.setLayoutParams(layoutParams);
            }
        }
    }

    public void initRecordsView() {
        // TODO: add loop for additional records. Currently just doing the goal, since that's all
        // we have.
        Record tempRecord = environment.getGoal(); // get the goal record
        ImageView imageView = new ImageView(LevelActivity.this); // create a new ImageView
        imageView.setImageResource(R.drawable.record);            // set the "block" sprite to it
        // get the level layout
        RelativeLayout RL = (RelativeLayout) findViewById(R.id.level_layout);
        // get the dimensions for the sprite and convert them for the device's screen
        int dimX = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                tempRecord.getDimensions().x, getResources().getDisplayMetrics());
        int dimY = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                tempRecord.getDimensions().y, getResources().getDisplayMetrics());
        // create new layout parameters for the sprite
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(dimX, dimY);
        // get the location of the block and convert the coordinates for the device's screen
        int newX = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                tempRecord.getLocation().x, getResources().getDisplayMetrics());
        int newY = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                tempRecord.getLocation().y, getResources().getDisplayMetrics());
        // set the margins for the ImageView (i.e. position on the screen)
        layoutParams.setMargins(newX, newY, 0, 0);
        // add the ImageView to the layout
        RL.addView(imageView, layoutParams);

    }

    public void updateCharacterView() {
        // get the dimensions for the sprite and convert them for the device's screen
        ImageView imageView = (ImageView) findViewById(R.id.character_sprite);
        int dimX = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                Environment.player.getDimensions().x, getResources().getDisplayMetrics());
        int dimY = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                Environment.player.getDimensions().y, getResources().getDisplayMetrics());
        // get the location of the block and convert the coordinates for the device's screen
        int newX = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, Environment.player.getLocation().x, getResources().getDisplayMetrics());
        int newY = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, Environment.player.getLocation().y, getResources().getDisplayMetrics());
        // create new layout parameters for the sprite
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(dimX, dimY);
        layoutParams.setMargins(newX, newY, 0, 0);
        imageView.setLayoutParams(layoutParams);
    }

    public void move(int d) {
        // make the character move in direction d.
        Environment.player.horizontalMove(d);
    }

    public void jump() {
        // make the player jump if possible.
        Environment.player.jump(environment.onBlock(Environment.player));
    }

    public void shoot() {
        environment.getBullets().add(Environment.player.shoot());
    }

    public void stopCharacter() {
        Environment.player.setVelocityX(0);
    }

    public void removeView(View view) {
        view.setVisibility(View.INVISIBLE);
    }


}
