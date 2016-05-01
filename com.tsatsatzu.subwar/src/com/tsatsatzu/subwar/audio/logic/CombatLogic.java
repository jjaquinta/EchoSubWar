package com.tsatsatzu.subwar.audio.logic;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.language.AbstractCaverphone;
import org.apache.commons.codec.language.Caverphone2;
import org.apache.commons.codec.language.DoubleMetaphone;

import com.tsatsatzu.subwar.audio.data.SWInvocationBean;
import com.tsatsatzu.subwar.game.data.SWContextBean;
import com.tsatsatzu.subwar.game.data.SWOperationBean;
import com.tsatsatzu.subwar.game.data.SWPingBean;

public class CombatLogic
{
    private static final DoubleMetaphone mDoubleMetaphone = new DoubleMetaphone();
    private static final AbstractCaverphone mCaverphone = new Caverphone2();

    public static void fire(SWInvocationBean ssn, String direction) throws SWAudioException
    {
        int dir = parseDirection(direction, ssn.getState().getLastMove());
        if (dir < 0)
        {
            ssn.addText("You need to tell me which direction you want to fire in, sir.");
            return;
        }
        SWContextBean ret = InvocationLogic.game(ssn, SWOperationBean.TORPEDO, dir, null);
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
}
