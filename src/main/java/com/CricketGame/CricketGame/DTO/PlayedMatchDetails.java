package com.CricketGame.CricketGame.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayedMatchDetails {
       private String winningTeamName;
       private String message;
       public PlayedMatchDetails(String winningTeamName){
           this.winningTeamName = winningTeamName;
           message = "Match Conducted Successfully";
       }
}
