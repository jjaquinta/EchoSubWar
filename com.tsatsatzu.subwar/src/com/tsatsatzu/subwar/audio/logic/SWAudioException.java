package com.tsatsatzu.subwar.audio.logic;

public class SWAudioException extends Exception
{
    /**
     * 
     */
    private static final long serialVersionUID = 8269692846238071844L;

    public SWAudioException()
    {
        super();
    }

    public SWAudioException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public SWAudioException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public SWAudioException(String message)
    {
        super(message);
    }

    public SWAudioException(Throwable cause)
    {
        super(cause);
    }
}
