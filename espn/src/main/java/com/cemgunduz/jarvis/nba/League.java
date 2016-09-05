package com.cemgunduz.jarvis.nba;

import com.cemgunduz.jarvis.nba.statsheets.scrapers.PlayerStatsheetScraper;

import java.util.List;

/**
 * Created by cem on 05/09/16.
 */
public class League {

    private List<BasketballPlayer> basketballPlayerList;
    private PlayerStatsheetScraper playerStatsheetScraper;

    public League(PlayerStatsheetScraper playerStatsheetScraper) {
        this.playerStatsheetScraper = playerStatsheetScraper;
        basketballPlayerList = playerStatsheetScraper.scrapePlayerSheets();
    }


}
