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
    
    public static final String ERR_YOU_ARE_OUT_OF_TORPEDOS = "you are out of torpedos";
    public static final String ERR_YOU_HAVE_BEEN_DESTROYED = "you have been destroyed";
    public static final String ERR_NOT_IN_GAME = "not in game";
    public static final String ERR_ALREADY_IN_GAME = "already in game";
}