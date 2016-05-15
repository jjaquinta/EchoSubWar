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
package com.tsatsatzu.subwar.game.logic;

import com.tsatsatzu.subwar.game.data.SWUserBean;

// TODO: Auto-generated Javadoc
/**
 * The Class UserLogic.
 */
public class UserLogic
{
    
    /**
     * Validate.
     *
     * @param userID the user id
     * @return the SW user bean
     */
    public static SWUserBean validate(String userID)
    {
        SWUserBean user = IOLogic.getUser(userID);
        if (user == null)
            user = newInstance(userID);
        return user;
    }
    
    /**
     * New instance.
     *
     * @param userID the user id
     * @return the SW user bean
     */
    public static SWUserBean newInstance(String userID)
    {
        SWUserBean user = new SWUserBean();
        user.setUserID(userID);
        user.setUserName(null);
        user.setTitle("Captain");
        user.setSubName(null);
        user.setMaxTorpedoes(6);
        user.setInGame(-1);
        return user;
    }

    /**
     * Sets the user name.
     *
     * @param user the user
     * @param name the name
     */
    public static void setUserName(SWUserBean user, String name)
    {
        if (name != null)
            if (name.length() == 0)
                user.setUserName(null);
            else
                user.setUserName(name);
    }

    /**
     * Sets the ship name.
     *
     * @param user the user
     * @param name the name
     */
    public static void setShipName(SWUserBean user, String name)
    {
        if (name != null)
            if (name.length() == 0)
                user.setSubName(null);
            else
                user.setSubName(name);
    }
}
