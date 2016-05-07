package com.tsatsatzu.subwar.audio.logic;

import com.tsatsatzu.subwar.audio.data.SWInvocationBean;
import com.tsatsatzu.subwar.game.data.SWOperationBean;

public class ScanLogic
{

    public static void listen(SWInvocationBean ssn) throws SWAudioException
    {
        switch (ssn.getState().getState())
        {
            case AudioConstLogic.STATE_PRE_GAME:
                ssn.addText("I'm sorry sir.");
                ssn.addText("We can't use the underwater microphones until we launch.");
                FrameworkLogic.addPregamePrompt(ssn);
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

    public static void sonar(SWInvocationBean ssn) throws SWAudioException
    {
        switch (ssn.getState().getState())
        {
            case AudioConstLogic.STATE_PRE_GAME:
                ssn.addText("I'm sorry sir.");
                ssn.addText("We can't use the sonar until we launch.");
                FrameworkLogic.addPregamePrompt(ssn);
                break;
            case AudioConstLogic.STATE_GAME_ABORT:
                ssn.getState().setState(AudioConstLogic.STATE_GAME_BASE);
                // fall through into normal command
            case AudioConstLogic.STATE_GAME_BASE:
                InvocationLogic.game(ssn, SWOperationBean.SONAR);
                ssn.addSound(AudioConstLogic.SOUND_SONAR);
                ssn.addText("Pinging sonar, sir.");
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
