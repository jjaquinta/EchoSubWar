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

// TODO: Auto-generated Javadoc
/**
 * The Class SWAudioException.
 */
public class SWAudioException extends Exception
{
    
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 8269692846238071844L;

    /**
     * Instantiates a new SW audio exception.
     */
    public SWAudioException()
    {
        super();
    }

    /**
     * Instantiates a new SW audio exception.
     *
     * @param message the message
     * @param cause the cause
     * @param enableSuppression the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    public SWAudioException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * Instantiates a new SW audio exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public SWAudioException(String message, Throwable cause)
    {
        super(message, cause);
    }

    /**
     * Instantiates a new SW audio exception.
     *
     * @param message the message
     */
    public SWAudioException(String message)
    {
        super(message);
    }

    /**
     * Instantiates a new SW audio exception.
     *
     * @param cause the cause
     */
    public SWAudioException(Throwable cause)
    {
        super(cause);
    }
}
