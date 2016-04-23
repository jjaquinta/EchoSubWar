package com.tsatsatzu.subwar.game.data;

public class SWOperationBean
{

    public static final String ENTER_GAME = "ENTER_GAME";
    public static final String QUERY_USER = "QUERY_USER";
    public static final String TEST = "TEST";
    public static final String SET_USER_DETAILS = "SET_USER_DETAILS";
    public static final String MOVE = "MOVE";
    
    public static final int NORTH = SWPingBean.NORTH;
    public static final int SOUTH = SWPingBean.SOUTH;
    public static final int EAST = SWPingBean.EAST;
    public static final int WEST = SWPingBean.WEST;
    
    private String  mOperation;
    private String  mUserID;
    private String  mCredentials;
    private String  mString1;
    private String  mString2;
    private int     mInt1;
    
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
    public String getString1()
    {
        return mString1;
    }
    public void setString1(String string1)
    {
        mString1 = string1;
    }
    public String getString2()
    {
        return mString2;
    }
    public void setString2(String string2)
    {
        mString2 = string2;
    }
    public int getInt1()
    {
        return mInt1;
    }
    public void setInt1(int int1)
    {
        mInt1 = int1;
    }
}
