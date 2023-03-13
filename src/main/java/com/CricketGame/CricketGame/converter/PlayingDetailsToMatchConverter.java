package com.CricketGame.CricketGame.converter;

import com.CricketGame.CricketGame.DTO.PlayedMatchDetails;
import com.CricketGame.CricketGame.DTO.PlayingDetails;
import com.CricketGame.CricketGame.model.Match;
import com.CricketGame.CricketGame.model.Team;
import com.CricketGame.CricketGame.service.MatchService;
import com.CricketGame.CricketGame.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayingDetailsToMatchConverter {
    // teamservice
    @Autowired
    TeamService teamService;
    @Autowired
    MatchService matchService;


    public PlayedMatchDetails play(PlayingDetails playingDetails) {

           String firstTeamName = playingDetails.getFirstTeamName();
           String secondTeamName = playingDetails.getSecondTeamName();
           int numberOfOvers = playingDetails.getNumberOfOvers();

           Team teamA = teamService.getTeamByname(firstTeamName);
           Team teamB = teamService.getTeamByname(secondTeamName);
           System.out.println(teamB.getId() + " " + teamA.getId() );
           Match match = new Match(teamA.getId() , teamB.getId()) ;
           match.setNumberOfOvers(numberOfOvers);
           match = matchService.play(match);

           String winningTeam = "";

           if(match.getWinningTeamId().equals(teamA.getId())){
               winningTeam = firstTeamName;
           } else if (match.getWinningTeamId().equals(teamA.getId())) {
               winningTeam = secondTeamName;
           } else {
               winningTeam = "No Winner";
           }

           return new PlayedMatchDetails(winningTeam);
    }
}
