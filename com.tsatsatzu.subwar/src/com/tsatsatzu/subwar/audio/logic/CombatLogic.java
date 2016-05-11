package com.tsatsatzu.subwar.audio.logic;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.language.AbstractCaverphone;
import org.apache.commons.codec.language.Caverphone2;
import org.apache.commons.codec.language.DoubleMetaphone;

import com.tsatsatzu.subwar.audio.data.SWInvocationBean;
import com.tsatsatzu.subwar.game.data.SWContextBean;
import com.tsatsatzu.subwar.game.data.SWOperationBean;
import com.tsatsatzu.subwar.game.data.SWPingBean;
import com.tsatsatzu.subwar.game.data.SWUserBean;
import com.tsatsatzu.utils.obj.StringUtils;

public class CombatLogic
{
    private static final DoubleMetaphone mDoubleMetaphone = new DoubleMetaphone();
    private static final AbstractCaverphone mCaverphone = new Caverphone2();

    public static void fire(SWInvocationBean ssn, String direction) throws SWAudioException
    {
        switch (ssn.getState().getState())
        {
            case AudioConstLogic.STATE_GAME_ABORT:
                ssn.getState().setState(AudioConstLogic.STATE_GAME_BASE);
            case AudioConstLogic.STATE_GAME_BASE:
                int dir = parseDirection(direction, ssn.getState().getLastMove());
                if (dir < 0)
                {
                    ssn.addText("You need to tell me which direction you want to fire in, sir.");
                    return;
                }
                if (ssn.getGame().getUserPosition().getTorpedoes() == 0)
                {
                    ssn.addText("You don't have any torpedoes left.");
                    ssn.addText("We should probably return to dock.");
                    return;
                }
                SWContextBean ret = null;
                ret = InvocationLogic.game(ssn, SWOperationBean.TORPEDO, dir, null);
                int hits = Integer.parseInt(ret.getLastOperationMessage());
                ssn.addSound(AudioConstLogic.SOUND_TORPEDO);
                ssn.addText("Torpedo away, {captain}!");
                ssn.addPause();
                ssn.addSound(AudioConstLogic.SOUND_EXPLOSION);
                if (hits == 0)
                    ssn.addText("Looks like a miss.");
                else
                {
                    if (hits == 1)
                        ssn.addText("Direct hit!");
                    else
                        ssn.addText("Wow, we managed to hit "+hits+" subs in one go!");
                    if (hits != ssn.getGame().getUserPosition().getHits())
                        ssn.addText("That brings our total to "+ssn.getGame().getUserPosition().getHits()+" kills this mission.");
                }
                ssn.addPause();
                ssn.addText("What are your orders now?");
                ssn.getState().setLastMove(dir);
                break;
            case AudioConstLogic.STATE_INITIAL:
            case AudioConstLogic.STATE_INTRO1_1:
            case AudioConstLogic.STATE_INTRO1_2:
            case AudioConstLogic.STATE_INTRO1_3:
            case AudioConstLogic.STATE_INTRO2_1:
            case AudioConstLogic.STATE_INTRO3_1:
            case AudioConstLogic.STATE_PRE_GAME:
                ssn.addText("We need to launch first before we can fire our torpedos!");
                FrameworkLogic.addPregamePrompt(ssn);
                ssn.getState().setState(AudioConstLogic.STATE_PRE_GAME);
                break;
            default:
                throw new SWAudioException("FIRE:"+ssn.getState().getState()+" not implemented");
        }
    }

    private static int parseDirection(String direction, int lastMove)
    {
        int dir = parseExactMatch(direction);
        if (dir >= 0)
            return dir;
        dir = parseDoubleMetaphone(direction);
        if (dir >= 0)
            return dir;
        dir = parseCaverphone(direction);
        if (dir >= 0)
            return dir;
        return lastMove;
    }

    private static int parseExactMatch(String direction)
    {
        return parseMatch(direction, SWPingBean.DIRECTIONS);
    }

    private static int parseDoubleMetaphone(String direction)
    {
        String dir = mDoubleMetaphone.encode(direction);
        return parseMatch(dir, SWPingBean.DIRECTIONS_DM);
    }

    private static int parseCaverphone(String direction)
    {
        try
        {
            String dir = mCaverphone.encode(direction);
            return parseMatch(dir, SWPingBean.DIRECTIONS_DM);
        }
        catch (EncoderException e)
        {
            return -1;
        }
    }

    private static int parseMatch(String direction, String[] directions)
    {
        for (int i = SWOperationBean.NORTH; i <= SWOperationBean.NORTHWEST; i += 2)
            if (directions[i].equalsIgnoreCase(direction))
                return i;
        return -1;
    }

    public static void leaders(SWInvocationBean ssn) throws SWAudioException
    {
        SWContextBean context = InvocationLogic.game(ssn, SWOperationBean.LEADERS, AudioConstLogic.MAX_LEADERS, null);
        List<String> leaders = new ArrayList<>();
        for (int i = 0; i < context.getLeaders().size(); i++)
        {
            SWUserBean leader = context.getLeaders().get(i);
            StringBuffer txt = new StringBuffer();
            txt.append(ResponseLogic.ORDINAL[i]+" place ");
            if (!StringUtils.trivial(leader.getUserName()))
            {
                if (!StringUtils.trivial(leader.getTitle()))
                    txt.append(leader.getTitle()+" ");
                txt.append(leader.getUserName()+" ");
            }
            if (!StringUtils.trivial(leader.getSubName()))
                txt.append("commanding The "+leader.getSubName()+" with ");
            txt.append(leader.getNumberOfKills()+" kills");
            leaders.add(txt.toString());
        }
        int success = 0;
        if (ssn.getUser().getNumberOfShots() > 0)
            success = (100*ssn.getUser().getNumberOfKills())/ssn.getUser().getNumberOfShots();
        ssn.addText(ResponseLogic.andList(leaders));
        ssn.addText("You have launched "+ssn.getUser().getNumberOfShots()+" torpedoes and have sunk "
                +ssn.getUser().getNumberOfKills()+" subs, for a success rate of "+success+" percent.");
        ssn.addText("You have embarked on "+ssn.getUser().getNumberOfGames()+" missions.");
        ssn.addPause();
        ssn.addText("Would you like me to tell you about the ship, about combat, consult the leaderboard, or are you ready to launch?");
    }
}
