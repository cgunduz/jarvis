package com.cemgunduz.jarvis.nba.statsheets;

import com.cemgunduz.jarvis.nba.calculators.stat.Stat;

/**
 * Created by cem on 05/09/16.
 */
public class Statsheet {

    private int gamesPlayed;
    private Double minutes;

    private Double fgPercentage;
    private Double fgMissed;
    private Double fgScored;
    private Double fgAttempted;

    private Double ftPercentage;
    private Double ftMissed;
    private Double ftScored;
    private Double ftAttempted;

    private Double threePointers;
    private Double rebounds;
    private Double assists;
    private Double turnovers;
    private Double steals;
    private Double blocks;
    private Double points;

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public Double getMinutes() {
        return minutes;
    }

    public void setMinutes(Double minutes) {
        this.minutes = minutes;
    }

    public Double getFgPercentage() {
        return fgPercentage;
    }

    public void setFgPercentage(Double fgPercentage) {
        this.fgPercentage = fgPercentage;
    }

    public Double getFgScored() {
        return fgScored;
    }

    public void setFgScored(Double fgScored) {
        this.fgScored = fgScored;
    }

    public Double getFgAttempted() {
        return fgAttempted;
    }

    public void setFgAttempted(Double fgAttempted) {
        this.fgAttempted = fgAttempted;
    }

    public Double getFtPercentage() {
        return ftPercentage;
    }

    public void setFtPercentage(Double ftPercentage) {
        this.ftPercentage = ftPercentage;
    }

    public Double getFtScored() {
        return ftScored;
    }

    public void setFtScored(Double ftScored) {
        this.ftScored = ftScored;
    }

    public Double getFtAttempted() {
        return ftAttempted;
    }

    public void setFtAttempted(Double ftAttempted) {
        this.ftAttempted = ftAttempted;
    }

    public Double getThreePointers() {
        return threePointers;
    }

    public void setThreePointers(Double threePointers) {
        this.threePointers = threePointers;
    }

    public Double getRebounds() {
        return rebounds;
    }

    public void setRebounds(Double rebounds) {
        this.rebounds = rebounds;
    }

    public Double getAssists() {
        return assists;
    }

    public void setAssists(Double assists) {
        this.assists = assists;
    }

    public Double getTurnovers() {
        return turnovers;
    }

    public void setTurnovers(Double turnovers) {
        this.turnovers = turnovers;
    }

    public Double getSteals() {
        return steals;
    }

    public void setSteals(Double steals) {
        this.steals = steals;
    }

    public Double getBlocks() {
        return blocks;
    }

    public void setBlocks(Double blocks) {
        this.blocks = blocks;
    }

    public Double getPoints() {
        return points;
    }

    public void setPoints(Double points) {
        this.points = points;
    }

    public Double getFgMissed() {
        return fgMissed;
    }

    public void setFgMissed(Double fgMissed) {
        this.fgMissed = fgMissed;
    }

    public Double getFtMissed() {
        return ftMissed;
    }

    public void setFtMissed(Double ftMissed) {
        this.ftMissed = ftMissed;
    }

    public void precentagePredictor()
    {
        if(fgAttempted != null || ftAttempted != null || ftPercentage == null)
            return;

        fgScored = threePointers;
        Double expectedPercentagePoints = points - threePointers * 3;
        ftScored = points * 0.2;
        expectedPercentagePoints -= ftScored;
        fgScored += expectedPercentagePoints/2;

        if(fgPercentage == 0.0) fgAttempted = 0.0;
        else fgAttempted = fgScored * 1/fgPercentage;

        if(ftPercentage == 0.0) ftAttempted = 0.0;
        else ftAttempted = ftScored * 1/ftPercentage;

        fgMissed = fgAttempted - fgScored;
        ftMissed = ftAttempted - ftScored;
    }

    public double getByStatName(Stat stat)
    {
        if(stat.equals(Stat.AST)) return assists;
        else if(stat.equals(Stat.BLK)) return blocks;
        else if(stat.equals(Stat.FGM)) return fgMissed;
        else if(stat.equals(Stat.FGS)) return fgScored;
        else if(stat.equals(Stat.FTM)) return ftMissed;
        else if(stat.equals(Stat.FTS)) return ftScored;
        else if(stat.equals(Stat.PM3)) return threePointers;
        else if(stat.equals(Stat.PTS)) return points;
        else if(stat.equals(Stat.REB)) return rebounds;
        else if(stat.equals(Stat.STL)) return steals;
        else if(stat.equals(Stat.TUR)) return turnovers;

        throw new UnsupportedOperationException("Stat not mapped !");
    }
}
