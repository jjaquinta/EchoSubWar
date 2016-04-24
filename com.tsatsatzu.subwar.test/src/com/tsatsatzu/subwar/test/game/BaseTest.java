package com.tsatsatzu.subwar.test.game;

import org.junit.Before;

import com.tsatsatzu.subwar.game.data.SWOperationBean;

public class BaseTest
{
    protected static final String TEST_USER = "amadan";
    protected static final String TEST_CREDENTIALS = "xyzzy";

    @Before
    public void setUp()
    {
        // Set I/O driver to the memory model.
        // Testing is going to screw with data.
        // We don't want to save that to Dynamo.
        System.setProperty("ioDriver", "com.tsatsatzu.subwar.game.logic.mem.MemIODriver");
        // Override built in apiKeys with our test API key.
        // That way we don't have to use a real key for testing,
        // and the key is safe to check in while clear text.
        System.setProperty("apiKeys", TEST_CREDENTIALS);
    }
    
    protected SWOperationBean getOperation(String opType)
    {        
        SWOperationBean op = new SWOperationBean();
        op.setOperation(opType);
        op.setUserID(TEST_USER);
        op.setCredentials(TEST_CREDENTIALS);
        return op;
    }

}
