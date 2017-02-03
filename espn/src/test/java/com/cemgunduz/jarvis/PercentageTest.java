package com.cemgunduz.jarvis;

import com.cemgunduz.jarvis.nba.BasketballPlayer;
import com.cemgunduz.jarvis.nba.League;
import com.cemgunduz.jarvis.nba.calculators.player.PlayerReport;
import com.cemgunduz.jarvis.nba.calculators.player.PlayerValueCalculator;
import com.cemgunduz.jarvis.nba.calculators.stat.SimpleStatValueCalculator;
import com.cemgunduz.jarvis.nba.calculators.stat.Stat;
import com.cemgunduz.jarvis.nba.statsheets.scrapers.PlayerStatsheetScraper;
import com.cemgunduz.jarvis.nba.statsheets.scrapers.PreSeasonStatsheetScraper;
import com.cemgunduz.jarvis.nba.statsheets.scrapers.SeasonStatsheetScraper;
import org.junit.Test;

import java.util.List;

/**
 * Created by cem on 07/09/16.
 */
public class PercentageTest {

    @Test
    public void percentages()
    {
        PlayerStatsheetScraper playerStatsheetScraper = new SeasonStatsheetScraper();
        List<BasketballPlayer> playerList = playerStatsheetScraper.scrapePlayerSheets();

        League league = new League(playerList);

        PlayerValueCalculator playerValueCalculator = new PlayerValueCalculator(
                new SimpleStatValueCalculator(), league);

        List<PlayerReport> playerReports = playerValueCalculator.calculate();

        double magPosFt = 0;
        double magNegFt = 0;
        double magPosFg = 0;
        double magNegFg = 0;

        double rebTotal = 0;
        for(PlayerReport playerReport : playerReports)
        {
            Double val = playerReport.getStatMap().get(Stat.REB) == null ? 0.0 : playerReport.getStatMap().get(Stat.REB);
            rebTotal += val;

            Double fts = playerReport.getStatMap().get(Stat.FTS);
            Double ftm = playerReport.getStatMap().get(Stat.FTM);
            Double fgs = playerReport.getStatMap().get(Stat.FGS);
            Double fgm = playerReport.getStatMap().get(Stat.FGM);

            fts = fts == null ? 0 : fts;
            ftm = ftm == null ? 0 : ftm;
            fgs = fgs == null ? 0 : fgs;
            fgm = fgm == null ? 0 : fgm;

            if(fts > -1 * ftm)
                magPosFt += fts + ftm;
            else
                magNegFt += fts + ftm;

            if(fgs > -1 * fgm)
                magPosFg += fgs + fgm;
            else
                magNegFg += fgs + fgm;
        }

        System.out.println("Done!");
    }
}
