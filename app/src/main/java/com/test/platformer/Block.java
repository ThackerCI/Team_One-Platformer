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
public class Block
{
    int vertDimension;
    int horzDimension;
    //location (x,y)
    int xLoc;
    int yLoc;

    //default constructor
    public Block()
    {
        vertDimension = 0;
        horzDimension = 0;
        xLoc = 0;
        yLoc = 0;
    }

    //overloaded constructor
    public Block(int nVertDimension, int nHorzDimension, int nXLoc, int nYLoc)
    {
        vertDimension = nVertDimension;
        horzDimension = nHorzDimension;
        xLoc = nXLoc;
        yLoc = nYLoc;
    }

    //----gets and sets----
    public int getHorzDimension()
    {
        return horzDimension;
    }

    public void setHorzDimension(int nHorzDimension)
    {
        horzDimension = nHorzDimension;
    }

    public int getVertDimension()
    {
        return vertDimension;
    }

    public void setVertDimension(int nVertDimension)
    {
        vertDimension = nVertDimension;
    }

    public int getXLoc()
    {
        return xLoc;
    }

    public void setXLoc(int nXLoc)
    {
        xLoc = nXLoc;
    }

    public int getYLoc()
    {
        return yLoc;
    }

    public void setYLoc(int nYLoc)
    {
        yLoc = nYLoc;
    }

}
