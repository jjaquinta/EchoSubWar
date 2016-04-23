package com.tsatsatzu.subwar.game.data;

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
    // ship statistics
    private String  mSubName;
    private int     mMaxTorpedoes;
    // game statistics
    private int     mInGame;
    
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
}
