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
package com.tsatsatzu.subwar.alexa.api;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.Image;
import com.amazon.speech.ui.OutputSpeech;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;
import com.amazon.speech.ui.SsmlOutputSpeech;
import com.amazon.speech.ui.StandardCard;
import com.tsatsatzu.subwar.alexa.service.SubWarServlet;
import com.tsatsatzu.subwar.audio.api.SubWarAudioAPI;
import com.tsatsatzu.subwar.audio.data.SWInvocationBean;
import com.tsatsatzu.subwar.audio.data.SWSessionBean;
import com.tsatsatzu.utils.obj.StringUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class SubWarAlexaAPI.
 */
public class SubWarAlexaAPI
{

    /**
     * Do session started.
     *
     * @param request the request
     * @param session the session
     */
    public static void doSessionStarted(SessionStartedRequest request,
            Session session)
    {
    }

    /**
     * Do launch.
     *
     * @param request the request
     * @param session the session
     * @return the speechlet response
     */
    public static SpeechletResponse doLaunch(LaunchRequest request,
            Session session)
    {
        try
        {
            SWSessionBean ssn = new SWSessionBean();
            ssn.setUserID(session.getUser().getUserId());
            SWInvocationBean inv = SubWarAudioAPI.invoke(ssn, SubWarAudioAPI.CMD_LAUNCH_APP);
            SpeechletResponse resp = invocationToResponse(inv);
            return resp;
        }
        catch (Exception e)
        {
            SubWarServlet.debug(e);
            SpeechletResponse resp = exceptionToResponse(e);
            return resp;
        }
    }

    /**
     * Do intent.
     *
     * @param request the request
     * @param session the session
     * @return the speechlet response
     */
    public static SpeechletResponse doIntent(IntentRequest request,
            Session session)
    {
        try
        {
            SWSessionBean ssn = new SWSessionBean();
            ssn.setUserID(session.getUser().getUserId());
            String verb = intentToVerb(request.getIntent());
            String[] args = intentToArgs(request.getIntent());
            SWInvocationBean inv = SubWarAudioAPI.invoke(ssn, verb, args);
            SpeechletResponse resp = invocationToResponse(inv);
            return resp;
        }
        catch (Exception e)
        {
            SubWarServlet.debug(e);
            SpeechletResponse resp = exceptionToResponse(e);
            return resp;
        }
    }

    /**
     * Do session ended.
     *
     * @param request the request
     * @param session the session
     */
    public static void doSessionEnded(SessionEndedRequest request,
            Session session)
    {
        try
        {
            SWSessionBean ssn = new SWSessionBean();
            ssn.setUserID(session.getUser().getUserId());
            SubWarAudioAPI.invoke(ssn, SubWarAudioAPI.CMD_TERMINATE_APP);
        }
        catch (Exception e)
        {
            SubWarServlet.debug(e);
        }
    }
    
    /**
     * Intent to verb.
     *
     * @param intent the intent
     * @return the string
     */
    private static String intentToVerb(Intent intent)
    {
        switch (intent.getName())
        {
            // If we had any intents names that didn't exactly match the verbs,
            // we would add them here as extra cases.
            default:
                return intent.getName();
        }
    }
    
    /**
     * Intent to args.
     *
     * @param intent the intent
     * @return the string[]
     */
    private static String[] intentToArgs(Intent intent)
    {
        switch (intent.getName())
        {
            case "FIRE":
                String dir = getSlotValue(intent, "dir");
                if (dir == null)
                    return new String[0];
                else
                    return new String[]{ dir };
            case "CALLME":
                String name = getSlotValue(intent, "name");
                if (name == null)
                    return new String[0];
                else
                    return new String[]{ name };
            case "CALLSHIP":
                String shipname = getSlotValue(intent, "shipname");
                if (shipname == null)
                    return new String[0];
                else
                    return new String[]{ shipname };
            default:
                return new String[0];
        }
    }

    /**
     * Gets the slot value.
     *
     * @param intent the intent
     * @param name the name
     * @return the slot value
     */
    private static String getSlotValue(Intent intent, String name)
    {
        Slot slot = intent.getSlot(name);
        if (slot == null)
            return null;
        String val = slot.getValue();
        return val;
    }
    
    /**
     * Invocation to response.
     *
     * @param inv the inv
     * @return the speechlet response
     */
    private static SpeechletResponse invocationToResponse(SWInvocationBean inv)
    {
        // Create the Simple card content.
        StandardCard card = new StandardCard();
        card.setTitle("Sub War");
        if (!StringUtils.trivial(inv.getWrittenText()))
        {
            String cardText = inv.getWrittenText();
            card.setText(cardText);
        }
        else
        {
            String cardText = inv.getWrittenText();
            card.setText(cardText);
        }
        if (!StringUtils.trivial(inv.getLargeImage()) || !StringUtils.trivial(inv.getSmallImage()))
        {
            Image i = new Image();
            i.setLargeImageUrl(inv.getLargeImage());
            i.setSmallImageUrl(inv.getSmallImage());
            card.setImage(i);
        }
        // Create the plain text output.
        // Create the speechlet response.
        SpeechletResponse response = new SpeechletResponse();
        response.setShouldEndSession(inv.isEndSession());
        response.setOutputSpeech(toOutputSpeech(inv.getSpokenText()));
        if (!StringUtils.trivial(inv.getRepromptText()))
        {
            String repromptText = inv.getRepromptText();
            Reprompt reprompt = new Reprompt();
            reprompt.setOutputSpeech(toOutputSpeech(repromptText));
            response.setReprompt(reprompt);
        }
        
        response.setCard(card);
        return response;
    }
    
    /**
     * To output speech.
     *
     * @param txt the txt
     * @return the output speech
     */
    private static OutputSpeech toOutputSpeech(String txt)
    {
        if (txt.indexOf('<') < 0)
        {
            PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
            speech.setText(txt);
            return speech;
        }
        else
        {
            SsmlOutputSpeech speech = new SsmlOutputSpeech();
            speech.setSsml("<speak>"+txt+"</speak>");
            return speech;
        }
    }


    /**
     * Exception to response.
     *
     * @param e the e
     * @return the speechlet response
     */
    private static SpeechletResponse exceptionToResponse(Exception e)
    {
        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle(e.getLocalizedMessage());
        String cardText = toCardText(e);
        card.setContent(cardText);
        // Create the plain text output.
        // Create the speechlet response.
        SpeechletResponse response = new SpeechletResponse();
        response.setShouldEndSession(true);
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(e.toString());
        response.setOutputSpeech(speech);
        response.setCard(card);
        return response;
    }
    
    /**
     * To card text.
     *
     * @param e the e
     * @return the string
     */
    private static String toCardText(Throwable e)
    {
        StringBuffer sb = new StringBuffer();
        sb.append(e.toString()+":\n");
        for (StackTraceElement ste : e.getStackTrace())
            sb.append("  "+ste+"\n");
        if (e.getCause() != null)
        {
            sb.append("Caused by ");
            sb.append(toCardText(e.getCause()));
        }
        return sb.toString();
    }
}
