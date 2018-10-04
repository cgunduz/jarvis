package com.cemgunduz.jarvis.nba.statsheets.scrapers;

import com.cemgunduz.jarvis.nba.BasketballPlayer;
import com.cemgunduz.jarvis.nba.statsheets.Statsheet;
import com.cemgunduz.jarvis.nba.statsheets.StatsheetType;
import com.cemgunduz.jarvis.nba.statsheets.scrapers.models.EspnPlayer;
import com.cemgunduz.jarvis.nba.statsheets.scrapers.models.Players;
import com.cemgunduz.jarvis.nba.statsheets.scrapers.models.Stat;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by cem on 05/09/16.
 */
public class PreSeasonStatsheetScraper implements PlayerStatsheetScraper {

    private static final String ESPN_PROJECTIONS =
            "http://fantasy.espn.com/apis/v3/games/fba/seasons/2019/segments/0/leaguedefaults/1?view=kona_player_info";

    private static final String weirdHeader = "{\"players\":{\"filterStatsForSplitTypeIds\":{\"value\":[0]},\"filterStatsForSourceIds\":{\"value\":[1]},\"sortDraftRanks\":{\"sortPriority\":2,\"sortAsc\":true,\"value\":\"STANDARD\"},\"sortPercOwned\":{\"sortPriority\":1,\"sortAsc\":false},\"filterSlotIds\":{\"value\":[0,1,2,3,4,5,6,7,8,9,10,11]},\"limit\":300,\"offset\":0,\"filterStatsForTopScoringPeriodIds\":{\"value\":20,\"additionalValue\":[\"002019\",\"102019\",\"002018\",\"012019\",\"022019\",\"032019\",\"042019\"]}}}";

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<BasketballPlayer> scrapePlayerSheets() {

        List<BasketballPlayer> result = new ArrayList<>();

        Document doc = null;
        Players mappedJson = null;
        try {
            doc = Jsoup.connect(ESPN_PROJECTIONS)
                    .timeout(100000)
                    .ignoreContentType(true)
                    .maxBodySize(0)
                    .header("X-Fantasy-Filter", weirdHeader)
                    .get();

            String json = doc.text();
            mappedJson = new ObjectMapper().readValue(json, Players.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        for (EspnPlayer espnPlayer: mappedJson.getPlayers()) {
            BasketballPlayer basketballPlayer = new BasketballPlayer();
            basketballPlayer.setName(espnPlayer.getPlayer().getFullName());

            // TODO : Get the position

            if(espnPlayer.getPlayer().getStats() == null ||
                    espnPlayer.getPlayer().getStats().size() == 0){
                continue;
            }

            Stat previousYearStats = espnPlayer.getPlayer().getStats().get(
                    espnPlayer.getPlayer().getStats().size() == 1 ? 0 : 1
            );

            Stat projectionStats = espnPlayer.getPlayer().getStats().get(0);

            basketballPlayer.setSheet(
                    statsheetByLine(previousYearStats), StatsheetType.PREVIOUS_YEAR
            );

            basketballPlayer.setSheet(
                    statsheetByLine(projectionStats), StatsheetType.PROJECTIONS
            );

            result.add(basketballPlayer);
        }

        return result;
    }

    private Statsheet statsheetByLine(Stat stat) {

        Double gamesPlayed = stat.extractStat(42);
        if (gamesPlayed.equals(0.0))
            return null;

        Statsheet statsheet = new Statsheet();
        statsheet.setGamesPlayed( gamesPlayed.intValue() );

        statsheet.setMinutes(stat.extractStat(40));
        statsheet.setFgPercentage(stat.extractStat(19));
        statsheet.setFtPercentage(stat.extractStat(20));
        statsheet.setThreePointers(stat.extractStat(17));
        statsheet.setRebounds(stat.extractStat(6));
        statsheet.setAssists(stat.extractStat(3));
        statsheet.setSteals(stat.extractStat(2));
        statsheet.setBlocks(stat.extractStat(1));
        statsheet.setTurnovers(stat.extractStat(11));
        statsheet.setPoints(stat.extractStat(0));

        statsheet.precentagePredictor();

        return statsheet;
    }

    private double extractStats(Element line, int statNo) {
        return Double.valueOf(line.getElementsByClass("playertableStat").get(statNo).text());
    }
}
