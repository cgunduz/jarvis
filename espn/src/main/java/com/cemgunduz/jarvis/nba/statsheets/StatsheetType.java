package com.cemgunduz.jarvis.nba.statsheets;

/**
 * Created by cem on 05/09/16.
 */
public enum StatsheetType {

    // TODO : Read from configuration
    PREVIOUS_YEAR(0.15), PROJECTIONS(0.85);

    private double weight;

    StatsheetType(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }
}
