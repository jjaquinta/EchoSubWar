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

// TODO: Auto-generated Javadoc
/**
 * The Class SWGameBean.
 */
public class SWGameBean
{
    
    /** The East. */
    private int mEast;
    
    /** The West. */
    private int mWest;
    
    /** The North. */
    private int mNorth;
    
    /** The South. */
    private int mSouth;
    
    /** The Max depth. */
    private int mMaxDepth;
    
    /** The Ships. */
    private Map<String,SWPositionBean> mShips = new HashMap<>();
    
    /** The AI. */
    private Map<String,IComputerPlayer> mAI = new HashMap<>();
    
    /**
     * Gets the east.
     *
     * @return the east
     */
    public int getEast()
    {
        return mEast;
    }
    
    /**
     * Sets the east.
     *
     * @param east the new east
     */
    public void setEast(int east)
    {
        mEast = east;
    }
    
    /**
     * Gets the west.
     *
     * @return the west
     */
    public int getWest()
    {
        return mWest;
    }
    
    /**
     * Sets the west.
     *
     * @param west the new west
     */
    public void setWest(int west)
    {
        mWest = west;
    }
    
    /**
     * Gets the north.
     *
     * @return the north
     */
    public int getNorth()
    {
        return mNorth;
    }
    
    /**
     * Sets the north.
     *
     * @param north the new north
     */
    public void setNorth(int north)
    {
        mNorth = north;
    }
    
    /**
     * Gets the south.
     *
     * @return the south
     */
    public int getSouth()
    {
        return mSouth;
    }
    
    /**
     * Sets the south.
     *
     * @param south the new south
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
     * Gets the ai.
     *
     * @return the ai
     */
    public Map<String, IComputerPlayer> getAI()
    {
        return mAI;
    }
    
    /**
     * Sets the ai.
     *
     * @param aI the a i
     */
    public void setAI(Map<String, IComputerPlayer> aI)
    {
        mAI = aI;
    }
}
