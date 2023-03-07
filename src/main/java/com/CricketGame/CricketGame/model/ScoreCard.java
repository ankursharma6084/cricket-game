package com.CricketGame.CricketGame.model;


import java.util.ArrayList;

public class ScoreCard {
    private String battingTeam ;
    private String bowlingTeam ;
    private int totalScore ;
    private int totalWickets;
    private int foursInMatch ;
    private int sixesInMatch;
    private ArrayList<Over> overs ;
    private ArrayList<PlayerPerformanceInMatch> playerPerformance;
    private ArrayList<BowlerPerformanceInMatch> bowlerPerformance;
    public String getBattingTeam() {
        return battingTeam;
    }

    public ArrayList<BowlerPerformanceInMatch> getBowlerPerformance() {
        return bowlerPerformance;
    }

    public String getBowlingTeam() {
        return bowlingTeam;
    }

    public ScoreCard(String battingTeam, String bowlingTeam) {
        this.battingTeam = battingTeam;
        this.bowlingTeam = bowlingTeam;
        totalScore = 0;
        totalWickets = 0;
        playerPerformance = new ArrayList<PlayerPerformanceInMatch>();
        bowlerPerformance = new ArrayList<>();
        overs = new ArrayList<>() ;
    }

    public int getFoursInMatch() {
        return foursInMatch;
    }

    public void setFoursInMatch(int foursInMatch) {
        this.foursInMatch = foursInMatch;
    }

    public int getSixesInMatch() {
        return sixesInMatch;
    }

    public void setSixesInMatch(int sixesInMatch) {
        this.sixesInMatch = sixesInMatch;
    }

    public int getTotalWickets() {
        return totalWickets;
    }

    public void setTotalWickets(int totalWickets) {
        this.totalWickets = totalWickets;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public ArrayList<PlayerPerformanceInMatch> getPlayerPerformance() {
        return playerPerformance;
    }
    public ArrayList<Over> getOvers() {
        return overs;
    }
    public void setOvers(ArrayList<Over> overs) {
        this.overs = overs;
    }

}
