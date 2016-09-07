package com.cemgunduz.jarvis;

import com.cemgunduz.jarvis.nba.BasketballPlayer;
import com.cemgunduz.jarvis.nba.statsheets.scrapers.PlayerStatsheetScraper;
import com.cemgunduz.jarvis.nba.statsheets.scrapers.PreSeasonStatsheetScraper;
import org.junit.Test;

import java.util.List;

/**
 * Created by cem on 05/09/16.
 */
public class ScraperTest {

    PlayerStatsheetScraper playerStatsheetScraper = new PreSeasonStatsheetScraper();

    @Test
    public void contextLoads() {

        List<BasketballPlayer> players = playerStatsheetScraper.scrapePlayerSheets();
        System.out.println(players.get(0).getName());


    }


}
