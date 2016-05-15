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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;

import com.tsatsatzu.subwar.audio.api.SubWarAudioAPI;
import com.tsatsatzu.subwar.audio.data.SWInvocationBean;
import com.tsatsatzu.subwar.audio.data.SWSessionBean;
import com.tsatsatzu.subwar.game.api.SubWarGameAPI;
import com.tsatsatzu.subwar.game.data.SWContextBean;
import com.tsatsatzu.subwar.game.data.SWOperationBean;

/**
 * The Class BaseTest.
 * This is the parent class of all audio layer tests.
 */
public class BaseTest
{
    
    /** The Constant TEST_USER.
     * We need to authenticate as someone. This is the user to use
     * to login for testing. */
    protected static final String TEST_USER = "amadan";
    
    /** The Constant TEST_CREDENTIALS. 
     * This is that user's credentials. */
    protected static final String TEST_CREDENTIALS = "xyzzy";
    
    /** The session object with the user's credentials in it. */
    private SWSessionBean mSsn;

    /**
     * This is the set up run before each test.
     */
    @Before
    public void setUp()
    {
        // Set I/O driver to the memory model.
        // Testing is going to screw with data.
        // We don't want to save that to Dynamo.
        if (System.getProperty("ioDriver") == null)
            System.setProperty("ioDriver", "com.tsatsatzu.subwar.game.logic.mem.MemIODriver");
        // Override built in apiKeys with our test API key.
        // That way we don't have to use a real key for testing,
        // and the key is safe to check in while clear text.
        System.setProperty("audio.api.key", TEST_CREDENTIALS);
        System.setProperty("apiKeys", TEST_CREDENTIALS);
        mSsn = getSession();
        SWOperationBean op = new SWOperationBean();
        op.setOperation(SWOperationBean.TEST);
        op.setInt1(SWOperationBean.TEST_CLEAR_USER);
        op.setUserID(TEST_USER);
        op.setCredentials(TEST_CREDENTIALS);
        SubWarGameAPI.invoke(op);
        System.out.println("-------------------------------------------------------");
    }
    
    /**
     * Gets a pre-filled session object.
     *
     * @return the session
     */
    protected SWSessionBean getSession()
    {        
        SWSessionBean ssn = new SWSessionBean();
        ssn.setUserID(TEST_USER);
        return ssn;
    }

    /**
     * Dialog.
     * This conducts a call to the audio layer. It makes sure it runs with no error,
     * and tests conformity for the reply.
     *
     * @param verb the verb
     * @param args any args for the verb
     * @return the SW invocation bean
     */
    protected SWInvocationBean dialog(String verb, String... args)
    {
        System.out.println("Alexa, "+verb);
        SWInvocationBean resp = SubWarAudioAPI.invoke(mSsn, verb, args);
        assertNotNull("No spoken text", resp.getSpokenText());
        assertNotEquals("No spoken text", 0, resp.getSpokenText().trim().length());
        assertNotNull("No written text", resp.getWrittenText());
        assertNotEquals("No written text", 0, resp.getWrittenText().trim().length());
        System.out.println(resp.getWrittenText());
        assertEquals("unexpected situation", -1, resp.getWrittenText().indexOf("unexpected situation"));
        if (!resp.isEndSession())
        {
            assertNotNull("No reprompt", resp.getRepromptText());
            assertNotEquals("No reprompt", 0, resp.getRepromptText().trim().length());
        }
        assertNotNull(resp.getUser());
        System.out.println("state="+resp.getState().getState());
        return resp;
    }
    
    /**
     * Gets an operation object. Fill in baisc parameters.
     *
     * @param opType the type of operation used
     * @return the populated operation
     */
    protected SWOperationBean getOperation(String opType)
    {        
        SWOperationBean op = new SWOperationBean();
        op.setOperation(opType);
        op.setUserID(TEST_USER);
        op.setCredentials(TEST_CREDENTIALS);
        return op;
    }

    /**
     * Ask the Ai to make a move.
     */
    protected void aiMove()
    {
        SWOperationBean op = getOperation(SWOperationBean.TEST);
        op.setInt1(SWOperationBean.TEST_AI_MOVE);
        SWContextBean context = SubWarGameAPI.invoke(op);
        assertNull(context.getLastOperationError());
    }
}