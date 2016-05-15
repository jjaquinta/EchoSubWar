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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.tsatsatzu.subwar.game.api.SubWarGameAPI;
import com.tsatsatzu.subwar.game.data.SWContextBean;
import com.tsatsatzu.subwar.game.data.SWOperationBean;

/**
 * The Class AITest.
 * Tests the AI logic for gameplay. 
 */
public class AITest extends BaseTest
{

    /**
     * This is kind of a dumb test.
     * Basically it loops for a long time, letting the AI player fight itself.
     * However, if there are any critical bugs, then the crash can be seen.
     */
    @Test
    public void test()
    {
        SWOperationBean op = getOperation(SWOperationBean.TEST);
        op.setInt1(SWOperationBean.TEST_RESET_SEED);
        op.setInt2(1);
        SWContextBean context = SubWarGameAPI.invoke(op);
        assertNull(context.getLastOperationError());

        op = getOperation(SWOperationBean.ENTER_GAME);
        context = SubWarGameAPI.invoke(op);
        assertNull(context.getLastOperationError());
        assertNotNull(context.getUser());

        for (int i = 0; i < 120; i++)
        {
            op = getOperation(SWOperationBean.TEST);
            op.setInt1(SWOperationBean.TEST_AI_MOVE);
            context = SubWarGameAPI.invoke(op);
            assertNull(context.getLastOperationError());
        }
    }

}
