/*
 * Created on Sep 16, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.tsatsatzu.utils.obj;

public class StringUtils
{
    /**
     * This is a convenience function to test for both null and zero length.
     */
    public static boolean trivial(String title)
    {
        return (title == null) || (title.length() == 0);
    }

    /**
     * This trims *only* spaces from the ends of a string.
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
