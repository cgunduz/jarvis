package com.cemgunduz.jarvis.nba.calculators;

import com.cemgunduz.jarvis.nba.BasketballPlayer;
import com.cemgunduz.jarvis.nba.League;
import com.cemgunduz.jarvis.nba.calculators.player.PlayerReport;
import com.cemgunduz.jarvis.nba.calculators.player.PlayerValueCalculator;
import com.cemgunduz.jarvis.nba.calculators.stat.SimpleStatValueCalculator;
import com.cemgunduz.jarvis.nba.statsheets.scrapers.PlayerStatsheetScraper;
import com.cemgunduz.jarvis.nba.statsheets.scrapers.SeasonStatsheetScraper;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cem on 26/10/16.
 */
public class SeasonReportCompiler {

    public List<PlayerReport> compile() {

        PlayerStatsheetScraper playerStatsheetScraper = new SeasonStatsheetScraper();
        List<BasketballPlayer> playerList = playerStatsheetScraper.scrapePlayerSheets();

        League league = new League(playerList);

        PlayerValueCalculator playerValueCalculator = new PlayerValueCalculator(
                new SimpleStatValueCalculator(), league);

        List<PlayerReport> playerReports = playerValueCalculator.calculate();

        int order = 1;
        for(PlayerReport playerReport : playerReports)
        {
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

        return playerReports.stream().filter(player -> player.isAvailable()).
                collect(Collectors.toList());
    }
}
