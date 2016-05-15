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

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.tsatsatzu.subwar.audio.api.SubWarAudioAPI;
import com.tsatsatzu.subwar.audio.data.SWInvocationBean;
import com.tsatsatzu.subwar.game.api.SubWarGameAPI;
import com.tsatsatzu.subwar.game.data.SWContextBean;
import com.tsatsatzu.subwar.game.data.SWOperationBean;

// TODO: Auto-generated Javadoc
/**
 * The Class RandomTest.
 */
public class RandomTest extends BaseTest
{
    
    /** The invocations. */
    private Map<String, String[]> INVOCATIONS = new HashMap<>();
    
    /**
     * Populate.
     */
    @Before
    public void populate()
    {
        INVOCATIONS.put(SubWarAudioAPI.CMD_CANCEL, new String[] { });
        INVOCATIONS.put(SubWarAudioAPI.CMD_HELP, new String[] { });
        INVOCATIONS.put(SubWarAudioAPI.CMD_REPEAT, new String[] { });
        INVOCATIONS.put(SubWarAudioAPI.CMD_STARTOVER, new String[] { });
        INVOCATIONS.put(SubWarAudioAPI.CMD_STOP, new String[] { });
        INVOCATIONS.put(SubWarAudioAPI.CMD_YES, new String[] { });
        INVOCATIONS.put(SubWarAudioAPI.CMD_NO, new String[] { });
        INVOCATIONS.put(SubWarAudioAPI.CMD_NORTH, new String[] { });
        INVOCATIONS.put(SubWarAudioAPI.CMD_SOUTH, new String[] { });
        INVOCATIONS.put(SubWarAudioAPI.CMD_EAST, new String[] { });
        INVOCATIONS.put(SubWarAudioAPI.CMD_WEST, new String[] { });
        INVOCATIONS.put(SubWarAudioAPI.CMD_NORTHWEST, new String[] { });
        INVOCATIONS.put(SubWarAudioAPI.CMD_NORTHEAST, new String[] { });
        INVOCATIONS.put(SubWarAudioAPI.CMD_SOUTHWEST, new String[] { });
        INVOCATIONS.put(SubWarAudioAPI.CMD_SOUTHEAST, new String[] { });
        INVOCATIONS.put(SubWarAudioAPI.CMD_DIVE, new String[] { });
        INVOCATIONS.put(SubWarAudioAPI.CMD_RISE, new String[] { });
        INVOCATIONS.put(SubWarAudioAPI.CMD_LISTEN, new String[] { });
        INVOCATIONS.put(SubWarAudioAPI.CMD_SONAR, new String[] { });
        INVOCATIONS.put(SubWarAudioAPI.CMD_FIRE, new String[] { 
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
        });
        INVOCATIONS.put(SubWarAudioAPI.CMD_CALL_ME, new String[] { 
                "",
                "Diana",
                "Bob",
        });
        INVOCATIONS.put(SubWarAudioAPI.CMD_CALL_SHIP, new String[] { 
                "",
                "Cincinnati",
                "Fort Lauderdale",
        });
        INVOCATIONS.put(SubWarAudioAPI.CMD_LAUNCH, new String[] { });
        INVOCATIONS.put(SubWarAudioAPI.CMD_DOCK, new String[] { });
        INVOCATIONS.put(SubWarAudioAPI.CMD_SHIP, new String[] { });
        INVOCATIONS.put(SubWarAudioAPI.CMD_COMBAT, new String[] { });
        INVOCATIONS.put(SubWarAudioAPI.CMD_LEADERS, new String[] { });
    }
    
    /**
     * Play.
     */
    @Test
    public void play()
    {
        Random rnd = new Random(0); // fixed seed
        String[] verbs = INVOCATIONS.keySet().toArray(new String[0]);
        
        SWOperationBean op = getOperation(SWOperationBean.TEST);
        op.setInt1(SWOperationBean.TEST_RESET_SEED);
        op.setInt2(1);
        SWContextBean context = SubWarGameAPI.invoke(op);
        assertNull(context.getLastOperationError());

        SWInvocationBean resp;
        resp = dialog(SubWarAudioAPI.CMD_LAUNCH_APP);
        assertNull(resp.getGame());
        assertFalse(resp.isEndSession());
        for (int i = 0; i < 512; i++)
        {
            if (resp.isEndSession())
            {
                resp = dialog(SubWarAudioAPI.CMD_LAUNCH_APP);
                assertNull(resp.getGame());
                assertFalse(resp.isEndSession());
            }
            else
            {
                String verb = verbs[rnd.nextInt(verbs.length)];
                String[] args = INVOCATIONS.get(verb);
                if (args.length == 0)
                    resp = dialog(verb);
                else
                {
                    String arg = args[rnd.nextInt(args.length)];
                    resp = dialog(verb, arg);
                }
            }
            aiMove();
        }
    }
}
