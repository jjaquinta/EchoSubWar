package com.tsatsatzu.subwar.game.logic.ai;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.tsatsatzu.subwar.game.data.SWGameBean;
import com.tsatsatzu.subwar.game.data.SWPingBean;
import com.tsatsatzu.subwar.game.data.SWPositionBean;
import com.tsatsatzu.subwar.game.logic.GameConstLogic;
import com.tsatsatzu.subwar.game.logic.GameLogic;

public class SimplePlayer implements IComputerPlayer
{
    private Random mRND = new Random(0);
    private Map<String, SimpleData> mData = new HashMap<>();

    @Override
    public void init(SWGameBean game, String id)
    {
        SimpleData data = new SimpleData();
        data.setID(id);
        data.setTargetDep(-1);
        mData.put(id, data);
        log(data, "enters the game.");
    }

    @Override
    public void move(SWGameBean game, String id, long tick)
    {
        SimpleData data = mData.get(id);
        if (data == null)
            throw new IllegalStateException("No data for id="+id);
        SWPositionBean pos = game.getShips().get(id);
        if (pos == null)
        {
            log(data, "No position for "+id+". We must have been killed.");
            return;
        }
        log(data, "at "+pos.getLongitude()+","+pos.getLattitude()+","+pos.getDepth());
        if (pos.getTorpedoes() == 0)
        {
            GameLogic.doLeaveGame(game, id);
            return;
        }
        filterSoundings(pos);
        if (data.getFireDLon() != null)
            fireTorpedo(game, pos, data, tick);
        else if (pos.getSoundings().size() > 0)
            reactToSoundings(game, pos, data, tick);
        else if (data.getTargetDep() < 0)
            findTarget(game, pos, data, tick);
        else
            moveToTarget(game, pos, data, tick);
        pos.setLastMove(tick);
    }
    
    private void filterSoundings(SWPositionBean pos)
    {
        long cutoff = System.currentTimeMillis() - GameConstLogic.AI_MOVE_TICK;
        for (Iterator<SWPingBean> i = pos.getSoundings().iterator(); i.hasNext(); )
        {
            SWPingBean ping = i.next();
            if (ping.getType() == SWPingBean.BOOM)
                i.remove();
            else if (ping.getTime() < cutoff)
                i.remove();
        }
    }

    private void reactToSoundings(SWGameBean game, SWPositionBean pos, SimpleData data, long tick)
    {
        SWPingBean target = pos.getSoundings().get(pos.getSoundings().size() - 1);        
        pos.getSoundings().clear();
        if (data.getTargetDep() != pos.getDepth())
        {
            log(data, "heard something at "+target);
            setCourseToTarget(game, pos, data, target);
            moveToTarget(game, pos, data, tick);
        }
        else
        {
            int dx = Math.abs(pos.getLongitude() - data.getTargetLon());
            int dy = Math.abs(pos.getLattitude() - data.getTargetLat());
            double d = Math.sqrt(dx*dx + dy*dy);
            if (d > target.getDistance())
            {
                log(data, "heard something at "+target);
                setCourseToTarget(game, pos, data, target);
                moveToTarget(game, pos, data, tick);
            }
        }
    }

    private void fireTorpedo(SWGameBean game, SWPositionBean pos, SimpleData data, long tick)
    {
        log(data, "Firing "+data.getFireDLon()+", "+data.getFireDLat());
        GameLogic.doTorpedo(data.getID(), game, data.getFireDLon(), data.getFireDLat(), tick);
        data.setFireDLon(null);
        data.setFireDLat(null);
    }
    
    private void findTarget(SWGameBean game, SWPositionBean pos, SimpleData data, long tick)
    {
        List<SWPingBean> sounding;
        if (mRND.nextInt(3) == 2)
        {
            log(data, "pinging");
            sounding = GameLogic.doPing(data.getID(), game, tick);
        }
        else
        {
            log(data, "listening");
            sounding = GameLogic.doListen(data.getID(), game, tick);
        }
        if (sounding.size() > 0)
        {
            SWPingBean target = sounding.get(0);
            log(data, "heard something at "+target);
            setCourseToTarget(game, pos, data, target);
        }
        else
        {
            log(data, "didn't hear anything. Move randomly. ");
            data.setTargetLon(pos.getLongitude() + mRND.nextInt(5) - 2);
            if (data.getTargetLon() < game.getWest())
                data.setTargetLon(game.getWest());
            if (data.getTargetLon() > game.getEast())
                data.setTargetLon(game.getEast());
            data.setTargetLat(pos.getLattitude() + mRND.nextInt(7) - 3);
            if (data.getTargetLat() < game.getNorth())
                data.setTargetLat(game.getNorth());
            if (data.getTargetLat() > game.getSouth())
                data.setTargetLat(game.getSouth());
            data.setTargetDep(pos.getDepth() + mRND.nextInt(3) - 1);
            if (data.getTargetDep() < 0)
                data.setTargetDep(0);
            if (data.getTargetDep() > game.getMaxDepth())
                data.setTargetDep(game.getMaxDepth());
            log(data, "moving to "+data.getTargetLon()+","+data.getTargetLat()+","+data.getTargetDep()+".");
        }
    }

