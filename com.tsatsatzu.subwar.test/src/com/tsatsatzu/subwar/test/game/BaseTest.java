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

import org.junit.Before;

import com.tsatsatzu.subwar.game.data.SWOperationBean;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseTest.
 */
public class BaseTest
{
    
    /** The Constant TEST_USER. */
    protected static final String TEST_USER = "amadan";
    
    /** The Constant TEST_CREDENTIALS. */
    protected static final String TEST_CREDENTIALS = "xyzzy";

    /**
     * Sets the up.
     */
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
    
    /**
     * Gets the operation.
     *
     * @param opType the op type
     * @return the operation
     */
    protected SWOperationBean getOperation(String opType)
    {        
        SWOperationBean op = new SWOperationBean();
        op.setOperation(opType);
        op.setUserID(TEST_USER);
        op.setCredentials(TEST_CREDENTIALS);
        return op;
    }

}
