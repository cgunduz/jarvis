package com.cemgunduz.jarvis.nba.statsheets.scrapers;

import com.cemgunduz.jarvis.configuration.GlobalConfiguration;
import com.cemgunduz.jarvis.nba.BasketballPlayer;
import com.cemgunduz.jarvis.nba.Position;
import com.cemgunduz.jarvis.nba.calculators.player.Status;
import com.cemgunduz.jarvis.nba.statsheets.Statsheet;
import com.cemgunduz.jarvis.nba.statsheets.StatsheetType;
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

    private static final String LEAGUE_PLAYER_URL =
            "http://games.espn.com/fba/freeagency?leagueId=142666&" +
                    "teamId=3&seasonId=2017&avail=-1&startIndex=$START_INDEX&version=$SHEET_TYPE";

    @Autowired
    GlobalConfiguration globalConfiguration;

    private List<String> teamNames = new ArrayList<>();

    @Override
    public List<BasketballPlayer> scrapePlayerSheets() {

        // TODO : Fix hack
        if(globalConfiguration == null)
            globalConfiguration = new GlobalConfiguration();

        List<BasketballPlayer> basketballPlayers = new ArrayList<>();

        // Make a for each statsheet check here
        for(StatsheetType sheetType : StatsheetType.values())
        {
            String espnUrl = LEAGUE_PLAYER_URL.replace("$SHEET_TYPE", sheetType.getWebName());
            for(int startIndex = 0; startIndex < 300; startIndex+=50)
            {
                String url = espnUrl.replace("$START_INDEX", String.valueOf(startIndex));

                Document doc = null;
                try {
                    String cookie = globalConfiguration.getCookie();
                    doc = Jsoup.connect(url).timeout(100000).
                            header("Cookie", globalConfiguration.getCookie()).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Elements playersHtml = doc.getElementsByClass("pncPlayerRow");
                for(Element playerHtml : playersHtml)
                {
                    PlayerHtmlAnalyzer analyzer = new PlayerHtmlAnalyzer(playerHtml);

                    String playerName = analyzer.getPlayerName();
                    String nbaTeamName = analyzer.getNbaTeamName();

                    String teamName = analyzer.getTeamName();
                    if(!teamNames.contains(teamName))
                        teamNames.add(teamName);

                    String teamId = String.valueOf(teamNames.indexOf(teamName)+1);

                    // TODO : Implement
                    Position position = Position.getPositionByString("TO_BE_FILLED");

                    Status status = analyzer.getStatus();

                    BasketballPlayer basketballPlayer = new BasketballPlayer();
                    basketballPlayer.setName(playerName);
                    basketballPlayer.setPosition(position);
                    basketballPlayer.setStatus(status);
                    basketballPlayer.setTeamId(teamId);
                    basketballPlayer.setNbaTeamName(nbaTeamName);
                    basketballPlayer.setTeamName(teamName);

                    if(basketballPlayers.contains(basketballPlayer))
                    {
                        basketballPlayer = basketballPlayers.get(
                                basketballPlayers.indexOf(basketballPlayer)
                        );
                    }
                    else
                    {
                        basketballPlayers.add(basketballPlayer);
                    }

                    Statsheet statsheet = new Statsheet();
                    statsheet.setMinutes(analyzer.getMinutes());
                    statsheet.setThreePointers(analyzer.getThreePointers());
                    statsheet.setAssists(analyzer.getAsists());
                    statsheet.setBlocks(analyzer.getBlocks());
                    statsheet.setPoints(analyzer.getPoints());
                    statsheet.setRebounds(analyzer.getRebounds());
                    statsheet.setSteals(analyzer.getSteals());
                    statsheet.setTurnovers(analyzer.getTurnovers());

                    statsheet.setFgAttempted(analyzer.getFgAttempted());
                    statsheet.setFgMissed(analyzer.getFgMissed());
                    statsheet.setFgScored(analyzer.getFgScored());
                    statsheet.setFtAttempted(analyzer.getFtAttempted());
                    statsheet.setFtMissed(analyzer.getFtMissed());
                    statsheet.setFtScored(analyzer.getFtScored());

                    if(statsheet.getFgAttempted().equals(0.0))
                        statsheet.setFgPercentage(0.0);
                    else
                        statsheet.setFgPercentage(statsheet.getFgScored()/statsheet.getFgAttempted());

                    if(statsheet.getFtAttempted().equals(0.0))
                        statsheet.setFtPercentage(0.0);
                    else
                        statsheet.setFtPercentage(statsheet.getFtScored()/statsheet.getFtAttempted());

                    // TODO : Add percentages

                    basketballPlayer.setSheet(statsheet, sheetType);
                }
            }
        }



        return basketballPlayers;
    }
}
