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
package com.tsatsatzu.subwar.audio.logic;

import com.tsatsatzu.subwar.audio.data.SWInvocationBean;
import com.tsatsatzu.subwar.game.data.SWOperationBean;
import com.tsatsatzu.subwar.game.logic.GameConstLogic;

// TODO: Auto-generated Javadoc
/**
 * The Class FrameworkLogic.
 */
public class FrameworkLogic
{

    /**
     * Yes.
     *
     * @param ssn the ssn
     * @throws SWAudioException the SW audio exception
     */
    public static void yes(SWInvocationBean ssn) throws SWAudioException
    {
        switch (ssn.getState().getState())
        {
            case AudioConstLogic.STATE_INITIAL:
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
            case AudioConstLogic.STATE_PRE_GAME:
                PlayLogic.doStartGame(ssn);
                break;
            case AudioConstLogic.STATE_GAME_BASE:
                ssn.addText("I'm sorry, {captain}. I'm not sure what you are saying 'yes' to.");
                addGamePrompt(ssn);
                break;
            case AudioConstLogic.STATE_GAME_ABORT:
                returnToDock(ssn);
                break;
            default:
                throw new SWAudioException("YES:"+ssn.getState().getState()+" not implemented");
        }
    }

    /**
     * No.
     *
     * @param ssn the ssn
     * @throws SWAudioException the SW audio exception
     */
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
                addPregamePrompt(ssn);
                ssn.getState().setState(AudioConstLogic.STATE_PRE_GAME);
                break;
            case AudioConstLogic.STATE_INITIAL:
            case AudioConstLogic.STATE_PRE_GAME:
                ssn.addText("I'm sorry, {captain}. I'm not sure what you are saying 'no' to.");
                addPregamePrompt(ssn);
                break;
            case AudioConstLogic.STATE_GAME_BASE:
                ssn.addText("I'm sorry, {captain}. I'm not sure what you are saying 'no' to.");
                addGamePrompt(ssn);
                break;
            case AudioConstLogic.STATE_GAME_ABORT:
                ssn.addText("OK. Back to battle!");
                addGamePrompt(ssn);
                break;
            default:
                throw new SWAudioException("NO:"+ssn.getState().getState()+" not implemented");
        }
    }

    /**
     * Combat info.
     *
     * @param ssn the ssn
     */
    private static void combatInfo(SWInvocationBean ssn)
    {
        ssn.addText("Our duty is to patrol the Acton Straits and destroy any and all enemy submarines you encounter.");
        ssn.addText("The straits are ten kilometers east to west and twenty kilometers north to south.");
        ssn.addText("You can give the order to go in any direction, plus dive or rise, and I will pass it on to the crew.");
        ssn.addText("Finally, you can give the order to fire the torpedoes and we will fire in the last direction we moved,");
        ssn.addText("or whatever direction you specified.");
    }

    /**
     * Adds the pregame prompt.
     *
     * @param ssn the ssn
     */
    public static void addPregamePrompt(SWInvocationBean ssn)
    {
        if ((ssn.getSpokenText().length() > 0) && !ssn.getSpokenText().endsWith(AudioConstLogic.SOUND_PAUSE))
            ssn.addPause();
        ssn.addText("Would you like me to tell you about the ship, about combat, consult the leaderboard, or are you ready to launch?");
    }

    /**
     * Adds the game prompt.
     *
     * @param ssn the ssn
     */
    public static void addGamePrompt(SWInvocationBean ssn)
    {
        ssn.addPause();
        ssn.addText("Try giving an order to move, fire, listen, or ping the sonar.");
    }

    /**
     * Ship info.
     *
     * @param ssn the ssn
     */
    private static void shipInfo(SWInvocationBean ssn)
    {
        ssn.addText("We are a Marvel Class Hunter Killer submarine.");
        ssn.addText("We can carry six torpedoes and are rated up to a depth of 300 meters."); 
        ssn.addText("We have a top of the line sonar system that can report on any ships within six kilometers."); 
        ssn.addText("However, whenever we release a sonar ping, we also alert everyone else to our location.");
        ssn.addText("We also have underwater microphones and if you order us to listen, we can hear any nearby ships.");
    }

    /**
     * Cancel.
     *
     * @param ssn the ssn
     * @throws SWAudioException the SW audio exception
     */
    public static void cancel(SWInvocationBean ssn) throws SWAudioException
    {
        startOver(ssn);
    }

    /**
     * Help.
     *
     * @param ssn the ssn
     * @throws SWAudioException the SW audio exception
     */
    public static void help(SWInvocationBean ssn) throws SWAudioException
    {
        switch (ssn.getState().getState())
        {
            case AudioConstLogic.STATE_INITIAL:
            case AudioConstLogic.STATE_INTRO1_1:
            case AudioConstLogic.STATE_INTRO1_2:
            case AudioConstLogic.STATE_INTRO1_3:
            case AudioConstLogic.STATE_INTRO2_1:
            case AudioConstLogic.STATE_INTRO3_1:
            case AudioConstLogic.STATE_PRE_GAME:
                ssn.addText("To learn more about the game, say ship or combat.");
                ssn.addText("To see the best players, say leaderboard.");
                ssn.addText("Or say launch to start the game.");
                addPregamePrompt(ssn);
                break;
            case AudioConstLogic.STATE_GAME_BASE:
                ssn.addText("Just give the order to move North, South, East or West and I値l pass it on, {captain}.");
                ssn.addText("You may also say Dive or Rise and I値l adjust the ballast.");
                ssn.addText("You can say Fire for me to launch a torpedo, or Sonar and I値l send out a ping.");
                ssn.addText("We can also just wait and listen passively for nearby traffic.");
                ssn.addText("If all is lost, you can order us to return to port.");
                addGamePrompt(ssn);
                break;
            case AudioConstLogic.STATE_GAME_ABORT:
                ssn.addText("Say yes to abort and return to dock, no to keep on with the mission.");
                break;
            default:
                throw new SWAudioException("HELP:"+ssn.getState().getState()+" not implemented");
        }
    }

    /**
     * Repeat.
     *
     * @param ssn the ssn
     * @throws SWAudioException the SW audio exception
     */
    public static void repeat(SWInvocationBean ssn) throws SWAudioException
    {
        if (ssn.getState().getLastSpokenText() != null)
        {
            ssn.setSpokenText(ssn.getState().getLastSpokenText());
            ssn.setWrittenText(ssn.getState().getLastWrittenText());
            ssn.setRepromptText(ssn.getState().getLastRepromptText());
            switch (ssn.getState().getState())
            {
                case AudioConstLogic.STATE_INTRO1_1:
                case AudioConstLogic.STATE_INTRO1_2:
                case AudioConstLogic.STATE_INTRO1_3:
                case AudioConstLogic.STATE_INTRO2_1:
                case AudioConstLogic.STATE_INTRO3_1:
                case AudioConstLogic.STATE_INITIAL:
                case AudioConstLogic.STATE_PRE_GAME:
                    addPregamePrompt(ssn);
                    break;
                case AudioConstLogic.STATE_GAME_ABORT:
                case AudioConstLogic.STATE_GAME_BASE:
                    addGamePrompt(ssn);
                    break;
                default:
                    throw new SWAudioException("REPEAT:"+ssn.getState().getState()+" not implemented");
            }
        }
        else
        {
            ssn.addText("I'm not sure what you want to repeat.");
            ssn.addText("I haven't said anything yet!");
        }
    }

    /**
     * Start over.
     *
     * @param ssn the ssn
     * @throws SWAudioException the SW audio exception
     */
    public static void startOver(SWInvocationBean ssn) throws SWAudioException
    {
        switch (ssn.getState().getState())
        {
            case AudioConstLogic.STATE_GAME_BASE:
                if (ssn.getGame().getUserPosition() == null)
                    throw new SWAudioException(GameConstLogic.ERR_YOU_HAVE_BEEN_DESTROYED);
                if (ssn.getGame().getUserPosition().getTorpedoes() == 0)
                    returnToDock(ssn);
                else
                {
                    ssn.addText("We've still got torpedoes left.");
                    ssn.addText("Are you sure you want to give up now?");
                    ssn.getState().setState(AudioConstLogic.STATE_GAME_ABORT);
                }
                break;
            case AudioConstLogic.STATE_GAME_ABORT:
                if (ssn.getGame().getUserPosition() == null)
                    throw new SWAudioException(GameConstLogic.ERR_YOU_HAVE_BEEN_DESTROYED);
                returnToDock(ssn);
                break;
            case AudioConstLogic.STATE_INITIAL:
            case AudioConstLogic.STATE_INTRO1_1:
            case AudioConstLogic.STATE_INTRO1_2:
            case AudioConstLogic.STATE_INTRO1_3:
            case AudioConstLogic.STATE_INTRO2_1:
            case AudioConstLogic.STATE_INTRO3_1:
            case AudioConstLogic.STATE_PRE_GAME:
                ssn.addText("Starting over again.");
                addPregamePrompt(ssn);
                break;
            default:
                throw new SWAudioException("START OVER:"+ssn.getState().getState()+" not implemented");
        }
    }

    /**
     * Return to dock.
     *
     * @param ssn the ssn
     * @throws SWAudioException the SW audio exception
     */
    private static void returnToDock(SWInvocationBean ssn) throws SWAudioException
    {
        ssn.addText("Returning to dock sir.");
        if (ssn.getGame().getUserPosition().getHits() > 0)
            ssn.addText("Let's stock up again and get back out there!");
        else
            ssn.addText("Better luck next time.");
        InvocationLogic.game(ssn, SWOperationBean.EXIT_GAME);
        ssn.getState().setState(AudioConstLogic.STATE_PRE_GAME);
        addPregamePrompt(ssn);
    }

    /**
     * Stop.
     *
     * @param ssn the ssn
     * @throws SWAudioException the SW audio exception
     */
    public static void stop(SWInvocationBean ssn) throws SWAudioException
    {
        switch (ssn.getState().getState())
        {
            case AudioConstLogic.STATE_INITIAL:
            case AudioConstLogic.STATE_INTRO1_1:
            case AudioConstLogic.STATE_INTRO1_2:
            case AudioConstLogic.STATE_INTRO1_3:
            case AudioConstLogic.STATE_INTRO2_1:
            case AudioConstLogic.STATE_INTRO3_1:
            case AudioConstLogic.STATE_PRE_GAME:
                ssn.addText("Aye aye sir.");
                ssn.addText("Your ship will be waiting any time you want to come back.");
                ssn.setEndSession(true);
                break;
            case AudioConstLogic.STATE_GAME_ABORT:
            case AudioConstLogic.STATE_GAME_BASE:
                InvocationLogic.game(ssn, SWOperationBean.EXIT_GAME);
                ssn.addText("Abandoning mission sir.");
                ssn.addText("Better luck next time.");
                ssn.setEndSession(true);
                break;
            default:
                throw new SWAudioException("STOP:"+ssn.getState().getState()+" not implemented");
        }
    }

    /**
     * Start game.
     *
     * @param ssn the ssn
     * @throws SWAudioException the SW audio exception
     */
    public static void startGame(SWInvocationBean ssn) throws SWAudioException
    {
        switch (ssn.getState().getState())
        {
            case AudioConstLogic.STATE_INITIAL:
            case AudioConstLogic.STATE_INTRO1_1:
            case AudioConstLogic.STATE_INTRO1_2:
            case AudioConstLogic.STATE_INTRO1_3:
            case AudioConstLogic.STATE_INTRO2_1:
            case AudioConstLogic.STATE_INTRO3_1:
            case AudioConstLogic.STATE_PRE_GAME:
                PlayLogic.doStartGame(ssn);
                break;
            case AudioConstLogic.STATE_GAME_ABORT:
                ssn.getState().setState(AudioConstLogic.STATE_GAME_BASE);
            case AudioConstLogic.STATE_GAME_BASE:
                ssn.addText("We've already launched {captain}.");
                addGamePrompt(ssn);
                break;
            default:
                throw new SWAudioException("START_GAME:"+ssn.getState().getState()+" not implemented");
        }
    }

    /**
     * Ship.
     *
     * @param ssn the ssn
     * @throws SWAudioException the SW audio exception
     */
    public static void ship(SWInvocationBean ssn) throws SWAudioException
    {
        switch (ssn.getState().getState())
        {
            case AudioConstLogic.STATE_INITIAL:
            case AudioConstLogic.STATE_INTRO1_1:
            case AudioConstLogic.STATE_INTRO1_2:
            case AudioConstLogic.STATE_INTRO1_3:
            case AudioConstLogic.STATE_INTRO2_1:
            case AudioConstLogic.STATE_INTRO3_1:
            case AudioConstLogic.STATE_PRE_GAME:
                shipInfo(ssn);
                ssn.addPause();
                addPregamePrompt(ssn);
                ssn.getState().setState(AudioConstLogic.STATE_PRE_GAME);
                break;
            case AudioConstLogic.STATE_GAME_ABORT:
                ssn.getState().setState(AudioConstLogic.STATE_GAME_BASE);
            case AudioConstLogic.STATE_GAME_BASE:
                shipInfo(ssn);
                ssn.addPause();
                addGamePrompt(ssn);
                break;
            default:
                throw new SWAudioException("SHIP:"+ssn.getState().getState()+" not implemented");
        }
    }

    /**
     * Combat.
     *
     * @param ssn the ssn
     * @throws SWAudioException the SW audio exception
     */
    public static void combat(SWInvocationBean ssn) throws SWAudioException
    {
        switch (ssn.getState().getState())
        {
            case AudioConstLogic.STATE_INITIAL:
            case AudioConstLogic.STATE_INTRO1_1:
            case AudioConstLogic.STATE_INTRO1_2:
            case AudioConstLogic.STATE_INTRO1_3:
            case AudioConstLogic.STATE_INTRO2_1:
            case AudioConstLogic.STATE_INTRO3_1:
            case AudioConstLogic.STATE_PRE_GAME:
                combatInfo(ssn);
                ssn.addPause();
                addPregamePrompt(ssn);
                ssn.getState().setState(AudioConstLogic.STATE_PRE_GAME);
                break;
            case AudioConstLogic.STATE_GAME_ABORT:
                ssn.getState().setState(AudioConstLogic.STATE_GAME_BASE);
            case AudioConstLogic.STATE_GAME_BASE:
                combatInfo(ssn);
                ssn.addPause();
                addGamePrompt(ssn);
                break;
            default:
                throw new SWAudioException("COMBAT:"+ssn.getState().getState()+" not implemented");
        }
    }
}