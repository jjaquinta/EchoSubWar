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
package com.tsatsatzu.subwar.test.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.tsatsatzu.subwar.game.data.SWPingBean;

// TODO: Auto-generated Javadoc
/**
 * The Class AngleTest.
 */
public class AngleTest
{
    
    /**
     * Assert nearly equals.
     *
     * @param msg the msg
     * @param expected the expected
     * @param actual the actual
     */
    private void assertNearlyEquals(String msg, double expected, double actual)
    {
        assertTrue(msg+", expected="+expected+", actual="+actual, Math.abs(expected - actual) < 1/180.0*Math.PI);
    }
    
    /**
     * Test angles.
     */
    @Test
    public void testAngles()
    {
        double a, a2;
        int dir;
        int[] delta;
        a = SWPingBean.deltaToAngle(1, 0);
        assertNearlyEquals("Delta 1, 0", SWPingBean.ANGLES[SWPingBean.EAST], a);
        dir = SWPingBean.angleToDirection(a);
        assertEquals("Delta 1, 0", SWPingBean.EAST, dir);
        a2 = SWPingBean.directionToAngle(dir);
        assertNearlyEquals("Delta 1, 0", a, a2);
        delta = SWPingBean.directionToDelta(dir);
        assertEquals("Delta lat", 1, delta[0]);
        assertEquals("Delta lon", 0, delta[1]);
        
        a = SWPingBean.deltaToAngle(-1, 0);
        assertNearlyEquals("Delta -1, 0", SWPingBean.ANGLES[SWPingBean.WEST], a);
        dir = SWPingBean.angleToDirection(a);
        assertEquals("Delta -1, 0", SWPingBean.WEST, dir);
        a2 = SWPingBean.directionToAngle(dir);
        assertNearlyEquals("Delta -1, 0", a, a2);
        delta = SWPingBean.directionToDelta(dir);
        assertEquals("Delta lat", -1, delta[0]);
        assertEquals("Delta lon", 0, delta[1]);
        
        a = SWPingBean.deltaToAngle(0, 1);
        assertNearlyEquals("Delta 0, 1", SWPingBean.ANGLES[SWPingBean.SOUTH], a);
        dir = SWPingBean.angleToDirection(a);
        assertEquals("Delta 0, 1", SWPingBean.SOUTH, dir);
        a2 = SWPingBean.directionToAngle(dir);
        assertNearlyEquals("Delta 0, 1", a, a2);
        delta = SWPingBean.directionToDelta(dir);
        assertEquals("Delta lat", 0, delta[0]);
        assertEquals("Delta lon", 1, delta[1]);

        a = SWPingBean.deltaToAngle(0, -1);
        assertNearlyEquals("Delta 0, -1", SWPingBean.ANGLES[SWPingBean.NORTH], a);
        dir = SWPingBean.angleToDirection(a);
        assertEquals("Delta 0, -1", SWPingBean.NORTH, dir);
        a2 = SWPingBean.directionToAngle(dir);
        assertNearlyEquals("Delta 0, -1", a, a2);
        delta = SWPingBean.directionToDelta(dir);
        assertEquals("Delta lat", 0, delta[0]);
        assertEquals("Delta lon", -1, delta[1]);
        
        a = SWPingBean.deltaToAngle(-1, 1);
        assertNearlyEquals("Delta -1, 1", SWPingBean.ANGLES[SWPingBean.SOUTHWEST], a);
        dir = SWPingBean.angleToDirection(a);
        assertEquals("Delta -1, 1", SWPingBean.SOUTHWEST, dir);
        a2 = SWPingBean.directionToAngle(dir);
        assertNearlyEquals("Delta -1, 1", a, a2);
        delta = SWPingBean.directionToDelta(dir);
        assertEquals("Delta lat", -1, delta[0]);
        assertEquals("Delta lon", 1, delta[1]);

        a = SWPingBean.deltaToAngle(1, 1);
        assertNearlyEquals("Delta 1, 1", SWPingBean.ANGLES[SWPingBean.SOUTHEAST], a);
        dir = SWPingBean.angleToDirection(a);
        assertEquals("Delta 1, 1", SWPingBean.SOUTHEAST, dir);
        a2 = SWPingBean.directionToAngle(dir);
        assertNearlyEquals("Delta 1, 1", a, a2);
        delta = SWPingBean.directionToDelta(dir);
        assertEquals("Delta lat", 1, delta[0]);
        assertEquals("Delta lon", 1, delta[1]);
        
        a = SWPingBean.deltaToAngle(-1, -1);
        assertNearlyEquals("Delta -1, -1", SWPingBean.ANGLES[SWPingBean.NORTHWEST], a);
        dir = SWPingBean.angleToDirection(a);
        assertEquals("Delta -1, -1", SWPingBean.NORTHWEST, dir);
        a2 = SWPingBean.directionToAngle(dir);
        assertNearlyEquals("Delta -1, -1", a, a2);
        delta = SWPingBean.directionToDelta(dir);
        assertEquals("Delta lat", -1, delta[0]);
        assertEquals("Delta lon", -1, delta[1]);

        a = SWPingBean.deltaToAngle(1, -1);
        assertNearlyEquals("Delta 1, -1", SWPingBean.ANGLES[SWPingBean.NORTHEAST], a);
        dir = SWPingBean.angleToDirection(a);
        assertEquals("Delta 1, -1", SWPingBean.NORTHEAST, dir);
        a2 = SWPingBean.directionToAngle(dir);
        assertNearlyEquals("Delta 1, -1", a, a2);
        delta = SWPingBean.directionToDelta(dir);
        assertEquals("Delta lat", 1, delta[0]);
        assertEquals("Delta lon", -1, delta[1]);
    }
    
    /**
     * Test directions.
     */
    @Test
    public void testDirections()
    {
        for (int dir = SWPingBean.NORTH; dir <= SWPingBean.NORTHNORTHWEST; dir++)
        {
            double a = SWPingBean.directionToAngle(dir);
            int d2 = SWPingBean.angleToDirection(a);
            //System.out.println(dir+" -> "+(a/Math.PI*180)+" -> "+d2);
            assertEquals(SWPingBean.DIRECTIONS[dir], dir, d2);
        }
    }
}
