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
import com.tsatsatzu.subwar.game.data.SWGameDetailsBean;
import com.tsatsatzu.subwar.game.data.SWOperationBean;
import com.tsatsatzu.subwar.game.data.SWPositionBean;
import com.tsatsatzu.subwar.game.logic.GameConstLogic;

/**
 * The Class MoveLogic.
 * Handlers for moving the ship.
 */
public class MoveLogic
{
    
    /**
     * North.
     * The handler for moving North.
     *
     * @param ssn the session
     * @throws SWAudioException the audio exception
     */
    public static void north(SWInvocationBean ssn) throws SWAudioException
    {
        doMove(ssn, 0, -1, 0, SWOperationBean.NORTH);
    }

    /**
     * South.
     * The handler for moving South.
     *
     * @param ssn the session
     * @throws SWAudioException the audio exception
     */
    public static void south(SWInvocationBean ssn) throws SWAudioException
    {
        doMove(ssn, 0, 1, 0, SWOperationBean.SOUTH);
    }

    /**
     * East.
     * The handler for moving East.
     *
     * @param ssn the session
     * @throws SWAudioException the audio exception
     */
    public static void east(SWInvocationBean ssn) throws SWAudioException
    {
        doMove(ssn, 1, 0, 0, SWOperationBean.EAST);
    }

    /**
     * West.
     * The handler for moving West.
     *
     * @param ssn the session
     * @throws SWAudioException the audio exception
     */
    public static void west(SWInvocationBean ssn) throws SWAudioException
    {
        doMove(ssn, -1, 0, 0, SWOperationBean.WEST);
    }

    /**
     * Northwest.
     * The handler for moving Northwest.
     *
     * @param ssn the session
     * @throws SWAudioException the audio exception
     */
    public static void northwest(SWInvocationBean ssn) throws SWAudioException
    {
        doMove(ssn, -1, -1, 0, SWOperationBean.NORTHWEST);
    }

    /**
     * Northeast.
     * The handler for moving Northeast.
     *
     * @param ssn the session
     * @throws SWAudioException the audio exception
     */
    public static void northeast(SWInvocationBean ssn) throws SWAudioException
    {
        doMove(ssn, 1, -1, 0, SWOperationBean.NORTHEAST);
    }

    /**
     * Southwest.
     * The handler for moving Southwest.
     *
     * @param ssn the session
     * @throws SWAudioException the audio exception
     */
    public static void southwest(SWInvocationBean ssn) throws SWAudioException
    {
        doMove(ssn, -1, 1, 0, SWOperationBean.SOUTHWEST);
    }

    /**
     * Southeast.
     * The handler for moving Southeast.
     *
     * @param ssn the session
     * @throws SWAudioException the audio exception
     */
    public static void southeast(SWInvocationBean ssn) throws SWAudioException
    {
        doMove(ssn, 1, 1, 0, SWOperationBean.SOUTHEAST);
    }

    /**
     * Dive.
     * The handler for diving.
     *
     * @param ssn the session
     * @throws SWAudioException the audio exception
     */
    public static void dive(SWInvocationBean ssn) throws SWAudioException
    {
        doMove(ssn, 0, 0, 1, SWOperationBean.LOWER);
    }

    /**
     * Rise.
     * The handler for rising.
     *
     * @param ssn the session
     * @throws SWAudioException the audio exception
     */
    public static void rise(SWInvocationBean ssn) throws SWAudioException
    {
        doMove(ssn, 0, 0, -1, SWOperationBean.RAISE);
    }

