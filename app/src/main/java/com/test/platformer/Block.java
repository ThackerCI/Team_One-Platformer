package com.test.platformer;

import android.graphics.Point;

// Author: Aaron Trusty
// Last Modified: 3/21/16 by Isaiah Thacker
// Iteration 2
// The Block class defines objects which represent impassible walls and floors in a level.

public class Block {
    // the location of the block
    private Point location;
    // the dimensions of the block
    private Point dimensions;

    //default constructor
    public Block() {
        location = new Point(0, 0);
        dimensions = new Point(0, 0);
    }

    //overloaded constructor
    public Block(Point nLocation, Point nDimension) {
        location = new Point(nLocation.x * 30, nLocation.y * 30);
        dimensions = new Point(nDimension);
    }

    //----gets and sets----
    public Point getLocation() {
        return location;
    }

    public void setLocation(Point nLocation) {
        location.set(nLocation.x, nLocation.y);
    }

    public Point getDimensions() {
        return dimensions;
    }

    public void setDimensions(Point nDimensions) {
        dimensions.set(nDimensions.x, nDimensions.y);
    }

}
