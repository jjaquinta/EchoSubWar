package com.tsatsatzu.subwar.alexa.api;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.tsatsatzu.subwar.audio.api.SubWarAudioAPI;
import com.tsatsatzu.subwar.audio.data.SWInvocationBean;
import com.tsatsatzu.subwar.audio.data.SWSessionBean;

public class SubWarAlexaAPI
{

    public static void doSessionStarted(SessionStartedRequest request,
            Session session)
    {
    }

    public static SpeechletResponse doLaunch(LaunchRequest request,
            Session session)
    {
        SWSessionBean ssn = new SWSessionBean();
        ssn.setUserID(session.getUser().getUserId());
        SWInvocationBean inv = SubWarAudioAPI.invoke(ssn, SubWarAudioAPI.CMD_LAUNCH_APP);
        SpeechletResponse resp = invocationToResponse(inv);
        return resp;
    }

    public static SpeechletResponse doIntent(IntentRequest request,
            Session session)
    {
        SWSessionBean ssn = new SWSessionBean();
        ssn.setUserID(session.getUser().getUserId());
        String verb = intentToVerb(request.getIntent());
        String[] args = intentToArgs(request.getIntent());
        int i = 0;
        for (Slot slot : request.getIntent().getSlots().values())
            args[i++] = slot.getValue();
        SWInvocationBean inv = SubWarAudioAPI.invoke(ssn, verb, args);
        SpeechletResponse resp = invocationToResponse(inv);
        return resp;
    }

    public static void doSessionEnded(SessionEndedRequest request,
            Session session)
    {
        SWSessionBean ssn = new SWSessionBean();
        ssn.setUserID(session.getUser().getUserId());
        SubWarAudioAPI.invoke(ssn, SubWarAudioAPI.CMD_TERMINATE_APP);
    }
    
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
    
    private static String[] intentToArgs(Intent intent)
    {
        switch (intent.getName())
        {
            case "FIRE":
                if ((intent.getSlots().size() == 0) || (intent.getSlots().get("dir").getValue().length() == 0))
                    return new String[0];
                else
                    return new String[]{ intent.getSlots().get("dir").getValue() };
            default:
                return new String[0];
        }
    }

    private static SpeechletResponse invocationToResponse(SWInvocationBean inv)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
