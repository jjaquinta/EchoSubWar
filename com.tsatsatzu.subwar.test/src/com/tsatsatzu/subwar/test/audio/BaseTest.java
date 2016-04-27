package com.tsatsatzu.subwar.test.audio;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import org.junit.Before;

import com.tsatsatzu.subwar.audio.api.SubWarAudioAPI;
import com.tsatsatzu.subwar.audio.data.SWInvocationBean;
import com.tsatsatzu.subwar.audio.data.SWSessionBean;

public class BaseTest
{
    protected static final String TEST_USER = "amadan";
    protected static final String TEST_CREDENTIALS = "xyzzy";
    
    private SWSessionBean mSsn;

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
        System.setProperty("audio.api.key", TEST_CREDENTIALS);
        System.setProperty("apiKeys", TEST_CREDENTIALS);
        mSsn = getSession();
    }
    
    protected SWSessionBean getSession()
    {        
        SWSessionBean ssn = new SWSessionBean();
        ssn.setUserID(TEST_USER);
        return ssn;
    }

    protected SWInvocationBean dialog(String verb, String... args)
    {
        System.out.println("Alexa, "+verb);
        SWInvocationBean resp = SubWarAudioAPI.invoke(mSsn, verb, args);
        assertNotNull(resp.getSpokenText());
        assertNotNull(resp.getWrittenText());
        assertNotNull(resp.getRepromptText());
        assertNotNull(resp.getUser());
        System.out.println(resp.getWrittenText());
        assertEquals("unexpected situation", -1, resp.getWrittenText().indexOf("unexpected situation"));
        return resp;
    }
}