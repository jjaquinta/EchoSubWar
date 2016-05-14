package com.tsatsatzu.subwar.audio.data;

import com.tsatsatzu.subwar.audio.api.SubWarAudioAPI;
import com.tsatsatzu.subwar.audio.logic.AudioConstLogic;

public class SWStateBean
{
    private String    mState = AudioConstLogic.STATE_INITIAL;
    private int       mLastReportedTorpedos = -1;
    private int       mLastMove = -1;
    private String    mLastVerb;
    private String[]  mLastArgs;
    private String    mLastSpokenText;
    private String    mLastWrittenText;
    private String    mLastRepromptText;

    public String getState()
    {
        return mState;
    }

    public void setState(String state)
    {
        mState = state;
        SubWarAudioAPI.debug("Setting state="+state);
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

    public String getLastVerb()
    {
        return mLastVerb;
    }

    public void setLastVerb(String lastVerb)
    {
        mLastVerb = lastVerb;
    }

    public String[] getLastArgs()
    {
        return mLastArgs;
    }

    public void setLastArgs(String[] lastArgs)
    {
        mLastArgs = lastArgs;
    }

    public String getLastSpokenText()
    {
        return mLastSpokenText;
    }

    public void setLastSpokenText(String lastSpokenText)
    {
        mLastSpokenText = lastSpokenText;
    }

    public String getLastWrittenText()
    {
        return mLastWrittenText;
    }

    public void setLastWrittenText(String lastWrittenText)
    {
        mLastWrittenText = lastWrittenText;
    }

    public String getLastRepromptText()
    {
        return mLastRepromptText;
    }

    public void setLastRepromptText(String lastRepromptText)
    {
        mLastRepromptText = lastRepromptText;
    }
}
