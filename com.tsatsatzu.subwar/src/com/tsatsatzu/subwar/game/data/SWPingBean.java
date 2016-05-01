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
    public static final double[][] fDELTAS = {
            null,
            {0.0,-1.0},
            {0.3826834323650898,-0.9238795325112867},
            {0.7071067811865475,-0.7071067811865476},
            {0.9238795325112867,-0.38268343236508984},
            {1.0,0},
            {0.9238795325112867,0.3826834323650897},
            {0.7071067811865476,0.7071067811865475},
            {0.3826834323650899,0.9238795325112867},
            {0,1.0},
            {-0.38268343236508967,0.9238795325112868},
            {-0.7071067811865475,0.7071067811865477},
            {-0.9238795325112865,0.38268343236509034},
            {-1.0,0},
            {-0.9238795325112866,-0.38268343236509},
            {-0.7071067811865477,-0.7071067811865474},
            {-0.3826834323650904,-0.9238795325112865},
            {0.0,-1.0},
    };
    public static final String[] DIRECTIONS = {
            "",
            "North",
            "north-northeast",
            "Northeast",
            "East-northeast",
            "East",
            "East-southeast",
            "Southeast",
            "South-southeast",
            "South",
            "South-southwest",
            "Southwest",
            "West-southwest",
            "West",
            "West-northwest",
            "Northwest",
            "Norht-northwest",
    };
    public static final String[] DIRECTIONS_DM = {
            "",
            "NR0",
            "NR0N",
            "NR0S",
            "ASTN",
            "AST",
            "ASTS",
            "S0ST",
            "S0S0",
            "S0",
            "S0S0",
            "S0ST",
            "ASTS",
            "AST",
            "ASTN",
            "NR0S",
            "NRTN",
    };
    public static final String[] DIRECTIONS_CA = {
            "",
            "NT11111111",
            "NTNTST1111",
            "NTST111111",
            "ASTNTST111",
            "AST1111111",
            "ASTSTST111",
            "STST111111",
            "STSTST1111",
            "ST11111111",
            "STSTWST111",
            "STWST11111",
            "WSTSTWST11",
            "WST1111111",
            "WSTNTWST11",
            "NTWST11111",
            "NTNTWST111",
    };

    public static final String[] TYPES = {
            "",
            "LISTEN",
            "PING",
            "PONG",
            "BOOM",
    };
    
    public static final int LISTEN = 1;
    public static final int PING = 2;
    public static final int PONG = 3;
    public static final int BOOM = 4;

    private int             mType;
    private long            mTime;
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

    public static double[] directionToDeltaF(int dir, double dist)
    {
        double[] delta = new double[2];
        delta[0] = fDELTAS[dir][0]*dist;
        delta[1] = fDELTAS[dir][1]*dist;
        return delta;
    }

    public static int angleToDirection(double a)
    {
        a = normalizeAngle(a);
        for (int dir = 1; dir <= NORTHNORTHWEST + 1; dir++)
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
    
    @Override
    public String toString()
    {
        return "["+TYPES[mType]+", dist="+mDistance+", alt="+mAltitude+", dir="+DIRECTIONS[mDirection]+", time="+mTime+"]";
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

    public int getType()
    {
        return mType;
    }

    public void setType(int type)
    {
        mType = type;
    }

    public long getTime()
    {
        return mTime;
    }

    public void setTime(long time)
    {
        mTime = time;
    }

}
