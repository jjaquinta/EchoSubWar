package com.tsatsatzu.subwar.game.logic;

public class GameConstLogic
{
    public static final int MAX_SHIPS_PER_GAME = 20;
    public static final int GAME_HEIGHT = 20; // lattitude/N-S
    public static final int GAME_WIDTH = 10; // longitude/E-W
    public static final int GAME_DEPTH = 3;
    public static final int MAX_TORPEDOES = 6;

    public static final double LISTEN_RANGE = Math.sqrt(2);
    public static final double PING_RANGE = 6;
    public static final double TORPEDO_RANGE = 6;
    
    public static final int MAX_AIS_PER_GAME = MAX_SHIPS_PER_GAME/6;
    public static final long AI_MOVE_TICK = 12*1000L; // how often the computer moves
}