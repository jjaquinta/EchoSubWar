package com.tsatsatzu.subwar.game.logic.dynamo;

import java.util.List;

import com.tsatsatzu.subwar.game.data.SWUserBean;
import com.tsatsatzu.subwar.game.logic.IIODriver;

/*
 * This I/O driver is used for persisting values in a set of DynamoDB databases.
 */

public class DynamoIODriver implements IIODriver
{

    @Override
    public void clearCaches()
    {
        throw new IllegalStateException("Not implemented.");
    }

    @Override
    public SWUserBean getUser(String id)
    {
        throw new IllegalStateException("Not implemented.");
    }

    @Override
    public void saveUser(SWUserBean user)
    {
        throw new IllegalStateException("Not implemented.");
    }

    @Override
    public void deleteUser(String id)
    {
        throw new IllegalStateException("Not implemented.");
    }

    @Override
    public List<SWUserBean> getTopUsers(int total)
    {
        throw new IllegalStateException("Not implemented.");
    }

}
