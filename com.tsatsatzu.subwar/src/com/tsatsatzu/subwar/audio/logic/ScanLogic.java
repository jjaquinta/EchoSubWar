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

/**
 * The Class ScanLogic.
 * Audio logic around basic scanning verbs
 */
public class ScanLogic
{

    /**
     * Listen.
     * Handle the listen verb.
     *
     * @param ssn the session
     * @throws SWAudioException the audio exception
     */
    public static void listen(SWInvocationBean ssn) throws SWAudioException
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
                ssn.addText("I'm sorry sir.");
                ssn.addText("We can't use the underwater microphones until we launch.");
                FrameworkLogic.addPregamePrompt(ssn);
                ssn.getState().setState(AudioConstLogic.STATE_PRE_GAME);
                break;
            case AudioConstLogic.STATE_GAME_ABORT:
                ssn.getState().setState(AudioConstLogic.STATE_GAME_BASE);
                // fall through into normal command
            case AudioConstLogic.STATE_GAME_BASE:
                InvocationLogic.game(ssn, SWOperationBean.MICROPHONE);
                ssn.addText("Silent running sir.");
                ssn.addPause();
                if (ssn.getGame().getUserPosition().getSoundings().size() > 0)
                    PlayLogic.describeSoundings(ssn);
                else
                    ssn.addText("Nothing heard, {captain}.");
                ssn.addPause();
                ssn.addText("What next?");
                break;
            default:
                throw new SWAudioException("LISTEN:"+ssn.getState().getState()+" not implemented");
        }
    }

    /**
     * Sonar.
     * Handle the ping verb.
     *
     * @param ssn the session
     * @throws SWAudioException the audio exception
     */
    public static void sonar(SWInvocationBean ssn) throws SWAudioException
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
                ssn.addText("I'm sorry sir.");
                ssn.addText("We can't use the sonar until we launch.");
                FrameworkLogic.addPregamePrompt(ssn);
                ssn.getState().setState(AudioConstLogic.STATE_PRE_GAME);
                break;
            case AudioConstLogic.STATE_GAME_ABORT:
                ssn.getState().setState(AudioConstLogic.STATE_GAME_BASE);
                // fall through into normal command
            case AudioConstLogic.STATE_GAME_BASE:
                InvocationLogic.game(ssn, SWOperationBean.SONAR);
                ssn.addSound(AudioConstLogic.SOUND_SONAR);
                ssn.addText("Pinging sonar sir.");
                ssn.addSound(AudioConstLogic.SOUND_SONAR);
                ssn.addPause();
                ssn.addSound(AudioConstLogic.SOUND_SONAR);
                if (ssn.getGame().getUserPosition().getSoundings().size() > 0)
                    PlayLogic.describeSoundings(ssn);
                else
                    ssn.addText("Nothing heard, {captain}.");
                ssn.addPause();
                ssn.addText("What next?");
                break;
            default:
                throw new SWAudioException("SONAR:"+ssn.getState().getState()+" not implemented");
        }
    }

}
