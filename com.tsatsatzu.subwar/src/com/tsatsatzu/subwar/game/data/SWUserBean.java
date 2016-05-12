package com.tsatsatzu.subwar.game.data;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.tsatsatzu.subwar.game.logic.dynamo.DynamoUtils;
import com.tsatsatzu.utils.obj.StringUtils;

public class SWUserBean
{
    // primary key
    private String  mUserID;
    // user statistics
    private String  mUserName;
    private String  mTitle;
    // book keeping
    private long    mLastInteraction;
    private int     mNumberOfInteractions;
    private int     mNumberOfSessions;
    private int     mNumberOfGames;
    private int     mNumberOfKills;
    private int     mNumberOfShots;
    // ship statistics
    private String  mSubName;
    private int     mMaxTorpedoes;
    // game statistics
    private int     mInGame;

    // utility functions
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
    
    private void putString(Map<String, AttributeValue> u, String key, String value)
    {
        if (!StringUtils.trivial(value))
            u.put(key, new AttributeValue().withS(value));
    }
    
    // getters and setters
    
    public String getUserID()
    {
        return mUserID;
    }
    public void setUserID(String userID)
    {
        mUserID = userID;
    }
    public String getUserName()
    {
        return mUserName;
    }
    public void setUserName(String userName)
    {
        mUserName = userName;
    }
    public String getSubName()
    {
        return mSubName;
    }
    public void setSubName(String subName)
    {
        mSubName = subName;
    }
    public long getLastInteraction()
    {
        return mLastInteraction;
    }
    public void setLastInteraction(long lastInteraction)
    {
        mLastInteraction = lastInteraction;
    }
    public int getNumberOfSessions()
    {
        return mNumberOfSessions;
    }
    public void setNumberOfSessions(int numberOfSessions)
    {
        mNumberOfSessions = numberOfSessions;
    }
    public int getNumberOfGames()
    {
        return mNumberOfGames;
    }
    public void setNumberOfGames(int numberOfGames)
    {
        mNumberOfGames = numberOfGames;
    }
    public int getNumberOfKills()
    {
        return mNumberOfKills;
    }
    public void setNumberOfKills(int numberOfKills)
    {
        mNumberOfKills = numberOfKills;
    }
    public int getNumberOfInteractions()
    {
        return mNumberOfInteractions;
    }
    public void setNumberOfInteractions(int numberOfInteractions)
    {
        mNumberOfInteractions = numberOfInteractions;
    }
    public int getInGame()
    {
        return mInGame;
    }
    public void setInGame(int inGame)
    {
        mInGame = inGame;
    }
    public String getTitle()
    {
        return mTitle;
    }
    public void setTitle(String title)
    {
        mTitle = title;
    }
    public int getMaxTorpedoes()
    {
        return mMaxTorpedoes;
    }
    public void setMaxTorpedoes(int maxTorpedoes)
    {
        mMaxTorpedoes = maxTorpedoes;
    }
    public int getNumberOfShots()
    {
        return mNumberOfShots;
    }
    public void setNumberOfShots(int numberOfShots)
    {
        mNumberOfShots = numberOfShots;
    }
}
