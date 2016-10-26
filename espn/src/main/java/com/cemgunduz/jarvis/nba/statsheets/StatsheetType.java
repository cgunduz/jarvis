package com.cemgunduz.jarvis.nba.statsheets;

/**
 * Created by cem on 05/09/16.
 */
public enum StatsheetType {

    // TODO : Read from configuration
    PREVIOUS_YEAR(0.15, "lastSeason"), PROJECTIONS(0.85, "projections"),
    LAST_30(0.0, "last30"), LAST_15(0.0, "last15"), LAST_7(0.0, "last7"),
    THIS_YEAR(0.0, "currSeason");

    private double weight;
    private String webName;

    StatsheetType(double weight, String webName) {
        this.weight = weight;
        this.webName = webName;
    }

    public double getWeight() {
        return weight;
    }

    public String getWebName() {
        return webName;
    }
}
