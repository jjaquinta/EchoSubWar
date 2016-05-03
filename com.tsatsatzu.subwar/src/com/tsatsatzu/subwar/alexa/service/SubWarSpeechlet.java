package com.tsatsatzu.subwar.alexa.service;

import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.tsatsatzu.subwar.alexa.api.SubWarAlexaAPI;

/**
 * This sample shows how to create a simple speechlet for handling speechlet requests.
 */
public class SubWarSpeechlet  implements Speechlet
{    
    @Override
    public void onSessionStarted(SessionStartedRequest request, Session session)
            throws SpeechletException
    {
        SubWarAlexaAPI.doSessionStarted(request, session);
    }

    @Override
    public SpeechletResponse onLaunch(LaunchRequest request, Session session)
            throws SpeechletException
    {
        SpeechletResponse ret = SubWarAlexaAPI.doLaunch(request, session);
        return ret;
    }

    @Override
    public SpeechletResponse onIntent(IntentRequest request, Session session)
            throws SpeechletException
    {
        SpeechletResponse ret = SubWarAlexaAPI.doIntent(request, session);
        return ret;
    }

    @Override
    public void onSessionEnded(SessionEndedRequest request, Session session)
            throws SpeechletException
    {
        SubWarAlexaAPI.doSessionEnded(request, session);
    }
}
