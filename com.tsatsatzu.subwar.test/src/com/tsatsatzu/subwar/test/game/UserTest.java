package com.tsatsatzu.subwar.test.game;

import static org.junit.Assert.*;

import org.junit.Test;

import com.tsatsatzu.subwar.game.api.SubWarGameAPI;
import com.tsatsatzu.subwar.game.data.SWContextBean;
import com.tsatsatzu.subwar.game.data.SWOperationBean;

public class UserTest extends BaseTest
{
    @Test
    public void test()
    {
        SWOperationBean op = getOperation(SWOperationBean.QUERY_USER);
        SWContextBean context = SubWarGameAPI.invoke(op);
        assertNull(context.getLastOperationError());
        assertNotNull(context.getUser());
        assertNull(context.getUser().getUserName());
        assertNull(context.getUser().getSubName());
        
        op = getOperation(SWOperationBean.SET_USER_DETAILS);
        op.setString1("Dick");
        context = SubWarGameAPI.invoke(op);
        assertNull(context.getLastOperationError());
        assertNotNull(context.getUser());
        assertEquals("Dick", context.getUser().getUserName());
        assertNull(context.getUser().getSubName());
        
        op = getOperation(SWOperationBean.SET_USER_DETAILS);
        op.setString2("Ticonderoga");
        context = SubWarGameAPI.invoke(op);
        assertNull(context.getLastOperationError());
        assertNotNull(context.getUser());
        assertEquals("Dick", context.getUser().getUserName());
        assertEquals("Ticonderoga", context.getUser().getSubName());

        op = getOperation(SWOperationBean.SET_USER_DETAILS);
        op.setString1("Jane");
        op.setString2("Lollipop");
        context = SubWarGameAPI.invoke(op);
        assertNull(context.getLastOperationError());
        assertNotNull(context.getUser());
        assertEquals("Jane", context.getUser().getUserName());
        assertEquals("Lollipop", context.getUser().getSubName());

        op = getOperation(SWOperationBean.SET_USER_DETAILS);
        op.setString1("");
        op.setString2("");
        context = SubWarGameAPI.invoke(op);
        assertNull(context.getLastOperationError());
        assertNotNull(context.getUser());
        assertNull(context.getUser().getUserName());
        assertNull(context.getUser().getSubName());

        op = getOperation(SWOperationBean.QUERY_USER);
        context = SubWarGameAPI.invoke(op);
        assertNull(context.getLastOperationError());
        assertNotNull(context.getUser());
        assertNull(context.getUser().getUserName());
        assertNull(context.getUser().getSubName());
    }
}
