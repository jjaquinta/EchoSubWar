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

// TODO: Auto-generated Javadoc
/**
 * The Class SWOperationBean.
 */
public class SWOperationBean
{

    /** The Constant ENTER_GAME. */
    public static final String ENTER_GAME = "ENTER_GAME";
    
    /** The Constant QUERY_USER. */
    public static final String QUERY_USER = "QUERY_USER";
    
    /** The Constant TEST. */
    public static final String TEST = "TEST";
    
    /** The Constant SET_USER_DETAILS. */
    public static final String SET_USER_DETAILS = "SET_USER_DETAILS";
    
    /** The Constant MOVE. */
    public static final String MOVE = "MOVE";
    
    /** The Constant MICROPHONE. */
    public static final String MICROPHONE = "MICROPHONE";
    
    /** The Constant SONAR. */
    public static final String SONAR = "SONAR";
    
    /** The Constant TORPEDO. */
    public static final String TORPEDO = "TORPEDO";
    
    /** The Constant EXIT_GAME. */
    public static final String EXIT_GAME = "EXIT_GAME";
    
    /** The Constant LEADERS. */
    public static final String LEADERS = "LEADERS";
    
    /** The Constant TEST_CLEAR_USER. */
    public static final int TEST_CLEAR_USER = 0;
    
    /** The Constant TEST_RESET_SEED. */
    public static final int TEST_RESET_SEED = 1;
    
    /** The Constant TEST_AI_MOVE. */
    public static final int TEST_AI_MOVE = 2;
    
    /** The Constant NORTH. */
    public static final int NORTH = SWPingBean.NORTH;
    
    /** The Constant NORTHWEST. */
    public static final int NORTHWEST = SWPingBean.NORTHWEST;
    
    /** The Constant NORTHEAST. */
    public static final int NORTHEAST = SWPingBean.NORTHEAST;
    
    /** The Constant SOUTH. */
    public static final int SOUTH = SWPingBean.SOUTH;
    
    /** The Constant SOUTHWEST. */
    public static final int SOUTHWEST = SWPingBean.SOUTHWEST;
    
    /** The Constant SOUTHEAST. */
    public static final int SOUTHEAST = SWPingBean.SOUTHEAST;
    
    /** The Constant EAST. */
    public static final int EAST = SWPingBean.EAST;
    
    /** The Constant WEST. */
    public static final int WEST = SWPingBean.WEST;
    
    /** The Constant RAISE. */
    public static final int RAISE = 101;
    
    /** The Constant LOWER. */
    public static final int LOWER = 102;
    
    /** The Operation. */
    private String  mOperation;
    
    /** The User id. */
    private String  mUserID;
    
    /** The Credentials. */
    private String  mCredentials;
    
    /** The String1. */
    private String  mString1;
    
    /** The String2. */
    private String  mString2;
    
    /** The Int1. */
    private int     mInt1;
    
    /** The Int2. */
    private int     mInt2;
    
    /**
     * Gets the operation.
     *
     * @return the operation
     */
    public String getOperation()
    {
        return mOperation;
    }
    
    /**
     * Sets the operation.
     *
     * @param operation the new operation
     */
    public void setOperation(String operation)
    {
        mOperation = operation;
    }
    
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
     * Gets the credentials.
     *
     * @return the credentials
     */
    public String getCredentials()
    {
        return mCredentials;
    }
    
    /**
     * Sets the credentials.
     *
     * @param credentials the new credentials
     */
    public void setCredentials(String credentials)
    {
        mCredentials = credentials;
    }
    
    /**
     * Gets the string1.
     *
     * @return the string1
     */
    public String getString1()
    {
        return mString1;
    }
    
    /**
     * Sets the string1.
     *
     * @param string1 the new string1
     */
    public void setString1(String string1)
    {
        mString1 = string1;
    }
    
    /**
     * Gets the string2.
     *
     * @return the string2
     */
    public String getString2()
    {
        return mString2;
    }
    
    /**
     * Sets the string2.
     *
     * @param string2 the new string2
     */
    public void setString2(String string2)
    {
        mString2 = string2;
    }
    
    /**
     * Gets the int1.
     *
     * @return the int1
     */
    public int getInt1()
    {
        return mInt1;
    }
    
    /**
     * Sets the int1.
     *
     * @param int1 the new int1
     */
    public void setInt1(int int1)
    {
        mInt1 = int1;
    }
    
    /**
     * Gets the int2.
     *
     * @return the int2
     */
    public int getInt2()
    {
        return mInt2;
    }
    
    /**
     * Sets the int2.
     *
     * @param int2 the new int2
     */
    public void setInt2(int int2)
    {
        mInt2 = int2;
    }
}
