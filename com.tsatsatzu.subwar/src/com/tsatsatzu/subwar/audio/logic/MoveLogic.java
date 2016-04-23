package com.tsatsatzu.subwar.audio.logic;

import com.tsatsatzu.subwar.audio.data.SWInvocationBean;
import com.tsatsatzu.subwar.game.api.SubWarGameAPI;
import com.tsatsatzu.subwar.game.data.SWContextBean;
import com.tsatsatzu.subwar.game.data.SWOperationBean;

public class MoveLogic
{

    public static void startGame(SWInvocationBean ssn)
    {
        if (ssn.getUser().getSubName() != null)
            ssn.addText("Launching the {ship}!");
        else
            ssn.addText("Launching ship!");
        ssn.addSound(AudioConstLogic.SOUND_SHIP_LAUNCH);
        SWOperationBean op = new SWOperationBean();
        op.setOperation(SWOperationBean.ENTER_GAME);
        op.setUserID(ssn.getUser().getUserID());
        op.setCredentials(AudioConstLogic.API_KEY);
        SWContextBean context = SubWarGameAPI.invoke(op);
        ssn.addText(context.getLastOperationMessage());
    }

    public static void north(SWInvocationBean ssn)
    {
        throw new IllegalStateException("not implemented");
    }

    public static void south(SWInvocationBean ssn)
    {
        throw new IllegalStateException("not implemented");
    }

    public static void east(SWInvocationBean ssn)
    {
        throw new IllegalStateException("not implemented");
    }

    public static void west(SWInvocationBean ssn)
    {
        throw new IllegalStateException("not implemented");
    }

    public static void northwest(SWInvocationBean ssn)
    {
        throw new IllegalStateException("not implemented");
    }

    public static void northeast(SWInvocationBean ssn)
    {
        throw new IllegalStateException("not implemented");
    }

    public static void southwest(SWInvocationBean ssn)
    {
        throw new IllegalStateException("not implemented");
    }

    public static void southeast(SWInvocationBean ssn)
    {
        throw new IllegalStateException("not implemented");
    }

    public static void dive(SWInvocationBean ssn)
    {
        throw new IllegalStateException("not implemented");
    }

    public static void rise(SWInvocationBean ssn)
    {
        throw new IllegalStateException("not implemented");
    }

}
