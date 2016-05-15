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
