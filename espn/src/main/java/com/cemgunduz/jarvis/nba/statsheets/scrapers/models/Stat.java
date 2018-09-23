package com.cemgunduz.jarvis.nba.statsheets.scrapers.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Stat {

    Map<Integer, Double> averageStats;
    Integer seasonId;

    public Map<Integer, Double> getAverageStats() {
        return averageStats;
    }

    public void setAverageStats(Map<Integer, Double> averageStats) {
        this.averageStats = averageStats;
    }

    public Integer getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(Integer seasonId) {
        this.seasonId = seasonId;
    }

    public Double extractStat(int location){
        try{
            if(averageStats.containsKey(location))
                return averageStats.get(location);
        } catch (Exception e){}


        return 0.0;
    }
}
