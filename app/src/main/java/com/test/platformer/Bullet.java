package com.test.platformer;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import android.graphics.Point;
import android.view.View;

/**
 * @author Aaron Trusty
 */
public class Bullet {
    private View bulletView;
    private Point location;
    private int power;
    private Point velocity;
    private int timeRemaining;
    private boolean flag = false;
    private final int duration = 20;
    //constant dimensions for bullet. May be changed later
    private final Point dimensions = new Point(3, 3);

    //default constructor. Will include
    //  package from player class that will
    //      make default bullet come from the players 
    //          position plus a few pixels to make it appear
    //              that the bullet is coming from the weapon.
    public Bullet() {
        location = new Point(0, 0);
        power = 0;
        velocity = new Point(0, 0);
        timeRemaining = 0;
    }

    //overloaded constructor
    public Bullet(Point coords, int nPower, Point nVelocity) {
        location = new Point(coords);
        power = nPower;
        velocity = new Point(nVelocity);
        timeRemaining = duration;
    }

    public Bullet(Bullet b) {
        location = new Point(b.getLocation());
        power = b.getPower();
        velocity = new Point(b.getVelocity());
        timeRemaining = b.getTimeRemaining();
    }


    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }


    public View getBulletView() {
        return bulletView;
    }

    public void setBulletView(View bulletView) {
        this.bulletView = bulletView;
    }

    public int getTimeRemaining() {
        return timeRemaining;
    }

    public void setTimeRemaining(int timeRemaining) {
        this.timeRemaining = timeRemaining;
    }

    public int getDuration() {
        return duration;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int nPower) {
        power = nPower;
    }

    public Point getVelocity() {
        return velocity;
    }

    public void setVelocity(Point nVelocity) {
        velocity.set(nVelocity.x, nVelocity.y);
    }


    public Point getLocation() {
        return location;
    }

    //set location
    public void setLocation(Point nLocation) {
        location.set(nLocation.x, nLocation.y);
    }

    public Point getDimensions() {
        return dimensions;
    }


    //toString for class
    public String toString() {
        return "The bullet is currently at position (" + location.x + "," + location.y + ").\n";
    }


}
