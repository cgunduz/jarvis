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

    public PlayerTeam()
    {
        this("0", "Dummy");
    }

    public PlayerTeam(String playerEspnId, String playerName) {
        this.playerEspnId = playerEspnId;
        this.playerName = playerName;
    }

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
            if(playerReport.isInjured()) continue;

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

    public String getPlayerEspnId() {
        return playerEspnId;
    }

    public String getPlayerName()
    {
        return playerReports.get(0) != null ? playerReports.get(0).getPlayerTeamName() : playerName;
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Team id : ");
        stringBuilder.append(playerEspnId);
        stringBuilder.append(newLine());
        stringBuilder.append("Team name : ");
        stringBuilder.append(getPlayerName());
        stringBuilder.append(newLine());
        stringBuilder.append("FG : ");
        stringBuilder.append(getTeamCumulativeForFG());
        stringBuilder.append(newLine());
        stringBuilder.append("FT : ");
        stringBuilder.append(getTeamCumulativeForFT());
        stringBuilder.append(newLine());
        for(Stat stat : Stat.values())
        {
            if(stat.isSpecial())
                continue;

            stringBuilder.append(stat.name());
            stringBuilder.append(" : ");
            stringBuilder.append(getTeamCumulativeBy(stat));
            stringBuilder.append(newLine());
        }

        stringBuilder.append(" PLAYERS : ");
        stringBuilder.append(newLine());
        for(PlayerReport playerReport : playerReports)
        {
            stringBuilder.append(playerReport.toString());
            stringBuilder.append(newLine());
        }

        return stringBuilder.toString();
    }

    private String newLine(){ return "\n"; }
}
