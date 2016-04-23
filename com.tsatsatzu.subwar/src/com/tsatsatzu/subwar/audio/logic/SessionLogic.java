package com.tsatsatzu.subwar.audio.logic;

import java.util.HashMap;
import java.util.Map;

import com.tsatsatzu.subwar.audio.data.SWInvocationBean;
import com.tsatsatzu.subwar.audio.data.SWSessionBean;
import com.tsatsatzu.subwar.audio.data.SWStateBean;
import com.tsatsatzu.subwar.game.api.SubWarGameAPI;
import com.tsatsatzu.subwar.game.data.SWContextBean;
import com.tsatsatzu.subwar.game.data.SWOperationBean;
import com.tsatsatzu.subwar.game.data.SWUserBean;
import com.tsatsatzu.subwar.game.logic.IOLogic;

public class SessionLogic
{
    // volitile states
    private static Map<String, SWStateBean> mStates = new HashMap<>();
    
    public static SWInvocationBean loadSession(SWSessionBean ssn)
    {
        SWInvocationBean invocation = new SWInvocationBean();
        invocation.setSession(ssn);

        SWOperationBean op = new SWOperationBean();
        op.setOperation(SWOperationBean.QUERY_USER);
        op.setUserID(ssn.getUserID());
        op.setCredentials(AudioConstLogic.API_KEY);
        SWContextBean context = SubWarGameAPI.invoke(op);
        if (context.getLastOperationError() != null)
            throw new IllegalStateException(context.getLastOperationError());
        invocation.setUser(context.getUser());
        invocation.setGame(context.getGame());
        if (!mStates.containsKey(ssn.getUserID()))
            mStates.put(ssn.getUserID(), new SWStateBean());
        invocation.setState(mStates.get(ssn.getUserID()));
        
        return invocation;
    }
    
    public static void saveSession(SWInvocationBean context)
    {
        SWUserBean user = context.getUser();
        IOLogic.saveUser(user);
    }

    public static void launch(SWInvocationBean ssn)
    {
        if (ssn.getUser().getNumberOfGames() < 1)
        {   // intro 1
            ssn.addSound(AudioConstLogic.SOUND_BOSUN_WHISTLE);
            ssn.addText("Attention. Captain on deck!");
            ssn.addPause();
            ssn.addText("Welcome, Sir.");
            ssn.addText("I am Lieutenant Alexa, your first officer.");
            ssn.addText("Are you ready to hunt some submarines?");
            ssn.getState().setState(AudioConstLogic.STATE_INTRO1_1);
        }
        else
            throw new IllegalStateException("not implemented: launch."+ssn.getUser().getNumberOfGames());
    }

    public static void terminate(SWInvocationBean ssn)
    {
        throw new IllegalStateException("not implemented");
    }

    public static void callMe(SWInvocationBean ssn, String direction)
    {
        throw new IllegalStateException("not implemented");
    }

    public static void callShip(SWInvocationBean ssn, String direction)
    {
        throw new IllegalStateException("not implemented");
    }

}
