/*
 * Copyright 2016 Jo Jaquinta, TsaTsaTzu
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tsatsatzu.subwar.audio.data;

import com.tsatsatzu.subwar.audio.api.SubWarAudioAPI;
import com.tsatsatzu.subwar.audio.logic.AudioConstLogic;

// TODO: Auto-generated Javadoc
/**
 * The Class SWStateBean.
 */
public class SWStateBean
{
    
    /** The State. */
    private String    mState = AudioConstLogic.STATE_INITIAL;
    
    /** The Last reported torpedos. */
    private int       mLastReportedTorpedos = -1;
    
    /** The Last move. */
    private int       mLastMove = -1;
    
    /** The Last verb. */
    private String    mLastVerb;
    
    /** The Last args. */
    private String[]  mLastArgs;
    
    /** The Last spoken text. */
    private String    mLastSpokenText;
    
    /** The Last written text. */
    private String    mLastWrittenText;
    
    /** The Last reprompt text. */
    private String    mLastRepromptText;

    /**
     * Gets the state.
     *
     * @return the state
     */
    public String getState()
    {
        return mState;
    }

    /**
     * Sets the state.
     *
     * @param state the new state
     */
    public void setState(String state)
    {
        mState = state;
        SubWarAudioAPI.debug("Setting state="+state);
    }

    /**
     * Gets the last reported torpedos.
     *
     * @return the last reported torpedos
     */
    public int getLastReportedTorpedos()
    {
        return mLastReportedTorpedos;
    }

    /**
     * Sets the last reported torpedos.
     *
     * @param lastReportedTorpedos the new last reported torpedos
     */
    public void setLastReportedTorpedos(int lastReportedTorpedos)
    {
        mLastReportedTorpedos = lastReportedTorpedos;
    }

    /**
     * Gets the last move.
     *
     * @return the last move
     */
    public int getLastMove()
    {
        return mLastMove;
    }

    /**
     * Sets the last move.
     *
     * @param lastMove the new last move
     */
    public void setLastMove(int lastMove)
    {
        mLastMove = lastMove;
    }

    /**
     * Gets the last verb.
     *
     * @return the last verb
     */
    public String getLastVerb()
    {
        return mLastVerb;
    }

    /**
     * Sets the last verb.
     *
     * @param lastVerb the new last verb
     */
    public void setLastVerb(String lastVerb)
    {
        mLastVerb = lastVerb;
    }

    /**
     * Gets the last args.
     *
     * @return the last args
     */
    public String[] getLastArgs()
    {
        return mLastArgs;
    }

    /**
     * Sets the last args.
     *
     * @param lastArgs the new last args
     */
    public void setLastArgs(String[] lastArgs)
    {
        mLastArgs = lastArgs;
    }

    /**
     * Gets the last spoken text.
     *
     * @return the last spoken text
     */
    public String getLastSpokenText()
    {
        return mLastSpokenText;
    }

    /**
     * Sets the last spoken text.
     *
     * @param lastSpokenText the new last spoken text
     */
    public void setLastSpokenText(String lastSpokenText)
    {
        mLastSpokenText = lastSpokenText;
    }

    /**
     * Gets the last written text.
     *
     * @return the last written text
     */
    public String getLastWrittenText()
    {
        return mLastWrittenText;
    }

    /**
     * Sets the last written text.
     *
     * @param lastWrittenText the new last written text
     */
    public void setLastWrittenText(String lastWrittenText)
    {
        mLastWrittenText = lastWrittenText;
    }

    /**
     * Gets the last reprompt text.
     *
     * @return the last reprompt text
     */
    public String getLastRepromptText()
    {
        return mLastRepromptText;
    }

    /**
     * Sets the last reprompt text.
     *
     * @param lastRepromptText the new last reprompt text
     */
    public void setLastRepromptText(String lastRepromptText)
    {
        mLastRepromptText = lastRepromptText;
    }
}
