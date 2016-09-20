package com.cemgunduz.jarvis.nba.calculators.statistics.player;

import com.cemgunduz.jarvis.nba.calculators.player.PlayerReport;
import com.cemgunduz.jarvis.nba.calculators.stat.Stat;
import com.cemgunduz.jarvis.nba.calculators.team.PlayerTeam;
import org.bouncycastle.jcajce.provider.symmetric.TEA;

import java.util.*;

/**
 * Created by cem on 20/09/16.
 */
public class RandomTeamStatistics {

    private static final int TEAM_COUNT = 8;
    private static final int PLAYER_MAX_DRAFT_NUMBER = 105;

    List<PlayerReport> playerReports;
    private StatisticalPlayerTeam[] playerTeams = new StatisticalPlayerTeam[TEAM_COUNT];

    private PlayerSimulationPoints playerSimulationPoints = new PlayerSimulationPoints();

    public RandomTeamStatistics(List<PlayerReport> playerReports) {
        this.playerReports = playerReports;

        int order = 1;
        for(PlayerReport playerReport : playerReports)
        {
            double minutes = playerReport.getGamesPlayed();
            playerReport.setTotalValue( playerReport.getTotalValue() * minutes / 82);
            playerReport.espnRankings = order;
            order++;
        }

        playerReports.sort(new Comparator<PlayerReport>() {
            @Override
            public int compare(PlayerReport o1, PlayerReport o2) {

                if(o1.getTotalValue() == o2.getTotalValue()) return 0;
                return o1.getTotalValue() > o2.getTotalValue() ? -1 : 1;
            }
        });
    }

    public PlayerSimulationPoints simulate(int times)
    {
        for(int i = 0; i < times; i++)
        {
            simulate();
        }

        playerSimulationPoints.sort();
        return playerSimulationPoints;
    }

    private void randomatePlayers()
    {
        Random random = new Random();
        List<PlayerReport> copyList = new ArrayList<>();
        copyList.addAll(playerReports.subList(0, PLAYER_MAX_DRAFT_NUMBER));

        for(int i = 0; i < 13; i++)
        {
            for(int j = 0; j < TEAM_COUNT; j++)
            {
                int randomNum = random.nextInt(copyList.size());
                playerTeams[j].addPlayer(copyList.get(randomNum));

                copyList.remove(randomNum);
            }
        }
    }

    private void compare()
    {
        for(int i = 0; i < TEAM_COUNT; i++)
        {
            for(int j = i+1; j < TEAM_COUNT; j++)
            {
                int result = compare(playerTeams[i], playerTeams[j]);
                if(result > 0) playerTeams[i].incrementBy(1);
                else if(result < 0 ) playerTeams[j].incrementBy(1);
            }
        }
    }

    private void simulate()
    {
        clear();
        randomatePlayers();
        compare();
        allocatePointsToPlayers();
    }

    private void allocatePointsToPlayers()
    {
        for(int i = 0; i < TEAM_COUNT; i++)
        {
            for(PlayerReport playerReport : playerTeams[i].getPlayerReports())
            {
                playerSimulationPoints.increment(playerReport, playerTeams[i].getPoints());
            }
        }
    }

    private int compare(PlayerTeam o1, PlayerTeam o2) {

        int teamOnePoints = 0;
        int teamTwoPoints = 0;

        for(Stat stat : Stat.values())
        {
            if(stat.isSpecial()) continue;

            if(o1.getTeamCumulativeBy(stat) > o2.getTeamCumulativeBy(stat))
            {
                teamOnePoints++;
            }
            else if(o1.getTeamCumulativeBy(stat) < o2.getTeamCumulativeBy(stat))
            {
                teamTwoPoints++;
            }
        }

        if(o1.getTeamCumulativeForFG() > o2.getTeamCumulativeForFG()) teamOnePoints++;
        else if(o1.getTeamCumulativeForFG() < o2.getTeamCumulativeForFG()) teamTwoPoints++;

        if(o1.getTeamCumulativeForFT() > o2.getTeamCumulativeForFT()) teamOnePoints++;
        else if(o1.getTeamCumulativeForFT() < o2.getTeamCumulativeForFT()) teamTwoPoints++;

        return teamOnePoints - teamTwoPoints;
    }

    private void clear()
    {
        playerTeams = new StatisticalPlayerTeam[TEAM_COUNT];
        for(int i = 0; i < TEAM_COUNT; i++)
        {
            playerTeams[i] = new StatisticalPlayerTeam();
        }
    }
}
