package com.cemgunduz.jarvis.nba.statsheets.scrapers.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Players {

    List<EspnPlayer> players;

    public List<EspnPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(List<EspnPlayer> players) {
        this.players = players;
    }
}
