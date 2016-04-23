package com.tsatsatzu.subwar.game.data;

public class SWGameDetailsBean
{
    private int mEast;
    private int mWest;
    private int mNorth;
    private int mSouth;
    private int mMaxDepth;
    private SWPositionBean  mUserPosition;
    
    public int getEast()
    {
        return mEast;
    }
    public void setEast(int east)
    {
        mEast = east;
    }
    public int getWest()
    {
        return mWest;
    }
    public void setWest(int west)
    {
        mWest = west;
    }
    public int getNorth()
    {
        return mNorth;
    }
    public void setNorth(int north)
    {
        mNorth = north;
    }
    public int getSouth()
    {
        return mSouth;
    }
    public void setSouth(int south)
    {
        mSouth = south;
    }
    public int getMaxDepth()
    {
        return mMaxDepth;
    }
    public void setMaxDepth(int maxDepth)
    {
        mMaxDepth = maxDepth;
    }
    public SWPositionBean getUserPosition()
    {
        return mUserPosition;
    }
    public void setUserPosition(SWPositionBean userPosition)
    {
        mUserPosition = userPosition;
    }
}
