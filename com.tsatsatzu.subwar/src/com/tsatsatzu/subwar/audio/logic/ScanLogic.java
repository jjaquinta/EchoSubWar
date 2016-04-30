package com.tsatsatzu.subwar.audio.logic;

import com.tsatsatzu.subwar.audio.data.SWInvocationBean;
import com.tsatsatzu.subwar.game.data.SWOperationBean;

public class ScanLogic
{

    public static void listen(SWInvocationBean ssn) throws SWAudioException
    {
        InvocationLogic.game(ssn, SWOperationBean.MICROPHONE);
        ssn.addText("Silent running sir.");
        ssn.addPause();
        if (ssn.getGame().getUserPosition().getSoundings().size() > 0)
            PlayLogic.describeSoundings(ssn);
        else
            ssn.addText("Nothing heard, {captain}.");
        ssn.addPause();
        ssn.addText("What next?");
    }

    public static void sonar(SWInvocationBean ssn) throws SWAudioException
    {
        InvocationLogic.game(ssn, SWOperationBean.SONAR);
        ssn.addText("Pinging sonar, sir.");
        ssn.addPause();
        if (ssn.getGame().getUserPosition().getSoundings().size() > 0)
            PlayLogic.describeSoundings(ssn);
        else
            ssn.addText("Nothing heard, {captain}.");
        ssn.addPause();
        ssn.addText("What next?");
    }

}
