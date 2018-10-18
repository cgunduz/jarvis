package com.cemgunduz.jarvis.nba.statsheets.scrapers;

import com.cemgunduz.jarvis.configuration.GlobalConfiguration;
import com.cemgunduz.jarvis.nba.BasketballPlayer;
import com.cemgunduz.jarvis.nba.Position;
import com.cemgunduz.jarvis.nba.calculators.player.Status;
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
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cem on 24/10/16.
 */
public class SeasonStatsheetScraper implements PlayerStatsheetScraper {

    public SeasonStatsheetScraper(String leagueId) {
        this.leagueId = leagueId;
    }

    private String leagueId;

    // TODO : Legaue id changes per team
    private static final String LEAGUE_PLAYER_URL =
            "http://fantasy.espn.com/apis/v3/games/fba/seasons/2019/segments/0/leagues/LEAGUE_ID_PLACEHOLDER?scoringPeriodId=1&view=kona_player_info";

    @Autowired
    GlobalConfiguration globalConfiguration;

    private List<String> teamNames = new ArrayList<>();

    @Override
    public List<BasketballPlayer> scrapePlayerSheets() {

        // TODO : Fix hack
        if(globalConfiguration == null)
            globalConfiguration = new GlobalConfiguration();

        String url = LEAGUE_PLAYER_URL.replace("LEAGUE_ID_PLACEHOLDER", leagueId);

        List<BasketballPlayer> result = new ArrayList<>();

        Document doc = null;
        Players mappedJson = null;
        try {
            doc = Jsoup.connect(url)
                    .timeout(100000)
                    .ignoreContentType(true)
                    .maxBodySize(0)
                    .header("cookie", globalConfiguration.getCookie())
                    //.header("X-Fantasy-Filter", weirdHeader)
                    .get();

            String json = doc.text();
            mappedJson = new ObjectMapper().readValue(json, Players.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        for (EspnPlayer espnPlayer: mappedJson.getPlayers()) {
            BasketballPlayer basketballPlayer = new BasketballPlayer();
            basketballPlayer.setTeamId(String.valueOf(espnPlayer.getOnTeamId()));
            basketballPlayer.setTeamName(String.valueOf(espnPlayer.getOnTeamId()));
            basketballPlayer.setFreeAgent(espnPlayer.getStatus().equals("FREEAGENT") ||
                                            espnPlayer.getStatus().equals("WAIVERS"));
            basketballPlayer.setName(espnPlayer.getPlayer().getFullName());

            // TODO : Get the position

            if(espnPlayer.getPlayer().getStats() == null ||
                    espnPlayer.getPlayer().getStats().size() == 0){
                continue;
            }

            // TODO : Needs to change this is fully wrong

            for(Stat stat : espnPlayer.getPlayer().getStats()){

                StatsheetType type = getById(stat.getId());
                if(type == null) continue;

                basketballPlayer.setSheet(statsheetByLine(stat), type);
            }

            result.add(basketballPlayer);
        }

        return result;
    }

    private Statsheet statsheetByLine(Stat stat) {

        if(stat == null) return null;

        Double gamesPlayed = stat.extractStat(42);
        if (gamesPlayed.equals(0.0))
            return null;

        Statsheet statsheet = new Statsheet();
        statsheet.setGamesPlayed( gamesPlayed.intValue() );

        statsheet.setMinutes(stat.extractStat(40));

        statsheet.setFgPercentage(stat.extractStat(19));
        statsheet.setFgScored(stat.extractStat(13));
        statsheet.setFgMissed(stat.extractStat(14));
        statsheet.setFgAttempted(statsheet.getFgScored() + statsheet.getFgMissed());

        statsheet.setFtPercentage(stat.extractStat(20));
        statsheet.setFtScored(stat.extractStat(15));
        statsheet.setFtMissed(stat.extractStat(16));
        statsheet.setFtAttempted(statsheet.getFtScored() + statsheet.getFtMissed());

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

    // TODO : Just guessing here
    private StatsheetType getById(String id){

        switch(id) {
            case "102019" :
                // Statements
                return StatsheetType.PROJECTIONS;

            case "102018" :
                // Statements
                return StatsheetType.PREVIOUS_YEAR;

            case "002019" :
                // Statements
                return StatsheetType.THIS_YEAR;

            case "012019" :
                // Statements
                return StatsheetType.LAST_7;

            case "022019" :
                // Statements
                return StatsheetType.LAST_15;

            case "032019" :
                // Statements
                return StatsheetType.LAST_30;
        }

        return null;
    }
}
