package com.CricketGame.CricketGame.model;

import com.CricketGame.CricketGame.constants.PlayerCategory;

public class PlayerPerformanceInMatch {
    private String playerName ;
    private PlayerCategory playerCategory;
    private int runsScored;
    private int fours;
    private int sixes;
    public PlayerCategory getPlayerCategory() {
        return playerCategory;
    }
    public void setPlayerCategory(PlayerCategory playerCategory) {
        this.playerCategory = playerCategory;
    }
    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getRunsScored() {
        return runsScored;
    }

    public void setRunsScored(int runsScored) {
        this.runsScored = runsScored;
    }

    public int getFours() {
        return fours;
    }

    public void setFours(int fours) {
        this.fours = fours;
    }

    public int getSixes() {
        return sixes;
    }

    public void setSixes(int sixes) {
        this.sixes = sixes;
    }

    public PlayerPerformanceInMatch(String playerName){
        this.playerName = playerName;
        runsScored = 0;
        fours = 0;
        sixes = 0;
    }
}

