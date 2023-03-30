package com.CricketGame.CricketGame.model;

import com.CricketGame.CricketGame.constants.PlayerCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerPerformanceInMatch {
    private String playerId;
    private String playerName ;
    private PlayerCategory playerCategory;
    private int runsScored;
    private int fours;
    private int sixes;
    public PlayerPerformanceInMatch(String id, String playerName){
        this.playerId = id;
        this.playerName = playerName;
        runsScored = 0;
        fours = 0;
        sixes = 0;
    }
}

