package com.cemgunduz.jarvis.nba.calculators.player;

import com.cemgunduz.jarvis.nba.BasketballPlayer;
import com.cemgunduz.jarvis.nba.calculators.stat.Stat;
import com.cemgunduz.jarvis.nba.statsheets.StatsheetType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cem on 06/09/16.
 */
public class PlayerReport {

    private BasketballPlayer basketballPlayer;
    private Map<Stat, Double> statMap = new HashMap<>();
    private double totalValue;

    // TODO : Find a diff solution
    public int espnRankings;

    public PlayerReport(BasketballPlayer basketballPlayer) {
        this.basketballPlayer = basketballPlayer;
    }

    public void addValue(Stat stat, Double value)
    {
        if(!statMap.containsKey(stat))
        {
            statMap.put(stat, value);
            return;
        }

        statMap.put(stat, value + statMap.get(stat));
    }

    public Map<Stat, Double> getStatMap() {
        return statMap;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public void calculateTotalValue()
    {
        totalValue = 0;
        for(Double val : statMap.values())
        {
            totalValue += val;
        }
    }

    public void quantify(double percentage)
    {
        percentage = 1 + percentage;
        for(Stat stat : statMap.keySet())
        {
            Double oldVal = statMap.get(stat);
            statMap.put(stat, oldVal * percentage);
        }
    }

    public String getPlayerName()
    {
        return basketballPlayer.getName();
    }

    public int getGamesPlayed() {

        if(basketballPlayer.getSheet(StatsheetType.PROJECTIONS) == null ) return 0;
        return basketballPlayer.getSheet(StatsheetType.PROJECTIONS).getGamesPlayed();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerReport that = (PlayerReport) o;

        return basketballPlayer != null ? basketballPlayer.equals(that.basketballPlayer) : that.basketballPlayer == null;

    }

    @Override
    public int hashCode() {
        return basketballPlayer != null ? basketballPlayer.hashCode() : 0;
    }
}
