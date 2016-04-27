package com.tsatsatzu.subwar.game.logic.ai;

import com.tsatsatzu.subwar.game.data.SWGameBean;

public interface IComputerPlayer
{
    public void init(SWGameBean game, String id);
    public void move(SWGameBean game, String id, long tick);
    public void term(SWGameBean game, String id);
}
