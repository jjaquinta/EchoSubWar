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

import java.util.List;

import com.tsatsatzu.subwar.game.data.SWUserBean;

/**
 * The Class IOLogic.
 * All I/O traffic comes through this class. In most cases it is
 * handed off to the driver class. This indirection is there so
 * that you can choose, later, to use a different storage mechanism.
 */
public class IOLogic
{
    /** The Driver. */
    private static IIODriver mDriver = null;
    
    /**
     * Gets the driver.
     * By default, this uses the dynamo driver. But this can be overridden by setting a value in the credentials.
     *
     * @return the driver
     */
    public static IIODriver getDriver()
    {
        if (mDriver == null)
        {
            String driverClassName = CredentialsLogic.getProperty("ioDriver", "com.tsatsatzu.subwar.game.logic.dynamo.DynamoIODriver");
            try
            {
                Class<?> driverClass = Class.forName(driverClassName);
                mDriver = (IIODriver)driverClass.newInstance();
            }
            catch (ClassNotFoundException | InstantiationException | IllegalAccessException | ClassCastException e)
            {
                throw new IllegalStateException("ioDriver set to '"+driverClassName+"', could not resolve", e);
            }
        }
        return mDriver;
    }

    /**
     * Save user.
     * Saves the user to the backing store
     *
     * @param user the user
     */
    public static void saveUser(SWUserBean user)
    {
        getDriver().saveUser(user);
    }

    /**
     * Gets the user.
     * Gets the user from the backing store.
     *
     * @param userID the user id
     * @return the user
     */
    public static SWUserBean getUser(String userID)
    {
        SWUserBean user = getDriver().getUser(userID);
        return user;
    }

    /**
     * Gets the top users.
     * Gets the top #total users from the backing store.
     *
     * @param total the total
     * @return the top users
     */
    public static List<SWUserBean> getTopUsers(int total)
    {
        List<SWUserBean> users = getDriver().getTopUsers(total);
        return users;
    }
}