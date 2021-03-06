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

/**
 * The Class SWSessionBean.
 * This contains the raw data from the invoking context.
 * Right now this is just a user ID. It is assumed to be maintianed by the invoking context.
 * All that is required by this layer is that it is unique and unchanging per user.
 */
public class SWSessionBean
{
    
    /** The User id. */
    private String  mUserID;

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
}
