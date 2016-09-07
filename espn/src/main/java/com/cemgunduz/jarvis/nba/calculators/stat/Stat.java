package com.cemgunduz.jarvis.nba.calculators.stat;

/**
 * Created by cem on 06/09/16.
 */
public enum Stat {

    // TODO : Read quantifiers from config file
    AST(true, 1),
    BLK(true, 1),
    PM3(true, 1),
    STL(true, 1),
    REB(true, 1),
    TUR(false, 1),
    PTS(true, 1),
    FGM(false, 7),
    FGS(true, 7),
    FTM(false, 2.4),
    FTS(true, 2.4);

    private boolean positive;
    private double quantifier;

    Stat(boolean positive, double quantifier) {
        this.positive = positive;
        this.quantifier = quantifier;
    }

    public boolean isPositive() {
        return positive;
    }

    public double getQuantifier() {
        return quantifier;
    }
}
