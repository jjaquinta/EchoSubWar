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
import com.tsatsatzu.utils.obj.StringUtils;

public class SubWarAudioAPI
{
    public static final String CMD_LAUNCH_APP = "$LAUNCH";
    public static final String CMD_TERMINATE_APP = "$TERMINATE";
    public static final String CMD_CANCEL = "AMAZON.CancelIntent";
    public static final String CMD_HELP = "AMAZON.HelpIntent";
    public static final String CMD_REPEAT = "AMAZON.RepeatIntent";
    public static final String CMD_STARTOVER = "AMAZON.StartOverIntent";
    public static final String CMD_STOP = "AMAZON.StopIntent";
    public static final String CMD_YES = "AMAZON.YesIntent";
    public static final String CMD_NO = "AMAZON.NoIntent";
    public static final String CMD_NORTH = "NORTH";
    public static final String CMD_SOUTH = "SOUTH";
    public static final String CMD_EAST = "EAST";
    public static final String CMD_WEST = "WEST";
    public static final String CMD_NORTHWEST = "NORTHWEST";
    public static final String CMD_NORTHEAST = "NORTHEAST";
    public static final String CMD_SOUTHWEST = "SOUTHWEST";
    public static final String CMD_SOUTHEAST = "SOUTHEAST";
    public static final String CMD_DIVE = "DIVE";
    public static final String CMD_RISE = "RISE";
    public static final String CMD_LISTEN = "LISTEN";
    public static final String CMD_SONAR = "SONAR";
    public static final String CMD_FIRE = "FIRE";
    public static final String CMD_CALL_ME = "CALLME";
    public static final String CMD_CALL_SHIP = "CALLSHIP";
    public static final String CMD_LAUNCH = "LAUNCH";
    
    public static SWInvocationBean invoke(SWSessionBean ssn, String verb, String... args)
    {
        SWInvocationBean context = SessionLogic.loadSession(ssn);
        try
        {
            invokeVerb(context, verb, args);
            if (StringUtils.trivial(context.getRepromptText()))
                setGenericReprompt(context);
        }
        catch (SWAudioException e)
        {
            InvocationLogic.recordException(context, e);
        }
        SessionLogic.saveSession(context);
        return context;
    }

    private static void invokeVerb(SWInvocationBean context, String verb,
            String... args) throws SWAudioException
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
                CombatLogic.fire(context, args[0]);
                break;
            case CMD_CALL_ME:
                SessionLogic.callMe(context, args[0]);
                break;
            case CMD_CALL_SHIP:
                SessionLogic.callShip(context, args[0]);                
                break;     
            default:
                throw new SWAudioException("Unknown verb: "+verb);
        }
    }

    private static void setGenericReprompt(SWInvocationBean context)
    {
        switch (context.getState().getState())
        {
            case AudioConstLogic.STATE_GAME_BASE:
                context.addReprompt("Try move, listen, ping, or fire.");
                break;
            case AudioConstLogic.STATE_PRE_GAME:
                context.addReprompt("Choose ship, combat, leaderboard, or launch.");
                break;
            default:
                System.err.println("Don't know how to set generic reprompt for state="+context.getState().getState());
                break;
        }
    }
}
