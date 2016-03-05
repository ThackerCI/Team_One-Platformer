package com.example.h4l3st0rm.willitbeinjava;
import android.graphics.Rect;
/**
 * A character is what will be controlled by the player.The character
 * class is responsible for resolving and appropriately outputting
 * player inputs.
 *
 * @author John Hale
 * Help from Kevin Glass and Dr. Jerry Perez's Space Invader's project.
 */
public class Character {

    /**Cooldown time after each shot*/
    private double shotCoolDown;
    /**Time of last shot fired*/
    private double lastShot;
    /**Current Location of character x-axis*/
    private double x;
    /**Current Location of character y-axis*/
    private double y;
    /**Start time of character jump*/
    private double initialJump;
    /**Time character will be moving vertically in case of jump*/
    private int jumpTime;
    /**The velocity of gravity downwards, or upward jump*/
    private int verticalVelocity;
    /**True if character is on a block object*/
    private boolean onBlock;
    /** The sprite that represents this entity */
//    protected Sprite sprite;
    /**Scalar multiple effecting enemy attack*/
    private int defense;
    /**Horizontal velocity of character*/
    private int velocity;
    /**Scalar multiple effecting character attack*/
    private int strength;
    /**Current health of character*/
    private int health;
    /**Max health of character*/
    private int maxHealth;
    /** The rectangle used for this entity during collisions  resolution */
    private Rect me;
    /** The rectangle used for other entities during collision resolution */
    private Rect him;
    /** The size of the character, Currently set at 200*/
    private static final int SQUARE_SIDE_LENGTH = 200;


    /**
     * Construct a Character based on a sprite image and a location.
     *
     * @param ref The reference to the image to be displayed for this character
     * @param x The initial x location of this Character
     * @param y The initial y location of this Character
     */
    /**
     public Character(String ref,int x,int y) {
     this.sprite = SpriteStore.get().getSprite(ref);
     this.x = x;
     this.y = y;
     }
     */



    /**********************Get/Set Functions*********************************************************/
    /**
     * Set the horizontal velocity of character.
     *
     * @param velocity The horizontal velocity of the character (pixels/sec)
     * */
    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    /**
     * Get the horizontal velocity of character.
     *
     * @return The horizontal velocity of character (pixels/sec)
     * */
    public int getVelocity() {
        return velocity;
    }

    /**
     * Set the x-axis location of the character
     *
     * @param x The current x-axis location of the character
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Get the current x-axis location of the character
     *
     * @return The current x-axis location of the character
     */
    public double getX() {
        return x;
    }
    /**
     * Set the y-axis location of the character
     *
     * @param y The current x-axis location of the character
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Get the current y-axis location of the character
     *
     * @return The current y-axis location of the character
     */
    public double getY() {
        return y;
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
    public int getMaxHealth(){
        return maxHealth;
    }

/**************************Movement Functions*********************************************************/
    /**
     * Request that the character move itself based on a certain ammount
     * of time passing. This will also account for jump.
     *
     * @param delta The ammount of time that has passed in milliseconds
     */
    public void horizontalMove(long delta) {
        // update the location of the entity based on move speeds
        this.x += (delta * this.velocity) / 1000;
    }

    /**
     * Checks if verticalVelocity is positive is positive(i.e. player wants character to jump)
     * If positive, Checks to see if character is on a block
     * If on a block, goes into timed loop causing character to move upwards temporarily
     * else verticalVelocity is negative, gravity stays into being
     * @param delta The amount of time that has passed in milliseconds since last update
     */
    public void verticalMove(long delta) {
        if (this.verticalVelocity > 0) {
            if (this.onBlock) {
                initialJump = System.currentTimeMillis();
                while (System.currentTimeMillis() - this.initialJump < jumpTime) {
                    this.y += (delta * this.verticalVelocity) / 1000;
                }
                this.verticalVelocity = -this.verticalVelocity;
                this.y += (delta * this.verticalVelocity) / 1000;
            }
        }
        else {
            this.y += (delta * this.verticalVelocity) /1000;
        }
    }
/**
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
 }
 */

}