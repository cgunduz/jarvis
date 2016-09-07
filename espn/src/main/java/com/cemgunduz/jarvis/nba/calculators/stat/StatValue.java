package com.cemgunduz.jarvis.nba.calculators.stat;

/**
 * Created by cem on 06/09/16.
 */
public class StatValue {

    private Stat stat;
    private double statTotal;
    private double mean;
    private double pointsPerStat;

    public StatValue(Stat stat, double statTotal, double mean, double pointsPerStat) {
        this.stat = stat;
        this.statTotal = statTotal;
        this.mean = mean;
        this.pointsPerStat = pointsPerStat;
    }

    public Stat getStat() {
        return stat;
    }

    public double getStatTotal() {
        return statTotal;
    }

    public double getMean() {
        return mean;
    }

    public double getPointsPerStat() {
        return pointsPerStat;
    }
}
