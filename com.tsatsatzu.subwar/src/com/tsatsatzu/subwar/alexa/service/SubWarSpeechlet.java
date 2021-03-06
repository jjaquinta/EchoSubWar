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
 * This directs Alexa calls into our API handlers.
 */
public class SubWarSpeechlet  implements Speechlet
{    
    
    /* (non-Javadoc)
     * @see com.amazon.speech.speechlet.Speechlet#onSessionStarted(com.amazon.speech.speechlet.SessionStartedRequest, com.amazon.speech.speechlet.Session)
     */
    @Override
    public void onSessionStarted(SessionStartedRequest request, Session session)
            throws SpeechletException
    {
        SubWarAlexaAPI.doSessionStarted(request, session);
    }

    /* (non-Javadoc)
     * @see com.amazon.speech.speechlet.Speechlet#onLaunch(com.amazon.speech.speechlet.LaunchRequest, com.amazon.speech.speechlet.Session)
     */
    @Override
    public SpeechletResponse onLaunch(LaunchRequest request, Session session)
            throws SpeechletException
    {
        SpeechletResponse ret = SubWarAlexaAPI.doLaunch(request, session);
        return ret;
    }

    /* (non-Javadoc)
     * @see com.amazon.speech.speechlet.Speechlet#onIntent(com.amazon.speech.speechlet.IntentRequest, com.amazon.speech.speechlet.Session)
     */
    @Override
    public SpeechletResponse onIntent(IntentRequest request, Session session)
            throws SpeechletException
    {
        SpeechletResponse ret = SubWarAlexaAPI.doIntent(request, session);
        return ret;
    }

    /* (non-Javadoc)
     * @see com.amazon.speech.speechlet.Speechlet#onSessionEnded(com.amazon.speech.speechlet.SessionEndedRequest, com.amazon.speech.speechlet.Session)
     */
    @Override
    public void onSessionEnded(SessionEndedRequest request, Session session)
            throws SpeechletException
    {
        SubWarAlexaAPI.doSessionEnded(request, session);
    }
}
