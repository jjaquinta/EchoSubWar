package com.tsatsatzu.subwar.game.logic;

import com.tsatsatzu.subwar.game.data.SWUserBean;

public class UserLogic
{
    public static SWUserBean validate(String userID)
    {
        SWUserBean user = IOLogic.getUser(userID);
        if (user == null)
            user = newInstance(userID);
        return user;
    }
    
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

    public static void setUserName(SWUserBean user, String name)
    {
        if (name != null)
            if (name.length() == 0)
                user.setUserName(null);
            else
                user.setUserName(name);
    }

    public static void setShipName(SWUserBean user, String name)
    {
        if (name != null)
            if (name.length() == 0)
                user.setSubName(null);
            else
                user.setSubName(name);
    }
}
