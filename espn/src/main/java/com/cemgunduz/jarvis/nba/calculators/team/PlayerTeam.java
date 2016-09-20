package com.cemgunduz.jarvis.nba.calculators.team;

import com.cemgunduz.jarvis.nba.BasketballPlayer;
import com.cemgunduz.jarvis.nba.calculators.player.PlayerReport;
import com.cemgunduz.jarvis.nba.calculators.stat.Stat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cem on 20/09/16.
 */
public class PlayerTeam {

    private String playerName;
    private String playerEspnId;

    private List<PlayerReport> playerReports = new ArrayList<>();

    public void addPlayer(PlayerReport playerReport)
    {
        playerReports.add(playerReport);
    }

    public Double getTeamCumulativeBy(Stat stat)
    {
        Double result = 0.0;
        for(PlayerReport playerReport : playerReports)
        {
            result += playerReport.getStatMap().get(stat);
        }

        return result;
    }

    public Double getTeamCumulativeForFG()
    {
        return getTeamCumulativeBy(Stat.FGS) + getTeamCumulativeBy(Stat.FGM);
    }

    public Double getTeamCumulativeForFT()
    {
        return getTeamCumulativeBy(Stat.FTS) + getTeamCumulativeBy(Stat.FTM);
    }

    public List<PlayerReport> getPlayerReports() {
        return playerReports;
    }
}
