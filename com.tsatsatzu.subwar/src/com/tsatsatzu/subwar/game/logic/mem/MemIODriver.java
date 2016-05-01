package com.tsatsatzu.subwar.game.logic.mem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
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

    @Override
    public List<SWUserBean> getTopUsers(int total)
    {
        List<SWUserBean> users = new ArrayList<>();
        users.addAll(mUserStore.values());
        Collections.sort(users, new Comparator<SWUserBean>() {
            @Override
            public int compare(SWUserBean o1, SWUserBean o2)
            {
                int k1 = o1.getNumberOfKills();
                int k2 = o2.getNumberOfKills();
                if (k1 != k2)
                    return k1 - k2;
                if (k1 == 0)
                    return 0; // avoid divide by zero
                double s1 = (double)o1.getNumberOfKills()/(double)o1.getNumberOfShots();
                double s2 = (double)o2.getNumberOfKills()/(double)o2.getNumberOfShots();
                return (int)Math.signum(s1 - s2);
            }
        });
        while (users.size() > total)
            users.remove(total);
        return users;
    }
}
