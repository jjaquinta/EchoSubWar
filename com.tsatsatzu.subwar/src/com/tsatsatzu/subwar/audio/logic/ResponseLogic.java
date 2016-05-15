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

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class ResponseLogic.
 */
public class ResponseLogic
{
    
    /** The Constant ORDINAL. */
    public static final String[] ORDINAL =
    {
        "first",
        "second",
        "third",
        "fourth",
        "fifth",
        "sixth",
        "seventh",
        "eighth",
        "ninth",
        "tenth",
        "eleventh",
        "twelfth",
        "thirteenth",
        "fourteenth",
        "fifteenth",
        "sixteenth",
        "seventeenth",
        "eighteenth",
        "nineteenth",
        "twentieth",
        "twenty first",
        "twenty second",
        "twenty third",
        "twenty fourth",
        "twenty fifth",
        "twenty sixth",
        "twenty seventh",
        "twenty eighth",
        "twenty ninth",
        "thirtieth",
        "thirty first",
        "thirty second",
        "thirty third",
        "thirty fourth",
        "thirty fifth",
        "thirty sixth",
        "thirty seventh",
        "thirty eighth",
        "thirty ninth",
    };

    /**
     * And list.
     *
     * @param listens the listens
     * @return the string
     */
    public static String andList(List<String> listens)
    {
        StringBuffer txt = new StringBuffer();
        for (int i = 0; i < listens.size(); i++)
        {
            if (i > 0)
            {
                txt.append(", ");
                if (i == listens.size() - 1)
                    txt.append("and ");
            }
            txt.append(listens.get(i));
        }
        return txt.toString();
    }

}
