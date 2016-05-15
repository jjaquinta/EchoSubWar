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
import com.tsatsatzu.subwar.game.logic.GameConstLogic;

// TODO: Auto-generated Javadoc
/**
 * The Class AttackTest.
 */
public class AttackTest extends BaseTest
{
    
    /**
     * Test cardinal.
     */
    @Test
    public void testCardinal()
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

        // test torpedo
        op = getOperation(SWOperationBean.TORPEDO);
        op.setInt1(SWOperationBean.NORTH);
        context = SubWarGameAPI.invoke(op);
        assertNull(context.getLastOperationError());
        assertNotNull(context.getGame());
        assertNotNull(context.getGame().getUserPosition());

        op = getOperation(SWOperationBean.TORPEDO);
        op.setInt1(SWOperationBean.SOUTH);
        context = SubWarGameAPI.invoke(op);
        assertNull(context.getLastOperationError());
        assertNotNull(context.getGame());
        assertNotNull(context.getGame().getUserPosition());

        op = getOperation(SWOperationBean.TORPEDO);
        op.setInt1(SWOperationBean.EAST);
        context = SubWarGameAPI.invoke(op);
        assertNull(context.getLastOperationError());
        assertNotNull(context.getGame());
        assertNotNull(context.getGame().getUserPosition());

        op = getOperation(SWOperationBean.TORPEDO);
        op.setInt1(SWOperationBean.WEST);
        context = SubWarGameAPI.invoke(op);
        assertNull(context.getLastOperationError());
        assertNotNull(context.getGame());
        assertNotNull(context.getGame().getUserPosition());
    }

    /**
     * Test angle.
     */
    @Test
    public void testAngle()
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

        // test torpedo
        op = getOperation(SWOperationBean.TORPEDO);
        op.setInt1(SWOperationBean.NORTHWEST);
        context = SubWarGameAPI.invoke(op);
        assertNull(context.getLastOperationError());
        assertNotNull(context.getGame());
        assertNotNull(context.getGame().getUserPosition());

        op = getOperation(SWOperationBean.TORPEDO);
        op.setInt1(SWOperationBean.SOUTHWEST);
        context = SubWarGameAPI.invoke(op);
        assertNull(context.getLastOperationError());
        assertNotNull(context.getGame());
        assertNotNull(context.getGame().getUserPosition());

        op = getOperation(SWOperationBean.TORPEDO);
        op.setInt1(SWOperationBean.NORTHEAST);
        context = SubWarGameAPI.invoke(op);
        assertNull(context.getLastOperationError());
        assertNotNull(context.getGame());
        assertNotNull(context.getGame().getUserPosition());

        op = getOperation(SWOperationBean.TORPEDO);
        op.setInt1(SWOperationBean.SOUTHEAST);
        context = SubWarGameAPI.invoke(op);
        assertNull(context.getLastOperationError());
        assertNotNull(context.getGame());
        assertNotNull(context.getGame().getUserPosition());
    }

    /**
     * Test empty.
     */
    @Test
    public void testEmpty()
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

        // empty torpedoes
        for (int i = 0; i < GameConstLogic.MAX_TORPEDOES; i++)
        {
            op = getOperation(SWOperationBean.TORPEDO);
            op.setInt1(SWOperationBean.NORTH);
            context = SubWarGameAPI.invoke(op);
            assertNull(context.getLastOperationError());
            assertNotNull(context.getGame());
            assertNotNull(context.getGame().getUserPosition());
        }

        op = getOperation(SWOperationBean.TORPEDO);
        op.setInt1(SWOperationBean.NORTH);
        context = SubWarGameAPI.invoke(op);
        assertNotNull(context.getLastOperationError());
        assertNotNull(context.getGame());
        assertNotNull(context.getGame().getUserPosition());

        op = getOperation(SWOperationBean.EXIT_GAME);
        context = SubWarGameAPI.invoke(op);
        assertNull(context.getLastOperationError());
        assertNull(context.getGame());

        op = getOperation(SWOperationBean.ENTER_GAME);
        context = SubWarGameAPI.invoke(op);
        assertNull(context.getLastOperationError());
        assertNotNull(context.getGame());
        assertNotNull(context.getGame().getUserPosition());
        
        // make sure restock
        op = getOperation(SWOperationBean.TORPEDO);
        op.setInt1(SWOperationBean.NORTH);
        context = SubWarGameAPI.invoke(op);
        assertNull(context.getLastOperationError());
        assertNotNull(context.getGame());
        assertNotNull(context.getGame().getUserPosition());
    }
}
