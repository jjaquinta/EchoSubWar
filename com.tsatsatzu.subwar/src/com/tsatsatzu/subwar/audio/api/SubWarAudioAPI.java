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
package com.tsatsatzu.subwar.audio.api;

import com.tsatsatzu.subwar.audio.data.SWInvocationBean;
import com.tsatsatzu.subwar.audio.data.SWSessionBean;
import com.tsatsatzu.subwar.audio.logic.AudioConstLogic;
import com.tsatsatzu.subwar.audio.logic.CombatLogic;
import com.tsatsatzu.subwar.audio.logic.FrameworkLogic;
import com.tsatsatzu.subwar.audio.logic.InvocationLogic;
import com.tsatsatzu.subwar.audio.logic.MoveLogic;
import com.tsatsatzu.subwar.audio.logic.SWAudioException;
import com.tsatsatzu.subwar.audio.logic.ScanLogic;
import com.tsatsatzu.subwar.audio.logic.SessionLogic;
import com.tsatsatzu.subwar.game.api.ISubWarGameLogger;
import com.tsatsatzu.subwar.game.api.SubWarGameAPI;
import com.tsatsatzu.subwar.game.data.SWOperationBean;
import com.tsatsatzu.subwar.game.logic.GameConstLogic;
import com.tsatsatzu.utils.obj.StringUtils;

/**
 * The Class SubWarAudioAPI.
 * This is the primary input point for the audio layer.
 */
public class SubWarAudioAPI
{
    
    /** The Constant CMD_LAUNCH_APP. */
    public static final String CMD_LAUNCH_APP = "$LAUNCH";
    
    /** The Constant CMD_TERMINATE_APP. */
    public static final String CMD_TERMINATE_APP = "$TERMINATE";
    
    /** The Constant CMD_CANCEL. */
    public static final String CMD_CANCEL = "AMAZON.CancelIntent";
    
    /** The Constant CMD_HELP. */
    public static final String CMD_HELP = "AMAZON.HelpIntent";
    
    /** The Constant CMD_REPEAT. */
    public static final String CMD_REPEAT = "AMAZON.RepeatIntent";
    
    /** The Constant CMD_STARTOVER. */
    public static final String CMD_STARTOVER = "AMAZON.StartOverIntent";
    
    /** The Constant CMD_STOP. */
    public static final String CMD_STOP = "AMAZON.StopIntent";
    
    /** The Constant CMD_YES. */
    public static final String CMD_YES = "AMAZON.YesIntent";
    
    /** The Constant CMD_NO. */
    public static final String CMD_NO = "AMAZON.NoIntent";
    
    /** The Constant CMD_NORTH. */
    public static final String CMD_NORTH = "NORTH";
    
    /** The Constant CMD_SOUTH. */
    public static final String CMD_SOUTH = "SOUTH";
    
    /** The Constant CMD_EAST. */
    public static final String CMD_EAST = "EAST";
    
    /** The Constant CMD_WEST. */
    public static final String CMD_WEST = "WEST";
    
    /** The Constant CMD_NORTHWEST. */
    public static final String CMD_NORTHWEST = "NORTHWEST";
    
    /** The Constant CMD_NORTHEAST. */
    public static final String CMD_NORTHEAST = "NORTHEAST";
    
    /** The Constant CMD_SOUTHWEST. */
    public static final String CMD_SOUTHWEST = "SOUTHWEST";
    
    /** The Constant CMD_SOUTHEAST. */
    public static final String CMD_SOUTHEAST = "SOUTHEAST";
    
    /** The Constant CMD_DIVE. */
    public static final String CMD_DIVE = "DIVE";
    
    /** The Constant CMD_RISE. */
    public static final String CMD_RISE = "RISE";
    
    /** The Constant CMD_LISTEN. */
    public static final String CMD_LISTEN = "LISTEN";
    
    /** The Constant CMD_SONAR. */
    public static final String CMD_SONAR = "SONAR";
    
    /** The Constant CMD_FIRE. */
    public static final String CMD_FIRE = "FIRE";
    
    /** The Constant CMD_CALL_ME. */
    public static final String CMD_CALL_ME = "CALLME";
    
    /** The Constant CMD_CALL_SHIP. */
    public static final String CMD_CALL_SHIP = "CALLSHIP";
    
