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
     * @param title
     * @return
     */
    public static boolean trivial(String title)
    {
        return (title == null) || (title.length() == 0);
    }
}
