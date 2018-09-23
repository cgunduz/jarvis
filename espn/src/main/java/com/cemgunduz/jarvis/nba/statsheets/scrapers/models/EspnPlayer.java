package com.cemgunduz.jarvis.nba.statsheets.scrapers.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EspnPlayer {

    Integer id;
    Integer onTeamId;
    Player player;
    String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOnTeamId() {
        return onTeamId;
    }

    public void setOnTeamId(Integer onTeamId) {
        this.onTeamId = onTeamId;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
