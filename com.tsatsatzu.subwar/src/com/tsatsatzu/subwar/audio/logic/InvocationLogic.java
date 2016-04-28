package com.tsatsatzu.subwar.audio.logic;

import com.tsatsatzu.subwar.audio.data.SWInvocationBean;
import com.tsatsatzu.subwar.game.api.SubWarGameAPI;
import com.tsatsatzu.subwar.game.data.SWContextBean;
import com.tsatsatzu.subwar.game.data.SWOperationBean;

public class InvocationLogic
{
    public static SWContextBean game(SWInvocationBean ssn, String opType) throws SWAudioException
    {
        return game(ssn, opType, null, null);
    }
    public static SWContextBean game(SWInvocationBean ssn, String opType, String string1, String string2) throws SWAudioException
    {
        SWOperationBean op = new SWOperationBean();
        op.setUserID(ssn.getSession().getUserID());
        op.setCredentials(AudioConstLogic.API_KEY);
        op.setOperation(opType);
        op.setString1(string1);
        op.setString2(string2);
        SWContextBean context = SubWarGameAPI.invoke(op);
        if (context.getLastOperationError() != null)
            throw new SWAudioException(context.getLastOperationError());
        ssn.setUser(context.getUser());
        ssn.setGame(context.getGame());
        return context;
    }

    public static void recordException(SWInvocationBean context,
            SWAudioException e)
    {
        context.addText("An unexpected situation happened: "+e.getMessage());
        context.addWrittenLine("");
        for (StackTraceElement ele : e.getStackTrace())
        {
            context.addWrittenLine(ele.toString());
        }
        context.setEndSession(true);
    }
}
