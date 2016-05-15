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
package com.tsatsatzu.subwar.game.data;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.tsatsatzu.subwar.game.logic.dynamo.DynamoUtils;
import com.tsatsatzu.utils.obj.StringUtils;

/**
 * The Class SWUserBean.
 * Persistent details about a human user.
 */
public class SWUserBean
{
    
    /** The User id. */
    // primary key
    private String  mUserID;
    
    /** The User name. */
    // user statistics
    private String  mUserName;
    
    /** The Title. */
    private String  mTitle;
    
    // book keeping

    /** The Last interaction. */
    private long    mLastInteraction;
    
    /** The Number of interactions. */
    private int     mNumberOfInteractions;
    
    /** The Number of sessions. */
    private int     mNumberOfSessions;
    
    /** The Number of games. */
    private int     mNumberOfGames;
    
    /** The Number of kills. */
    private int     mNumberOfKills;
    
    /** The Number of shots. */
    private int     mNumberOfShots;
    
    // ship statistics

    /** The Sub name. */
    private String  mSubName;
    
    /** The Max torpedoes. */
    private int     mMaxTorpedoes;
    
    // game statistics

    /** The game shard. */
    private int     mInGame;

    // utility functions
    
    /**
     * From map.
     * Used to instantiate object from Dynamo store
     *
     * @param data the data
     */
    public void fromMap(Map<String, AttributeValue> data)
    {
        setUserID(DynamoUtils.getString(data, "UserID"));
        setUserName(DynamoUtils.getString(data, "UserName"));
        setTitle(DynamoUtils.getString(data, "Title"));
        setSubName(DynamoUtils.getString(data, "SubName"));
        setLastInteraction(DynamoUtils.getLong(data, "LastInteraction"));
        setNumberOfInteractions(DynamoUtils.getInt(data, "NumberOfInteractions"));
        setNumberOfSessions(DynamoUtils.getInt(data, "NumberOfSessions"));
        setNumberOfGames(DynamoUtils.getInt(data, "NumberOfGames"));
        setNumberOfKills(DynamoUtils.getInt(data, "NumberOfKills"));
        setNumberOfShots(DynamoUtils.getInt(data, "NumberOfShots"));
        setMaxTorpedoes(DynamoUtils.getInt(data, "MaxTorpedoes"));
        setInGame(DynamoUtils.getInt(data, "InGame"));
    }

    /**
     * To map.
     * Used to serialize object to dynamo store
     *
     * @return the map
     */
    public Map<String, AttributeValue> toMap()
    {
        Map<String, AttributeValue> u = new HashMap<String, AttributeValue>();
        putString(u, "UserID", getUserID());
        putString(u, "UserName", getUserName());
        putString(u, "Title", getTitle());
        putString(u, "SubName", getSubName());
        u.put("LastInteraction", new AttributeValue().withN(Long.toString(getLastInteraction())));
        u.put("NumberOfInteractions", new AttributeValue().withN(Integer.toString(getNumberOfInteractions())));
        u.put("NumberOfSessions", new AttributeValue().withN(Integer.toString(getNumberOfSessions())));
        u.put("NumberOfGames", new AttributeValue().withN(Integer.toString(getNumberOfGames())));
        u.put("NumberOfKills", new AttributeValue().withN(Integer.toString(getNumberOfKills())));
        u.put("NumberOfShots", new AttributeValue().withN(Integer.toString(getNumberOfShots())));
        u.put("MaxTorpedoes", new AttributeValue().withN(Integer.toString(getMaxTorpedoes())));
        u.put("InGame", new AttributeValue().withN(Integer.toString(getInGame())));
        return u;
    }
    
    /**
     * Put string.
     * Dynamo doesn't like empty or null strings. Just wrap that logic here.
     *
     * @param u the u
     * @param key the key
     * @param value the value
     */
    private void putString(Map<String, AttributeValue> u, String key, String value)
    {
        if (!StringUtils.trivial(value))
            u.put(key, new AttributeValue().withS(value));
    }
    
    // getters and setters
    
    /**
     * Gets the user id.
     *
     * @return the user id
     */
    public String getUserID()
    {
        return mUserID;
    }
    
