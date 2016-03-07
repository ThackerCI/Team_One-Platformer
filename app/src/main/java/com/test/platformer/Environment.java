package com.test.platformer;

/**
 * Created by Isaiah on 2/28/2016.
 */

import android.graphics.Point;
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
    private static final int GRAVITY = 5;


    // creating an empty level for level 1 and a test character
    List<Block> blocks1 = new ArrayList<Block>();
    Level l1;
    Character player = new Character(new Point(20, 20), 3, 5, 3, 5);


    public Environment() {
        blocks = new ArrayList<Block>();
        records = new ArrayList<Record>();
        bullets = new ArrayList<Bullet>();
        Block b1 = new Block(new Point(15,15), new Point(30,30));
        Block b2 = new Block(new Point(45,15), new Point(30,30));
        blocks1.add(b1);
        blocks1.add(b2);
        l1 = new Level(blocks1, new ArrayList<Record>(), new Record(new Point(0, 0), false), new Point(0, 0));
        //goal = new GoalRecord();
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
    public void update(Character c) {
        updateBullets();
        updateCharacter(c);
        updateRecords(c);
        if (iterationFlag) {
            endLevel(1);
        }
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
                Point tempVelocity = new Point(0, c.getVelocity().y);
                c.setVelocity(tempVelocity);
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
                Point tempVelocity = new Point(c.getVelocity().x, 0);
                c.setVelocity(tempVelocity);
                //FIXME::::: Set Grav immunity to zero at this point as well. !!!!!!!!!!!!!!!!!!!!
                break;
            }
        }
        // if the character isn't jumping and isn't standing on a block, start him falling.
        if (!onBlock(c) && c.getJumpTime() == 0) {
            c.getVelocity().offset(0, GRAVITY);
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
        // Get the left and right "X" boundaries of the first box
        int left1 = L1.x;
        int right1 = left1 + S1.x;
        // Get the top and bottom "Y" boundaries of the first box
        int top1 = L1.y;
        int bottom1 = top1 + S1.y;
        // Get the left and right "X" boundaries of the second box
        int left2 = L2.x;
        int right2 = left2 + S2.x;
        // Get the top and bottom "Y" boundaries of the second box
        int top2 = L2.y;
        int bottom2 = top2 + S2.y;

        // check if the top-left point of the first box is in the second box.
        if (((left2 < left1) && (left1 < right2)) && ((top2 < top1) && (top1 < bottom2))) {
            return true;
        }
        // check if the top-right point of the first box is in the second box.
        if (((left2 < right1) && (right1 < right2)) && ((top2 < top1) && (top1 < bottom2))) {
            return true;
        }
        // check if the bottom-left point of the first box is in the second box.
        if (((left2 < left1) && (left1 < right2)) && ((top2 < bottom1) && (bottom1 < bottom2))) {
            return true;
        }
        // check if the bottom-right point of the first box is in the second box.
        if (((left2 < right1) && (right1 < right2)) && ((top2 < bottom1) && (bottom1 < bottom2))) {
            return true;
        }
        // if all of the above fail, then the only way the boxes intersect is if all of the points from the second box are
        // in the first box, so we test to see if any of these points do NOT satisfy this condition, and, if so, return
        // false
        if (!(((left1 < right2) && (right2 < right1)) && ((top1 < bottom2) && (bottom2 < bottom1)))) {
            return false;
        }
        if (!(((left1 < left2) && (left2 < right1)) && ((top1 < bottom2) && (bottom2 < bottom1)))) {
            return false;
        }
        if (!(((left1 < right2) && (right2 < right1)) && ((top1 < top2) && (top2 < bottom1)))) {
            return false;
        }
        if (!(((left1 < left2) && (left2 < right1)) && ((top1 < top2) && (top2 < bottom1)))) {
            return false;
        }
        return true;

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
