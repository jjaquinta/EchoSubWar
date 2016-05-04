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

public class SubWarAlexaAPI
{

    public static void doSessionStarted(SessionStartedRequest request,
            Session session)
    {
    }

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

    public static SpeechletResponse doIntent(IntentRequest request,
            Session session)
    {
        try
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
        catch (Exception e)
        {
            SubWarServlet.debug(e);
            SpeechletResponse resp = exceptionToResponse(e);
            return resp;
        }
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
