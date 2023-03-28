package com.CricketGame.CricketGame.service;

import com.CricketGame.CricketGame.constants.Coin;
import com.CricketGame.CricketGame.converter.PlayerPerformance;
import com.CricketGame.CricketGame.exception.InvalidDetailsException;
import com.CricketGame.CricketGame.model.BowlerPerformanceInMatch;
import com.CricketGame.CricketGame.model.Match;
import com.CricketGame.CricketGame.model.Player;
import com.CricketGame.CricketGame.model.PlayerPerformanceInMatch;
import com.CricketGame.CricketGame.model.ScoreCard;
import com.CricketGame.CricketGame.model.Team;
import com.CricketGame.CricketGame.repository.MatchRepository;
import com.CricketGame.CricketGame.repository.PlayerRepository;
import com.CricketGame.CricketGame.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private TeamRepository teamRepository;
    @Override
    public String createPlayer(Player player) {
        // further validation
        playerRepository.save(player);
        return "Player created";
    }

    @Override
    public String updatePlayer(Player player, String id) {
        // further validation

        // Does the same kind of check has to be required for player also ??
           player.setId(id);
           playerRepository.save(player);
           return "Player updated";
    }

    @Override
    public List<Player> getPlayerByname(String name) throws InvalidDetailsException {
        // Check
        List<Player> players = playerRepository.findByName(name);
        if(players.isEmpty()){
            throw new InvalidDetailsException("No players found with name " + name) ;
        }
        else return players;
    }

    @Override
    public List<Player> getPlayerByTeam(String teamId) throws InvalidDetailsException {

        teamRepository.findById(teamId)
                .orElseThrow( ()-> new InvalidDetailsException("Team not found with id " + teamId)) ;

        List<Player> players = playerRepository.findByTeamId(teamId);
        if(players.isEmpty()){
            throw new InvalidDetailsException("No players found with team " + teamId) ;
        }
        else return players;
    }

    @Override
    public String deletePlayer(String id) {
        // further validation
         playerRepository.deleteById(id);
         return "Player deleted";
    }

    @Override
    public Player getPlayerById(String id) throws InvalidDetailsException {
           return playerRepository.findById(id)
                   .orElseThrow( ()-> new InvalidDetailsException("Player not found with id " + id)) ;
    }

    @Override
    public PlayerPerformance getPlayerPerformance(String playerId, String matchId) throws InvalidDetailsException {
        Match match = matchRepository.findById(matchId)
                .orElseThrow( ()-> new InvalidDetailsException("Match not found with id " + matchId)) ;
        Player player = getPlayerById(playerId);

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
