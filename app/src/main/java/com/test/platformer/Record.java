package com.test.platformer;//  Olutayo Elelu
//  Last Edited: 02.29.16
//  Record Breaker - Records Class
//  Iteration 2

//- Location (Point): Defined by our game mechanics. For now, a pair of ints serving as x and y coordinate. Functions include getLocation and setLocation.
//- Sprite (Picture File reference): Color coordinated for different effects/Goal. Functions include getSprite.
//- Track (MP3/Music File reference): Information relating to the actual MP3 of the object. Can be pulled by the game engine to play it. Functions include getTrack, playTrack
//- Goal(Boolean): is the record a goal record or not? Used mainly by the main game engine to know when to end the level. Functions include isGoal and setGoal
//- Collect(Boolean): Has the record been collected? Functions include isCollected

import android.graphics.Point;

public class Record {
    private Point location;
    private String sprite;
    //    private boolean goal;
    private boolean collected;
    private final Point dimensions = new Point(40, 40);

    public Record(Point p, boolean c) {
        location = p;
        collected = c;
    }

    public Record(Record R) {
        this.location = new Point(R.location);
        this.collected = R.collected;
    }

    //Location
    //Location (int x, int y): Defined by our game mechanics. For now, a pair of ints serving as x and y coordinate.
    //Functions include getLocation and setLocation.
    //represented as an array w/ 2 values
    public void setLocation(Point p) {
        location.x = p.x;
        location.y = p.y;
    }

    public Point getLocation() {
        return location;
    }

    //Goal(Boolean): is the record a goal record or not?
    //Collect(Boolean): Has the record been collected? Functions include isCollected
    //Used mainly by the main game engine to know when to end the level. Functions include isGoal and setGoal

//    public boolean isGoal() {
//        return goal;
//    }

//    public void setGoal() {
//        goal = true;
//    }

    public boolean isCollected() {
        return collected;
    }

    public void collectRecord() {
        collected = true;
    }

    public Point getDimensions() {
        return dimensions;
    }
   /*
   //- Sprite (Picture File reference): Color coordinated for different effects/Goal. Functions include getSprite.
   
   public void getSprite()
   {
   
   }
   
   
   
   
   //Track-related functions will be completed next iteration
   */

    public void printRecord() {
        System.out.println("Location is " + getLocation());
//        System.out.println("Is this a goal record: " + isGoal());
        System.out.println("Has it been collected: " + isCollected());
    }
}