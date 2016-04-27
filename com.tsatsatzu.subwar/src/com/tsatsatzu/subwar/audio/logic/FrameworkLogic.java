package com.tsatsatzu.subwar.audio.logic;

import com.tsatsatzu.subwar.audio.data.SWInvocationBean;
import com.tsatsatzu.subwar.game.data.SWOperationBean;

public class FrameworkLogic
{

    public static void yes(SWInvocationBean ssn) throws SWAudioException
    {
        switch (ssn.getState().getState())
        {
            case AudioConstLogic.STATE_INTRO1_1:
                doStartGame(ssn);
                break;
            case AudioConstLogic.STATE_INTRO1_2:
                ssn.addText("Our duty is to patrol the Acton Straits and destroy any and all enemy submarines you encounter.");
                ssn.addText("The straits are ten kilometers east to west and twenty kilometers north to south.");
                ssn.addText("You can give the order to go in any direction, plus dive or rise, and I will pass it on to the crew.");
                ssn.addText("If you order a ping, we will operate the sonar and report the results.");
                ssn.addText("Finally, you can give the order to fire the torpedoes and we will launch in the last direction we moved,");
                ssn.addText("or whatever direction you specified.");
                ssn.addPause();
                ssn.addText("Are you ready to launch?");
                ssn.getState().setState(AudioConstLogic.STATE_INTRO1_3);
                break;
            case AudioConstLogic.STATE_INTRO1_3:
                doStartGame(ssn);
                break;
            default:
                throw new SWAudioException("YES:"+ssn.getState().getState()+" not implemented");
        }
    }

    public static void no(SWInvocationBean ssn) throws SWAudioException
    {
        switch (ssn.getState().getState())
        {
            case AudioConstLogic.STATE_INTRO1_1:
                ssn.addText("Well, I’m here to help familiarize you with your ship.");
                ssn.addPause();
                ssn.addText("We are a Marvel Class Hunter Killer submarine.");
                ssn.addText("We can carry six torpedoes and are rated up to a depth of 300 meters."); 
                ssn.addText("We have a top of the line sonar system that can report on any ships within six kilometers."); 
                ssn.addText("However, whenever we release a sonar ping, we also alert everyone else to our location.");
                ssn.addPause();
                ssn.addText("Would you like to know more about the combat situation we are entering?");
                ssn.getState().setState(AudioConstLogic.STATE_INTRO1_2);
                break;
            case AudioConstLogic.STATE_INTRO1_2:
            case AudioConstLogic.STATE_INTRO1_3:
                ssn.addText("Would you like me to tell you about the ship, about combat, consult the leaderboard, or are you ready to launch?");
                ssn.getState().setState(AudioConstLogic.STATE_INTRO1_4);
                break;
            default:
                throw new SWAudioException("NO:"+ssn.getState().getState()+" not implemented");
        }
    }

    public static void cancel(SWInvocationBean context)
    {
        throw new IllegalStateException("Need to recover code");
    }

    public static void help(SWInvocationBean context)
    {
        throw new IllegalStateException("Need to recover code");
    }

    public static void repeat(SWInvocationBean context)
    {
        throw new IllegalStateException("Need to recover code");
    }

    public static void startOver(SWInvocationBean context)
    {
        throw new IllegalStateException("Need to recover code");
    }

    public static void stop(SWInvocationBean ssn) throws SWAudioException
    {
        switch (ssn.getState().getState())
        {
            case AudioConstLogic.STATE_INTRO1_4:
                ssn.addText("Aye, aye, sir.");
                ssn.addText("Your ship will be waiting any time you want to come back.");
                ssn.setEndSession(true);
                break;
            case AudioConstLogic.STATE_GAME_BASE:
                InvocationLogic.game(ssn, SWOperationBean.EXIT_GAME);
                ssn.addText("Abandoning mission, sir.");
                ssn.addText("Better luck next time.");
                ssn.setEndSession(true);
                break;
            default:
                throw new SWAudioException("STOP:"+ssn.getState().getState()+" not implemented");
        }
    }

    public static void startGame(SWInvocationBean ssn) throws SWAudioException
    {
        switch (ssn.getState().getState())
        {
            case AudioConstLogic.STATE_INTRO1_4:
                doStartGame(ssn);
                break;
            default:
                throw new SWAudioException("START_GAME:"+ssn.getState().getState()+" not implemented");
        }
    }

    private static void doStartGame(SWInvocationBean ssn)
            throws SWAudioException
    {
        InvocationLogic.game(ssn, SWOperationBean.ENTER_GAME);
        ssn.addSound(AudioConstLogic.SOUND_SHIP_LAUNCH);
        ssn.addText("We’ve launched!");
        ssn.getState().setState(AudioConstLogic.STATE_GAME_BASE);
    }
}