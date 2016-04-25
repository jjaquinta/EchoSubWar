package com.tsatsatzu.subwar.game.data;

public class SWPingBean
{
    // North = -ve Lattitude
    // South = +ve Lattitude
    // East = +ve Longitude
    // West = -ve Longitude
    // Angle 0 = North, 90 = East, 180 = South, 270 = West
    
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
            16*COMPASS_ANGLE,
    };
    public static final int[][] DELTAS = {
            null,
            {0, -1},
            null,
            {1, -1},
            null,
            {1, 0},
            null,
            {1, 1},
            null,
            {0, 1},
            null,
            {-1, 1},
            null,
            {-1, 0},
            null,
            {-1, -1},
            null,
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
        return ANGLES[direction];
    }

    public static double deltaToAngle(int deltaLongitude, int deltaLattitude)
    {
        double a = Math.atan2(deltaLongitude, -deltaLattitude);
        a = normalizeAngle(a);
        return a;
    }

    public static int[] directionToDelta(int dir)
    {
        return DELTAS[dir];
    }

    public static int angleToDirection(double a)
    {
        a = normalizeAngle(a);
        for (int dir = 1; dir < NORTHNORTHWEST + 1; dir++)
        {
            double delta = Math.abs(ANGLES[dir] - a);
            if (delta <= COMPASS_ANGLE/2)
                return ((dir - 1)%16) + 1;
        }
        throw new IllegalStateException("Could not find closest angle to "+a);
    }

    private static double normalizeAngle(double a)
    {
        while (a > Math.PI*2)
            a -= Math.PI*2;
        while (a < 0)
            a += Math.PI*2;
        return a;
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
