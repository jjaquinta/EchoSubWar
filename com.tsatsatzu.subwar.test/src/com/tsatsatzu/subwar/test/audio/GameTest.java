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
import com.tsatsatzu.subwar.game.api.SubWarGameAPI;
import com.tsatsatzu.subwar.game.data.SWContextBean;
import com.tsatsatzu.subwar.game.data.SWOperationBean;

// TODO: Auto-generated Javadoc
/**
 * The Class GameTest.
 */
public class GameTest extends BaseTest
{
    
    /**
     * Play.
     */
    @Test
    public void play()
    {
        SWOperationBean op = getOperation(SWOperationBean.TEST);
        op.setInt1(SWOperationBean.TEST_RESET_SEED);
        op.setInt2(1);
        SWContextBean context = SubWarGameAPI.invoke(op);
        assertNull(context.getLastOperationError());

        SWInvocationBean resp;
        resp = dialog(SubWarAudioAPI.CMD_LAUNCH_APP);
        assertNull(resp.getGame());
        assertFalse(resp.isEndSession());
        resp = dialog(SubWarAudioAPI.CMD_LAUNCH);
        assertNotNull(resp.getGame());
        assertFalse(resp.isEndSession());
        aiMove();
        resp = dialog(SubWarAudioAPI.CMD_HELP);
        assertNotNull(resp.getGame());
        assertFalse(resp.isEndSession());
        aiMove();
        resp = dialog(SubWarAudioAPI.CMD_NORTH);
        assertNotNull(resp.getGame());
        assertFalse(resp.isEndSession());
        aiMove();
        resp = dialog(SubWarAudioAPI.CMD_LISTEN);
        assertNotNull(resp.getGame());
        assertFalse(resp.isEndSession());
        aiMove();
        resp = dialog(SubWarAudioAPI.CMD_SONAR);
        assertNotNull(resp.getGame());
        assertFalse(resp.isEndSession());
        aiMove();
        resp = dialog(SubWarAudioAPI.CMD_DIVE);
        assertNotNull(resp.getGame());
        assertFalse(resp.isEndSession());
        aiMove();
        resp = dialog(SubWarAudioAPI.CMD_WEST);
        assertNotNull(resp.getGame());
        assertFalse(resp.isEndSession());
        aiMove();
        resp = dialog(SubWarAudioAPI.CMD_LISTEN);
        assertNotNull(resp.getGame());
        assertFalse(resp.isEndSession());
        aiMove();
        resp = dialog(SubWarAudioAPI.CMD_FIRE, "east");
        assertNotNull(resp.getGame());
        assertFalse(resp.isEndSession());
        aiMove();
        resp = dialog(SubWarAudioAPI.CMD_EAST);
        assertNotNull(resp.getGame());
        assertFalse(resp.isEndSession());
        aiMove();
        resp = dialog(SubWarAudioAPI.CMD_DIVE);
        assertNotNull(resp.getGame());
        assertFalse(resp.isEndSession());
        resp = dialog(SubWarAudioAPI.CMD_DOCK);
        assertNull(resp.getGame());
        assertFalse(resp.isEndSession());
        resp = dialog(SubWarAudioAPI.CMD_STOP);
        assertNull(resp.getGame());
        assertTrue(resp.isEndSession());
    }
}
