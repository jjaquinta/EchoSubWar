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
}