    /** The Constant CMD_LAUNCH. */
    public static final String CMD_LAUNCH = "LAUNCH";
    
    /** The Constant CMD_DOCK. */
    public static final String CMD_DOCK = "DOCK";
    
    /** The Constant CMD_SHIP. */
    public static final String CMD_SHIP = "SHIP";
    
    /** The Constant CMD_COMBAT. */
    public static final String CMD_COMBAT = "COMBAT";
    
    /** The Constant CMD_LEADERS. */
    public static final String CMD_LEADERS = "LEADERS";
    
    /** The Logger. 
     * The default logger reports debug statements to standard error. */
    private static ISubWarAudioLogger mLogger = new ISubWarAudioLogger() {        
        @Override
        public void debug(Throwable t)
        {
            t.printStackTrace();
        }
        
        @Override
        public void debug(String msg)
        {
            System.err.println(msg);
        }
    };
    static
    {
        SubWarGameAPI.setLogger(new ISubWarGameLogger() {            
            @Override
            public void debug(Throwable t)
            {
                SubWarAudioAPI.debug(t);
            }            
            @Override
            public void debug(String msg)
            {
                SubWarAudioAPI.debug(msg);
            }
        });
    }

    /**
     * Invoke.
     * This is the primary entry point for the audio layer.
     * A session object, plus the verb and direct objects are passed in. An invocation result object
     * is passed back with all the text in it.
     *
     * @param ssn the session
     * @param verb the verb
     * @param args the direct objects for the verb
     * @return the invocation context
     */
    public static SWInvocationBean invoke(SWSessionBean ssn, String verb, String... args)
    {
        debug("Invoking "+verb);
        SWInvocationBean context = SessionLogic.loadSession(ssn);
        try
        {
            invokeVerb(context, verb, args);
            if (StringUtils.trivial(context.getRepromptText()))
                setGenericReprompt(context);
            debug("Reply "+context.getSpokenText());
        }
        catch (SWAudioException e)
        {
            debug("Caught in SubWarAudioAPI.invoke():");
            debug(e);
            InvocationLogic.recordException(context, e);
        }
        SessionLogic.saveSession(context);
        return context;
    }

    /**
     * Invoke verb.
     * Once the framing logic is done, do the actual verb processing.
     *
     * @param context the context
     * @param verb the verb
     * @param args the direct objects
     * @throws SWAudioException the audio exception
     */
    private static void invokeVerb(SWInvocationBean context, String verb,
            String... args) throws SWAudioException
    {
        try
        {
            switch (verb)
            {
                case CMD_LAUNCH_APP:
                    SessionLogic.launch(context);
                    break;
                case CMD_TERMINATE_APP:
                    SessionLogic.terminate(context);
                    break;
                case CMD_YES:
                    FrameworkLogic.yes(context);
                    break;
                case CMD_NO:
                    FrameworkLogic.no(context);
                    break;
                case CMD_CANCEL:
                    FrameworkLogic.cancel(context);
                    break;
                case CMD_HELP:
                    FrameworkLogic.help(context);
                    break;
                case CMD_REPEAT:
                    FrameworkLogic.repeat(context);
                    break;
                case CMD_STARTOVER:
                    FrameworkLogic.startOver(context);
                    break;
                case CMD_STOP:
                    FrameworkLogic.stop(context);
                    break;
                case CMD_LAUNCH:
                    FrameworkLogic.startGame(context);
                    break;
                case CMD_NORTH:
                    MoveLogic.north(context);
                    break;
                case CMD_SOUTH:
                    MoveLogic.south(context);
                    break;
                case CMD_EAST:
                    MoveLogic.east(context);
                    break;
                case CMD_WEST:
                    MoveLogic.west(context);
                    break;
                case CMD_NORTHWEST:
                    MoveLogic.northwest(context);
                    break;
                case CMD_NORTHEAST:
                    MoveLogic.northeast(context);
                    break;
                case CMD_SOUTHWEST:
                    MoveLogic.southwest(context);
                    break;
                case CMD_SOUTHEAST:
                    MoveLogic.southeast(context);
                    break;
                case CMD_DIVE:
                    MoveLogic.dive(context);
                    break;
                case CMD_RISE:
                    MoveLogic.rise(context);
                    break;
                case CMD_LISTEN:
                    ScanLogic.listen(context);
                    break;
                case CMD_SONAR:
                    ScanLogic.sonar(context);
                    break;
                case CMD_FIRE:
                    CombatLogic.fire(context, args.length > 0 ? args[0] : "");
                    break;
                case CMD_CALL_ME:
                    SessionLogic.callMe(context, args.length > 0 ? args[0] : "");
                    break;
                case CMD_CALL_SHIP:
                    SessionLogic.callShip(context, args.length > 0 ? args[0] : "");                
                    break;     
                case CMD_SHIP:
                    FrameworkLogic.ship(context);                
                    break;     
                case CMD_COMBAT:
                    FrameworkLogic.combat(context);                
                    break;     
                case CMD_LEADERS:
                    CombatLogic.leaders(context);                
                    break;     
                case CMD_DOCK:
                    MoveLogic.dock(context);                
                    break;     
                default:
                    throw new SWAudioException("Unknown verb: "+verb);
            }
        }
        catch (SWAudioException e)
        {
            handleException(context, e);
        }
        if (!CMD_REPEAT.equals(verb))
        {
            context.getState().setLastVerb(verb);
            context.getState().setLastArgs(args);
        }
    }

