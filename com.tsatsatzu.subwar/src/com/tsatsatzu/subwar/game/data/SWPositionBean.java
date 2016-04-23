package com.tsatsatzu.subwar.game.data;

import java.util.ArrayList;
import java.util.List;

public class SWPositionBean
{
    private int mLongitude;
    private int mLattitude;
    private int mDepth;
    private List<SWPingBean> mSoundings = new ArrayList<>();

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
}
