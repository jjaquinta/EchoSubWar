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
package com.tsatsatzu.subwar.game.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.tsatsatzu.subwar.game.api.SubWarGameAPI;
import com.tsatsatzu.subwar.game.data.SWGameBean;
import com.tsatsatzu.subwar.game.data.SWGameDetailsBean;
import com.tsatsatzu.subwar.game.data.SWPingBean;
import com.tsatsatzu.subwar.game.data.SWPositionBean;
import com.tsatsatzu.subwar.game.data.SWUserBean;
import com.tsatsatzu.subwar.game.logic.ai.IComputerPlayer;
import com.tsatsatzu.subwar.game.logic.ai.SimplePlayer;

/**
 * The Class GameLogic.
 * Core business logic for running the game.
 */
public class GameLogic
{
    /** The Constant SUCCESS. */
    public static final int SUCCESS = 0;
    
    /** The random number source. */
    private static Random mRND = new Random();
    
    /** The Games.
     * There is a maximum number of users allowed per game. So when that is exceeded we have to
     * create a new shard. */
    private static List<SWGameBean> mGames = new ArrayList<>();

    /**
     * Gets the game details.
     *
     * @param inGame the index of the game
     * @param userID the user id
     * @return the game details
     */
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

    /**
     * Join game.
     * Adds a human user to a game.
     *
     * @param user the user
     * @return success/fail code
     * @throws SWGameException the game exception
     */
    public static int joinGame(SWUserBean user) throws SWGameException
    {
        if (user.getInGame() >= 0)
            throw new SWGameException(GameConstLogic.ERR_ALREADY_IN_GAME);
        // find a game with room
        synchronized (mGames)
        {
            int game = 0;
            while (game < mGames.size())
                if (mGames.get(game).getShips().size() < GameConstLogic.MAX_SHIPS_PER_GAME)
                    break;
            if (game == mGames.size())
                mGames.add(newGame());
            updateGame(mGames.get(game));
            doJoinGame(mGames.get(game), user.getUserID());
            user.setInGame(game);
        }
        user.setNumberOfGames(user.getNumberOfGames() + 1);
        return SUCCESS;
    }

    /**
     * Leave game.
     * A human user is leaving the game
     *
     * @param user the user
     * @return success/fail code
     * @throws SWGameException the game exception
     */
    public static int leaveGame(SWUserBean user) throws SWGameException
    {
        if (user.getInGame() < 0)
            throw new SWGameException(GameConstLogic.ERR_NOT_IN_GAME);
        SWGameBean game = mGames.get(user.getInGame());
        updateGame(game);
        doLeaveGame(game, user.getUserID());
        user.setInGame(-1);
        return SUCCESS;
    }

    /**
     * Move.
     * A human user is making a move
     *
     * @param user the user
     * @param dLon the delta longitude
     * @param dLat the delta lattitude
     * @param dDep the delta depth
     * @return success/fail code
     * @throws SWGameException the game exception
     */
    public static int move(SWUserBean user, int dLon, int dLat, int dDep) throws SWGameException
    {
        if (user.getInGame() < 0)
            throw new SWGameException(GameConstLogic.ERR_NOT_IN_GAME);
        SWGameBean game = mGames.get(user.getInGame());
        int ret = doMoveShip(user.getUserID(), dLon, dLat, dDep, game);
        return ret;
    }

    /**
     * Listen.
     * A human is using the underwater microphone.
     *
     * @param user the user
     * @return success/fail code
     * @throws SWGameException the game exception
     */
    public static int listen(SWUserBean user) throws SWGameException
    {
        if (user.getInGame() < 0)
            throw new SWGameException(GameConstLogic.ERR_NOT_IN_GAME);
        SWGameBean game = mGames.get(user.getInGame());
        updateGame(game);
        SWPositionBean pos = game.getShips().get(user.getUserID());
        if (pos == null)
            throw new SWGameException(GameConstLogic.ERR_YOU_HAVE_BEEN_DESTROYED);
        List<SWPingBean> pings = doListen(user.getUserID(), game, System.currentTimeMillis());
        pos.getSoundings().addAll(pings);
        return SUCCESS;
    }

    /**
     * Ping.
     * A human is using the sonar
     *
     * @param user the user
     * @return success/fail code
     * @throws SWGameException the game exception
     */
    public static int ping(SWUserBean user) throws SWGameException
    {
        if (user.getInGame() < 0)
            throw new SWGameException(GameConstLogic.ERR_NOT_IN_GAME);
        SWGameBean game = mGames.get(user.getInGame());
        updateGame(game);
        SWPositionBean pos = game.getShips().get(user.getUserID());
        if (pos == null)
            throw new SWGameException(GameConstLogic.ERR_YOU_HAVE_BEEN_DESTROYED);
        List<SWPingBean> pings = doPing(user.getUserID(), game, System.currentTimeMillis());
        pos.getSoundings().addAll(pings);
        return SUCCESS;
    }

