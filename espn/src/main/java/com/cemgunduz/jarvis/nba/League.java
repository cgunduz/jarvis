package com.cemgunduz.jarvis.nba;

import com.cemgunduz.jarvis.nba.calculators.player.PlayerReport;
import com.cemgunduz.jarvis.nba.calculators.player.PlayerValueCalculator;
import com.cemgunduz.jarvis.nba.calculators.stat.SimpleStatValueCalculator;
import com.cemgunduz.jarvis.nba.calculators.team.PlayerTeam;
import com.cemgunduz.jarvis.nba.statsheets.Statsheet;
import com.cemgunduz.jarvis.nba.statsheets.StatsheetType;
import com.cemgunduz.jarvis.nba.statsheets.scrapers.PlayerStatsheetScraper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by cem on 05/09/16.
 */
public class League {

    public static final String SCUM_LEAGUE = "142666";
    public static final String PARALEL_LEAGUE = "7051735";

    private List<BasketballPlayer> trainingRelatedBasketballPlayers;
    private List<BasketballPlayer> basketballPlayers;
    private int leagueSize;
    private PlayerValueCalculator playerValueCalculator;

    public League(List<BasketballPlayer> basketballPlayers) {
        this(basketballPlayers, false);
    }

    public League(List<BasketballPlayer> basketballPlayers, boolean autoCalculate) {
        this.trainingRelatedBasketballPlayers = basketballPlayers;
        this.basketballPlayers = basketballPlayers;
        if (!autoCalculate) return;

        this.basketballPlayers = trainingRelatedBasketballPlayers.stream().filter(basketballPlayer ->
                !basketballPlayer.isFreeAgent()).collect(Collectors.toList());
        this.leagueSize = findAmountOfTeams();
        this.playerValueCalculator = new PlayerValueCalculator(
                new SimpleStatValueCalculator(), this);
    }

    public int getLeagueSize() {
        return leagueSize;
    }

    public List<Statsheet> provideTrainingData()
    {
        // TODO : Read from configuration file
        return provideTrainingData(StatsheetType.PROJECTIONS);
    }

    public List<PlayerTeam> getPlayerTeams()
    {
        List<PlayerTeam> playerTeams = new ArrayList<>();

        for(int i = 1; i<leagueSize+1; i++)
        {
            // TODO : Implement team owner names ( maybe not needed )
            PlayerTeam playerTeam = new PlayerTeam(String.valueOf(i), "UNKNOWN");

            final String currentPlayerId = String.valueOf(i);
            basketballPlayers.stream().
                    filter(basketballPlayer -> basketballPlayer.getTeamId().equals(currentPlayerId)).
                    forEach(basketballPlayer ->
                    playerTeam.addPlayer(
                            playerValueCalculator.calculate(basketballPlayer)
            ));

            playerTeams.add(playerTeam);
        }

        return playerTeams;
    }

    public List<Statsheet> provideTrainingData(StatsheetType statsheetType)
    {
        List<Statsheet> result = new ArrayList<>();

        for(BasketballPlayer basketballPlayer : trainingRelatedBasketballPlayers)
        {
            result.add(basketballPlayer.getSheet(statsheetType));
        }

        return result;
    }

    public List<BasketballPlayer> getBasketballPlayers() {
        return basketballPlayers;
    }

    private Integer findAmountOfTeams()
    {
        Set<Integer> teamIds = new HashSet<>();
        for(BasketballPlayer basketballPlayer : basketballPlayers)
        {
            teamIds.add(Integer.valueOf(basketballPlayer.getTeamId()));
        }

        return teamIds.size();
    }
}
