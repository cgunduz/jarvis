package com.cemgunduz.jarvis.nba;

/**
 * Created by cem on 05/09/16.
 */
public enum Position {

    PG,SG,SF,PF,C;

    public static Position getPositionByString(String pos)
    {
        for(Position position : values())
        {
            if(position.toString().equals(pos)) return position;
        }

        return null;
    }
}
