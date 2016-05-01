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
                PlayLogic.doStartGame(ssn);
                break;
            case AudioConstLogic.STATE_INTRO1_2:
                combatInfo(ssn);
                ssn.addPause();
                ssn.addText("Are you ready to launch?");
                ssn.addReprompt("Say \"yes\" to launch your ship, \"no\" for more information.");
                ssn.getState().setState(AudioConstLogic.STATE_INTRO1_3);
                break;
            case AudioConstLogic.STATE_INTRO1_3:
                PlayLogic.doStartGame(ssn);
                break;
            case AudioConstLogic.STATE_INTRO2_1:
                ssn.addText("Just say \"call me Diana\" and I値l address you as that.");
                ssn.addText("Try it now.");
                ssn.addReprompt("To set your name, say \"call me Diana\".");
                ssn.getState().setState(AudioConstLogic.STATE_PRE_GAME);
                break;
            case AudioConstLogic.STATE_INTRO3_1:
                ssn.addText("Just say \"call my ship Cincinnati\" and I値l call it that.");
                ssn.addText("You can use the name of any big city or American president.");
                ssn.addText("Try it now.");
                ssn.addReprompt("To set your ship's name, say \"call my ship Cincinnati\".");
                ssn.getState().setState(AudioConstLogic.STATE_PRE_GAME);
                break;
            default:
                throw new SWAudioException("YES:"+ssn.getState().getState()+" not implemented");
        }
    }

    private static void combatInfo(SWInvocationBean ssn)
    {
        ssn.addText("Our duty is to patrol the Acton Straits and destroy any and all enemy submarines you encounter.");
        ssn.addText("The straits are ten kilometers east to west and twenty kilometers north to south.");
        ssn.addText("You can give the order to go in any direction, plus dive or rise, and I will pass it on to the crew.");
        ssn.addText("Finally, you can give the order to fire the torpedoes and we will launch in the last direction we moved,");
        ssn.addText("or whatever direction you specified.");
    }

    public static void no(SWInvocationBean ssn) throws SWAudioException
    {
        switch (ssn.getState().getState())
        {
            case AudioConstLogic.STATE_INTRO1_1:
                ssn.addText("Well, I知 here to help familiarize you with your ship.");
                ssn.addPause();
                shipInfo(ssn);
                ssn.addPause();
                ssn.addText("Would you like to know more about the combat situation we are entering?");
                ssn.addReprompt("Say \"yes\" for more information, or \"no\" to start.");
                ssn.getState().setState(AudioConstLogic.STATE_INTRO1_2);
                break;
            case AudioConstLogic.STATE_INTRO1_2:
            case AudioConstLogic.STATE_INTRO1_3:
            case AudioConstLogic.STATE_INTRO2_1:
            case AudioConstLogic.STATE_INTRO3_1:
                ssn.addText("Would you like me to tell you about the ship, about combat, consult the leaderboard, or are you ready to launch?");
                ssn.getState().setState(AudioConstLogic.STATE_PRE_GAME);
                break;
            default:
                throw new SWAudioException("NO:"+ssn.getState().getState()+" not implemented");
        }
    }

    private static void shipInfo(SWInvocationBean ssn)
    {
        ssn.addText("We are a Marvel Class Hunter Killer submarine.");
        ssn.addText("We can carry six torpedoes and are rated up to a depth of 300 meters."); 
        ssn.addText("We have a top of the line sonar system that can report on any ships within six kilometers."); 
        ssn.addText("However, whenever we release a sonar ping, we also alert everyone else to our location.");
        ssn.addText("We also have underwater microphones and if you order us to listen, we can hear any nearby ships.");
    }

    public static void cancel(SWInvocationBean context)
    {
        throw new IllegalStateException("Need to recover code");
    }

    public static void help(SWInvocationBean ssn) throws SWAudioException
    {
        switch (ssn.getState().getState())
        {
            case AudioConstLogic.STATE_GAME_BASE:
                ssn.addText("Just give the order to move North, South, East or West and I値l pass it on, {captain}.");
                ssn.addText("You may also say Dive or Rise and I値l adjust the ballast.");
                ssn.addText("You can say Fire for me to launch a torpedo, or Sonar and I値l send out a ping.");
                ssn.addText("We can also just wait and listen passively for nearby traffic.");
                ssn.addText("If all is lost, you can order us to return to port.");
                ssn.addPause();
                ssn.addText("What are your orders?");
                break;
            default:
                throw new SWAudioException("HELP:"+ssn.getState().getState()+" not implemented");
        }
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
            case AudioConstLogic.STATE_INTRO1_1:
            case AudioConstLogic.STATE_PRE_GAME:
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
            case AudioConstLogic.STATE_INTRO1_1:
            case AudioConstLogic.STATE_PRE_GAME:
            case AudioConstLogic.STATE_INTRO2_1:
            case AudioConstLogic.STATE_INTRO3_1:
                PlayLogic.doStartGame(ssn);
                break;
            default:
                throw new SWAudioException("START_GAME:"+ssn.getState().getState()+" not implemented");
        }
    }

    public static void ship(SWInvocationBean ssn) throws SWAudioException
    {
        switch (ssn.getState().getState())
        {
            case AudioConstLogic.STATE_PRE_GAME:
                shipInfo(ssn);
                ssn.addPause();
                ssn.addText("Would you like me to tell you about the ship, about combat, consult the leaderboard, or are you ready to launch?");
                break;
            default:
                throw new SWAudioException("SHIP:"+ssn.getState().getState()+" not implemented");
        }
    }

    public static void combat(SWInvocationBean ssn) throws SWAudioException
    {
        switch (ssn.getState().getState())
        {
            case AudioConstLogic.STATE_PRE_GAME:
                combatInfo(ssn);
                ssn.addPause();
                ssn.addText("Would you like me to tell you about the ship, about combat, consult the leaderboard, or are you ready to launch?");
                break;
            default:
                throw new SWAudioException("COMBAT:"+ssn.getState().getState()+" not implemented");
        }
    }
}