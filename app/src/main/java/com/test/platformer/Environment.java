package com.test.platformer;

/**
 * Created by Isaiah on 2/28/2016.
 */

import android.graphics.Point;
import android.graphics.Rect;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class Environment {

    private List<Block> blocks;
    private List<Record> records;
    private List<Bullet> bullets;
    private Record goal;
    private int i;
    private boolean iterationFlag;
    private static final int GRAVITY = 1;


    // creating an empty level for level 1 and a test character
    public static Character player = new Character(new Point(0, 0), new Point(30,30), 3, 5, 3, 5);


    public Environment() {
        blocks = new ArrayList<Block>();
        records = new ArrayList<Record>();
        bullets = new ArrayList<Bullet>();
        //goal = new GoalRecord();
    }

    public Level levelOne(){
        List<Block> blocks1 = blocksOne();
        Record goal1 = new Record(new Point(240, 30), false);
        Point starting1 = new Point(120,60);

        return new Level(blocks1, new ArrayList<Record>(), goal1, starting1);
    }

    public ArrayList<Block> blocksOne(){
        ArrayList<Block> blocks1 = new ArrayList<Block>();
        blocks1.add(new Block(new Point(0,0), new Point(30,30)));
        blocks1.add(new Block(new Point(30,0), new Point(30,30)));
        blocks1.add(new Block(new Point(30,30), new Point(30,30)));
        blocks1.add(new Block(new Point(60,30), new Point(30,30)));
        blocks1.add(new Block(new Point(60,60), new Point(30,30)));
        blocks1.add(new Block(new Point(60,90), new Point(30,30)));
        blocks1.add(new Block(new Point(60,120), new Point(30,30)));
        blocks1.add(new Block(new Point(90,120), new Point(30,30)));
        blocks1.add(new Block(new Point(120,120), new Point(30,30)));
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
        c.setLocation(tempPoint);                    // initialize the player's starting point
        // FIXME: UNCOMMENT WHEN FUNCTION c.reset();                                   // initialize the character's stats
    }


    // update(c) calls updateBullets(), updateCharacter(c), and updateRecords(c), then calls endLevel(1) if the
    // iteration flag is thrown.
    public boolean update(Character c) {
        updateBullets();
        updateCharacter(c);
        updateRecords(c);
        return iterationFlag;
    }

    private void endLevel(int k) {
        // FIXME: Return to the main menu activity
    }

    private void updateBullets() {
        for (i = 0; i < this.bullets.size(); ++i) {                 // iterate through all bullets
            iterationFlag = false;                                   // reset the flag
            Point tempLoc = new Point(this.bullets.get(i).getLocation());    // get the bullet's current location
            Point tempDims = new Point(this.bullets.get(i).getDimensions()); // get the bullet's dimensions
            int j;
            for (j = 0; j < this.blocks.size(); ++j) {                // iterate through the environment's blocks
                Block curBlock = this.blocks.get(j);                   // get the current block
                if (boxIntersect(tempLoc, tempDims, curBlock.getLocation(), curBlock.getDimensions())) {// if this bullet is
                    // hitting a block
                    this.bullets.remove(i);                              // delete the bullet
                    --i;                                                 // subtract from i since an element has been removed
                    iterationFlag = true;                                // keep the now nonexistent bullet from moving
                    break;                                               // no need to check the other blocks
                }
            }
            if (!iterationFlag) {                                     // if the bullet wasn't removed
                tempLoc.offset(this.bullets.get(i).getVelocity().x, this.bullets.get(i).getVelocity().y);   // add the bullet's velocity to the temp
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
        if (onBlock(c)){
            c.setJumpTime(0);
        }
        // if the character isn't jumping and isn't standing on a block, start him falling.
        if (!onBlock(c) && c.getJumpTime() == 0) {
            c.getVelocity().offset(0, GRAVITY);
        }
        if (c.getJumpTime() > 0){
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
        Rect r1 = new Rect(L1.x, L1.y, L1.x+S1.x-1, L1.y+S1.y-1);
        Rect r2 = new Rect(L2.x, L2.y, L2.x+S2.x-1, L2.y+S2.y-1);
        return r1.intersect(r2);
    }

    public boolean onBlock(Character c) {
        Point tempLoc = new Point(c.getLocation());
        tempLoc.offset(0, -GRAVITY); // suppose the character falls
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