    /**
     * Handle exception.
     * Exceptions from the lower game layer are not always fatal.
     * Specifically your ship may have been blow up between commands.
     * This is handled at this point.
     *
     * @param ssn the session
     * @param e the exception
     * @throws SWAudioException the audio exception
     */
    private static void handleException(SWInvocationBean ssn,
            SWAudioException e) throws SWAudioException
    {
        if (e.getMessage().equals(GameConstLogic.ERR_YOU_HAVE_BEEN_DESTROYED))
        {
            ssn.addSound(AudioConstLogic.SOUND_EXPLOSION);
            ssn.addText("Oh, dear. We got torpedoed!");
            ssn.addText("Launching lifeboats and returning to dock.");
            FrameworkLogic.addPregamePrompt(ssn);
            ssn.getState().setState(AudioConstLogic.STATE_PRE_GAME);
            if (ssn.getUser().getInGame() >= 0)
                InvocationLogic.game(ssn, SWOperationBean.EXIT_GAME);
            return;
        }
        else
            throw e;
    }

    /**
     * Sets the generic reprompt.
     * If the reprompt text has not already been supplied, it is added here.
     *
     * @param context the new generic reprompt
     */
    private static void setGenericReprompt(SWInvocationBean context)
    {
        switch (context.getState().getState())
        {
            case AudioConstLogic.STATE_GAME_BASE:
                context.addReprompt("Try move, listen, ping, or fire.");
                break;
            case AudioConstLogic.STATE_INITIAL:
            case AudioConstLogic.STATE_INTRO1_1:
            case AudioConstLogic.STATE_INTRO1_2:
            case AudioConstLogic.STATE_INTRO1_3:
            case AudioConstLogic.STATE_INTRO2_1:
            case AudioConstLogic.STATE_INTRO3_1:
            case AudioConstLogic.STATE_PRE_GAME:
                context.addReprompt("Choose ship, combat, leaderboard, or launch.");
                break;
            case AudioConstLogic.STATE_GAME_ABORT:
                context.addReprompt("Say yes to abort and return to dock, no to keep on with the mission.");
                break;
            default:
                SubWarAudioAPI.debug("Don't know how to set generic reprompt for state="+context.getState().getState());
                break;
        }
    }
    
    /**
     * Sets the logger.
     * Lets the default debug message handling be overridden.
     *
     * @param logger the new logger
     */
    public static void setLogger(ISubWarAudioLogger logger)
    {
        mLogger = logger;
    }
    
    /**
     * Debug.
     *
     * @param msg the message
     */
    public static void debug(String msg)
    {
        if (mLogger != null)
            if (msg.startsWith("GAME:"))
                mLogger.debug(msg);
            else
                mLogger.debug("AUDIO: "+msg);
    }
    
    /**
     * Debug.
     *
     * @param e the exception
     */
    public static void debug(Throwable e)
    {
        if (mLogger != null)
            mLogger.debug(e);
    }
}
