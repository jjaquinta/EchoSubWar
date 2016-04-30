package com.tsatsatzu.subwar.audio.logic;

import java.util.List;

public class ResponseLogic
{

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
