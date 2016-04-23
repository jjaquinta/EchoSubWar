package com.tsatsatzu.subwar.test.game;

import static org.junit.Assert.*;

import org.junit.Test;

import com.tsatsatzu.subwar.game.api.SubWarGameAPI;
import com.tsatsatzu.subwar.game.data.SWContextBean;
import com.tsatsatzu.subwar.game.data.SWOperationBean;

public class MoveTest extends BaseTest
{
    @Test
    public void test()
    {
        SWOperationBean op = getOperation(SWOperationBean.TEST);
        SWContextBean context = SubWarGameAPI.invoke(op);
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

        // test north
        while (context.getGame().getUserPosition().getLattitude() > context.getGame().getNorth())
        {
            op = getOperation(SWOperationBean.MOVE);
            op.setInt1(SWOperationBean.NORTH);
            context = SubWarGameAPI.invoke(op);
            assertNull(context.getLastOperationError());
            assertNotNull(context.getGame());
            assertNotNull(context.getGame().getUserPosition());            
        }
        op = getOperation(SWOperationBean.MOVE);
        op.setInt1(SWOperationBean.NORTH);
        context = SubWarGameAPI.invoke(op);
        assertNotNull(context.getLastOperationError());
        assertNotNull(context.getGame());
        assertNotNull(context.getGame().getUserPosition());            

        // test east
        while (context.getGame().getUserPosition().getLongitude() < context.getGame().getEast())
        {
            op = getOperation(SWOperationBean.MOVE);
            op.setInt1(SWOperationBean.EAST);
            context = SubWarGameAPI.invoke(op);
            assertNull(context.getLastOperationError());
            assertNotNull(context.getGame());
            assertNotNull(context.getGame().getUserPosition());            
        }
        op = getOperation(SWOperationBean.MOVE);
        op.setInt1(SWOperationBean.EAST);
        context = SubWarGameAPI.invoke(op);
        assertNotNull(context.getLastOperationError());
        assertNotNull(context.getGame());
        assertNotNull(context.getGame().getUserPosition());            

        // test south
        while (context.getGame().getUserPosition().getLattitude() < context.getGame().getSouth())
        {
            op = getOperation(SWOperationBean.MOVE);
            op.setInt1(SWOperationBean.SOUTH);
            context = SubWarGameAPI.invoke(op);
            assertNull(context.getLastOperationError());
            assertNotNull(context.getGame());
            assertNotNull(context.getGame().getUserPosition());            
        }
        op = getOperation(SWOperationBean.MOVE);
        op.setInt1(SWOperationBean.SOUTH);
        context = SubWarGameAPI.invoke(op);
        assertNotNull(context.getLastOperationError());
        assertNotNull(context.getGame());
        assertNotNull(context.getGame().getUserPosition());            

        // test west
        while (context.getGame().getUserPosition().getLongitude() > context.getGame().getWest())
        {
            op = getOperation(SWOperationBean.MOVE);
            op.setInt1(SWOperationBean.WEST);
            context = SubWarGameAPI.invoke(op);
            assertNull(context.getLastOperationError());
            assertNotNull(context.getGame());
            assertNotNull(context.getGame().getUserPosition());            
        }
        op = getOperation(SWOperationBean.MOVE);
        op.setInt1(SWOperationBean.WEST);
        context = SubWarGameAPI.invoke(op);
        assertNotNull(context.getLastOperationError());
        assertNotNull(context.getGame());
        assertNotNull(context.getGame().getUserPosition());            

    }
}
