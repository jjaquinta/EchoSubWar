package com.tsatsatzu.subwar.game.logic;

import java.util.List;

import com.tsatsatzu.subwar.game.data.SWUserBean;

public interface IIODriver
{
    public void clearCaches();
    public SWUserBean getUser(String id);
    public void saveUser(SWUserBean user);
    public void deleteUser(String id);
    public List<SWUserBean> getTopUsers(int total);
}
