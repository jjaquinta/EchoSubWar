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

public class SWPositionBean
{
    private int mLongitude;
    private int mLattitude;
    private int mDepth;
    private List<SWPingBean> mSoundings = new ArrayList<>();
    private long    mLastMove;
    private int mTorpedoes;
    private int mHits;

    // utils
    @Override
    public String toString()
    {
        return mLongitude+","+mLattitude+","+mDepth;
    }
    
    // getters and setters
    
    public List<SWPingBean> getSoundings()
    {
        return mSoundings;
    }

    public void setSoundings(List<SWPingBean> soundings)
    {
        mSoundings = soundings;
    }

    public int getLongitude()
    {
        return mLongitude;
    }

    public void setLongitude(int longitude)
    {
        mLongitude = longitude;
    }

    public int getLattitude()
    {
        return mLattitude;
    }

    public void setLattitude(int lattitude)
    {
        mLattitude = lattitude;
    }

    public int getDepth()
    {
        return mDepth;
    }

    public void setDepth(int depth)
    {
        mDepth = depth;
    }

    public long getLastMove()
    {
        return mLastMove;
    }

    public void setLastMove(long lastMove)
    {
        mLastMove = lastMove;
    }

    public int getTorpedoes()
    {
        return mTorpedoes;
    }

    public void setTorpedoes(int torpedoes)
    {
        mTorpedoes = torpedoes;
    }

    public int getHits()
    {
        return mHits;
    }

    public void setHits(int hits)
    {
        mHits = hits;
    }
}
