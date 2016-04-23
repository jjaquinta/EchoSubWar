package com.tsatsatzu.subwar.game.data;

import java.util.HashMap;
import java.util.Map;

public class SWGameBean
{
    private int mEast;
    private int mWest;
    private int mNorth;
    private int mSouth;
    private int mMaxDepth;
    private Map<String,SWPositionBean> mShips = new HashMap<>();
    
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
    public Map<String, SWPositionBean> getShips()
    {
        return mShips;
    }
    public void setShips(Map<String, SWPositionBean> ships)
    {
        mShips = ships;
    }
}
