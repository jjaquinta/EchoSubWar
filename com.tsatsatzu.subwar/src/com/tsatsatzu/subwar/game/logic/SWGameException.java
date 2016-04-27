package com.tsatsatzu.subwar.game.logic;

public class SWGameException extends Exception
{
    /**
     * 
     */
    private static final long serialVersionUID = 8269692846238071844L;

    public SWGameException()
    {
        super();
    }

    public SWGameException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public SWGameException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public SWGameException(String message)
    {
        super(message);
    }

    public SWGameException(Throwable cause)
    {
        super(cause);
    }
}
