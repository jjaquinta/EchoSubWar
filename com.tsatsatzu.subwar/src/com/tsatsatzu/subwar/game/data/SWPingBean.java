package com.tsatsatzu.subwar.game.data;

public class SWPingBean
{
    public static final int LEVEL          = 0;
    public static final int UP             = 1;
    public static final int DOWN           = -1;

    public static final int NORTH          = 1;
    public static final int NORTHNORTHEAST = 2;
    public static final int NORTHEAST      = 3;
    public static final int EASTNORTHEAST  = 4;
    public static final int EAST           = 5;
    public static final int EASTSOUTHEAST  = 6;
    public static final int SOUTHEAST      = 7;
    public static final int SOUTHSOUTHEAST = 8;
    public static final int SOUTH          = 9;
    public static final int SOUTHSOUTHWEST = 10;
    public static final int SOUTHWEST      = 11;
    public static final int WESTSOUTHWEST  = 12;
    public static final int WEST           = 13;
    public static final int WESTNORTHWEST  = 14;
    public static final int NORTHWEST      = 15;
    public static final int NORTHNORTHWEST = 16;
    
    private static final double COMPASS_ANGLE = Math.PI/8;
    public static final double[] ANGLES = {
            0,
            0,
            1*COMPASS_ANGLE,
            2*COMPASS_ANGLE,
            3*COMPASS_ANGLE,
            4*COMPASS_ANGLE,
            5*COMPASS_ANGLE,
            6*COMPASS_ANGLE,
            7*COMPASS_ANGLE,
            8*COMPASS_ANGLE,
            9*COMPASS_ANGLE,
            10*COMPASS_ANGLE,
            11*COMPASS_ANGLE,
            12*COMPASS_ANGLE,
            13*COMPASS_ANGLE,
            14*COMPASS_ANGLE,
            15*COMPASS_ANGLE,
    };
    public static final String[] DIRECTIONS = {
            "",
            "NORTH",
            "NORTHNORTHEAST",
            "NORTHEAST",
            "EASTNORTHEAST",
            "EAST",
            "EASTSOUTHEAST",
            "SOUTHEAST",
            "SOUTHSOUTHEAST",
            "SOUTH",
            "SOUTHSOUTHWEST",
            "SOUTHWEST",
            "WESTSOUTHWEST",
            "WEST",
            "WESTNORTHWEST",
            "NORTHWEST",
            "NORTHNORTHWEST",
    };

    private double          mDistance;
    private int             mAltitude;
    private int             mDirection;

    // utility functions

    public static double directionToAngle(int direction)
    {
        throw new IllegalStateException("need to recover code");
    }

    public static double deltaToAngle(int i, int j)
    {
        throw new IllegalStateException("need to recover code");
    }

    public static int angleToDirection(double a)
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
