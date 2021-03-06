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
package com.tsatsatzu.subwar.audio.api;

/**
 * The Interface ISubWarAudioLogger.
 * This allows for the debug messages of the audio layer to be trapped by a higher layer.
 */
public interface ISubWarAudioLogger
{
    
    /**
     * Debug.
     *
     * @param msg the msg
     */
    public void debug(String msg);
    
    /**
     * Debug.
     *
     * @param t the t
     */
    public void debug(Throwable t);
}
