package com.tsatsatzu.subwar.game.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.tsatsatzu.subwar.game.data.SWContextBean;
import com.tsatsatzu.subwar.game.data.SWGameBean;
import com.tsatsatzu.subwar.game.data.SWPingBean;
import com.tsatsatzu.subwar.game.data.SWPositionBean;

public class GameLogic
{
    private static Random mRND = new Random();
    private static List<SWGameBean> mGames = new ArrayList<>();

    public static void joinGame(SWContextBean context)
    {
        if (context.getUser().getInGame() < 0)
        {
            // find a game with room
            synchronized (mGames)
            {
                int game = 0;
                while (game < mGames.size())
                    if (mGames.get(game).getShips().size() < GameConstLogic.MAX_SHIPS_PER_GAME)
                        break;
                if (game == mGames.size())
                    mGames.add(newGame());
                doJoinGame(context.getUser().getUserID(), game);
                context.getUser().setInGame(game);
            }
        }
    }
    
    private static SWGameBean newGame()
    {
        SWGameBean game = new SWGameBean();
        game.setNorth(-GameConstLogic.GAME_HEIGHT/2);
        game.setSouth(GameConstLogic.GAME_HEIGHT/2);
        game.setEast(-GameConstLogic.GAME_WIDTH/2);
        game.setWest(GameConstLogic.GAME_WIDTH/2);
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

    public static void doMoveShip(String id, int dLat, int dLon, int dDep,
            SWGameBean game)
    {
        throw new IllegalStateException("need to recover code");
    }
    
    public static void testResetToSeed(long seed)
    {
        mRND = new Random(seed);
    }
}