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
import com.tsatsatzu.subwar.game.logic.SWGameException;

/**
 * The Class SimplePlayer.
 * This is a basic AI for running the robotic players.
 * If it has no target, it listens or pings. If it still has no target, it just wanders.
 * If it does have a target, it moves towards it. When it is within a straight line
 * of it, it fires. If it happens to be on top of it, it moves to the side first.
 */
public class SimplePlayer implements IComputerPlayer
{
    
    /** The random number source. We use a fixed seed here so that the behavior is predictable for testing. */
    private Random mRND = new Random(0);
    
    /** The persistent data to stor each robot's state. */
    private Map<String, SimpleData> mData = new HashMap<>();

    /* (non-Javadoc)
     * @see com.tsatsatzu.subwar.game.logic.ai.IComputerPlayer#init(com.tsatsatzu.subwar.game.data.SWGameBean, java.lang.String)
     */
    @Override
    public void init(SWGameBean game, String id)
    {
        SimpleData data = new SimpleData();
        data.setID(id);
        data.setTargetDep(-1);
        mData.put(id, data);
        log(data, "enters the game.");
    }

    /* (non-Javadoc)
     * @see com.tsatsatzu.subwar.game.logic.ai.IComputerPlayer#move(com.tsatsatzu.subwar.game.data.SWGameBean, java.lang.String, long)
     */
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
    
    /**
     * Filter soundings.
     * Work out which are relevant, and which are not.
     *
     * @param pos the position object with the soundings in it
     */
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

    /**
     * React to soundings.
     * Now that we have the relevant soundings, pick one and act on it.
     *
     * @param game the game object
     * @param pos the position
     * @param data the robot data
     * @param tick the pint in time
     */
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

    /**
     * Fire torpedo.
     * We hvae a target, shoot it!
     *
     * @param game the game
     * @param pos the position
     * @param data the robot data
     * @param tick the current time
     */
    private void fireTorpedo(SWGameBean game, SWPositionBean pos, SimpleData data, long tick)
    {
        log(data, "Firing "+data.getFireDLon()+", "+data.getFireDLat());
        GameLogic.doTorpedo(data.getID(), game, data.getFireDLon(), data.getFireDLat(), tick);
        data.setFireDLon(null);
        data.setFireDLat(null);
    }
    
    /**
     * Find target.
     * We dont' have a target. Let's use our detectors to find one.
     *
     * @param game the game
     * @param pos the position
     * @param data the robot data
     * @param tick the current time
     */
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

    /**
     * Sets the course to target.
     * Convert a sounding (direction and distance) into an lon,lat of where we want to go.
     *
     * @param game the game
     * @param pos the position
     * @param data the robot data
     * @param target the target
     */
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

    /**
     * Move to target.
     * Convert a lon,lat into an actual game move to get there.
     *
     * @param game the game
     * @param pos the position
     * @param data the robot data
     * @param tick the current time
     */
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
            try
            {
                GameLogic.doMoveShip(data.getID(), dLon, dLat, dDep, game);
            }
            catch (SWGameException e)
            {   // shouldn't happen. Reset target.
                data.setTargetLon(pos.getLongitude());
                data.setTargetLat(pos.getLattitude());
                data.setTargetDep(pos.getDepth());
            }
        }
    }

    /* (non-Javadoc)
     * @see com.tsatsatzu.subwar.game.logic.ai.IComputerPlayer#term(com.tsatsatzu.subwar.game.data.SWGameBean, java.lang.String)
     */
    @Override
    public void term(SWGameBean game, String id)
    {
        log(id, "leaves the game.");
        mData.remove(id);
    }
    
    /**
     * Log a message.
     *
     * @param data the data
     * @param msg the msg
     */
    private void log(SimpleData data, String msg)
    {
        log(data.getID(), msg);
    }
    
    /**
     * Log a message.
     *
     * @param id the id
     * @param msg the msg
     */
    private void log(String id, String msg)
    {
        //System.out.println(id+": "+msg);
    }
    
    /**
     * The Class SimpleData.
     * Stores the stuff we need to track a robot's state.
     */
    class SimpleData
    {
        
        /** The ID. */
        private String  mID;
        
        /** The Target longitude. */
        private int     mTargetLon;
        
        /** The Target lattitude. */
        private int     mTargetLat;
        
        /** The Target depth. */
        private int     mTargetDep;
        
        /** The Fire delta longitude. */
        private Integer     mFireDLon;
        
        /** The Fire delta lattitude. */
        private Integer     mFireDLat;

        /**
         * Gets the id.
         *
         * @return the id
         */
        public String getID()
        {
            return mID;
        }

        /**
         * Sets the id.
         *
         * @param iD the new id
         */
        public void setID(String iD)
        {
            mID = iD;
        }

        /**
         * Gets the target longitude.
         *
         * @return the target longitude
         */
        public int getTargetLon()
        {
            return mTargetLon;
        }

        /**
         * Sets the target longitude.
         *
         * @param targetLon the new target longitude
         */
        public void setTargetLon(int targetLon)
        {
            mTargetLon = targetLon;
        }

        /**
         * Gets the target lattitude.
         *
         * @return the target lattitude
         */
        public int getTargetLat()
        {
            return mTargetLat;
        }

        /**
         * Sets the target lattitude.
         *
         * @param targetLat the new target lattitude
         */
        public void setTargetLat(int targetLat)
        {
            mTargetLat = targetLat;
        }

        /**
         * Gets the target depth.
         *
         * @return the target depth
         */
        public int getTargetDep()
        {
            return mTargetDep;
        }

        /**
         * Sets the target depth.
         *
         * @param targetDep the new target depth
         */
        public void setTargetDep(int targetDep)
        {
            mTargetDep = targetDep;
        }

        /**
         * Gets the fire delta longitude.
         *
         * @return the fire delta longitude
         */
        public Integer getFireDLon()
        {
            return mFireDLon;
        }

        /**
         * Sets the fire delta longitude.
         *
         * @param fireDLon the new fire delta longitude
         */
        public void setFireDLon(Integer fireDLon)
        {
            mFireDLon = fireDLon;
        }

        /**
         * Gets the fire delta lattitude.
         *
         * @return the fire delta lattitude
         */
        public Integer getFireDLat()
        {
            return mFireDLat;
        }

        /**
         * Sets the fire delta lattitude.
         *
         * @param fireDLat the new fire delta lattitude
         */
        public void setFireDLat(Integer fireDLat)
        {
            mFireDLat = fireDLat;
        }
    }
}
