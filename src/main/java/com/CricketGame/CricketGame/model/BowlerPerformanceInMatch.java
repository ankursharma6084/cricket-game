package com.CricketGame.CricketGame.model;

public class BowlerPerformanceInMatch {
    private String name ;
    private int oversBowled ;
    private int ballsBowled ;
    private double economy;
    private int wicketsTaken ;
    private int runsScoredAgainst;
    public int getRunsScoredAgainst() {
        return runsScoredAgainst;
    }
    public void setRunsScoredAgainst(int runsScoredAgainst) {
        this.runsScoredAgainst = runsScoredAgainst;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOversBowled() {
        return oversBowled;
    }

    public void setOversBowled(int oversBowled) {
        this.oversBowled = oversBowled;
    }

    public int getBallsBowled() {
        return ballsBowled;
    }

    public void setBallsBowled(int ballsBowled) {
        this.ballsBowled = ballsBowled;
    }

    public double getEconomy() {
        return economy;
    }

    public void setEconomy(double economy) {
        this.economy = economy;
    }

    public int getWicketsTaken() {
        return wicketsTaken;
    }

    public void setWicketsTaken(int wicketsTaken) {
        this.wicketsTaken = wicketsTaken;
    }

    public BowlerPerformanceInMatch(String name){
        this.name = name ;
        oversBowled = 0;
        ballsBowled = 0;
        economy = 0;
        wicketsTaken = 0;
    }
}

