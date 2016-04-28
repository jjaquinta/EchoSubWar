package com.tsatsatzu.subwar.audio.logic;

import java.util.HashMap;
import java.util.Map;

import com.tsatsatzu.subwar.audio.data.SWInvocationBean;
import com.tsatsatzu.subwar.audio.data.SWSessionBean;
import com.tsatsatzu.subwar.audio.data.SWStateBean;
import com.tsatsatzu.subwar.game.data.SWOperationBean;
import com.tsatsatzu.subwar.game.data.SWUserBean;
import com.tsatsatzu.subwar.game.logic.IOLogic;
import com.tsatsatzu.utils.obj.StringUtils;

public class SessionLogic
{
    // volitile states
    private static Map<String, SWStateBean> mStates = new HashMap<>();
    
    public static SWInvocationBean loadSession(SWSessionBean ssn)
    {
        SWInvocationBean invocation = new SWInvocationBean();
        invocation.setSession(ssn);

        try
        {
            InvocationLogic.game(invocation, SWOperationBean.QUERY_USER);
            if (!mStates.containsKey(ssn.getUserID()))
                mStates.put(ssn.getUserID(), new SWStateBean());
            invocation.setState(mStates.get(ssn.getUserID()));
        }
        catch (SWAudioException e)
        {
            InvocationLogic.recordException(invocation, e);
        }
        
        return invocation;
    }
    
    public static void saveSession(SWInvocationBean context)
    {
        SWUserBean user = context.getUser();
        IOLogic.saveUser(user);
    }

    public static void launch(SWInvocationBean ssn)
    {
        if (ssn.getUser().getNumberOfGames() < 1)
        {   // intro 1
            ssn.addSound(AudioConstLogic.SOUND_BOSUN_WHISTLE);
            ssn.addText("Attention. Captain on deck!");
            ssn.addPause();
            ssn.addText("Welcome, Sir.");
            ssn.addText("I am Lieutenant Alexa, your first officer.");
            ssn.addText("Are you ready to hunt some submarines?");
            ssn.addReprompt("To launch your ship, say \"yes\".");
            ssn.addReprompt("For more information, say \"no\".");
            ssn.getState().setState(AudioConstLogic.STATE_INTRO1_1);
        }
        else if ((ssn.getUser().getNumberOfGames() < 5) && StringUtils.trivial(ssn.getUser().getUserName()))
        {   // intro 2
            ssn.addSound(AudioConstLogic.SOUND_BOSUN_WHISTLE);
            ssn.addText("Attention. Captain on deck!");
            ssn.addPause();
            ssn.addText("Welcome back, Sir.");
            ssn.addText("It is my pleasure to serve you again.");
            ssn.addText("Would you prefer me to address you by name?");
            ssn.addReprompt("To pick a name, say \"yes\".");
            ssn.addReprompt("For other options, say \"no\".");
            ssn.getState().setState(AudioConstLogic.STATE_INTRO2_1);
        }
        else if ((ssn.getUser().getNumberOfGames() < 10) && StringUtils.trivial(ssn.getUser().getSubName()))
        {   // intro 3
            ssn.addSound(AudioConstLogic.SOUND_BOSUN_WHISTLE);
            ssn.addText("Attention. Captain on deck!");
            ssn.addPause();
            ssn.addText("Welcome back, {captain}.");
            ssn.addText("You seem to be pretty comfortable with your command.");
            ssn.addText("Would you like to name your ship?");
            ssn.addReprompt("To pick a name, say \"yes\".");
            ssn.addReprompt("For other options, say \"no\".");
            ssn.getState().setState(AudioConstLogic.STATE_INTRO3_1);
        }
        else
        {   // intro 4
            ssn.addSound(AudioConstLogic.SOUND_BOSUN_WHISTLE);
            ssn.addText("Attention. Captain on deck!");
            ssn.addPause();
            ssn.addText("Welcome again, {captain}.");
            ssn.addText("{ship} is loaded and ready for patrol.");
            ssn.addText("Just give the word and we will launch.");
            ssn.getState().setState(AudioConstLogic.STATE_INTRO1_4);
        }
    }

    public static void terminate(SWInvocationBean ssn)
    {
        throw new IllegalStateException("not implemented");
    }

    public static void callMe(SWInvocationBean ssn, String name) throws SWAudioException
    {
        if (StringUtils.trivial(name))
        {
            ssn.addText("I'm not sure I caught that.");
            ssn.addText("Can you repeat it?");
            ssn.addText("Just say \"call me David\" and I'll call you that.");
        }
        else
        {
            InvocationLogic.game(ssn, SWOperationBean.SET_USER_DETAILS, name, "");
            ssn.addText("Will do, {captain}.");
            switch (ssn.getState().getState())
            {
                case AudioConstLogic.STATE_INTRO1_4:
                    ssn.addText("Would you like me to tell you about the ship, about combat, consult the leaderboard, or are you ready to launch?");
                    ssn.getState().setState(AudioConstLogic.STATE_INTRO1_4);
                    break;
                default:
                    System.err.println("CallMe: unhandled state - "+ssn.getState().getState());
            }
        }
    }

    public static void callShip(SWInvocationBean ssn, String name) throws SWAudioException
    {
        if (StringUtils.trivial(name))
        {
            ssn.addText("I'm not sure I caught that.");
            ssn.addText("Can you repeat it?");
            ssn.addText("Just say \"call my ship Boston\" and I'll call it that.");
        }
        else
        {
            InvocationLogic.game(ssn, SWOperationBean.SET_USER_DETAILS, "", name);
            ssn.addText("Will do, {captain}.");
            switch (ssn.getState().getState())
            {
                case AudioConstLogic.STATE_INTRO1_4:
                    ssn.addText("Would you like me to tell you about the ship, about combat, consult the leaderboard, or are you ready to launch {ship}?");
                    ssn.getState().setState(AudioConstLogic.STATE_INTRO1_4);
                    break;
                default:
                    System.err.println("CallShip: unhandled state - "+ssn.getState().getState());
            }
        }
    }

}
