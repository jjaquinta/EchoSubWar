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
package com.tsatsatzu.subwar.audio.logic;

import com.tsatsatzu.subwar.game.logic.CredentialsLogic;

public class AudioConstLogic
{
    static final String API_KEY = CredentialsLogic.getProperty("audio.api.key");
    
    private static final String SOUND_ROOT = "https://s3.amazonaws.com/tsatsatzu-alexa/sound/subwar/";
    public static final String SOUND_BOSUN_WHISTLE = SOUND_ROOT+"BOSUN_WHISTLE.mp3";
    public static final String SOUND_SHIP_LAUNCH = SOUND_ROOT+"SHIP_LAUNCH.mp3";
    public static final String SOUND_MOTOR_RUNNING = SOUND_ROOT+"MOTOR_RUNNING.mp3";
    public static final String SOUND_EXPLOSION = SOUND_ROOT+"EXPLOSION.mp3";
    public static final String SOUND_TORPEDO = SOUND_ROOT+"TORPEDO.mp3";
    public static final String SOUND_SONAR = SOUND_ROOT+"SONAR.mp3";
    public static final String SOUND_LISTEN_SHIP = SOUND_ROOT+"LISTEN_SHIP.mp3";

    public static final String SOUND_PAUSE = "<break strength=\"medium\"/>";
    
    public static final int MAX_LEADERS = 3;
    
    public static final String STATE_INITIAL = "init";
    public static final String STATE_INTRO1_1 = "intro.1.1";
    public static final String STATE_INTRO1_2 = "intro.1.2";
    public static final String STATE_INTRO1_3 = "intro.1.3";
    public static final String STATE_PRE_GAME = "pregame.base";
    public static final String STATE_INTRO2_1 = "intro.2.1";
    public static final String STATE_INTRO3_1 = "intro.3.1";

    public static final String STATE_GAME_BASE = "game.base";
    public static final String STATE_GAME_ABORT = "game.abort.query";

    public static final long TIMEOUT_IDLE = 5*60*1000L;
}
