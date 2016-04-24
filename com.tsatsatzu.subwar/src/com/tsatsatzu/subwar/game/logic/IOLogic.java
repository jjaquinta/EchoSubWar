package com.tsatsatzu.subwar.game.logic;

import com.tsatsatzu.subwar.game.data.SWUserBean;

public class IOLogic
{
    // All I/O traffic comes through this class. In most cases it is
    // handed off to the driver class. This indirection is there so
    // that you can choose, later, to use a different storage mechanism.
    // 
    private static IIODriver mDriver = null;
    
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

    public static void saveUser(SWUserBean user)
    {
        getDriver().saveUser(user);
    }

    public static SWUserBean getUser(String userID)
    {
        SWUserBean user = getDriver().getUser(userID);
        return user;
    }
    
}