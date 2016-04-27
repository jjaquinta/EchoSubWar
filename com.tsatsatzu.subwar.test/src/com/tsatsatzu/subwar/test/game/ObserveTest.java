package com.tsatsatzu.subwar.test.game;

import static org.junit.Assert.*;

import org.junit.Test;

import com.tsatsatzu.subwar.game.api.SubWarGameAPI;
import com.tsatsatzu.subwar.game.data.SWContextBean;
import com.tsatsatzu.subwar.game.data.SWOperationBean;

public class ObserveTest extends BaseTest
{
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
