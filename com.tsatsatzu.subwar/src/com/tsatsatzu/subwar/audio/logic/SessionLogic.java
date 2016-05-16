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

import java.util.HashMap;
import java.util.Map;

import com.tsatsatzu.subwar.audio.api.SubWarAudioAPI;
import com.tsatsatzu.subwar.audio.data.SWInvocationBean;
import com.tsatsatzu.subwar.audio.data.SWSessionBean;
import com.tsatsatzu.subwar.audio.data.SWStateBean;
import com.tsatsatzu.subwar.game.data.SWOperationBean;
import com.tsatsatzu.subwar.game.data.SWUserBean;
import com.tsatsatzu.subwar.game.logic.IOLogic;
import com.tsatsatzu.utils.obj.StringUtils;

/**
 * The Class SessionLogic.
 * Audio logic around basic session state events
 */
public class SessionLogic
{
    
    /** The States. 
     * The store for the volatile states. Only needs to live within a session.
     * Does not need to persist. */
    private static Map<String, SWStateBean> mStates = new HashMap<>();
    
    /**
     * Load session.
     * Pull information to populate the invocation object so the request can be executed.
     *
     * @param ssn the session
     * @return the invocation bean
     */
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
            normalizeState(invocation);
        }
        catch (SWAudioException e)
        {
            SubWarAudioAPI.debug("Caught in SessionLogic.loadSession():");
            SubWarAudioAPI.debug(e);
            InvocationLogic.recordException(invocation, e);
        }
        SubWarAudioAPI.debug("Loading session, state="+invocation.getState().getState());
        return invocation;
    }
    
    /**
     * Normalize state.
     * Due to random exits and so forth, the state may get out of sync.
     * This performs heuristics to see if that is the case and to correct it.
     *
     * @param invocation the invocation context
     */
    private static void normalizeState(SWInvocationBean invocation)
    {
        switch (invocation.getState().getState())
        {
            case AudioConstLogic.STATE_GAME_BASE:
            case AudioConstLogic.STATE_GAME_ABORT:
                if (invocation.getUser().getInGame() < 0)
                    invocation.getState().setState(AudioConstLogic.STATE_PRE_GAME);
                break;
            case AudioConstLogic.STATE_INITIAL:
            case AudioConstLogic.STATE_INTRO1_1:
            case AudioConstLogic.STATE_INTRO1_2:
            case AudioConstLogic.STATE_INTRO1_3:
            case AudioConstLogic.STATE_PRE_GAME:
            case AudioConstLogic.STATE_INTRO2_1:
            case AudioConstLogic.STATE_INTRO3_1:
                if (invocation.getUser().getInGame() >= 0)
                    invocation.getState().setState(AudioConstLogic.STATE_GAME_BASE);
                break;
        }
    }

    /**
     * Save session.
     * This does basic end-of-call maintenence.
     *
     * @param context the context
     */
    public static void saveSession(SWInvocationBean context)
    {
        SWUserBean user = context.getUser();
        IOLogic.saveUser(user);
        context.getState().setLastSpokenText(context.getSpokenText());
        context.getState().setLastWrittenText(context.getWrittenText());
        context.getState().setLastRepromptText(context.getRepromptText());
        SubWarAudioAPI.debug("Saving session, state="+context.getState().getState());
    }

    /**
     * Launch.
     * Audio logic for launch of interaction.
     *
     * @param ssn the session
     * @throws SWAudioException the audio exception
     */
    public static void launch(SWInvocationBean ssn) throws SWAudioException
    {
        if (ssn.getUser().getInGame() >= 0)
        {
            long elapsed = System.currentTimeMillis() - ssn.getUser().getLastInteraction();
            if (elapsed > AudioConstLogic.TIMEOUT_IDLE)
            {
                InvocationLogic.game(ssn, SWOperationBean.EXIT_GAME);
                // fall through to normal intro
            }
            else
            {
                ssn.addText("Resuming game. ");
                ssn.addPause();
                ssn.getState().setState(AudioConstLogic.STATE_GAME_BASE);
                PlayLogic.describeGame(ssn);
                FrameworkLogic.addGamePrompt(ssn);
                return;
            }
        }
        if (ssn.getUser().getNumberOfGames() < 1)
        {   // intro 1
            ssn.addSound(AudioConstLogic.SOUND_BOSUN_WHISTLE);
            ssn.addText("Attention. Captain on deck!");
            ssn.addPause();
            ssn.addText("Welcome Sir.");
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
            ssn.addText("Welcome back Sir.");
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
            ssn.getState().setState(AudioConstLogic.STATE_PRE_GAME);
        }
    }

    /**
     * Terminate.
     * Audio logic for termination of interaction.
     *
     * @param ssn the session
     */
    public static void terminate(SWInvocationBean ssn)
    {
        // NOOP
    }

    /**
     * Call me.
     * Handles the user making a request to name themselves.
     *
     * @param ssn the session
     * @param name the name
     * @throws SWAudioException the audio exception
     */
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
                case AudioConstLogic.STATE_INITIAL:
                case AudioConstLogic.STATE_INTRO1_1:
                case AudioConstLogic.STATE_INTRO1_2:
                case AudioConstLogic.STATE_INTRO1_3:
                case AudioConstLogic.STATE_INTRO2_1:
                case AudioConstLogic.STATE_INTRO3_1:
                case AudioConstLogic.STATE_PRE_GAME:
                    FrameworkLogic.addPregamePrompt(ssn);
                    ssn.getState().setState(AudioConstLogic.STATE_PRE_GAME);
                    break;
                case AudioConstLogic.STATE_GAME_BASE:
                case AudioConstLogic.STATE_GAME_ABORT:
                    FrameworkLogic.addGamePrompt(ssn);
                    ssn.getState().setState(AudioConstLogic.STATE_GAME_BASE);
                    break;
                default:
                    SubWarAudioAPI.debug("CallMe: unhandled state - "+ssn.getState().getState());
            }
        }
    }

    /**
     * Call ship.
     * Handler for the request to name the ship.
     *
     * @param ssn the session
     * @param name the name
     * @throws SWAudioException the audio exception
     */
    public static void callShip(SWInvocationBean ssn, String name) throws SWAudioException
    {
        if (StringUtils.trivial(name))
        {
            ssn.addText("I'm not sure I caught that.");
            ssn.addText("Can you repeat it?");
            ssn.addText("Just say \"call my ship Boston\" and I'll call it that.");
            return;
        }
        else
        {
            InvocationLogic.game(ssn, SWOperationBean.SET_USER_DETAILS, "", name);
            ssn.addText("Will do, {captain}.");
        }
        switch (ssn.getState().getState())
        {
            case AudioConstLogic.STATE_INITIAL:
            case AudioConstLogic.STATE_INTRO1_1:
            case AudioConstLogic.STATE_INTRO1_2:
            case AudioConstLogic.STATE_INTRO1_3:
            case AudioConstLogic.STATE_INTRO2_1:
            case AudioConstLogic.STATE_INTRO3_1:
            case AudioConstLogic.STATE_PRE_GAME:
                FrameworkLogic.addPregamePrompt(ssn);
                ssn.getState().setState(AudioConstLogic.STATE_PRE_GAME);
                break;
            case AudioConstLogic.STATE_GAME_ABORT:
            case AudioConstLogic.STATE_GAME_BASE:
                FrameworkLogic.addGamePrompt(ssn);
                ssn.getState().setState(AudioConstLogic.STATE_GAME_BASE);
                break;
            default:
                SubWarAudioAPI.debug("CallShip: unhandled state - "+ssn.getState().getState());
        }
    }

}
