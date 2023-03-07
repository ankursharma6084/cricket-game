package com.CricketGame.CricketGame.model;

public class PlayerPerformanceInMatch {
    private String playerName ;
    private int runsScored;
    private int fours;
    private int sixes;

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

