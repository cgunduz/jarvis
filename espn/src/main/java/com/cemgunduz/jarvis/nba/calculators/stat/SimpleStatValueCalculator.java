package com.cemgunduz.jarvis.nba.calculators.stat;

import com.cemgunduz.jarvis.nba.statsheets.Statsheet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cem on 06/09/16.
 */
public class SimpleStatValueCalculator implements StatValueCalculator {

    private static final double STAT_VAL_TOTAL = 100;

    public List<StatValue> calculateGlobalStatValue(List<Statsheet> statsheets)
    {
        List<StatValue> statValueList = new ArrayList<>();
        for(Stat stat : Stat.values())
        {
            double statTotal = 0;
            double mean = 0;
            double pointsPerStat = 0;
            for(Statsheet statsheet : statsheets)
            {
                if(statsheet == null || statsheet.getGamesPlayed() == 0) continue;
                Double val = statsheet.getByStatName(stat);
                if(val != null ) statTotal += val;
            }

            mean = statTotal / statsheets.size();

            double negator = stat.isPositive() ? 1 : -1;
            pointsPerStat = STAT_VAL_TOTAL * stat.getQuantifier() * negator / statTotal;

            StatValue statValue = new StatValue(
                   stat, statTotal, mean, pointsPerStat
            );
            statValueList.add(statValue);
        }

        return statValueList;
    }
}
