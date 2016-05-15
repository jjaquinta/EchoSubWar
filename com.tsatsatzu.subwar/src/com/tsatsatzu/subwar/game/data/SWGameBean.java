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
package com.tsatsatzu.subwar.game.data;

import java.util.HashMap;
import java.util.Map;

import com.tsatsatzu.subwar.game.logic.ai.IComputerPlayer;

/**
 * The Class SWGameBean.
 * Details of a running shard.
 */
public class SWGameBean
{
    
    /** The Eastern boundary. */
    private int mEast;
    
    /** The Western boundary. */
    private int mWest;
    
    /** The Northern boundary. */
    private int mNorth;
    
    /** The Southern boundary. */
    private int mSouth;
    
    /** The Max depth. */
    private int mMaxDepth;
    
    /** The Ships. */
    private Map<String,SWPositionBean> mShips = new HashMap<>();
    
    /** The AIs. */
    private Map<String,IComputerPlayer> mAI = new HashMap<>();
    
    /**
     * Gets the eastern boundary.
     *
     * @return the eastern boundary
     */
    public int getEast()
    {
        return mEast;
    }
    
    /**
     * Sets the eastern boundary.
     *
     * @param east the new eastern boundary
     */
    public void setEast(int east)
    {
        mEast = east;
    }
    
    /**
     * Gets the western boundary.
     *
     * @return the western boundary
     */
    public int getWest()
    {
        return mWest;
    }
    
    /**
     * Sets the western boundary.
     *
     * @param west the new western boundary
     */
    public void setWest(int west)
    {
        mWest = west;
    }
    
    /**
     * Gets the northern boundary.
     *
     * @return the northern boundary
     */
    public int getNorth()
    {
        return mNorth;
    }
    
    /**
     * Sets the northern boundary.
     *
     * @param north the new northern boundary
     */
    public void setNorth(int north)
    {
        mNorth = north;
    }
    
    /**
     * Gets the southern boundary.
     *
     * @return the southern boundary
     */
    public int getSouth()
    {
        return mSouth;
    }
    
    /**
     * Sets the southern boundary.
     *
     * @param south the new southern boundary
     */
    public void setSouth(int south)
    {
        mSouth = south;
    }
    
    /**
     * Gets the max depth.
     *
     * @return the max depth
     */
    public int getMaxDepth()
    {
        return mMaxDepth;
    }
    
    /**
     * Sets the max depth.
     *
     * @param maxDepth the new max depth
     */
    public void setMaxDepth(int maxDepth)
    {
        mMaxDepth = maxDepth;
    }
    
    /**
     * Gets the ships.
     *
     * @return the ships
     */
    public Map<String, SWPositionBean> getShips()
    {
        return mShips;
    }
    
    /**
     * Sets the ships.
     *
     * @param ships the ships
     */
    public void setShips(Map<String, SWPositionBean> ships)
    {
        mShips = ships;
    }
    
    /**
     * Gets the ais.
     *
     * @return the ais
     */
    public Map<String, IComputerPlayer> getAI()
    {
        return mAI;
    }
    
    /**
     * Sets the ais.
     *
     * @param aI the ais
     */
    public void setAI(Map<String, IComputerPlayer> aI)
    {
        mAI = aI;
    }
}
