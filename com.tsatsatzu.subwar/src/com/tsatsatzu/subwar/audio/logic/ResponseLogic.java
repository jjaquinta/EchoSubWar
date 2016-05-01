package com.tsatsatzu.subwar.audio.logic;

import java.util.List;

public class ResponseLogic
{
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
