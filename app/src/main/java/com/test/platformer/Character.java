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

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getStrength() {
        return strength;
    }
    /** The sprite that represents this entity */
//    protected Sprite sprite;
    /**
     * Scalar multiple effecting enemy attack
     */
    private int defense;
    private int speed;
    /**
     * velocity of character
     */
    private Point velocity;
    /**
     * Scalar multiple effecting character attack
     */
    private int strength;
    /**
     * Current health of character
     */
    private int health;
    /**
     * Max health of character
     */
    private int maxHealth;
    /** The rectangle used for this entity during collisions  resolution */
    //private Rectangle me = new Rectangle();
    /** The rectangle used for other entities during collision resolution */
    //private Rectangle him = new Rectangle();


    /**
     * Construct a Character based on a sprite image and a location.
     * <p>
     * //     * @param ref The reference to the image to be displayed for this character
     * //     * @param x   The initial x location of this Character
     * //     * @param y   The initial y location of this Character
     * <p>
     * public Character(String ref,int x,int y) {
     * this.sprite = SpriteStore.get().getSprite(ref);
     * this.x = x;
     * this.y = y;
     * }
     */

    public Character() {
        location = new Point(0, 0);
        dimensions = new Point(0, 0);
        velocity = new Point(0, 0);
        direction = 1;
    }


    /**********************Get/Set Functions*********************************************************/
    /**
     * Set the horizontal velocity of character.
     *
     * @param velocity The horizontal velocity of the character (pixels/sec)
     */
    public void setVelocity(Point velocity) {
        this.velocity = velocity;
    }

    /**
     * Get the horizontal velocity of character.
     *
     * @return The horizontal velocity of character (pixels/sec)
     */
    public Point getVelocity() {
        return velocity;
    }

    /**
     * Set the x-axis location of the character
     *
     * @param p the current x-axis location of the character
     */
    public void setLocation(Point p) {
        this.location.set(p.x, p.y);
    }

    public Point getLocation() {
        return this.location;
    }

    /**
     * Set the defensive factor of character
     *
     * @param defense the defenseive factor character
     */
    public void setDefense(int defense) {
        this.defense = defense;
    }

    /**
     * Get the Defensive stat of character
     *
     * @return The defensive stat of character
     */
    public int getDefense() {
        return defense;
    }

    /**
     * Set the strength factor of character
     *
     * @param strength The strength factor of the character
     */
    public void setStrength(int strength) {
        this.strength = strength;
    }

    /**
     * Set the current health of character
     *
     * @param health Current health of character
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Get the current health of character
     *
     * @return the current health of character
     */
    public int getHealth() {
        return health;
    }

    /**
     * Set the max health of the character
     *
     * @param maxHealth The max health of the character
     */
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    /**
     * Get the max health of the character
     *
     * @return The max health of the character
     */
    public int getMaxHealth() {
        return maxHealth;
    }

    public void setDimensions(Point p) {
        dimensions.set(p.x, p.y);
    }

    public Point getDimensions() {
        return dimensions;
    }

/**************************Movement Functions*********************************************************/
    /**
     * Request that the character move itself based on a certain amount
     * of time passing. This will also account for jump.
     *
     * @param delta The amount of time that has passed in milliseconds
     */
//    public void horizontalMove(long delta) {
//        // update the location of the entity based on move speeds
//        this.location.x += (delta * this.velocity.x) / 1000;
//    }

    /**
     * Checks if verticalVelocity is positive is positive(i.e. player wants character to jump)
     * If positive, Checks to see if character is on a block
     * If on a block, goes into timed loop causing character to move upwards temporarily
     * else verticalVelocity is negative, gravity stays into being
     *
     * @param delta The amount of time that has passed in milliseconds since last update
     */
//    public void verticalMove(long delta) {
//        if (this.verticalVelocity > 0) {
//            if (this.onBlock == true) {
//                initialJump = System.currentTimeMillis();
//                while (System.currentTimeMillis() - this.initialJump < jumpTime) {
//                    this.location.y += (delta * this.verticalVelocity) / 1000;
//                }
//                this.verticalVelocity = -this.verticalVelocity;
//                this.location.y += (delta * this.verticalVelocity) / 1000;
//            }
//        } else {
//            this.location.y += (delta * this.verticalVelocity) / 1000;
//        }
//    }
    /**
     *
     *
     public void tryToFire() {
     // check that we have waiting long enough to fire
     if (System.currentTimeMillis() - lastShot < shotCoolDown) {
     return;
     }

     // if we waited long enough, create the friendly shot, and record the time.
     lastShot = System.currentTimeMillis();
     FriendlyBullet shot = new FriendlyBullet(this,"sprites/shot.gif",this.getX()+10,this.getY()-30);
     Environmnent.add(shot);
     }


     /**
     *
     * @param other
     * @return
     *
    public boolean collidesWith(Block other) {
    me.setBounds((int) x,(int) y,sprite.getWidth(),sprite.getHeight());
    him.setBounds((int) other.x, (int) other.y, other.sprite.getWidth(), other.sprite.getHeight());
    return me.intersects(him);
    }


    /**
     * Notification that the character has collided with  something
     *
     * @param other The entity with which the ship has collided
     *
    public void collidedWith(Block other) {
    this.onBlock = true;
    }
     */
}
