package com.test.platformer;

/**
 * A character is what will be controlled by the player.The character
 * class is responsible for resolving and appropriately outputting
 * player inputs.
 *
 * @author John Hale
 * Help from Kevin Glass and Dr. Jerry Perez's Space Invader's project.
 */

import android.graphics.Point;
public class Character {

    //Cooldown time after each shot
    private int shotCoolDown;
    //max cooldown time after each shot
    private final int maxShotCoolDown = 10;
    //location of the character
    private Point location;
    //Time the character began his jump
    private double initialJump;
    //Time character will be moving vertically in case of jump
    private int jumpTime;
    //maximum time the character will move vertically in case of jump
    private final int maxJumpTime = 10;
    // direction is -1 for left, 1 for right
    private int direction;
    //dimensions of the character
    private Point dimensions;
    //Scalar multiple effecting enemy attack
    private int defense;
    //Max Velocity of the character = speed
    private int speed;
    //velocity of character
    private Point velocity;
    //Scalar multiple effecting character attack
    private int strength;
    //Current health of character
    private int health;
    //Max health of character
    private int maxHealth;

    public Character() {
        location = new Point(0, 0);
        dimensions = new Point(0, 0);
        velocity = new Point(0, 0);
        direction = 1;
    }

    /**Initializes new Character*/
    public Character(Point location, Point dimensions, int strength,
                     int speed, int defense, int maxHealth) {
        this.setLocation(location);
        this.setDimensions(dimensions);
        this.setStrength(strength);
        this.setSpeed(speed);
        this.setDefense(defense);
        this.setMaxHealth(maxHealth);
    }
    /**Resets Character's health and velocity*/
    public void reset() {
        this.setHealth(this.getMaxHealth());
        this.setDirection(1);
        Point velocity = new Point(0, 0);
        this.setVelocity(velocity);
    }

    /**********************Get/Set Functions For Various Variables************************************/

    public void setVelocity(Point velocity) {this.velocity = velocity;}
    public Point getVelocity() {return velocity;}

    public void setVelocityX(int x) {this.velocity.x = x;}
    public void setVelocityY(int y) {this.velocity.y = y;}



    public void setLocation(Point p) {
        this.location.set(p.x, p.y);
    }
    public Point getLocation() {
        return this.location;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }
    public int getDefense() {
        return defense;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }
    public int getStrength(){return this.strength;}

    public void setHealth(int health) {
        this.health = health;
    }
    public int getHealth() {
        return health;
    }


    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
    public int getMaxHealth() {
        return maxHealth;
    }

    public void setDimensions(Point p) {
        dimensions.set(p.x, p.y);
    }
    public Point getDimensions() {
        return dimensions;
    }

    public int getShotCoolDown() {
        return shotCoolDown;
    }
    public void setShotCoolDown(int shotCoolDown) {
        this.shotCoolDown = shotCoolDown;
    }

    public int getMaxShotCoolDown() {
        return maxShotCoolDown;
    }

    public double getInitialJump() {
        return initialJump;
    }
    public void setInitialJump(double initialJump) {
        this.initialJump = initialJump;
    }

    public int getJumpTime() {
        return jumpTime;
    }
    public void setJumpTime(int jumpTime) {
        this.jumpTime = jumpTime;
    }

    public int getMaxJumpTime() {
        return maxJumpTime;
    }

    public int getDirection() {
        return direction;
    }
    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {this.speed = speed;}

/**************************Movement Functions*********************************************************/
    /**
     * Request that the character move itself based on a certain amount
     * of time passing. This will also account for jump.
     *
     * @param delta The amount of time that has passed in milliseconds
     */
    public void horizontalMove(int direction) {
        //update the location of the entity based on move speeds
        this.setVelocityX(direction * this.getSpeed());
    }


    public void jump(boolean canJump) {
        if (canJump) {

            this.setVelocityY(5);
            this.setJumpTime(this.maxJumpTime);
        }
    }

    public Bullet shoot() {
        Point leftCorner = this.getLocation();
        int x = leftCorner.x;
        int y = leftCorner.y;
        Point dim = this.getDimensions();
        int h = dim.y;
        int w = dim.x;
        h = h/2;
        w = w/2;
        Point vel = new Point(5,0)
        Point center = new Point(x + w,y - h);
        Bullet shot = new Bullet(center, this.getStrength(), vel );
        return shot;
    }
}