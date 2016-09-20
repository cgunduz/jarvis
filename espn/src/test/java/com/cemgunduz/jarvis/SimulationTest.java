package com.cemgunduz.jarvis;

import com.cemgunduz.jarvis.nba.calculators.PreSeasonReportCompiler;
import com.cemgunduz.jarvis.nba.calculators.player.PlayerReport;
import com.cemgunduz.jarvis.nba.calculators.statistics.player.PlayerSimulationPoint;
import com.cemgunduz.jarvis.nba.calculators.statistics.player.PlayerSimulationPoints;
import com.cemgunduz.jarvis.nba.calculators.statistics.player.RandomTeamStatistics;
import org.junit.Test;

import java.util.List;

/**
 * Created by cem on 20/09/16.
 */
public class SimulationTest {

    @Test
    public void simulate()
    {
        PreSeasonReportCompiler preSeasonReportCompiler = new PreSeasonReportCompiler();
        List<PlayerReport> playerReports = preSeasonReportCompiler.compile();

        RandomTeamStatistics randomTeamStatistics = new RandomTeamStatistics(playerReports);

        PlayerSimulationPoints playerSimulationPoints = randomTeamStatistics.simulate(1000000);

        // TODO : Change later
        for(PlayerSimulationPoint playerPoint : playerSimulationPoints.getPlayerSimulationPointsList())
        {
            System.out.println(playerPoint.getPlayerName());
            System.out.println(playerPoint.getPoints());
        }
    }
}
