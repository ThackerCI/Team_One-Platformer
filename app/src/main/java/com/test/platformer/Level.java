package com.test.platformer;

// Author: Isaiah Thacker
// Last Modified: 3/21/16 by Isaiah Thacker
// Iteration 2
// The Level class defines objects which record data on the various levels that can be played in
// the game (list of blocks in the level, records in the level, and character starting point.)

import android.graphics.Point;

import java.util.List;

public class Level {
    // list of blocks in the level
    private List<Block> blocks;
    // list of records in the level (minus the goal)
    private List<Record> records;
    // the level's goal record
    private Record goal;
    // the level's starting point
    private Point startingPoint;

    // constructor
    public Level(List<Block> b, List<Record> r, Record g, Point s) {
        this.blocks = b;
        this.records = r;
        this.goal = g;
        this.startingPoint = s;
    }

    public Point getStartingPoint() {
        return this.startingPoint;
    }

    public List<Record> getRecords() {
        return this.records;
    }

    public List<Block> getBlocks() {
        return this.blocks;
    }

    public Record getGoal() {
        return this.goal;
    }

}
