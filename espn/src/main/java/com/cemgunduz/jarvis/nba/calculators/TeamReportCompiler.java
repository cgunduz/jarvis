package com.cemgunduz.jarvis.nba.calculators;

import com.cemgunduz.jarvis.nba.BasketballPlayer;
import com.cemgunduz.jarvis.nba.League;
import com.cemgunduz.jarvis.nba.calculators.stat.Stat;
import com.cemgunduz.jarvis.nba.calculators.team.PlayerTeam;
import com.cemgunduz.jarvis.nba.calculators.team.ReportablePlayerTeam;
import com.cemgunduz.jarvis.nba.statsheets.scrapers.PlayerStatsheetScraper;
import com.cemgunduz.jarvis.nba.statsheets.scrapers.SeasonStatsheetScraper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cem on 26/10/16.
 */
public class TeamReportCompiler {

    public TeamReportCompiler(String leagueId) {
        this.leagueId = leagueId;
    }

    private String leagueId;

    public List<ReportablePlayerTeam> compile() {

        PlayerStatsheetScraper playerStatsheetScraper = new SeasonStatsheetScraper(leagueId);
        List<BasketballPlayer> playerList = playerStatsheetScraper.scrapePlayerSheets();

        League league = new League(
                playerList
                , true
        );

        List<ReportablePlayerTeam> reportablePlayerTeams =
                league.getPlayerTeams().stream().map(playerTeam ->
                        new ReportablePlayerTeam(playerTeam)).collect(Collectors.toList());

        for(int teamNo1=0; teamNo1 < league.getLeagueSize(); teamNo1++)
        {
            for(int teamNo2=teamNo1+1; teamNo2 < league.getLeagueSize(); teamNo2++)
            {
                compare(reportablePlayerTeams.get(teamNo1), reportablePlayerTeams.get(teamNo2));
            }
        }

        return reportablePlayerTeams;
    }

    private void compare(ReportablePlayerTeam team1, ReportablePlayerTeam team2)
    {
        double gap = 0.1;
        // TODO : Factor in a better gap
        // TODO : Refactor this part is bad coding example
        for(Stat stat : Stat.values())
        {
            if(stat.isSpecial()) continue;

            double team1Value = team1.getPlayerTeam().getTeamCumulativeBy(stat);
            double team2Value = team2.getPlayerTeam().getTeamCumulativeBy(stat);
            compare(team1, team2, team1Value, team2Value);
        }

        double fgVal1 = team1.getPlayerTeam().getTeamCumulativeForFG();
        double fgVal2 = team2.getPlayerTeam().getTeamCumulativeForFG();

        double ftVal1 = team1.getPlayerTeam().getTeamCumulativeForFT();
        double ftVal2 = team2.getPlayerTeam().getTeamCumulativeForFT();

        compare(team1, team2, fgVal1, fgVal2);
        compare(team1, team2, ftVal1, ftVal2);
    }

    private void compare(ReportablePlayerTeam team1, ReportablePlayerTeam team2,
                         Double team1Value, Double team2Value)
    {
        // TODO : Factor in a better gap
        double gap = 0.05;

        if(team1Value > team2Value)
        {
            team1.incrementPoints(0.6);
            if(team1Value * (1 - gap ) > team2Value)
            {
                team1.incrementPoints(0.4);
            }
            else
            {
                team2.incrementPoints(0.4);
            }
        }
        else
        {
            team2.incrementPoints(0.6);
            if(team2Value * (1 - gap ) > team1Value)
            {
                team2.incrementPoints(0.4);
            }
            else
            {
                team1.incrementPoints(0.4);
            }
        }


    }
}
