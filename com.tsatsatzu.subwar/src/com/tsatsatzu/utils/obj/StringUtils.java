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
package com.tsatsatzu.utils.obj;

/**
 * The Class StringUtils.
 * Some general string utilities. Sure, Apache has a library
 * that does a wider job, but I don't want to bloat the code
 * unnecessarily for just a few functions.
 */
public class StringUtils
{
    
    /**
     * This is a convenience function to test for both null and zero length.
     *
     * @param title the text to test
     * @return true, if null or zero lenght
     */
    public static boolean trivial(String title)
    {
        return (title == null) || (title.length() == 0);
    }

    /**
     * This trims *only* spaces from the ends of a string.
     *
     * @param txt the txt
     * @return the trimmed string
     */
    public static String trimSpaces(String txt)
    {
        while (txt.startsWith(" "))
            txt = txt.substring(1);
        while (txt.endsWith(" "))
            txt = txt.substring(0, txt.length() - 1);
        return txt;
    }
}
