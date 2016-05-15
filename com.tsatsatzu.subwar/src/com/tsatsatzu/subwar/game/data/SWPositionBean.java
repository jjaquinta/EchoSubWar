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

import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class SWPositionBean.
 */
public class SWPositionBean
{
    
    /** The Longitude. */
    private int mLongitude;
    
    /** The Lattitude. */
    private int mLattitude;
    
    /** The Depth. */
    private int mDepth;
    
    /** The Soundings. */
    private List<SWPingBean> mSoundings = new ArrayList<>();
    
    /** The Last move. */
    private long    mLastMove;
    
    /** The Torpedoes. */
    private int mTorpedoes;
    
    /** The Hits. */
    private int mHits;

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    // utils
    @Override
    public String toString()
    {
        return mLongitude+","+mLattitude+","+mDepth;
    }
    
    // getters and setters
    
    /**
     * Gets the soundings.
     *
     * @return the soundings
     */
    public List<SWPingBean> getSoundings()
    {
        return mSoundings;
    }

    /**
     * Sets the soundings.
     *
     * @param soundings the new soundings
     */
    public void setSoundings(List<SWPingBean> soundings)
    {
        mSoundings = soundings;
    }

    /**
     * Gets the longitude.
     *
     * @return the longitude
     */
    public int getLongitude()
    {
        return mLongitude;
    }

    /**
     * Sets the longitude.
     *
     * @param longitude the new longitude
     */
    public void setLongitude(int longitude)
    {
        mLongitude = longitude;
    }

    /**
     * Gets the lattitude.
     *
     * @return the lattitude
     */
    public int getLattitude()
    {
        return mLattitude;
    }

    /**
     * Sets the lattitude.
     *
     * @param lattitude the new lattitude
     */
    public void setLattitude(int lattitude)
    {
        mLattitude = lattitude;
    }

    /**
     * Gets the depth.
     *
     * @return the depth
     */
    public int getDepth()
    {
        return mDepth;
    }

    /**
     * Sets the depth.
     *
     * @param depth the new depth
     */
    public void setDepth(int depth)
    {
        mDepth = depth;
    }

    /**
     * Gets the last move.
     *
     * @return the last move
     */
    public long getLastMove()
    {
        return mLastMove;
    }

    /**
     * Sets the last move.
     *
     * @param lastMove the new last move
     */
    public void setLastMove(long lastMove)
    {
        mLastMove = lastMove;
    }

    /**
     * Gets the torpedoes.
     *
     * @return the torpedoes
     */
    public int getTorpedoes()
    {
        return mTorpedoes;
    }

    /**
     * Sets the torpedoes.
     *
     * @param torpedoes the new torpedoes
     */
    public void setTorpedoes(int torpedoes)
    {
        mTorpedoes = torpedoes;
    }

    /**
     * Gets the hits.
     *
     * @return the hits
     */
    public int getHits()
    {
        return mHits;
    }

    /**
     * Sets the hits.
     *
     * @param hits the new hits
     */
    public void setHits(int hits)
    {
        mHits = hits;
    }
}
