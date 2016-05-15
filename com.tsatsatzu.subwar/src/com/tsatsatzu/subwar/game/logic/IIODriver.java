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

/**
 * The Interface IIODriver.
 * Any storage mechanism needs to implement a drive to access it.
 */
public interface IIODriver
{
    
    /**
     * Clear caches.
     * If any caches are maintained, this clears them out.
     */
    public void clearCaches();
    
    /**
     * Gets the user from the store.
     *
     * @param id the id
     * @return the user
     */
    public SWUserBean getUser(String id);
    
    /**
     * Save user to store.
     *
     * @param user the user
     */
    public void saveUser(SWUserBean user);
    
    /**
     * Delete user from store.
     *
     * @param id the id
     */
    public void deleteUser(String id);
    
    /**
     * Gets the top users from the store.
     *
     * @param total the total
     * @return the top users
     */
    public List<SWUserBean> getTopUsers(int total);
}
