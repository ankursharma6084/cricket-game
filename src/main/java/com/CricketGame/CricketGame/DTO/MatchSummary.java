package com.CricketGame.CricketGame.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchSummary {
    private String id;
    private String firstTeamName;
    private String secondTeamName;
    private String winningTeamName;
}
