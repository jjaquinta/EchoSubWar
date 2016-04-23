package com.tsatsatzu.subwar.game.logic.mem;

import java.util.HashMap;
import java.util.Map;

import com.tsatsatzu.subwar.game.data.SWUserBean;
import com.tsatsatzu.subwar.game.logic.IIODriver;

/*
 * This I/O driver is used for unit testing purposes. It stores the User base only
 * in memory.
 */

public class MemIODriver implements IIODriver
{
    private Map<String, SWUserBean> mUserStore = new HashMap<String, SWUserBean>();

    @Override
    public void clearCaches()
    {
        // NOOP: everything is in memory. Nothing to clear.
    }

    @Override
    public SWUserBean getUser(String id)
    {
        return mUserStore.get(id);
    }

    @Override
    public void saveUser(SWUserBean user)
    {
        mUserStore.put(user.getUserID(), user);
    }

    @Override
    public void deleteUser(String id)
    {
        mUserStore.remove(id);
    }
}
