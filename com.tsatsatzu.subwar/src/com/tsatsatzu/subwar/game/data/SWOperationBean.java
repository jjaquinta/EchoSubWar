package com.tsatsatzu.subwar.game.data;

public class SWOperationBean
{

    public static final String ENTER_GAME = "ENTER_GAME";
    
    private String  mOperation;
    private String  mUserID;
    private String  mCredentials;
    
    public String getOperation()
    {
        return mOperation;
    }
    public void setOperation(String operation)
    {
        mOperation = operation;
    }
    public String getUserID()
    {
        return mUserID;
    }
    public void setUserID(String userID)
    {
        mUserID = userID;
    }
    public String getCredentials()
    {
        return mCredentials;
    }
    public void setCredentials(String credentials)
    {
        mCredentials = credentials;
    }
}
