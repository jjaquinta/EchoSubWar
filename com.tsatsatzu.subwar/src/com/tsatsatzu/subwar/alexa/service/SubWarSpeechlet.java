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
