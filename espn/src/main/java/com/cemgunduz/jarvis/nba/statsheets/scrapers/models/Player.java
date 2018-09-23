package com.cemgunduz.jarvis.nba.statsheets.scrapers.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Player {

    Integer defaultPositionId;
    //String draftRanksByRankType;
    String fullName;
    String id;
    String injuryStatus;
    Map<String, Object> ownership;
    List<Stat> stats;

    public Integer getDefaultPositionId() {
        return defaultPositionId;
    }

    public void setDefaultPositionId(Integer defaultPositionId) {
        this.defaultPositionId = defaultPositionId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInjuryStatus() {
        return injuryStatus;
    }

    public void setInjuryStatus(String injuryStatus) {
        this.injuryStatus = injuryStatus;
    }

    public Map<String, Object> getOwnership() {
        return ownership;
    }

    public void setOwnership(Map<String, Object> ownership) {
        this.ownership = ownership;
    }

    public List<Stat> getStats() {
        return stats;
    }

    public void setStats(List<Stat> stats) {
        this.stats = stats;
    }
}
