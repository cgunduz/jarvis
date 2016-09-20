package com.cemgunduz.jarvis.nba.statsheets.scrapers;

import com.cemgunduz.jarvis.nba.BasketballPlayer;
import com.cemgunduz.jarvis.nba.statsheets.Statsheet;
import com.cemgunduz.jarvis.nba.statsheets.StatsheetType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cem on 05/09/16.
 */
public class PreSeasonStatsheetScraper implements PlayerStatsheetScraper {

    private static final String ESPN_PROJECTIONS =
            "http://games.espn.com/fba/tools/projections?display=alt&startIndex=$START_INDEX";

    @Override
    public List<BasketballPlayer> scrapePlayerSheets() {

        int startIndex = 0;
        List<BasketballPlayer> result = new ArrayList<>();

        for(int i = 0; i < 10; i++)
        {
            String url = ESPN_PROJECTIONS.replace("$START_INDEX", String.valueOf(startIndex));
            Document doc = null;
            try {
                doc = Jsoup.connect(url).timeout(100000).get();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Elements players = doc.getElementsByClass("games-fullcol").get(0).getElementsByTag("table");

            for(Element player : players)
            {
                BasketballPlayer basketballPlayer = new BasketballPlayer();
                basketballPlayer.setName(player.getElementsByTag("a").get(0).text());

                // TODO : Get the position

                Element previousYearStats = player.getElementsByTag("tr").get(1);
                Element projectionStats = player.getElementsByTag("tr").get(2);

                basketballPlayer.setSheet(
                        statsheetByLine(previousYearStats), StatsheetType.PREVIOUS_YEAR
                );

                basketballPlayer.setSheet(
                        statsheetByLine(projectionStats), StatsheetType.PROJECTIONS
                );

                result.add(basketballPlayer);
            }

            startIndex += 15;
        }

        return result;
    }

    private Statsheet statsheetByLine(Element line)
    {
        String gamesPlayed = line.getElementsByClass("playertableStat").get(0).text();
        if(gamesPlayed.equals("--"))
            return null;

        Statsheet statsheet = new Statsheet();
        statsheet.setGamesPlayed(
                Integer.valueOf(gamesPlayed)
        );

        statsheet.setMinutes(extractStats(line, 1));
        statsheet.setFgPercentage(extractStats(line, 2));
        statsheet.setFtPercentage(extractStats(line,3));
        statsheet.setThreePointers(extractStats(line,4));
        statsheet.setRebounds(extractStats(line,5));
        statsheet.setAssists(extractStats(line,6));
        statsheet.setSteals(extractStats(line,8));
        statsheet.setBlocks(extractStats(line,9));
        statsheet.setTurnovers(extractStats(line,10));
        statsheet.setPoints(extractStats(line,11));

        statsheet.precentagePredictor();

        return statsheet;
    }

    private double extractStats(Element line, int statNo)
    {
        return Double.valueOf(line.getElementsByClass("playertableStat").get(statNo).text());
    }
}
