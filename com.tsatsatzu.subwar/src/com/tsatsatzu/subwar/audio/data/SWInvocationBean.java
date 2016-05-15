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

import com.tsatsatzu.subwar.audio.logic.AudioConstLogic;
import com.tsatsatzu.subwar.game.data.SWGameDetailsBean;
import com.tsatsatzu.subwar.game.data.SWUserBean;
import com.tsatsatzu.utils.obj.StringUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class SWInvocationBean.
 */
public class SWInvocationBean
{
    
    /** The Session. */
    private SWSessionBean   mSession;
    
    /** The State. */
    private SWStateBean     mState;
    
    /** The User. */
    private SWUserBean        mUser;
    
    /** The Game. */
    private SWGameDetailsBean   mGame;
    
    /** The Spoken text. */
    private String              mSpokenText = "";
    
    /** The Written text. */
    private String              mWrittenText = "";
    
    /** The Reprompt text. */
    private String              mRepromptText = "";
    
    /** The Large image. */
    private String              mLargeImage;
    
    /** The Small image. */
    private String              mSmallImage;
    
    /** The End session. */
    private boolean             mEndSession = false;

    // utility functions

    /**
     * Adds the sound.
     *
     * @param mp3 the mp3
     */
    public void addSound(String mp3)
    {
        addSpoken("<audio src=\""+mp3+"\"/>");
    }
    
    /**
     * Adds the pause.
     */
    public void addPause()
    {
        addSpoken(AudioConstLogic.SOUND_PAUSE);
        addWrittenLine("");
    }
    
    /**
     * Adds the text.
     *
     * @param txt the txt
     */
    public void addText(String txt)
    {
        if (txt == null)
            return;
        if (getUser().getSubName() != null)
        {
            txt = txt.replace("{ship}", "the "+getUser().getSubName());
            txt = txt.replace("{Ship}", "The "+getUser().getSubName());
        }
        else
        {
            txt = txt.replace("{ship}", "your ship");
            txt = txt.replace("{Ship}", "Your ship");
        }
        if (getUser().getUserName() != null)
            txt = txt.replace("{captain}", getUser().getTitle()+" "+getUser().getUserName());
        else
            txt = txt.replace("{captain}", getUser().getTitle());
        addSpoken(txt);
        addWritten(txt);
    }
    
    /**
     * Adds the spoken.
     *
     * @param txt the txt
     */
    public void addSpoken(String txt)
    {
        mSpokenText = StringUtils.trimSpaces(mSpokenText) + " " + StringUtils.trimSpaces(txt);
    }
    
    /**
     * Adds the written.
     *
     * @param txt the txt
     */
    public void addWritten(String txt)
    {
        mWrittenText = StringUtils.trimSpaces(mWrittenText) + " " + StringUtils.trimSpaces(txt);
    }
    
    /**
     * Adds the written line.
     *
     * @param txt the txt
     */
    public void addWrittenLine(String txt)
    {
        mWrittenText += txt;
        mWrittenText += "\n";
    }
    
    /**
     * Adds the reprompt.
     *
     * @param txt the txt
     */
    public void addReprompt(String txt)
    {
        if (txt == null)
            return;
        if (getUser().getSubName() != null)
            txt = txt.replace("{ship}", getUser().getSubName());
        if (getUser().getUserName() != null)
            txt = txt.replace("{user}", getUser().getUserName());
        mRepromptText = StringUtils.trimSpaces(mRepromptText) + " " + StringUtils.trimSpaces(txt);
    }
    
    // getters and setters
    
    /**
     * Gets the session.
     *
     * @return the session
     */
    public SWSessionBean getSession()
    {
        return mSession;
    }

    /**
     * Sets the session.
     *
     * @param session the new session
     */
    public void setSession(SWSessionBean session)
    {
        mSession = session;
    }

    /**
     * Gets the game.
     *
     * @return the game
     */
    public SWGameDetailsBean getGame()
    {
        return mGame;
    }

    /**
     * Sets the game.
     *
     * @param game the new game
     */
    public void setGame(SWGameDetailsBean game)
    {
        mGame = game;
    }

    /**
     * Gets the user.
     *
     * @return the user
     */
    public SWUserBean getUser()
    {
        return mUser;
    }

    /**
     * Sets the user.
     *
     * @param user the new user
     */
    public void setUser(SWUserBean user)
    {
        mUser = user;
    }

    /**
     * Gets the spoken text.
     *
     * @return the spoken text
     */
    public String getSpokenText()
    {
        return mSpokenText;
    }

    /**
     * Sets the spoken text.
     *
     * @param spokenText the new spoken text
     */
    public void setSpokenText(String spokenText)
    {
        mSpokenText = spokenText;
    }

    /**
     * Gets the written text.
     *
     * @return the written text
     */
    public String getWrittenText()
    {
        return mWrittenText;
    }

    /**
     * Sets the written text.
     *
     * @param writtenText the new written text
     */
    public void setWrittenText(String writtenText)
    {
        mWrittenText = writtenText;
    }

    /**
     * Gets the reprompt text.
     *
     * @return the reprompt text
     */
    public String getRepromptText()
    {
        return mRepromptText;
    }

    /**
     * Sets the reprompt text.
     *
     * @param repromptText the new reprompt text
     */
    public void setRepromptText(String repromptText)
    {
        mRepromptText = repromptText;
    }

    /**
     * Gets the large image.
     *
     * @return the large image
     */
    public String getLargeImage()
    {
        return mLargeImage;
    }

    /**
     * Sets the large image.
     *
     * @param largeImage the new large image
     */
    public void setLargeImage(String largeImage)
    {
        mLargeImage = largeImage;
    }

    /**
     * Gets the small image.
     *
     * @return the small image
     */
    public String getSmallImage()
    {
        return mSmallImage;
    }

    /**
     * Sets the small image.
     *
     * @param smallImage the new small image
     */
    public void setSmallImage(String smallImage)
    {
        mSmallImage = smallImage;
    }

    /**
     * Checks if is end session.
     *
     * @return true, if is end session
     */
    public boolean isEndSession()
    {
        return mEndSession;
    }

    /**
     * Sets the end session.
     *
     * @param endSession the new end session
     */
    public void setEndSession(boolean endSession)
    {
        mEndSession = endSession;
    }

    /**
     * Gets the state.
     *
     * @return the state
     */
    public SWStateBean getState()
    {
        return mState;
    }

    /**
     * Sets the state.
     *
     * @param state the new state
     */
    public void setState(SWStateBean state)
    {
        mState = state;
    }
    
}
