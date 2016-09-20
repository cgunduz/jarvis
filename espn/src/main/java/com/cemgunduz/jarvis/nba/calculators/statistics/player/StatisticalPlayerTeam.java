package com.cemgunduz.jarvis.nba.calculators.statistics.player;

import com.cemgunduz.jarvis.nba.calculators.team.PlayerTeam;

import java.util.ArrayList;

/**
 * Created by cem on 20/09/16.
 */
public class StatisticalPlayerTeam extends PlayerTeam {

    private int points = 0;

    public int getPoints() {
        return points;
    }

    public void incrementBy(int point)
    {
        points += point;
    }
}
