package com.tsatsatzu.subwar.game.data;

import java.util.HashMap;
import java.util.Map;

import com.tsatsatzu.subwar.game.logic.ai.IComputerPlayer;

public class SWGameBean
{
    private int mEast;
    private int mWest;
    private int mNorth;
    private int mSouth;
    private int mMaxDepth;
    private Map<String,SWPositionBean> mShips = new HashMap<>();
    private Map<String,IComputerPlayer> mAI = new HashMap<>();
    
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
    public Map<String, IComputerPlayer> getAI()
    {
        return mAI;
    }
    public void setAI(Map<String, IComputerPlayer> aI)
    {
        mAI = aI;
    }
}
