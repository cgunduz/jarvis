package com.cemgunduz.jarvis.nba;

import com.cemgunduz.jarvis.nba.statsheets.Statsheet;
import com.cemgunduz.jarvis.nba.statsheets.StatsheetType;
import com.cemgunduz.jarvis.nba.statsheets.scrapers.PlayerStatsheetScraper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cem on 05/09/16.
 */
public class League {

    private List<BasketballPlayer> basketballPlayers;

    public League(List<BasketballPlayer> basketballPlayers) {
        this.basketballPlayers = basketballPlayers;
    }

    public List<Statsheet> provideTrainingData()
    {
        // TODO : Read from configuration file
        return provideTrainingData(StatsheetType.PROJECTIONS);
    }

    public List<Statsheet> provideTrainingData(StatsheetType statsheetType)
    {
        List<Statsheet> result = new ArrayList<>();

        for(BasketballPlayer basketballPlayer : basketballPlayers)
        {
            result.add(basketballPlayer.getSheet(statsheetType));
        }

        return result;
    }

    public List<BasketballPlayer> getBasketballPlayers() {
        return basketballPlayers;
    }
}
