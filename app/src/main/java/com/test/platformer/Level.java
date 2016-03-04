package com.test.platformer;

import android.graphics.Point;

/**
 * Created by Isaiah on 2/28/2016.
 */

import java.util.List;

public class Level {
    private List<Block> blocks;
    private List<Record> records;
    private Record goal;
    private Point startingPoint;

    public Level(List<Block> b, List<Record> r, Record g, Point s) {
        this.blocks = b;
        this.records = r;
        this.goal = g;
        this.startingPoint = s;
        return;
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
