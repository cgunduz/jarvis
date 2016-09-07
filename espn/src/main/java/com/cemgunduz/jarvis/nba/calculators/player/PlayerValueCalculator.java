package com.cemgunduz.jarvis.nba.calculators.player;

import com.cemgunduz.jarvis.nba.BasketballPlayer;
import com.cemgunduz.jarvis.nba.League;
import com.cemgunduz.jarvis.nba.calculators.stat.StatValue;
import com.cemgunduz.jarvis.nba.calculators.stat.StatValueCalculator;
import com.cemgunduz.jarvis.nba.statsheets.Statsheet;
import com.cemgunduz.jarvis.nba.statsheets.StatsheetType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cem on 06/09/16.
 */
public class PlayerValueCalculator {

    private StatValueCalculator statValueCalculator;
    private Map<StatsheetType, List<StatValue>> globalStatValueCache = new HashMap<>();
    private League league;

    public PlayerValueCalculator(StatValueCalculator statValueCalculator, League league) {
        this.statValueCalculator = statValueCalculator;
        this.league = league;
    }

    public PlayerReport calculate(BasketballPlayer basketballPlayer)
    {
        PlayerReport playerReport = new PlayerReport(basketballPlayer);
        double quantifier = 0;

        for(StatsheetType statsheetType : StatsheetType.values())
        {
            // Player has not performed in this category, correct the calculations
            if(basketballPlayer.getSheet(statsheetType) == null)
            {
                quantifier += statsheetType.getWeight();
                continue;
            }

            for(StatValue statValue : getGlobalStatValues(statsheetType))
            {
                Double pps = statValue.getPointsPerStat();
                Double weight = statsheetType.getWeight();
                Double statContribution = basketballPlayer.getSheet(statsheetType).getByStatName(statValue.getStat());
                Double totalStatValue = weight * pps * statContribution;
                playerReport.addValue(statValue.getStat(), totalStatValue);
            }
        }

        playerReport.quantify(quantifier);
        playerReport.calculateTotalValue();
        return playerReport;
    }

    public List<PlayerReport> calculate()
    {
        List<PlayerReport> playerReports = new ArrayList<>();
        league.getBasketballPlayers().stream().forEach(
                (player) -> playerReports.add(calculate(player))
        );

        return playerReports;
    }

    private List<StatValue> getGlobalStatValues(StatsheetType statsheetType)
    {
        if(globalStatValueCache.containsKey(statsheetType)) return globalStatValueCache.get(statsheetType);

        List<StatValue> statValues = statValueCalculator.calculateGlobalStatValue(league.provideTrainingData(statsheetType));
        globalStatValueCache.put(statsheetType, statValues);
        return statValues;
    }
}
