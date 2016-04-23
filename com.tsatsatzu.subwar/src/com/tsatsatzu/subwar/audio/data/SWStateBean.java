package com.tsatsatzu.subwar.audio.data;

import com.tsatsatzu.subwar.audio.logic.AudioConstLogic;

public class SWStateBean
{
    private String  mState = AudioConstLogic.STATE_INITIAL;

    public String getState()
    {
        return mState;
    }

    public void setState(String state)
    {
        mState = state;
    }
}
