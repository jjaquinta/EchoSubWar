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
import com.tsatsatzu.subwar.game.api.SubWarGameAPI;
import com.tsatsatzu.subwar.game.data.SWContextBean;
import com.tsatsatzu.subwar.game.data.SWOperationBean;

public class InvocationLogic
{
    public static SWContextBean game(SWInvocationBean ssn, String opType) throws SWAudioException
    {
        return game(ssn, opType, (String)null, (String)null, (Integer)null, (Integer)null);
    }
    public static SWContextBean game(SWInvocationBean ssn, String opType, String string1, String string2) throws SWAudioException
    {
        return game(ssn, opType, string1, string2, null, null);
    }
    public static SWContextBean game(SWInvocationBean ssn, String opType, Integer int1, Integer int2) throws SWAudioException
    {
        return game(ssn, opType, null, null, int1, int2);
    }
    public static SWContextBean game(SWInvocationBean ssn, String opType, 
            String string1, String string2,
            Integer int1, Integer int2) throws SWAudioException
    {
        SWOperationBean op = new SWOperationBean();
        op.setUserID(ssn.getSession().getUserID());
        op.setCredentials(AudioConstLogic.API_KEY);
        op.setOperation(opType);
        op.setString1(string1);
        op.setString2(string2);
        if (int1 != null)
            op.setInt1(int1);
        if (int2 != null)
            op.setInt1(int2);
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
