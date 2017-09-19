package com.cemgunduz.jarvis.nba.calculators;

import com.cemgunduz.jarvis.nba.BasketballPlayer;
import com.cemgunduz.jarvis.nba.League;
import com.cemgunduz.jarvis.nba.calculators.player.PlayerReport;
import com.cemgunduz.jarvis.nba.calculators.player.PlayerValueCalculator;
import com.cemgunduz.jarvis.nba.calculators.stat.SimpleStatValueCalculator;
import com.cemgunduz.jarvis.nba.calculators.stat.Stat;
import com.cemgunduz.jarvis.nba.statsheets.scrapers.PlayerStatsheetScraper;
import com.cemgunduz.jarvis.nba.statsheets.scrapers.PreSeasonStatsheetScraper;

import java.util.Comparator;
import java.util.List;

/**
 * Created by cem on 20/09/16.
 */
public class PreSeasonReportCompiler {

    private int AUCTION_SIZE = 8;
    private int TOTAL_AUCTIONABLE_PER_TEAM = 12;
    private int TOTAL_AUCTION_MONEY = 1600;


    public List<PlayerReport> compile()
    {
        PlayerStatsheetScraper playerStatsheetScraper = new PreSeasonStatsheetScraper();
        List<BasketballPlayer> playerList = playerStatsheetScraper.scrapePlayerSheets();

        League league = new League(playerList);

        PlayerValueCalculator playerValueCalculator = new PlayerValueCalculator(
                new SimpleStatValueCalculator(), league);

        List<PlayerReport> playerReports = playerValueCalculator.calculate();

        int order = 1;
        double allValues = 0.0;
        for(PlayerReport playerReport : playerReports)
        {
            double minutes = playerReport.getGamesPlayed();
            playerReport.setTotalValue( playerReport.getTotalValue() * minutes / 82);
            allValues+= playerReport.getTotalValue();
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

        final double anchor = playerReports.get(AUCTION_SIZE * TOTAL_AUCTIONABLE_PER_TEAM).getTotalValue();

        final double allVals = allValues - anchor * playerReports.size();
        playerReports.stream().forEach(item -> item.setEstimatedAuctionValue(TOTAL_AUCTION_MONEY*((item.getTotalValue()-anchor)/allVals)));

        return playerReports;
    }
}
