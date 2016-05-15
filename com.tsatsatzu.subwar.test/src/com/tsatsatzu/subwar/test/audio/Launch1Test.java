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
package com.tsatsatzu.subwar.test.audio;

import static org.junit.Assert.*;

import org.junit.Test;

import com.tsatsatzu.subwar.audio.api.SubWarAudioAPI;
import com.tsatsatzu.subwar.audio.data.SWInvocationBean;

/**
 * The Class tests launching for the first time.
 */
public class Launch1Test extends BaseTest
{
    
    /**
     * Launch and reply No-Yes-No.
     */
    @Test
    public void launchNYN()
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
        resp = dialog(SubWarAudioAPI.CMD_STOP);
        assertNull(resp.getGame());
        assertTrue(resp.isEndSession());
    }
    
    /**
     * Launch and reply No-Yes-yes.
     */
    @Test
    public void launchNYY()
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
        resp = dialog(SubWarAudioAPI.CMD_YES);
        assertNotNull(resp.getGame());
        assertFalse(resp.isEndSession());
        resp = dialog(SubWarAudioAPI.CMD_STOP);
        assertNull(resp.getGame());
        assertTrue(resp.isEndSession());
    }
    
    /**
     * Launch and reply No-No.
     */
    @Test
    public void launchNN()
    {
        SWInvocationBean resp;
        resp = dialog(SubWarAudioAPI.CMD_LAUNCH_APP);
        assertNull(resp.getGame());
        assertFalse(resp.isEndSession());
        resp = dialog(SubWarAudioAPI.CMD_NO);
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
    
    /**
     * Launch and reply Yes.
     */
    @Test
    public void launchY()
    {
        SWInvocationBean resp;
        resp = dialog(SubWarAudioAPI.CMD_LAUNCH_APP);
        assertNull(resp.getGame());
        assertFalse(resp.isEndSession());
        resp = dialog(SubWarAudioAPI.CMD_YES);
        assertNotNull(resp.getGame());
        assertFalse(resp.isEndSession());
        resp = dialog(SubWarAudioAPI.CMD_STOP);
        assertNull(resp.getGame());
        assertTrue(resp.isEndSession());
    }
    
    /**
     * Launch with no further dialog.
     */
    @Test
    public void launch()
    {
        SWInvocationBean resp;
        resp = dialog(SubWarAudioAPI.CMD_LAUNCH_APP);
        assertNull(resp.getGame());
        assertFalse(resp.isEndSession());
        resp = dialog(SubWarAudioAPI.CMD_STOP);
        assertNull(resp.getGame());
        assertTrue(resp.isEndSession());
    }
}
