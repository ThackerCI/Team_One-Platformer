package com.test.platformer;

// Author: Isaiah Thacker
// Last Modified: 3/21/16 by Isaiah Thacker
// Iteration 2
// The Environment class defines objects which contain all data on the current gameplay environment
// (level layout, character locations, and so forth.) It can load data from levels into its fields,
// and update its current state based on the rules of the game.

import android.graphics.Point;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;


public class Environment {
    // blocks in the environment
    private List<Block> blocks;
    // non-goal records in the environment
    private List<Record> records;
    // bullets
    private List<Bullet> bullets;
    // the goal record
    private Record goal;
    // integer used for various loops
    private int i;
    // iterationFlag used for a variety of purposes
    private boolean iterationFlag;
    // constant gravity strength
    public static final int GRAVITY = 3;
    // constant dimensions of blocks. May be set to vary later
    private final Point blockDimensions = new Point(30, 30);


    // creating a test character
    public static Character player = new Character(new Point(0, 0), new Point(30, 30), 3, 3, 3, 5);


    public Environment() {
        blocks = new ArrayList<>();
        records = new ArrayList<>();
        bullets = new ArrayList<>();
        //goal = new GoalRecord();
    }

    // defining level one
    public Level levelOne() {
        List<Block> blocks1 = blocksOne();
        Record goal1 = new Record(new Point(8, 1), false);
        Point starting1 = new Point(150, 90);

        return new Level(blocks1, new ArrayList<Record>(), goal1, starting1);
    }

    // defining the blocks of level one
    private ArrayList<Block> blocksOne() {
        ArrayList<Block> blocks1 = new ArrayList<>();
        blocks1.add(new Block(new Point(3, 2), blockDimensions));
        blocks1.add(new Block(new Point(4, 2), blockDimensions));
        blocks1.add(new Block(new Point(4, 3), blockDimensions));
        blocks1.add(new Block(new Point(4, 4), blockDimensions));
        blocks1.add(new Block(new Point(4, 5), blockDimensions));
        blocks1.add(new Block(new Point(4, 6), blockDimensions));
        blocks1.add(new Block(new Point(5, 6), blockDimensions));
        blocks1.add(new Block(new Point(2, 2), blockDimensions));
        blocks1.add(new Block(new Point(6, 6), blockDimensions));
        blocks1.add(new Block(new Point(7, 6), blockDimensions));
        blocks1.add(new Block(new Point(8, 6), blockDimensions));
        blocks1.add(new Block(new Point(9, 6), blockDimensions));
        blocks1.add(new Block(new Point(8, 5), blockDimensions));
        blocks1.add(new Block(new Point(9, 5), blockDimensions));
        blocks1.add(new Block(new Point(9, 4), blockDimensions));
        blocks1.add(new Block(new Point(10, 4), blockDimensions));
        blocks1.add(new Block(new Point(11, 4), blockDimensions));
        blocks1.add(new Block(new Point(11, 3), blockDimensions));
        blocks1.add(new Block(new Point(11, 2), blockDimensions));
        blocks1.add(new Block(new Point(11, 1), blockDimensions));
        return blocks1;
    }


    // addBullet(b) adds bullet b to the current environment.
    public void addBullet(Bullet b) {
        this.bullets.add(b);
    }

    // initialize will be used to restart the current level as well as to load a new level
    public void initialize(Level l, Character c) {
        blocks.clear();                              // remove all current blocks from this list
        blocks.addAll(l.getBlocks());                // add all of the blocks from the level to this list
        records.clear();                             // remove all current records from this list
        records.addAll(l.getRecords());              // add all of the records from the level to this list
        bullets.clear();                             // remove all bullets from this list
        goal = l.getGoal();                          // set the goal record
        Point tempPoint = new Point(l.getStartingPoint().x, l.getStartingPoint().y);
        c.reset();                                   // initialize the character's stats
        c.setLocation(tempPoint);                    // initialize the player's starting point
    }


    // update(c) calls updateBullets(), updateCharacter(c), and updateRecords(c), returns the
    // iteration flag
    public boolean update(Character c) {
        updateBullets();
        updateCharacter(c);
        updateRecords(c);
        return iterationFlag;
    }

    private void updateBullets() {
        for (i = 0; i < this.bullets.size(); ++i) {                 // iterate through all bullets
            iterationFlag = false;                                  // reset the flag
            Bullet tempBullet = this.bullets.get(i);
            Point tempLoc = new Point(tempBullet.getLocation());    // get the bullet's current location
            Point tempDims = new Point(tempBullet.getDimensions()); // get the bullet's dimensions
            int j;
            if (tempBullet.getTimeRemaining() == 0) {               // if the bullet has expired
                tempBullet.setFlag(true);                           // flag it for removal
                iterationFlag = true;                               // keep the now nonexistent bullet from moving
                break;                                              // no need to check the other blocks
            } else {
                for (j = 0; j < this.blocks.size(); ++j) {              // iterate through the environment's blocks
                    Block curBlock = this.blocks.get(j);                // get the current block
                    if (boxIntersect(tempLoc, tempDims, curBlock.getLocation(), curBlock.getDimensions())) {// if this bullet is
                        // hitting a block
                        tempBullet.setFlag(true);                       // flag it for removal
                        iterationFlag = true;                           // keep the now nonexistent bullet from moving
                        break;                                          // no need to check the other blocks
                    }
                }
            }
            if (!iterationFlag) {                                     // if the bullet wasn't removed
                tempLoc.offset(this.bullets.get(i).getVelocity().x, this.bullets.get(i).getVelocity().y);   // add the bullet's velocity to the temp
                this.bullets.get(i).setTimeRemaining(this.bullets.get(i).getTimeRemaining() - 1); // decrement time remaining
                this.bullets.get(i).setLocation(tempLoc); // set the bullet's location to the temp
            }
        }
        iterationFlag = false;                                     // Reset the flag for later use
    }