    /**
     * Fire.
     * A human is firing the torpedo.
     *
     * @param user the user
     * @param fireDLon the fire delta longitude
     * @param fireDLat the fire delta lattitude
     * @return success/fail code
     * @throws SWGameException the game exception
     */
    public static int fire(SWUserBean user, int fireDLon, int fireDLat) throws SWGameException
    {
        if (user.getInGame() < 0)
            throw new SWGameException(GameConstLogic.ERR_NOT_IN_GAME);
        SWGameBean game = mGames.get(user.getInGame());
        updateGame(game);
        SWPositionBean pos = game.getShips().get(user.getUserID());
        if (pos == null)
            throw new SWGameException(GameConstLogic.ERR_YOU_HAVE_BEEN_DESTROYED);
        if (pos.getTorpedoes() <= 0)
            throw new SWGameException(GameConstLogic.ERR_YOU_ARE_OUT_OF_TORPEDOS);
        int hits = doTorpedo(user.getUserID(), game, fireDLon, fireDLat, System.currentTimeMillis());
        pos.setHits(pos.getHits() + hits);
        user.setNumberOfKills(user.getNumberOfKills() + hits);
        user.setNumberOfShots(user.getNumberOfShots() + 1);
        return SUCCESS|hits;
    }
    
    /**
     * New game.
     * All shards are full. Make another one.
     *
     * @return the SW game bean
     */
    private static SWGameBean newGame()
    {
        SWGameBean game = new SWGameBean();
        game.setNorth(-GameConstLogic.GAME_HEIGHT/2);
        game.setSouth(GameConstLogic.GAME_HEIGHT/2);
        game.setEast(GameConstLogic.GAME_WIDTH/2);
        game.setWest(-GameConstLogic.GAME_WIDTH/2);
        game.setMaxDepth(GameConstLogic.GAME_DEPTH);
        updateGame(game);
        return game;
    }

    /**
     * Do join game.
     * Add a player to a game.
     *
     * @param game the game
     * @param id the id
     */
    public static void doJoinGame(SWGameBean game, String id)
    {
        synchronized (game)
        {
            SWPositionBean pos = new SWPositionBean();
            pos.setDepth(0);
            pos.setLongitude(mRND.nextInt(GameConstLogic.GAME_WIDTH/2) - GameConstLogic.GAME_WIDTH/4);
            pos.setLattitude(mRND.nextInt(GameConstLogic.GAME_HEIGHT/2) - GameConstLogic.GAME_HEIGHT/4);
            pos.setLastMove(System.currentTimeMillis());
            pos.setTorpedoes(GameConstLogic.MAX_TORPEDOES);
            game.getShips().put(id, pos);
        }
    }

    /**
     * Do leave game.
     * Remove a player from a game.
     *
     * @param game the game
     * @param id the id
     */
    public static void doLeaveGame(SWGameBean game, String id)
    {
        synchronized (game)
        {
            if (game.getAI().containsKey(id))
                game.getAI().get(id).term(game, id);
            game.getShips().remove(id);
            game.getAI().remove(id);
        }
    }

    /**
     * Do torpedo.
     * A player fires a torpedo.
     *
     * @param id the id
     * @param game the game
     * @param fireDLon the fire delta longitude
     * @param fireDLat the fire delta lattitude
     * @param now the now
     * @return success/fail code
     */
    public static int doTorpedo(String id, SWGameBean game, Integer fireDLon,
            Integer fireDLat, long now)
    {
        SWPositionBean pos = game.getShips().get(id);
        if (pos.getTorpedoes() <= 0)
            return 0;
        pos.setTorpedoes(pos.getTorpedoes() - 1);
        int tLon = pos.getLongitude();
        int tLat = pos.getLattitude();
        for (int i = 0; i < GameConstLogic.TORPEDO_RANGE; i++)
        {
            tLon += fireDLon;
            tLat += fireDLat;
            log("Torpedo at "+tLon+","+tLat+","+pos.getDepth());
            List<String> hits = findShipsAt(game, tLon, tLat, pos.getDepth());
            if (hits.size() > 0)
            {
                log("Torpedo hits "+hits);
                for (String hit : hits)
                    doDie(game, hit);
                doBoom(game, tLon, tLat, pos.getDepth(), now);
                return hits.size();
            }
        }
        log("Torpedo misses");
        return 0;
    }

