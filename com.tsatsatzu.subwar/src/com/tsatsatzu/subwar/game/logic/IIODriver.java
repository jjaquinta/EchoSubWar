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

import java.util.List;

import com.tsatsatzu.subwar.game.data.SWUserBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface IIODriver.
 */
public interface IIODriver
{
    
    /**
     * Clear caches.
     */
    public void clearCaches();
    
    /**
     * Gets the user.
     *
     * @param id the id
     * @return the user
     */
    public SWUserBean getUser(String id);
    
    /**
     * Save user.
     *
     * @param user the user
     */
    public void saveUser(SWUserBean user);
    
    /**
     * Delete user.
     *
     * @param id the id
     */
    public void deleteUser(String id);
    
    /**
     * Gets the top users.
     *
     * @param total the total
     * @return the top users
     */
    public List<SWUserBean> getTopUsers(int total);
}
