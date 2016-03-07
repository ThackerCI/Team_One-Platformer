package com.test.platformer;

import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Character player;
    Level l1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    public void start(View view) {
        int levelID = 1;

        Intent levelIntent = new Intent(MainActivity.this, LevelActivity.class); //context = currentactivity.this // next, destination.class
        levelIntent.putExtra("levelID", levelID); // arbitrary name, object to be saved
        startActivity(levelIntent);  // go to BActivity with intent bIntent
    }
}