    /**
     * Find ships at given location.
     * Calculate if a torpedo hits something.
     *
     * @param game the game
     * @param tLon the torpedo longitude
     * @param tLat the torpedo lattitude
     * @param depth the depth
     * @return the list
     */
    private static List<String> findShipsAt(SWGameBean game, int tLon, int tLat, int depth)
    {
        List<String> hits = new ArrayList<>();
        for (String id : game.getShips().keySet())
        {
            SWPositionBean pos = game.getShips().get(id);
            if ((pos.getLongitude() == tLon) && (pos.getLattitude() == tLat) && (pos.getDepth() == depth))
                hits.add(id);
        }
        return hits;
    }

    /**
     * Do die.
     * A player dies.
     *
     * @param game the game
     * @param id the id
     */
    private static void doDie(SWGameBean game, String id)
    {
        doLeaveGame(game, id);
    }

    /**
     * Do boom.
     * Torpedo explosions get recorded by the microphones of nearby ships.
     * Even if they aren't in listen mode.
     *
     * @param game the game
     * @param lon the longitude
     * @param lat the lattitude
     * @param dep the depth
     * @param now the now
     */
    public static void doBoom(SWGameBean game, int lon, int lat, int dep, long now)
    {
        for (String shipID : game.getShips().keySet())
        {
            SWPositionBean shipPos = game.getShips().get(shipID);
            SWPingBean boom = makePing(shipPos, lon, lat, dep, SWPingBean.BOOM, now);
            if (boom.getDistance() <= GameConstLogic.TORPEDO_RANGE)
                shipPos.getSoundings().add(boom);
        }
    }

    /**
     * Do ping.
     * A player is pinging their sonar.
     *
     * @param id the id
     * @param game the game
     * @param now the now
     * @return the list
     */
    public static List<SWPingBean> doPing(String id, SWGameBean game, long now)
    {
        List<SWPingBean> pings = new ArrayList<>();
        SWPositionBean pos = game.getShips().get(id);
        if (pos == null)
            return pings;
        for (String shipID : game.getShips().keySet())
        {
            if (shipID.equals(id))
                continue;
            SWPositionBean shipPos = game.getShips().get(shipID);
            SWPingBean ping = makePing(pos, shipPos, SWPingBean.PING, now);
            if (ping.getDistance() <= GameConstLogic.PING_RANGE)
            {
                pings.add(ping);
                SWPingBean pong = makePing(shipPos, pos, SWPingBean.PONG, now);
                shipPos.getSoundings().add(pong);
            }
        }
        return pings;
    }

    /**
     * Do listen.
     * A player is using their microphones.
     *
     * @param id the id
     * @param game the game
     * @param now the now
     * @return the list
     */
    public static List<SWPingBean> doListen(String id, SWGameBean game, long now)
    {
        List<SWPingBean> pings = new ArrayList<>();
        SWPositionBean pos = game.getShips().get(id);
        if (pos == null)
            return pings;
        for (String shipID : game.getShips().keySet())
        {
            if (shipID.equals(id))
                continue;
            SWPositionBean shipPos = game.getShips().get(shipID);
            SWPingBean ping = makePing(pos, shipPos, SWPingBean.LISTEN, now);
            if (ping.getDistance() <= GameConstLogic.LISTEN_RANGE)
                pings.add(ping);
        }
        return pings;
    }

    /**
     * Make ping.
     * Construct a ping object.
     *
     * @param pinger the pinger
     * @param pingee the pingee
     * @param type the type
     * @param time the time
     * @return the SW ping bean
     */
    private static SWPingBean makePing(SWPositionBean pinger,
            SWPositionBean pingee, int type, long time)
    {
        return makePing(pinger, pingee.getLongitude(), pingee.getLattitude(), pingee.getDepth(), type, time);
    }
    
    /**
     * Make ping.
     * Construct a ping object.
     *
     * @param pinger the pinger
     * @param pingeeLon the pingee longitude
     * @param pingeeLat the pingee lattitude
     * @param pingeeDep the pingee depth
     * @param type the type
     * @param time the time
     * @return the SW ping bean
     */
    private static SWPingBean makePing(SWPositionBean pinger,
            int pingeeLon, int pingeeLat, int pingeeDep, int type, long time)
    {
        int deltaLon = pingeeLon - pinger.getLongitude();
        int deltaLat = pingeeLat - pinger.getLattitude();
        double a = SWPingBean.deltaToAngle(deltaLon, deltaLat);
        int dir = SWPingBean.angleToDirection(a);
        double dist = Math.sqrt(deltaLon*deltaLon + deltaLat*deltaLat);
        SWPingBean ping = new SWPingBean();
        ping.setType(type);
        ping.setTime(time);
        ping.setDirection(dir);
        ping.setDistance(dist);
        if (pinger.getDepth() < pingeeDep)
            ping.setAltitude(SWPingBean.DOWN);
        else if (pinger.getDepth() > pingeeDep)
            ping.setAltitude(SWPingBean.UP);
        else
            ping.setAltitude(SWPingBean.LEVEL);
        log("Pinger="+pinger.getLongitude()+","+pinger.getLattitude()
            +", pingee="+pingeeLon+","+pingeeLat
            +", delta="+deltaLon+","+deltaLat
            +", a="+(int)(a*128/Math.PI)+", dir="+dir+" "+SWPingBean.DIRECTIONS[dir]
            +", dist="+dist);
        return ping;
    }

