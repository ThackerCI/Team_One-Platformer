package com.test.platformer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aaron Trusty
 */
public class Bullet
{
    private int xCoord;
    private int yCoord;
    private int power;
    private int speed;
    private double timeRemaining;
    private int duration;
    //default constructor. Will include
    //  package from player class that will
    //      make default bullet come from the players
    //          position plus a few pixels to make it appear
    //              that the bullet is coming from the weapon.
    public Bullet()
    {
        xCoord = 0;
        yCoord = 0;
        power = 0;
        speed = 0;
        timeRemaining = 0;
        duration = 0;
    }

    //overloaded constructor
    public Bullet(int nXCoord, int nYCoord,int nPower,int nSpeed, int nTimeRemaining, int nDuration)
    {
        xCoord = nXCoord;
        yCoord = nYCoord;
        power = nPower;
        speed = nSpeed;
        timeRemaining = nTimeRemaining;
        duration = nDuration;
    }
    public int getPower()
    {
        return power;
    }

    public void setPower(int nPower)
    {
        power = nPower;
    }
    public int getSpeed()
    {
        return speed;
    }
    public void setSower(int nSpeed)
    {
        speed = nSpeed;
    }


    //x position of bullet
    public int getXCoord()
    {
        return xCoord;
    }

    //y position of bullet
    public int getYCoord()
    {
        return yCoord;
    }

    //set x position
    public void setXCoord(int updteXCoord)
    {
        xCoord = updteXCoord;
    }

    //set y position
    public void setYCoord(int updteYCoord) {
        yCoord = updteYCoord;
    }

    //toString for class
    public String toString()
    {
        return "The bullet is currently at poisiton (" + getYCoord() + "," + getXCoord() + ").\n";
    }

    //this method is in the UML under this class but it needs to be inlevel class
    public void despawn()
    {

    }


}
