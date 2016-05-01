package com.tsatsatzu.subwar.game.data;

import java.util.ArrayList;
import java.util.List;

public class SWPositionBean
{
    private int mLongitude;
    private int mLattitude;
    private int mDepth;
    private List<SWPingBean> mSoundings = new ArrayList<>();
    private long    mLastMove;
    private int mTorpedoes;
    private int mHits;

    // utils
    @Override
    public String toString()
    {
        return mLongitude+","+mLattitude+","+mDepth;
    }
    
    // getters and setters
    
    public List<SWPingBean> getSoundings()
    {
        return mSoundings;
    }

    public void setSoundings(List<SWPingBean> soundings)
    {
        mSoundings = soundings;
    }

    public int getLongitude()
    {
        return mLongitude;
    }

    public void setLongitude(int longitude)
    {
        mLongitude = longitude;
    }

    public int getLattitude()
    {
        return mLattitude;
    }

    public void setLattitude(int lattitude)
    {
        mLattitude = lattitude;
    }

    public int getDepth()
    {
        return mDepth;
    }

    public void setDepth(int depth)
    {
        mDepth = depth;
    }

    public long getLastMove()
    {
        return mLastMove;
    }

    public void setLastMove(long lastMove)
    {
        mLastMove = lastMove;
    }

    public int getTorpedoes()
    {
        return mTorpedoes;
    }

    public void setTorpedoes(int torpedoes)
    {
        mTorpedoes = torpedoes;
    }

    public int getHits()
    {
        return mHits;
    }

    public void setHits(int hits)
    {
        mHits = hits;
    }
}