    /**
     * Do move.
     * Handle the actual move
     *
     * @param ssn the session
     * @param dLon the delta longitude
     * @param dLat the delta lattitude
     * @param dDep the delta depth
     * @param dir the direction
     * @throws SWAudioException the audio exception
     */
    private static void doMove(SWInvocationBean ssn, int dLon, int dLat, int dDep, int dir) throws SWAudioException
    {
        switch (ssn.getState().getState())
        {
            case AudioConstLogic.STATE_GAME_ABORT:
                ssn.getState().setState(AudioConstLogic.STATE_GAME_BASE);
                // fall through into normal command
            case AudioConstLogic.STATE_GAME_BASE:
                SWGameDetailsBean game = ssn.getGame();
                SWPositionBean pos = game.getUserPosition();
                if (pos == null)
                    throw new SWAudioException(GameConstLogic.ERR_YOU_HAVE_BEEN_DESTROYED);
                int newLon = pos.getLongitude() + dLon;
                int newLat = pos.getLattitude() + dLat;
                int newDep = pos.getDepth() + dDep;
                if (newDep > game.getMaxDepth())
                {
                    ssn.addText("We can't go that deep {captain}!");
                    ssn.addText("{Ship} isn't rated for that depth.");
                    ssn.addPause();
                    ssn.addText("Just say rise it you meant for us to go up.");
                    return;
                }
                if (newDep < 0)
                {
                    ssn.addText("Um, {captain}, we're already on the surface.");
                    ssn.addPause();
                    ssn.addText("Tell us to dive if you want us to go down instead.");
                    return;
                }
                if (newLon < game.getWest())
                {
                    ssn.addText("I can't do that, {captain}.");
                    ssn.addText("That would run {ship} on to the Western shore.");
                    ssn.addPause();
                    ssn.addText("What direction would you like us to go?");
                    return;
                }
                if (newLon > game.getEast())
                {
                    ssn.addText("I can't comply, {captain}.");
                    ssn.addText("{Ship} would run up on the Eastern shore if we tried.");
                    ssn.addPause();
                    ssn.addText("What direction would you like us to go?");
                    return;
                }
                if (newLat < game.getNorth())
                {
                    ssn.addText("That would take us out of the Acton straights, {captain}.");
                    ssn.addText("There aren't any targets there.");
                    ssn.addPause();
                    ssn.addText("You might consider giving the order to go South instead.");
                    return;
                }
                if (newLat > game.getSouth())
                {
                    ssn.addText("That would take us out of the Acton straights, {captain}.");
                    ssn.addText("I don't think we're ready to give up yet.");
                    ssn.addPause();
                    ssn.addText("You might consider giving the order to go North instead.");
                    return;
                }
                InvocationLogic.game(ssn, SWOperationBean.MOVE, dir, null);
                ssn.addSound(AudioConstLogic.SOUND_MOTOR_RUNNING);
                //InvocationLogic.
                PlayLogic.describeGame(ssn);
                ssn.addPause();
                ssn.addText("What are your orders now?");
                if ((dir != SWOperationBean.LOWER) && (dir != SWOperationBean.RAISE))
                    ssn.getState().setLastMove(dir);
                break;
            case AudioConstLogic.STATE_INITIAL:
            case AudioConstLogic.STATE_INTRO1_1:
            case AudioConstLogic.STATE_INTRO1_2:
            case AudioConstLogic.STATE_INTRO1_3:
            case AudioConstLogic.STATE_INTRO2_1:
            case AudioConstLogic.STATE_INTRO3_1:
            case AudioConstLogic.STATE_PRE_GAME:
                ssn.addText("We need to launch first before we can move!");
                FrameworkLogic.addPregamePrompt(ssn);
                break;
            default:
                throw new SWAudioException("MOVE:"+ssn.getState().getState()+" not implemented");
        }
    }

    /**
     * Dock.
     * Handler for the dock verb.
     *
     * @param ssn the session
     * @throws SWAudioException the audio exception
     */
    public static void dock(SWInvocationBean ssn) throws SWAudioException
    {
        switch (ssn.getState().getState())
        {
            case AudioConstLogic.STATE_GAME_ABORT:
            case AudioConstLogic.STATE_GAME_BASE:
                InvocationLogic.game(ssn, SWOperationBean.EXIT_GAME);
                ssn.getState().setState(AudioConstLogic.STATE_PRE_GAME);
                ssn.addText("Returning to dock sir.");
                FrameworkLogic.addPregamePrompt(ssn);
                break;
            case AudioConstLogic.STATE_INITIAL:
            case AudioConstLogic.STATE_INTRO1_1:
            case AudioConstLogic.STATE_INTRO1_2:
            case AudioConstLogic.STATE_INTRO1_3:
            case AudioConstLogic.STATE_INTRO2_1:
            case AudioConstLogic.STATE_INTRO3_1:
            case AudioConstLogic.STATE_PRE_GAME:
                ssn.addText("We're already docked sir.");
                ssn.getState().setState(AudioConstLogic.STATE_PRE_GAME);
                FrameworkLogic.addPregamePrompt(ssn);
                break;
            default:
                throw new SWAudioException("DOCK:"+ssn.getState().getState()+" not implemented");
        }
    }

}
