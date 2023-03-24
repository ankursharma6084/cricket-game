package com.CricketGame.CricketGame.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayedMatchDetails {
       private String matchId;
       private String winningTeamName;
       private String message;
       public PlayedMatchDetails(String winningTeamName, String matchId){
           this.winningTeamName = winningTeamName;
           this.matchId = matchId;
           message = "Match Conducted Successfully";
       }
}
