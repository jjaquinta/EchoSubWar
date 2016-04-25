package com.tsatsatzu.subwar.game.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.tsatsatzu.subwar.game.data.SWGameBean;
import com.tsatsatzu.subwar.game.data.SWGameDetailsBean;
import com.tsatsatzu.subwar.game.data.SWPingBean;
import com.tsatsatzu.subwar.game.data.SWPositionBean;
import com.tsatsatzu.subwar.game.data.SWUserBean;

public class GameLogic
{
    private static Random mRND = new Random();
    private static List<SWGameBean> mGames = new ArrayList<>();

    public static SWGameDetailsBean getGameDetails(int inGame, String userID)
    {
        if ((inGame < 0) || (inGame >= mGames.size()))
            return null;
        SWGameBean game = mGames.get(inGame);
        SWGameDetailsBean details = new SWGameDetailsBean();
        details.setEast(game.getEast());
        details.setWest(game.getWest());
        details.setNorth(game.getNorth());
        details.setSouth(game.getSouth());
        details.setMaxDepth(game.getMaxDepth());
        details.setUserPosition(game.getShips().get(userID));
        return details;
    }

    public static String joinGame(SWUserBean user)
    {
        if (user.getInGame() >= 0)
            return "already in game";
        // find a game with room
        synchronized (mGames)
        {
            int game = 0;
            while (game < mGames.size())
                if (mGames.get(game).getShips().size() < GameConstLogic.MAX_SHIPS_PER_GAME)
                    break;
            if (game == mGames.size())
                mGames.add(newGame());
            doJoinGame(user.getUserID(), game);
            user.setInGame(game);
        }
        return null;
    }

    public static String move(SWUserBean user, int dLat, int dLon, int dDep)
    {
        if (user.getInGame() < 0)
            return "already in game";
        SWGameBean game = mGames.get(user.getInGame());
        String err = doMoveShip(user.getUserID(), dLat, dLon, dDep, game);
        return err;
    }
    
    private static SWGameBean newGame()
    {
        SWGameBean game = new SWGameBean();
        game.setNorth(-GameConstLogic.GAME_HEIGHT/2);
        game.setSouth(GameConstLogic.GAME_HEIGHT/2);
        game.setEast(GameConstLogic.GAME_WIDTH/2);
        game.setWest(-GameConstLogic.GAME_WIDTH/2);
        game.setMaxDepth(GameConstLogic.GAME_DEPTH);
        return game;
    }

    public static void doJoinGame(String id, int idx)
    {
        SWGameBean game = mGames.get(idx);
        synchronized (game)
        {
            SWPositionBean pos = new SWPositionBean();
            pos.setDepth(0);
            pos.setLongitude(mRND.nextInt(GameConstLogic.GAME_WIDTH/2) - GameConstLogic.GAME_WIDTH/4);
            pos.setLattitude(mRND.nextInt(GameConstLogic.GAME_HEIGHT/2) - GameConstLogic.GAME_HEIGHT/4);
            game.getShips().put(id, pos);
        }
    }

    public static void doTorpedo(String id, SWGameBean game, Integer fireDLon,
            Integer fireDLat)
    {
        throw new IllegalStateException("need to recover code");
    }

    public static List<SWPingBean> doPing(String id, SWGameBean game)
    {
        throw new IllegalStateException("need to recover code");
    }

    public static List<SWPingBean> doListen(String id, SWGameBean game)
    {
        throw new IllegalStateException("need to recover code");
    }

    public static String doMoveShip(String id, int dLat, int dLon, int dDep,
            SWGameBean game)
    {
        SWPositionBean pos = game.getShips().get(id);
        if (pos == null)
            return "no in game anymore";
        int newLat = pos.getLattitude() + dLat;
        int newLon = pos.getLongitude() + dLon;
        int newDep = pos.getDepth() - dDep; // -ve = sink, which we actually increment for
        if ((newLat < game.getNorth()) || (newLat > game.getSouth()))
            return "out of bounds";
        if ((newLon < game.getWest()) || (newLon > game.getEast()))
            return "out of bounds";
        if ((newDep < 0) || (newDep > game.getMaxDepth()))
            return "out of bounds";
        pos.setLattitude(newLat);
        pos.setLongitude(newLon);
        pos.setDepth(newDep);
        return null;
    }
    
    public static void testResetToSeed(long seed)
    {
        mRND = new Random(seed);
    }
}