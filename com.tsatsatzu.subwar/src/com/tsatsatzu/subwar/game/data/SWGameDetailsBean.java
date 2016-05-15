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

public class SWGameDetailsBean
{
    private int mEast;
    private int mWest;
    private int mNorth;
    private int mSouth;
    private int mMaxDepth;
    private SWPositionBean  mUserPosition;
    
    public int getEast()
    {
        return mEast;
    }
    public void setEast(int east)
    {
        mEast = east;
    }
    public int getWest()
    {
        return mWest;
    }
    public void setWest(int west)
    {
        mWest = west;
    }
    public int getNorth()
    {
        return mNorth;
    }
    public void setNorth(int north)
    {
        mNorth = north;
    }
    public int getSouth()
    {
        return mSouth;
    }
    public void setSouth(int south)
    {
        mSouth = south;
    }
    public int getMaxDepth()
    {
        return mMaxDepth;
    }
    public void setMaxDepth(int maxDepth)
    {
        mMaxDepth = maxDepth;
    }
    public SWPositionBean getUserPosition()
    {
        return mUserPosition;
    }
    public void setUserPosition(SWPositionBean userPosition)
    {
        mUserPosition = userPosition;
    }
}
