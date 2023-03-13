package com.CricketGame.CricketGame.service;

import com.CricketGame.CricketGame.DTO.PlayedSeriesDetails;
import com.CricketGame.CricketGame.DTO.PlayingDetailsInSeries;
import com.CricketGame.CricketGame.model.Match;
import com.CricketGame.CricketGame.model.Series;
import com.CricketGame.CricketGame.model.Team;
import com.CricketGame.CricketGame.repository.SeriesRepository;
import com.CricketGame.CricketGame.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeriesServiceImpl implements SeriesService {

    @Autowired
    MatchService matchService;
    @Autowired
    TeamService teamService;
    @Autowired
    SeriesRepository seriesRepository;

    @Override
    public PlayedSeriesDetails playSeries(PlayingDetailsInSeries playingDetailsInSeries) {

           String firstTeamName = playingDetailsInSeries.getFirstTeamName();
           String secondTeamName = playingDetailsInSeries.getSecondTeamName();
           int numberOfOvers = playingDetailsInSeries.getNumberOfOvers();

           Team teamA = teamService.getTeamByname(firstTeamName);
           Team teamB = teamService.getTeamByname(secondTeamName);
           int matchesInSeriesWonByTeamA = 0 ;
           int matchesInSeriesWonByTeamB = 0 ;

           Series series = new Series(playingDetailsInSeries.getSeriesName(), teamA.getId() , teamB.getId());

           for (int i=0 ; i<playingDetailsInSeries.getNumberOfMatches(); i++){

               Match match = new Match(teamA.getId() , teamB.getId()) ;
               match.setNumberOfOvers(numberOfOvers);
               match = matchService.play(match) ;
               series.getMatches().add(match);

               if(match.getWinningTeamId().equals(teamA.getId())) {
                   matchesInSeriesWonByTeamA++ ;
               } else if (match.getWinningTeamId().equals(teamB.getId())) {
                   matchesInSeriesWonByTeamB++;
               }
           }

           String details = "Series was played between " + firstTeamName + " and " + secondTeamName + ". ";
           PlayedSeriesDetails playedSeriesDetails = new PlayedSeriesDetails();

           if(matchesInSeriesWonByTeamA > matchesInSeriesWonByTeamB){
               details+= firstTeamName + " won the series by " + matchesInSeriesWonByTeamA + "-" +
                         matchesInSeriesWonByTeamB ;
               playedSeriesDetails.setSeriesWinnerTeamName(firstTeamName);
               series.setSeriesWinnerId(teamA.getId());

           } else if (matchesInSeriesWonByTeamA < matchesInSeriesWonByTeamB) {
               details+= secondTeamName + " won the series by " + matchesInSeriesWonByTeamB + "-" +
                       matchesInSeriesWonByTeamA ;
               playedSeriesDetails.setSeriesWinnerTeamName(secondTeamName);
               series.setSeriesWinnerId(teamB.getId());

           }  else{
               details+= "Series was drawn by " + matchesInSeriesWonByTeamB + "-" +
                       matchesInSeriesWonByTeamA ;
               playedSeriesDetails.setSeriesWinnerTeamName("Series Drawn");
               series.setSeriesWinnerId("Series Drawn");
           }

           // Adding Series Id to Teams
            teamA.getSeries().add(series.getId());
            teamB.getSeries().add(series.getId());
//            System.out.println("Series teamA " + teamA.getSeries());
//            System.out.println("Series teamB " + teamB.getSeries());

            teamService.updateTeam(teamA, teamA.getId());
            teamService.updateTeam(teamB, teamB.getId());

           seriesRepository.save(series);

           playedSeriesDetails.setSeriesDetails(details);
           return playedSeriesDetails;
    }

    @Override
    public Series getSeriesDetails(String id) {
        // Exception halndling left
        return seriesRepository.findById(id).orElseThrow( ()-> new RuntimeException() );
    }
}
