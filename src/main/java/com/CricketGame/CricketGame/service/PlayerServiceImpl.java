package com.CricketGame.CricketGame.service;

import com.CricketGame.CricketGame.constants.Coin;
import com.CricketGame.CricketGame.converter.PlayerPerformance;
import com.CricketGame.CricketGame.model.BowlerPerformanceInMatch;
import com.CricketGame.CricketGame.model.Match;
import com.CricketGame.CricketGame.model.Player;
import com.CricketGame.CricketGame.model.PlayerPerformanceInMatch;
import com.CricketGame.CricketGame.model.ScoreCard;
import com.CricketGame.CricketGame.repository.MatchRepository;
import com.CricketGame.CricketGame.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    MatchRepository matchRepository;
    @Override
    public void createPlayer(Player player) {
           playerRepository.save(player);
    }

    @Override
    public void updatePlayer(Player player, String id) {
           player.setId(id);
           playerRepository.save(player);
    }

    @Override
    public List<Player> getPlayerByname(String name) {
        return playerRepository.findByName(name);
    }

    @Override
    public List<Player> getPlayerByTeam(String team) {
        return playerRepository.findByTeamId(team);
    }

    @Override
    public void deletePlayer(String id) {
         playerRepository.deleteById(id);
    }

    @Override
    public Player getPlayerById(String id) {
           return playerRepository.findById(id).orElseThrow(()-> new RuntimeException());
    }

    @Override
    public PlayerPerformance getPlayerPerformance(String playerId, String matchId) {
        Match match = matchRepository.findById(matchId).orElseThrow(()-> new RuntimeException());
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
