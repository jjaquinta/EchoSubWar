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

import static org.junit.Assert.*;

import org.junit.Test;

import com.tsatsatzu.subwar.game.api.SubWarGameAPI;
import com.tsatsatzu.subwar.game.data.SWContextBean;
import com.tsatsatzu.subwar.game.data.SWOperationBean;

/**
 * The Class ObserveTest.
 * Testing the different ways of detecting other ships.
 */
public class ObserveTest extends BaseTest
{
    
    /**
     * Test sonar and underwater microphone.
     */
    @Test
    public void test()
    {
        SWOperationBean op = getOperation(SWOperationBean.TEST);
        op.setInt1(SWOperationBean.TEST_CLEAR_USER);
        SWContextBean context = SubWarGameAPI.invoke(op);
        assertNull(context.getLastOperationError());

        op = getOperation(SWOperationBean.TEST);
        op.setInt1(SWOperationBean.TEST_RESET_SEED);
        context = SubWarGameAPI.invoke(op);
        assertNull(context.getLastOperationError());

        op = getOperation(SWOperationBean.QUERY_USER);
        context = SubWarGameAPI.invoke(op);
        assertNull(context.getLastOperationError());
        assertNotNull(context.getUser());
        assertNull(context.getUser().getUserName());
        assertNull(context.getUser().getSubName());
        
        op = getOperation(SWOperationBean.ENTER_GAME);
        context = SubWarGameAPI.invoke(op);
        assertNull(context.getLastOperationError());
        assertNotNull(context.getGame());
        assertNotNull(context.getGame().getUserPosition());

        // test listen        
        op = getOperation(SWOperationBean.MICROPHONE);
        context = SubWarGameAPI.invoke(op);
        assertNull(context.getLastOperationError());
        assertNotNull(context.getGame());
        assertNotNull(context.getGame().getUserPosition());
        assertNotNull(context.getGame().getUserPosition().getSoundings());

        // test ping
        op = getOperation(SWOperationBean.SONAR);
        context = SubWarGameAPI.invoke(op);
        assertNull(context.getLastOperationError());
        assertNotNull(context.getGame());
        assertNotNull(context.getGame().getUserPosition());
        assertNotNull(context.getGame().getUserPosition().getSoundings());

    }
}
