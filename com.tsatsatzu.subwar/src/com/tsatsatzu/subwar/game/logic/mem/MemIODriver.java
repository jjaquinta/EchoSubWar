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
package com.tsatsatzu.subwar.game.logic.mem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsatsatzu.subwar.game.data.SWUserBean;
import com.tsatsatzu.subwar.game.logic.IIODriver;

// TODO: Auto-generated Javadoc
/*
 * This I/O driver is used for unit testing purposes. It stores the User base only
 * in memory.
 */

/**
 * The Class MemIODriver.
 */
public class MemIODriver implements IIODriver
{
    
    /** The User store. */
    private Map<String, SWUserBean> mUserStore = new HashMap<String, SWUserBean>();

    /* (non-Javadoc)
     * @see com.tsatsatzu.subwar.game.logic.IIODriver#clearCaches()
     */
    @Override
    public void clearCaches()
    {
        // NOOP: everything is in memory. Nothing to clear.
    }

    /* (non-Javadoc)
     * @see com.tsatsatzu.subwar.game.logic.IIODriver#getUser(java.lang.String)
     */
    @Override
    public SWUserBean getUser(String id)
    {
        return mUserStore.get(id);
    }

    /* (non-Javadoc)
     * @see com.tsatsatzu.subwar.game.logic.IIODriver#saveUser(com.tsatsatzu.subwar.game.data.SWUserBean)
     */
    @Override
    public void saveUser(SWUserBean user)
    {
        mUserStore.put(user.getUserID(), user);
    }

    /* (non-Javadoc)
     * @see com.tsatsatzu.subwar.game.logic.IIODriver#deleteUser(java.lang.String)
     */
    @Override
    public void deleteUser(String id)
    {
        mUserStore.remove(id);
    }

    /* (non-Javadoc)
     * @see com.tsatsatzu.subwar.game.logic.IIODriver#getTopUsers(int)
     */
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
