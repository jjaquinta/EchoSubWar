package com.tsatsatzu.subwar.test.audio;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.tsatsatzu.subwar.audio.api.SubWarAudioAPI;
import com.tsatsatzu.subwar.audio.data.SWInvocationBean;

public class PreGameTest extends BaseTest
{
    @Test
    public void play()
    {
        SWInvocationBean resp;
        resp = dialog(SubWarAudioAPI.CMD_LAUNCH_APP);
        assertNull(resp.getGame());
        assertFalse(resp.isEndSession());
        resp = dialog(SubWarAudioAPI.CMD_NO);
        assertNull(resp.getGame());
        assertFalse(resp.isEndSession());
        resp = dialog(SubWarAudioAPI.CMD_YES);
        assertNull(resp.getGame());
        assertFalse(resp.isEndSession());
        resp = dialog(SubWarAudioAPI.CMD_NO);
        assertNull(resp.getGame());
        assertFalse(resp.isEndSession());
        resp = dialog(SubWarAudioAPI.CMD_SHIP);
        assertNull(resp.getGame());
        assertFalse(resp.isEndSession());
        resp = dialog(SubWarAudioAPI.CMD_COMBAT);
        assertNull(resp.getGame());
        assertFalse(resp.isEndSession());
        resp = dialog(SubWarAudioAPI.CMD_LEADERS);
        assertNull(resp.getGame());
        assertFalse(resp.isEndSession());
        resp = dialog(SubWarAudioAPI.CMD_STOP);
        assertNull(resp.getGame());
        assertTrue(resp.isEndSession());
    }
}
