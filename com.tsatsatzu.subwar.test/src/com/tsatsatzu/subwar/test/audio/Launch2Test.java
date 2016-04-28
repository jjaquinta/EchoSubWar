package com.tsatsatzu.subwar.test.audio;

import static org.junit.Assert.*;

import org.junit.Test;

import com.tsatsatzu.subwar.audio.api.SubWarAudioAPI;
import com.tsatsatzu.subwar.audio.data.SWInvocationBean;

public class Launch2Test extends BaseTest
{
    @Test
    public void launchY()
    {
        SWInvocationBean resp;
        // first launch
        resp = dialog(SubWarAudioAPI.CMD_LAUNCH_APP);
        assertNull(resp.getGame());
        assertFalse(resp.isEndSession());
        resp = dialog(SubWarAudioAPI.CMD_LAUNCH);
        assertNotNull(resp.getGame());
        assertFalse(resp.isEndSession());
        resp = dialog(SubWarAudioAPI.CMD_STOP);
        assertNull(resp.getGame());
        assertTrue(resp.isEndSession());

        // second launch
        resp = dialog(SubWarAudioAPI.CMD_LAUNCH_APP);
        assertNull(resp.getGame());
        assertFalse(resp.isEndSession());
        resp = dialog(SubWarAudioAPI.CMD_YES);
        assertNull(resp.getGame());
        assertFalse(resp.isEndSession());
        resp = dialog(SubWarAudioAPI.CMD_CALLME, "Diana");
        assertNull(resp.getGame());
        assertFalse(resp.isEndSession());
        resp = dialog(SubWarAudioAPI.CMD_LAUNCH);
        assertNotNull(resp.getGame());
        assertFalse(resp.isEndSession());
        resp = dialog(SubWarAudioAPI.CMD_STOP);
        assertNull(resp.getGame());
        assertTrue(resp.isEndSession());
    }
    @Test
    public void launchN()
    {
        SWInvocationBean resp;
        // first launch
        resp = dialog(SubWarAudioAPI.CMD_LAUNCH_APP);
        assertNull(resp.getGame());
        assertFalse(resp.isEndSession());
        resp = dialog(SubWarAudioAPI.CMD_LAUNCH);
        assertNotNull(resp.getGame());
        assertFalse(resp.isEndSession());
        resp = dialog(SubWarAudioAPI.CMD_STOP);
        assertNull(resp.getGame());
        assertTrue(resp.isEndSession());

        // second launch
        resp = dialog(SubWarAudioAPI.CMD_LAUNCH_APP);
        assertNull(resp.getGame());
        assertFalse(resp.isEndSession());
        resp = dialog(SubWarAudioAPI.CMD_NO);
        assertNull(resp.getGame());
        assertFalse(resp.isEndSession());
        resp = dialog(SubWarAudioAPI.CMD_LAUNCH);
        assertNotNull(resp.getGame());
        assertFalse(resp.isEndSession());
        resp = dialog(SubWarAudioAPI.CMD_STOP);
        assertNull(resp.getGame());
        assertTrue(resp.isEndSession());
    }
    @Test
    public void launch()
    {
        SWInvocationBean resp;
        // first launch
        resp = dialog(SubWarAudioAPI.CMD_LAUNCH_APP);
        assertNull(resp.getGame());
        assertFalse(resp.isEndSession());
        resp = dialog(SubWarAudioAPI.CMD_LAUNCH);
        assertNotNull(resp.getGame());
        assertFalse(resp.isEndSession());
        resp = dialog(SubWarAudioAPI.CMD_STOP);
        assertNull(resp.getGame());
        assertTrue(resp.isEndSession());

        // second launch
        resp = dialog(SubWarAudioAPI.CMD_LAUNCH_APP);
        assertNull(resp.getGame());
        assertFalse(resp.isEndSession());
        resp = dialog(SubWarAudioAPI.CMD_LAUNCH);
        assertNotNull(resp.getGame());
        assertFalse(resp.isEndSession());
        resp = dialog(SubWarAudioAPI.CMD_STOP);
        assertNull(resp.getGame());
        assertTrue(resp.isEndSession());
    }
}
