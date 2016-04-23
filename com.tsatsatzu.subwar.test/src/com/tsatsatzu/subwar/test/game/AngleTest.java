package com.tsatsatzu.subwar.test.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.tsatsatzu.subwar.game.data.SWPingBean;

public class AngleTest
{
    private void assertNearlyEquals(String msg, double expected, double actual)
    {
        assertTrue(msg+", expected="+expected+", actual="+actual, Math.abs(expected - actual) < 1/180.0*Math.PI);
    }
    
    @Test
    public void testAngles()
    {
        double a, a2;
        int dir;
        a = SWPingBean.deltaToAngle(1, 0);
        assertNearlyEquals("Delta 1, 0", SWPingBean.ANGLES[SWPingBean.EAST], a);
        dir = SWPingBean.angleToDirection(a);
        assertEquals("Delta 1, 0", SWPingBean.EAST, dir);
        a2 = SWPingBean.directionToAngle(dir);
        assertNearlyEquals("Delta 1, 0", a, a2);
        
        a = SWPingBean.deltaToAngle(1, 1);
        assertNearlyEquals("Delta 1, 1", SWPingBean.ANGLES[SWPingBean.SOUTHEAST], a);
        dir = SWPingBean.angleToDirection(a);
        assertEquals("Delta 1, 1", SWPingBean.SOUTHEAST, dir);
        a2 = SWPingBean.directionToAngle(dir);
        assertNearlyEquals("Delta 1, 1", a, a2);
        
        a = SWPingBean.deltaToAngle(0, 1);
        assertNearlyEquals("Delta 0, 1", SWPingBean.ANGLES[SWPingBean.SOUTH], a);
        dir = SWPingBean.angleToDirection(a);
        assertEquals("Delta 0, 1", SWPingBean.SOUTH, dir);
        a2 = SWPingBean.directionToAngle(dir);
        assertNearlyEquals("Delta 0, 1", a, a2);
        
        a = SWPingBean.deltaToAngle(-1, 1);
        assertNearlyEquals("Delta -1, 1", SWPingBean.ANGLES[SWPingBean.SOUTHWEST], a);
        dir = SWPingBean.angleToDirection(a);
        assertEquals("Delta -1, 1", SWPingBean.SOUTHWEST, dir);
        a2 = SWPingBean.directionToAngle(dir);
        assertNearlyEquals("Delta -1, 1", a, a2);
        
        a = SWPingBean.deltaToAngle(-1, 0);
        assertNearlyEquals("Delta -1, 0", SWPingBean.ANGLES[SWPingBean.WEST], a);
        dir = SWPingBean.angleToDirection(a);
        assertEquals("Delta -1, 0", SWPingBean.WEST, dir);
        a2 = SWPingBean.directionToAngle(dir);
        assertNearlyEquals("Delta -1, 0", a, a2);
        
        a = SWPingBean.deltaToAngle(-1, -1);
        assertNearlyEquals("Delta -1, -1", SWPingBean.ANGLES[SWPingBean.NORTHWEST], a);
        dir = SWPingBean.angleToDirection(a);
        assertEquals("Delta -1, -1", SWPingBean.NORTHWEST, dir);
        a2 = SWPingBean.directionToAngle(dir);
        assertNearlyEquals("Delta -1, -1", a, a2);
        
        a = SWPingBean.deltaToAngle(0, -1);
        assertNearlyEquals("Delta 0, -1", SWPingBean.ANGLES[SWPingBean.NORTH], a);
        dir = SWPingBean.angleToDirection(a);
        assertEquals("Delta 0, -1", SWPingBean.NORTH, dir);
        a2 = SWPingBean.directionToAngle(dir);
        assertNearlyEquals("Delta 0, -1", a, a2);
        
        a = SWPingBean.deltaToAngle(1, -1);
        assertNearlyEquals("Delta 1, -1", SWPingBean.ANGLES[SWPingBean.NORTHEAST], a);
        dir = SWPingBean.angleToDirection(a);
        assertEquals("Delta 1, -1", SWPingBean.NORTHEAST, dir);
        a2 = SWPingBean.directionToAngle(dir);
        assertNearlyEquals("Delta 1, -1", a, a2);
    }
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
