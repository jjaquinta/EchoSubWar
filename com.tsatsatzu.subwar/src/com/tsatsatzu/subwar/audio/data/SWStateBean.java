package com.tsatsatzu.subwar.audio.data;

import com.tsatsatzu.subwar.audio.logic.AudioConstLogic;

public class SWStateBean
{
    private String  mState = AudioConstLogic.STATE_INITIAL;
    private int mLastReportedTorpedos = -1;
    private int mLastMove = -1;

    public String getState()
    {
        return mState;
    }

    public void setState(String state)
    {
        mState = state;
    }

    public int getLastReportedTorpedos()
    {
        return mLastReportedTorpedos;
    }

    public void setLastReportedTorpedos(int lastReportedTorpedos)
    {
        mLastReportedTorpedos = lastReportedTorpedos;
    }

    public int getLastMove()
    {
        return mLastMove;
    }

    public void setLastMove(int lastMove)
    {
        mLastMove = lastMove;
    }
}
