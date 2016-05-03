package com.tsatsatzu.subwar.audio.logic;

import com.tsatsatzu.subwar.game.logic.CredentialsLogic;

public class AudioConstLogic
{
    static final String API_KEY = CredentialsLogic.getProperty("audio.api.key");
    
    private static final String SOUND_ROOT = "https://s3.amazonaws.com/tsatsatzu-alexa/sound/blackjack/";
    public static final String SOUND_BOSUN_WHISTLE = SOUND_ROOT+"shuffle-cards-4.mp3";
    public static final String SOUND_SHIP_LAUNCH = SOUND_ROOT+"shuffle-cards-4.mp3";
    public static final String SOUND_MOTOR_RUNNING = SOUND_ROOT+"shuffle-cards-4.mp3";
    public static final String SOUND_EXPLOSION = SOUND_ROOT+"shuffle-cards-4.mp3";
    public static final String SOUND_TORPEDO = SOUND_ROOT+"shuffle-cards-4.mp3";

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