    // Currently, updateRecords(c) throws the iteration flag if c is intersecting the goal record.
    private void updateRecords(Character c) {
//    for (i = 0; i < this.records.size(); ++i){                 // iterate through all records. WILL BE ADDED WHEN
//                                                               // WE ADD MORE RECORDS
//    }
        Point goalLoc = this.goal.getLocation();
        Point goalDims = this.goal.getDimensions();
        Point charLoc = c.getLocation();
        Point charDims = c.getDimensions();
        if (boxIntersect(goalLoc, goalDims, charLoc, charDims)) {   // see if the character is intersecting the goal
            iterationFlag = true;                                    // if so, set the flag so that we can end the level.
        }
    }


    // updateCharacter(c) first moves character c horizontally if doing so would not cause the player to move into a block
    // If this condition is not satisfied, c's horizontal velocity is set to zero.
    // updateCharacter(c) then moves character c vertically if doing so would not cause the player to move into a block
    // If this condition is not satisfied, c's vertical velocity is set to zero.
    private void updateCharacter(Character c) {
        // load the character's current location into a temp variable
        Point tempLoc = new Point(c.getLocation().x, c.getLocation().y);
        // move the temp variable horizontally according to the character's velocity
        tempLoc.set(tempLoc.x + c.getVelocity().x, tempLoc.y);
        // Iterate through all blocks, seeing if this movement would cause c to intersect the block
        for (i = 0; i < this.getBlocks().size(); ++i) {
            Block tempBlock = this.getBlocks().get(i);
            // if a block would intersect c, move the temp location back and reduce c's horizontal velocity to zero.
            // Also, break.
            if (boxIntersect(tempLoc, c.getDimensions(), tempBlock.getLocation(), tempBlock.getDimensions())) {
                tempLoc.set(tempLoc.x - c.getVelocity().x, tempLoc.y);
                c.setVelocityX(0);
                break;
            }
        }
        // move the temp variable vertically according to the character's velocity
        tempLoc.offset(0, c.getVelocity().y);
        // Iterate through all blocks, seeing if this movement would cause c to intersect the block
        for (i = 0; i < this.getBlocks().size(); ++i) {
            Block tempBlock = this.getBlocks().get(i);
            // if a block would intersect c, move the temp location back and reduce c's vertical velocity to zero.
            // Also, break.
            if (boxIntersect(tempLoc, c.getDimensions(), tempBlock.getLocation(), tempBlock.getDimensions())) {
                tempLoc.offset(0, -c.getVelocity().y);
                c.setVelocityY(0);
                break;
            }
        }
        // if the character isn't jumping and isn't standing on a block, start him falling.
        if (!onBlock(c) && c.getJumpTime() == 0) {
            c.setVelocityY(GRAVITY);
        }
        // decrement c's jump time if applicable
        if (c.getJumpTime() > 0) {
            c.setJumpTime(c.getJumpTime() - 1);
        }

        c.setLocation(tempLoc);   // set the character's new location
    }

    // getGoal() returns the goal record in the current environment.
    public Record getGoal() {
        return this.goal;
    }

    // getBullets() returns the list of bullets in the current environment.
    public List<Bullet> getBullets() {
        return this.bullets;
    }

    // getRecords() returns the list of records in the current environment.
    public List<Record> getRecords() {
        return this.records;
    }

    // getBlocks() returns the list of blocks in the current environment.
    public List<Block> getBlocks() {
        return this.blocks;
    }

    // boxIntersect(L1, S1, L2, S2) returns true if the box anchored at point L1 with dimensions S1 and the box
    // anchored at point L2 with dimensions S2 overlap. (i.e. if one of the former box's corners is in the latter box,
    // or if all of the latter box's corners are in the former.)
    public static boolean boxIntersect(Point L1, Point S1, Point L2, Point S2) {
        Rect r1 = new Rect(L1.x, L1.y, L1.x + S1.x - 1, L1.y + S1.y - 1);
        Rect r2 = new Rect(L2.x, L2.y, L2.x + S2.x - 1, L2.y + S2.y - 1);
        return r1.intersect(r2);
    }

    // onBlock(c) returns true if character c is standing on a block, and false otherwise
    public boolean onBlock(Character c) {
        Point tempLoc = new Point(c.getLocation());
        tempLoc.offset(0, GRAVITY); // suppose the character falls
        // Iterate through all blocks, seeing if this movement would cause c to intersect the block
        for (i = 0; i < this.getBlocks().size(); ++i) {
            Block tempBlock = this.getBlocks().get(i);
            // if one of the blocks WOULD intersect, return true
            if (boxIntersect(tempLoc, c.getDimensions(), tempBlock.getLocation(), tempBlock.getDimensions())) {
                return true;
            }
        }
        // if c wouldn't intersect a block, return false.
        return false;
    }
}