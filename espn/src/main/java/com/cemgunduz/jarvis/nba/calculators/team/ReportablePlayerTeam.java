package com.cemgunduz.jarvis.nba.calculators.team;

import com.cemgunduz.jarvis.nba.calculators.player.PlayerReport;

import java.util.List;

/**
 * Created by cem on 26/10/16.
 */
public class ReportablePlayerTeam {

    private double points = 0;
    private PlayerTeam playerTeam;

    public ReportablePlayerTeam(PlayerTeam playerTeam) {
        this.playerTeam = playerTeam;
    }

    public double getPoints() {
        return points;
    }

    public void incrementPoints(double points) {
        this.points += points;
    }

    public PlayerTeam getPlayerTeam()
    {
        return playerTeam;
    }

    @Override
    public String toString() {
        return playerTeam.toString();
    }
}
