package com.cemgunduz.jarvis.nba.calculators.statistics.player;

import com.cemgunduz.jarvis.nba.calculators.player.PlayerReport;

/**
 * Created by cem on 20/09/16.
 */
public class PlayerSimulationPoint {

    private PlayerReport playerReport;
    private int points;
    private int participation = 0;

    public PlayerSimulationPoint(PlayerReport playerReport) {
        this.playerReport = playerReport;
    }

    public int getPoints() {
        return points;
    }

    public void increment(int points) {
        this.points += points;
    }

    public String getPlayerName()
    {
        return playerReport.getPlayerName();
    }

    public int getParticipation() {
        return participation;
    }

    public void incrementParticipation()
    {
        participation++;
    }

    public Double getRealPoints()
    {
        if(participation == 0) return 0.0;

        return (double)points / (double)participation;
    }

    public int getEspnRanking()
    {
        return playerReport.espnRankings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerSimulationPoint that = (PlayerSimulationPoint) o;

        return playerReport != null ? playerReport.equals(that.playerReport) : that.playerReport == null;

    }

    @Override
    public int hashCode() {
        return playerReport != null ? playerReport.hashCode() : 0;
    }
}
