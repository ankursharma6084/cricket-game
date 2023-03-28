package com.CricketGame.CricketGame.converter;

import com.CricketGame.CricketGame.DTO.PlayedMatchDetails;
import com.CricketGame.CricketGame.DTO.PlayingDetails;
import com.CricketGame.CricketGame.constants.PlayingFormat;
import com.CricketGame.CricketGame.exception.InvalidDetailsException;
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
    private TeamService teamService;
    @Autowired
    private MatchService matchService;

    public PlayedMatchDetails play(PlayingDetails playingDetails) throws InvalidDetailsException {

           // Handle if wrong data is sent --> Validation

           String firstTeamName = playingDetails.getFirstTeamName();
           String secondTeamName = playingDetails.getSecondTeamName();
           PlayingFormat playingFormat = playingDetails.getPlayingFormat();
           int numberOfOvers = 0 ;
           if(playingFormat.equals(PlayingFormat.CUSTOM))
                numberOfOvers = playingDetails.getNumberOfOvers();

           Team teamA = teamService.getTeamByname(firstTeamName);
           Team teamB = teamService.getTeamByname(secondTeamName);
           System.out.println(teamB.getId() + " " + teamA.getId() );

           // crete match in matchService
           return matchService.play(teamA, teamB, numberOfOvers, playingFormat);
    }
}