    private void setCourseToTarget(SWGameBean game, SWPositionBean pos,
            SimpleData data, SWPingBean target)
    {
        if (target.getDistance() < 1)
        {
            if (target.getAltitude() == SWPingBean.LEVEL)
            {
                log(data, "is on top of target, need to move to one side so we can fire.");
                data.setTargetDep(pos.getDepth());
                if (pos.getLattitude() == game.getNorth())
                    data.setTargetLat(pos.getLattitude() + 1);
                else
                    data.setTargetLat(pos.getLattitude() - 1);
                if (pos.getLongitude() == game.getWest())
                    data.setTargetLon(pos.getLongitude() + 1);
                else
                    data.setTargetLon(pos.getLongitude() - 1);
            }
            else
            {
                if (target.getAltitude() == SWPingBean.UP)
                {
                    log(data, "is at target, but need to rise.");
                    data.setTargetDep(pos.getDepth() - 1);
                }
                else
                {
                    log(data, "is at target, but need to dive.");
                    data.setTargetDep(pos.getDepth() + 1);
                }
                data.setTargetLat(pos.getLattitude());
                data.setTargetLon(pos.getLongitude());
            }
        }
        else if ((target.getDistance() < 2*Math.sqrt(2)) && (target.getAltitude() == SWPingBean.LEVEL))
        {
            double[] delta = SWPingBean.directionToDeltaF(target.getDirection(), target.getDistance());
            data.setFireDLon((int)delta[0]);
            data.setFireDLat((int)delta[1]);
            log(data, "Firing "+data.getFireDLon()+","+data.getFireDLat());
        }
        else
        {
            double[] delta = SWPingBean.directionToDeltaF(target.getDirection(), target.getDistance());
            log(data, "Bearing dir="+SWPingBean.DIRECTIONS[target.getDirection()]+", dLon="+delta[0]+", dLat="+delta[1]);
            if (target.getAltitude() == SWPingBean.UP)
                data.setTargetDep(pos.getDepth() - 1);
            else if (target.getAltitude() == SWPingBean.DOWN)
                data.setTargetDep(pos.getDepth() + 1);
            else
                data.setTargetDep(pos.getDepth());
            data.setTargetLon((int)(pos.getLongitude() + delta[0]));
            data.setTargetLat((int)(pos.getLattitude() + delta[1]));
            log(data, "moving to "+data.getTargetLon()+","+data.getTargetLat()+","+data.getTargetDep()+".");
        }
    }

    private void moveToTarget(SWGameBean game, SWPositionBean pos, SimpleData data, long tick)
    {
        int dLon = (int)Math.signum(data.getTargetLon() - pos.getLongitude());
        int dLat = (int)Math.signum(data.getTargetLat() - pos.getLattitude());
        int dDep = -(int)Math.signum(data.getTargetDep() - pos.getDepth());
        if ((dLon == 0) && (dLat == 0) && (dDep == 0))
        {   // we're at target
            log(data, "arrived at "+pos.getLongitude()+", "+pos.getLattitude()+", "+pos.getDepth()+".");
            data.setTargetDep(-1);
            findTarget(game, pos, data, tick);
        }
        else
        {
            log(data, "moves "+dLon+", "+dLat+", "+dDep+".");
            GameLogic.doMoveShip(data.getID(), dLat, dLon, dDep, game);
        }
    }

    @Override
    public void term(SWGameBean game, String id)
    {
        log(id, "leaves the game.");
        mData.remove(id);
    }
    
    private void log(SimpleData data, String msg)
    {
        log(data.getID(), msg);
    }
    
    private void log(String id, String msg)
    {
        System.out.println(id+": "+msg);
    }
    
    class SimpleData
    {
        private String  mID;
        private int     mTargetLon;
        private int     mTargetLat;
        private int     mTargetDep;
        private Integer     mFireDLon;
        private Integer     mFireDLat;

        public String getID()
        {
            return mID;
        }

        public void setID(String iD)
        {
            mID = iD;
        }

        public int getTargetLon()
        {
            return mTargetLon;
        }

        public void setTargetLon(int targetLon)
        {
            mTargetLon = targetLon;
        }

        public int getTargetLat()
        {
            return mTargetLat;
        }

        public void setTargetLat(int targetLat)
        {
            mTargetLat = targetLat;
        }

        public int getTargetDep()
        {
            return mTargetDep;
        }

        public void setTargetDep(int targetDep)
        {
            mTargetDep = targetDep;
        }

        public Integer getFireDLon()
        {
            return mFireDLon;
        }

        public void setFireDLon(Integer fireDLon)
        {
            mFireDLon = fireDLon;
        }

        public Integer getFireDLat()
        {
            return mFireDLat;
        }

        public void setFireDLat(Integer fireDLat)
        {
            mFireDLat = fireDLat;
        }
    }
}
