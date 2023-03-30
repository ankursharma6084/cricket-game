package com.CricketGame.CricketGame.DTO;

import com.CricketGame.CricketGame.constants.PlayerCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerPerformance {
    private String playerName ;
    private PlayerCategory playerCategory;
    private int runsScored;
    private int fours;
    private int sixes;
    private int oversBowled ;
    private int ballsBowled ;
    private double economy;
    private int wicketsTaken ;
    private int runsScoredAgainst;
}
