package com.cemgunduz.jarvis.nba.calculators.stat;

/**
 * Created by cem on 06/09/16.
 */
public enum Stat {

    // TODO : Read quantifiers from config file
    AST(true, 1, false),
    BLK(true, 1, false),
    PM3(true, 1, false),
    STL(true, 1, false ),
    REB(true, 1, false),
    TUR(false, 1, false),
    PTS(true, 1, false),
    FGM(false, 7, true),
    FGS(true, 7, true),
    FTM(false, 2.4, true),
    FTS(true, 2.4, true);

    private boolean positive;
    private double quantifier;
    private boolean isSpecial;

    Stat(boolean positive, double quantifier, boolean isSpecial) {
        this.positive = positive;
        this.quantifier = quantifier;
        this.isSpecial = isSpecial;
    }

    public boolean isPositive() {
        return positive;
    }

    public double getQuantifier() {
        return quantifier;
    }

    public boolean isSpecial() {
        return isSpecial;
    }
}
