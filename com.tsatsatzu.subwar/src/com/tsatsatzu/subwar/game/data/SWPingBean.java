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

// TODO: Auto-generated Javadoc
/**
 * The Class SWPingBean.
 */
public class SWPingBean
{
    // North = -ve Lattitude
    // South = +ve Lattitude
    // East = +ve Longitude
    // West = -ve Longitude
    // Angle 0 = North, 90 = East, 180 = South, 270 = West
    
    /** The Constant LEVEL. */
    public static final int LEVEL          = 0;
    
    /** The Constant UP. */
    public static final int UP             = 1;
    
    /** The Constant DOWN. */
    public static final int DOWN           = -1;

    /** The Constant NORTH. */
    public static final int NORTH          = 1;
    
    /** The Constant NORTHNORTHEAST. */
    public static final int NORTHNORTHEAST = 2;
    
    /** The Constant NORTHEAST. */
    public static final int NORTHEAST      = 3;
    
    /** The Constant EASTNORTHEAST. */
    public static final int EASTNORTHEAST  = 4;
    
    /** The Constant EAST. */
    public static final int EAST           = 5;
    
    /** The Constant EASTSOUTHEAST. */
    public static final int EASTSOUTHEAST  = 6;
    
    /** The Constant SOUTHEAST. */
    public static final int SOUTHEAST      = 7;
    
    /** The Constant SOUTHSOUTHEAST. */
    public static final int SOUTHSOUTHEAST = 8;
    
    /** The Constant SOUTH. */
    public static final int SOUTH          = 9;
    
    /** The Constant SOUTHSOUTHWEST. */
    public static final int SOUTHSOUTHWEST = 10;
    
    /** The Constant SOUTHWEST. */
    public static final int SOUTHWEST      = 11;
    
    /** The Constant WESTSOUTHWEST. */
    public static final int WESTSOUTHWEST  = 12;
    
    /** The Constant WEST. */
    public static final int WEST           = 13;
    
    /** The Constant WESTNORTHWEST. */
    public static final int WESTNORTHWEST  = 14;
    
    /** The Constant NORTHWEST. */
    public static final int NORTHWEST      = 15;
    
    /** The Constant NORTHNORTHWEST. */
    public static final int NORTHNORTHWEST = 16;
    
    /** The Constant COMPASS_ANGLE. */
    private static final double COMPASS_ANGLE = Math.PI/8;
    
    /** The Constant ANGLES. */
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
    
    /** The Constant DELTAS. */
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
    
    /** The deltas. */
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
    
    /** The Constant DIRECTIONS. */
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
            "North-northwest",
    };
    
    /** The Constant DIRECTIONS_DM. */
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
    
    /** The Constant DIRECTIONS_CA. */
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

    /** The Constant TYPES. */
    public static final String[] TYPES = {
            "",
            "LISTEN",
            "PING",
            "PONG",
            "BOOM",
    };
    
    /** The Constant LISTEN. */
    public static final int LISTEN = 1;
    
    /** The Constant PING. */
    public static final int PING = 2;
    
    /** The Constant PONG. */
    public static final int PONG = 3;
    
    /** The Constant BOOM. */
    public static final int BOOM = 4;

    /** The Type. */
    private int             mType;
    
    /** The Time. */
    private long            mTime;
    
    /** The Distance. */
    private double          mDistance;
    
    /** The Altitude. */
    private int             mAltitude;
    
    /** The Direction. */
    private int             mDirection;

    // utility functions

    /**
     * Direction to angle.
     *
     * @param direction the direction
     * @return the double
     */
    public static double directionToAngle(int direction)
    {
        return ANGLES[direction];
    }

    /**
     * Delta to angle.
     *
     * @param deltaLongitude the delta longitude
     * @param deltaLattitude the delta lattitude
     * @return the double
     */
    public static double deltaToAngle(int deltaLongitude, int deltaLattitude)
    {
        double a = Math.atan2(deltaLongitude, -deltaLattitude);
        a = normalizeAngle(a);
        return a;
    }

    /**
     * Direction to delta.
     *
     * @param dir the dir
     * @return the int[]
     */
    public static int[] directionToDelta(int dir)
    {
        return DELTAS[dir];
    }

    /**
     * Direction to delta f.
     *
     * @param dir the dir
     * @param dist the dist
     * @return the double[]
     */
    public static double[] directionToDeltaF(int dir, double dist)
    {
        double[] delta = new double[2];
        delta[0] = fDELTAS[dir][0]*dist;
        delta[1] = fDELTAS[dir][1]*dist;
        return delta;
    }

    /**
     * Angle to direction.
     *
     * @param a the a
     * @return the int
     */
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

    /**
     * Normalize angle.
     *
     * @param a the a
     * @return the double
     */
    private static double normalizeAngle(double a)
    {
        while (a > Math.PI*2)
            a -= Math.PI*2;
        while (a < 0)
            a += Math.PI*2;
        return a;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "["+TYPES[mType]+", dist="+mDistance+", alt="+mAltitude+", dir="+DIRECTIONS[mDirection]+", time="+mTime+"]";
    }

    // getters and setters

    /**
     * Gets the distance.
     *
     * @return the distance
     */
    public double getDistance()
    {
        return mDistance;
    }

    /**
     * Sets the distance.
     *
     * @param distance the new distance
     */
    public void setDistance(double distance)
    {
        mDistance = distance;
    }

    /**
     * Gets the altitude.
     *
     * @return the altitude
     */
    public int getAltitude()
    {
        return mAltitude;
    }

    /**
     * Sets the altitude.
     *
     * @param altitude the new altitude
     */
    public void setAltitude(int altitude)
    {
        mAltitude = altitude;
    }

    /**
     * Gets the direction.
     *
     * @return the direction
     */
    public int getDirection()
    {
        return mDirection;
    }

    /**
     * Sets the direction.
     *
     * @param direction the new direction
     */
    public void setDirection(int direction)
    {
        mDirection = direction;
    }

    /**
     * Gets the type.
     *
     * @return the type
     */
    public int getType()
    {
        return mType;
    }

    /**
     * Sets the type.
     *
     * @param type the new type
     */
    public void setType(int type)
    {
        mType = type;
    }

    /**
     * Gets the time.
     *
     * @return the time
     */
    public long getTime()
    {
        return mTime;
    }

    /**
     * Sets the time.
     *
     * @param time the new time
     */
    public void setTime(long time)
    {
        mTime = time;
    }

}
