package com.tsatsatzu.subwar.game.data;

public class SWPingBean
{
    public static final int LEVEL = 0;
    public static final int UP = 1;
    public static final int DOWN = -1;
    
    private double  mDistance;
    private int     mAltitude;
    private int     mDirection;

    // utility functions
    
    public static double directionToAngle(int direction)
    {
        throw new IllegalStateException("need to recover code");
    }

    // getters and setters
    
    public double getDistance()
    {
        return mDistance;
    }

    public void setDistance(double distance)
    {
        mDistance = distance;
    }

    public int getAltitude()
    {
        return mAltitude;
    }

    public void setAltitude(int altitude)
    {
        mAltitude = altitude;
    }

    public int getDirection()
    {
        return mDirection;
    }

    public void setDirection(int direction)
    {
        mDirection = direction;
    }

}
