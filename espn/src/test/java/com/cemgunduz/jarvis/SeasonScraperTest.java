package com.cemgunduz.jarvis;

import com.cemgunduz.jarvis.nba.BasketballPlayer;
import com.cemgunduz.jarvis.nba.statsheets.scrapers.PlayerStatsheetScraper;
import com.cemgunduz.jarvis.nba.statsheets.scrapers.PreSeasonStatsheetScraper;
import com.cemgunduz.jarvis.nba.statsheets.scrapers.SeasonStatsheetScraper;
import org.junit.Test;

import java.util.List;

/**
 * Created by cem on 24/10/16.
 */
public class SeasonScraperTest {

    PlayerStatsheetScraper playerStatsheetScraper = new SeasonStatsheetScraper();

    @Test
    public void contextLoads() {

        List<BasketballPlayer> players = playerStatsheetScraper.scrapePlayerSheets();
        System.out.println(players.get(0).getName());
    }
}
