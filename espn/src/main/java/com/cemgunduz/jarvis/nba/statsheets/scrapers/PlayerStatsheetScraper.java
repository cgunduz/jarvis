package com.cemgunduz.jarvis.nba.statsheets.scrapers;

import com.cemgunduz.jarvis.nba.BasketballPlayer;

import java.util.List;

/**
 * Created by cem on 05/09/16.
 */
public interface PlayerStatsheetScraper {

    List<BasketballPlayer> scrapePlayerSheets();
}
