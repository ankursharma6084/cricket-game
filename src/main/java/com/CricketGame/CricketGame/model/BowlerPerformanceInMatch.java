package com.CricketGame.CricketGame.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BowlerPerformanceInMatch {
    private String name ;
    private int oversBowled ;
    private int ballsBowled ;
    private double economy;
    private int wicketsTaken ;
    private int runsScoredAgainst;

    public BowlerPerformanceInMatch(String name){
        this.name = name ;
        oversBowled = 0;
        ballsBowled = 0;
        economy = 0;
        wicketsTaken = 0;
    }
}

