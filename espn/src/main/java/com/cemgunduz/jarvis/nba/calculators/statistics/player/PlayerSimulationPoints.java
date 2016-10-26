package com.cemgunduz.jarvis.nba.calculators.statistics.player;

import com.cemgunduz.jarvis.nba.calculators.player.PlayerReport;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by cem on 20/09/16.
 */
public class PlayerSimulationPoints {

    private List<PlayerSimulationPoint> playerSimulationPointsList = new ArrayList<>();

    public void add(PlayerReport playerReport)
    {
        PlayerSimulationPoint playerSimulationPoint = new PlayerSimulationPoint(playerReport);
        playerSimulationPointsList.add(playerSimulationPoint);
    }

    public void increment(PlayerReport playerReport, int points)
    {
        PlayerSimulationPoint proxy = new PlayerSimulationPoint(playerReport);

        if(!playerSimulationPointsList.contains(proxy)) add(playerReport);

        int index = playerSimulationPointsList.indexOf(proxy);
        PlayerSimulationPoint playerSimulationPoint = playerSimulationPointsList.get(index);
        playerSimulationPoint.increment(points);
        playerSimulationPoint.incrementParticipation();
    }

    public void sort()
    {
        playerSimulationPointsList.sort(new Comparator<PlayerSimulationPoint>() {

            @Override
            public int compare(PlayerSimulationPoint o1, PlayerSimulationPoint o2) {

                double res = o2.getRealPoints() - o1.getRealPoints();

                if(res > 0) return 1;
                else if(res == 0.0) return 0;

                return -1;
            }
        });
    }

    public List<PlayerSimulationPoint> getPlayerSimulationPointsList() {
        return playerSimulationPointsList;
    }
}