    /**
     * Sets the user id.
     *
     * @param userID the new user id
     */
    public void setUserID(String userID)
    {
        mUserID = userID;
    }
    
    /**
     * Gets the user name.
     *
     * @return the user name
     */
    public String getUserName()
    {
        return mUserName;
    }
    
    /**
     * Sets the user name.
     *
     * @param userName the new user name
     */
    public void setUserName(String userName)
    {
        mUserName = userName;
    }
    
    /**
     * Gets the sub name.
     *
     * @return the sub name
     */
    public String getSubName()
    {
        return mSubName;
    }
    
    /**
     * Sets the sub name.
     *
     * @param subName the new sub name
     */
    public void setSubName(String subName)
    {
        mSubName = subName;
    }
    
    /**
     * Gets the last interaction.
     *
     * @return the last interaction
     */
    public long getLastInteraction()
    {
        return mLastInteraction;
    }
    
    /**
     * Sets the last interaction.
     *
     * @param lastInteraction the new last interaction
     */
    public void setLastInteraction(long lastInteraction)
    {
        mLastInteraction = lastInteraction;
    }
    
    /**
     * Gets the number of sessions.
     *
     * @return the number of sessions
     */
    public int getNumberOfSessions()
    {
        return mNumberOfSessions;
    }
    
    /**
     * Sets the number of sessions.
     *
     * @param numberOfSessions the new number of sessions
     */
    public void setNumberOfSessions(int numberOfSessions)
    {
        mNumberOfSessions = numberOfSessions;
    }
    
    /**
     * Gets the number of games.
     *
     * @return the number of games
     */
    public int getNumberOfGames()
    {
        return mNumberOfGames;
    }
    
    /**
     * Sets the number of games.
     *
     * @param numberOfGames the new number of games
     */
    public void setNumberOfGames(int numberOfGames)
    {
        mNumberOfGames = numberOfGames;
    }
    
    /**
     * Gets the number of kills.
     *
     * @return the number of kills
     */
    public int getNumberOfKills()
    {
        return mNumberOfKills;
    }
    
    /**
     * Sets the number of kills.
     *
     * @param numberOfKills the new number of kills
     */
    public void setNumberOfKills(int numberOfKills)
    {
        mNumberOfKills = numberOfKills;
    }
    
    /**
     * Gets the number of interactions.
     *
     * @return the number of interactions
     */
    public int getNumberOfInteractions()
    {
        return mNumberOfInteractions;
    }
    
    /**
     * Sets the number of interactions.
     *
     * @param numberOfInteractions the new number of interactions
     */
    public void setNumberOfInteractions(int numberOfInteractions)
    {
        mNumberOfInteractions = numberOfInteractions;
    }
    
    /**
     * Gets the game shard.
     *
     * @return the game shard
     */
    public int getInGame()
    {
        return mInGame;
    }
    
    /**
     * Sets the game shard.
     *
     * @param inGame the new game shard
     */
    public void setInGame(int inGame)
    {
        mInGame = inGame;
    }
    
    /**
     * Gets the title.
     *
     * @return the title
     */
    public String getTitle()
    {
        return mTitle;
    }
    
    /**
     * Sets the title.
     *
     * @param title the new title
     */
    public void setTitle(String title)
    {
        mTitle = title;
    }
    
    /**
     * Gets the max torpedoes.
     *
     * @return the max torpedoes
     */
    public int getMaxTorpedoes()
    {
        return mMaxTorpedoes;
    }
    
    /**
     * Sets the max torpedoes.
     *
     * @param maxTorpedoes the new max torpedoes
     */
    public void setMaxTorpedoes(int maxTorpedoes)
    {
        mMaxTorpedoes = maxTorpedoes;
    }
    
    /**
     * Gets the number of shots.
     *
     * @return the number of shots
     */
    public int getNumberOfShots()
    {
        return mNumberOfShots;
    }
    
    /**
     * Sets the number of shots.
     *
     * @param numberOfShots the new number of shots
     */
    public void setNumberOfShots(int numberOfShots)
    {
        mNumberOfShots = numberOfShots;
    }
}
