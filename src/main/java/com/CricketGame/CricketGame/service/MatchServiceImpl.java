package com.CricketGame.CricketGame.service;

import com.CricketGame.CricketGame.DTO.MatchSummary;
import com.CricketGame.CricketGame.DTO.PlayedMatchDetails;
import com.CricketGame.CricketGame.DTO.PlayerPerformance;
import com.CricketGame.CricketGame.DTO.PlayingDetails;
import com.CricketGame.CricketGame.constants.Coin;
import com.CricketGame.CricketGame.constants.PlayingFormat;
import com.CricketGame.CricketGame.exception.InvalidDetailsException;
import com.CricketGame.CricketGame.model.BowlerPerformanceInMatch;
import com.CricketGame.CricketGame.model.Match;
import com.CricketGame.CricketGame.model.Player;
import com.CricketGame.CricketGame.model.PlayerPerformanceInMatch;
import com.CricketGame.CricketGame.model.ScoreCard;
import com.CricketGame.CricketGame.model.Team;
import com.CricketGame.CricketGame.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MatchServiceImpl implements MatchService{
    @Autowired
    private PlayMatch playMatch;
    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private TeamService teamService;
    @Autowired
    private PlayerService playerService;

    // write in converter
    @Override
    public PlayedMatchDetails play(Team teamA, Team teamB, int numberOfOvers, PlayingFormat playingFormat) {
        Match match = playMatch(teamA, teamB, numberOfOvers, playingFormat);

        String winningTeam = "";

        if(match.getWinningTeamId().equals(teamA.getId())){
            winningTeam = teamA.getName();
        } else if (match.getWinningTeamId().equals(teamB.getId())) {
            winningTeam = teamB.getName();
        } else {
            winningTeam = "No Winner";
        }

        return new PlayedMatchDetails(winningTeam , match.getId());

    }

    private Match playMatch(Team teamA, Team teamB, int numberOfOvers, PlayingFormat playingFormat)
                            throws InvalidDetailsException {
        Match match = new Match(teamA.getId() , teamB.getId()) ;
        match.setNumberOfOvers(numberOfOvers);
        match.setPlayingFormat(playingFormat);
        match = playMatch.play(match, teamService, playerService);
        matchRepository.save(match);
        return match;
    }

    @Override
    public Match playInningsMatch(Team teamA, Team teamB, int numberOfOvers, PlayingFormat playingFormat) {
        return playMatch(teamA, teamB, numberOfOvers, playingFormat);
    }

    @Override
    public List<MatchSummary> getAllMatches() {
        List<MatchSummary> matchSummaries = new ArrayList<>();
        List<Match> matches = matchRepository.findAll();
        for (Match match: matches){
            matchSummaries.add(createMatchSummary(match));
        }

        return matchSummaries;
    }

    private MatchSummary createMatchSummary(Match match)  {
        Team teamA = teamService.getTeamById(match.getFirstTeamId()) ;
        Team teamB = teamService.getTeamById(match.getSecondTeamId()) ;

        MatchSummary matchSummary = new MatchSummary(match.getId(), teamA.getName(), teamB.getName(),
                teamA.getId().equals(match.getWinningTeamId()) ? teamA.getName() : teamB.getName() ) ;
        if(match.getWinningTeamId().equals(null)){
            matchSummary.setWinningTeamName("No winner");
        }
        return matchSummary;
    }

    @Override
    public List<MatchSummary> getMatchesPlayedByATeam(String id)  {
        List<MatchSummary> matchSummaries = new ArrayList<>();
        List<Match> matches = matchRepository.findAll();
        for (Match match: matches){
            if(!id.equals(match.getFirstTeamId()) && !id.equals(match.getSecondTeamId())) continue;
                matchSummaries.add(createMatchSummary(match));
        }

        return matchSummaries;
    }

    @Override
    public Match getMatchDetailsById(String id) {
           return matchRepository.findById(id)
                   .orElseThrow( ()-> new InvalidDetailsException("No match details found with id " + id)) ;
    }

    public PlayerPerformance getPlayerPerformance(String playerId, String matchId) {
        Match match = getMatchDetailsById(matchId);

        Player player = playerService.getPlayerById(playerId);

        if(match.getTossWinnerId().equals(player.getTeamId()) )
        {
            if( match.getWinningTossTeamChoice() == Coin.Head )
                return setPlayerPerformanceDetails(match.getScoreCard().get(0) , match.getScoreCard().get(1), player.getName()) ;
            else
                return setPlayerPerformanceDetails(match.getScoreCard().get(1) , match.getScoreCard().get(0), player.getName()) ;
        }

        else{
            if( match.getWinningTossTeamChoice() == Coin.Head )
                return setPlayerPerformanceDetails(match.getScoreCard().get(1) , match.getScoreCard().get(0), player.getName()) ;
            else
                return setPlayerPerformanceDetails(match.getScoreCard().get(0) , match.getScoreCard().get(1), player.getName()) ;
        }

    }

    private PlayerPerformance setPlayerPerformanceDetails(ScoreCard battingScoreCard, ScoreCard bowlingScorecard
            , String playerName) {
        PlayerPerformance playerPerformance = new PlayerPerformance();
        for(PlayerPerformanceInMatch playerPerformanceInMatch : battingScoreCard.getPlayerPerformance()){
            if(playerPerformanceInMatch.getPlayerName().equals(playerName)){
                playerPerformance.setPlayerName(playerName);
                playerPerformance.setPlayerCategory(playerPerformanceInMatch.getPlayerCategory());
                playerPerformance.setRunsScored(playerPerformanceInMatch.getRunsScored());
                playerPerformance.setFours(playerPerformanceInMatch.getFours());
                playerPerformance.setSixes(playerPerformanceInMatch.getSixes());
            }
        }

        for(BowlerPerformanceInMatch bowlerPerformanceInMatch: bowlingScorecard.getBowlerPerformance()){
            if(bowlerPerformanceInMatch.getName().equals(playerName)){
                playerPerformance.setOversBowled(bowlerPerformanceInMatch.getOversBowled());
                playerPerformance.setBallsBowled(bowlerPerformanceInMatch.getBallsBowled());
                playerPerformance.setEconomy(bowlerPerformanceInMatch.getEconomy());
                playerPerformance.setRunsScoredAgainst(bowlerPerformanceInMatch.getRunsScoredAgainst());
                playerPerformance.setWicketsTaken(bowlerPerformanceInMatch.getWicketsTaken());
            }
        }
        return playerPerformance;
    }

}
