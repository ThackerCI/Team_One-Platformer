package com.test.platformer;

import android.graphics.Point;

/**
 * @author Aaron Trusty
 */
// last edited: 03/07/16
public class Block {
    // the location of the block
    Point location;
    // the dimensions of the block
    Point dimensions;

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
