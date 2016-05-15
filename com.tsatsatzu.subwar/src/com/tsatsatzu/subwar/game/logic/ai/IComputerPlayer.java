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
package com.tsatsatzu.subwar.game.logic.ai;

import com.tsatsatzu.subwar.game.data.SWGameBean;

/**
 * The Interface IComputerPlayer.
 * This encapsulates the functionality necessary to make the game moves for
 * computer based players. For each strategy, a new subclass of this can be
 * created and registered with the system. 
 */
public interface IComputerPlayer
{
    
    /**
     * Initializes the robotic system. 
     * If there is any setup to do for a new robotic player,
     * this is the place to do it.
     *
     * @param game the game object
     * @param id the robot's id
     */
    public void init(SWGameBean game, String id);
    
    /**
     * Move the robot.
     * Whatever logic is necessary to choose what a robot can do, should be done here.
     *
     * @param game the game object
     * @param id the robot's id
     * @param tick the elapsed time
     */
    public void move(SWGameBean game, String id, long tick);
    
    /**
     * Terminates the robotic system's connection to this robot player. 
     * If there is any 
     * cleanup to do for a new robotic player, this is the place to do it.
     *
     * @param game the game object
     * @param id the robot's id
     */
    public void term(SWGameBean game, String id);
}
