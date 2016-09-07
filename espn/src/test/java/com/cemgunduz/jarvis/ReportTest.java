package com.cemgunduz.jarvis;

import com.cemgunduz.jarvis.nba.BasketballPlayer;
import com.cemgunduz.jarvis.nba.League;
import com.cemgunduz.jarvis.nba.calculators.player.PlayerReport;
import com.cemgunduz.jarvis.nba.calculators.player.PlayerValueCalculator;
import com.cemgunduz.jarvis.nba.calculators.stat.SimpleStatValueCalculator;
import com.cemgunduz.jarvis.nba.calculators.stat.Stat;
import com.cemgunduz.jarvis.nba.statsheets.Statsheet;
import com.cemgunduz.jarvis.nba.statsheets.scrapers.PlayerStatsheetScraper;
import com.cemgunduz.jarvis.nba.statsheets.scrapers.PreSeasonStatsheetScraper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by cem on 07/09/16.
 */
public class ReportTest {

    @Test
    public void reportTest()
    {
        PlayerStatsheetScraper playerStatsheetScraper = new PreSeasonStatsheetScraper();
        List<BasketballPlayer> playerList = playerStatsheetScraper.scrapePlayerSheets();

        League league = new League(playerList);

        PlayerValueCalculator playerValueCalculator = new PlayerValueCalculator(
                new SimpleStatValueCalculator(), league);

        List<PlayerReport> playerReports = playerValueCalculator.calculate();

        int order = 1;
        for(PlayerReport playerReport : playerReports)
        {
            double minutes = playerReport.getGamesPlayed();
            playerReport.setTotalValue( playerReport.getTotalValue() * minutes / 82);
            playerReport.espnRankings = order;
            order++;
        }

        playerReports.sort(new Comparator<PlayerReport>() {
            @Override
            public int compare(PlayerReport o1, PlayerReport o2) {

                if(o1.getTotalValue() == o2.getTotalValue()) return 0;
                return o1.getTotalValue() > o2.getTotalValue() ? -1 : 1;
            }
        });

        int rank = 1;
        for(PlayerReport playerReport : playerReports)
        {
            System.out.println(String.valueOf(rank).concat(". ").concat(playerReport.getPlayerName())
                .concat(" - ").concat(String.valueOf(playerReport.espnRankings)));
            System.out.println(playerReport.getTotalValue());
            System.out.println(playerReport.getStatMap());
            try
            {
                System.out.println("FG : ".concat(String.valueOf(playerReport.getStatMap().get(Stat.FGS) + playerReport.getStatMap().get(Stat.FGM))));
                System.out.println("FT : ".concat(String.valueOf(playerReport.getStatMap().get(Stat.FTS) + playerReport.getStatMap().get(Stat.FTM))));
            }
            catch (Exception e)
            {
                System.out.println("FG : 0");
                System.out.println("FT : 0");
            }

            rank++;
        }
    }
}
