package com.tsatsatzu.subwar.test.game;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.tsatsatzu.subwar.game.api.SubWarGameAPI;
import com.tsatsatzu.subwar.game.data.SWContextBean;
import com.tsatsatzu.subwar.game.data.SWOperationBean;

public class AITest extends BaseTest
{

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
        System.out.println("User at "+context.getGame().getUserPosition());

        for (int i = 0; i < 120; i++)
        {
            op = getOperation(SWOperationBean.TEST);
            op.setInt1(SWOperationBean.TEST_AI_MOVE);
            context = SubWarGameAPI.invoke(op);
            assertNull(context.getLastOperationError());
        }
    }

}