    /**
     * Do move ship.
     * Move a player's ship.
     *
     * @param id the id
     * @param dLon the delta longitude
     * @param dLat the delta lattitude
     * @param dDep the delta depth
     * @param game the game
     * @return success/fail code
     * @throws SWGameException the game exception
     */
    public static int doMoveShip(String id, int dLon, int dLat, int dDep,
            SWGameBean game) throws SWGameException
    {
        SWPositionBean pos = game.getShips().get(id);
        if (pos == null)
            throw new SWGameException(GameConstLogic.ERR_NOT_IN_GAME);
        int newLat = pos.getLattitude() + dLat;
        int newLon = pos.getLongitude() + dLon;
        int newDep = pos.getDepth() - dDep; // -ve = sink, which we actually increment for
        if ((newLat < game.getNorth()) || (newLat > game.getSouth()))
            throw new SWGameException("out of bounds");
        if ((newLon < game.getWest()) || (newLon > game.getEast()))
            throw new SWGameException("out of bounds");
        if ((newDep < 0) || (newDep > game.getMaxDepth()))
            throw new SWGameException("out of bounds");
        pos.setLattitude(newLat);
        pos.setLongitude(newLon);
        pos.setDepth(newDep);
        return SUCCESS;
    }
    
    // AI stuff
    
    /** The AI count. */
    private static int mAICount = 0;
    
    /** The prefix to identify robots. */
    private static final String PREFIX_AI = "ai://";
    
    /**
     * Update game.
     * Move all the computer players.
     *
     * @param game the game
     */
    private static void updateGame(SWGameBean game)
    {
        long now = System.currentTimeMillis();
        // check if no players
        if (game.getShips().size() == game.getAI().size())
        {
            // just mark moved and return
            for (String id : game.getAI().keySet())
            {
                SWPositionBean pos = game.getShips().get(id);
                pos.setLastMove(now);
            }           
            return;
        }
        // check # of AIs
        while (game.getAI().size() < GameConstLogic.MAX_AIS_PER_GAME)
        {
            String id = PREFIX_AI+(++mAICount);
            doJoinGame(game, id);
            IComputerPlayer ai = new SimplePlayer();
            game.getAI().put(id, ai);
            ai.init(game, id);
        }
        // move ships
        for (String id : game.getAI().keySet().toArray(new String[0]))
        {
            IComputerPlayer ai = game.getAI().get(id);
            if (ai == null)
                continue; // killed
            SWPositionBean pos = game.getShips().get(id);
            if (pos == null)
                ai.term(game, id);
            else
            {
                long tick = pos.getLastMove() + GameConstLogic.AI_MOVE_TICK;
                while (tick < now)
                {
                    try
                    {
                        ai.move(game, id, tick);
                    }
                    catch (Exception e)
                    {
                        SubWarGameAPI.debug("Error trying to move "+id);
                        SubWarGameAPI.debug(e);
                        doLeaveGame(game, id);
                    }
                    tick += GameConstLogic.AI_MOVE_TICK;
                }
            }
        }
    }
    
    // testing stuff

    /**
     * Reset the random seed.
     *
     * @param seed the seed
     */
    public static void testResetToSeed(long seed)
    {
        mRND = new Random(seed);
    }

    /**
     * Move the AI player.
     */
    public static void testAIMove()
    {
        long tick = System.currentTimeMillis() - GameConstLogic.AI_MOVE_TICK - 1;
        for (SWGameBean game : mGames)
        {
            for (String id : game.getAI().keySet())
            {
                SWPositionBean pos = game.getShips().get(id);
                if (pos != null)
                    pos.setLastMove(tick);
            }
            updateGame(game);
        }
    }
    
    /**
     * Log a message.
     *
     * @param msg the msg
     */
    private static void log(String msg)
    {
        // System.out.println(msg);
    }
}