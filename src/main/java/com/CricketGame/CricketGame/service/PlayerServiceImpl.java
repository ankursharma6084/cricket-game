package com.CricketGame.CricketGame.service;

import com.CricketGame.CricketGame.DTO.PlayerInput;
import com.CricketGame.CricketGame.constants.Coin;
import com.CricketGame.CricketGame.DTO.PlayerPerformance;
import com.CricketGame.CricketGame.exception.InvalidDetailsException;
import com.CricketGame.CricketGame.model.BowlerPerformanceInMatch;
import com.CricketGame.CricketGame.model.Match;
import com.CricketGame.CricketGame.model.Player;
import com.CricketGame.CricketGame.model.PlayerPerformanceInMatch;
import com.CricketGame.CricketGame.model.ScoreCard;
import com.CricketGame.CricketGame.model.Team;
import com.CricketGame.CricketGame.repository.PlayerRepository;
import com.mongodb.MongoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private MatchService matchService;
    @Autowired
    private TeamService teamService;
    @Override
    public Player createPlayer(Player player) {
        // further validation
        Team team = teamService.getTeamById(player.getTeamId()) ;

        Player savedPlayer = playerRepository.save(player);
        if(savedPlayer.equals(null)){
            throw new MongoException("Player details not saved. Please try again");
        }
        ArrayList<String> teamPlayers = team.getPlayers() ;
        // didnt get the reason
        if(teamPlayers.equals(null)) {
            teamPlayers = new ArrayList<>();
        }
        teamPlayers.add(player.getId()) ;
        team.setPlayers(teamPlayers);
        teamService.updateTeam(team , team.getId()) ;
        return savedPlayer;
    }

    @Override
    public Player updatePlayer(PlayerInput playerInput, String id) {
        // further validation
         Player player = getPlayerById(id) ;
        // Does the same kind of check has to be required for player also ??

        // write logic for if we change teamId of a player then what to do
        if(! playerInput.getTeamId().equals(player.getTeamId())) {
               throw new InvalidDetailsException("Team of a player cannot be changed") ;
//             List<Player> playersFromOldTeam = teamService.getAllPlayers(player1.getTeamId()) ;
        }

//        player.setId(id);
        player.setPlayerCategory(playerInput.getPlayerCategory());
        player.setName(playerInput.getName());
        Player savedPlayer = playerRepository.save(player);
        if(savedPlayer.equals(null)){
            throw new MongoException("Player details not saved. Please try again");
        }
        return savedPlayer;
    }

    @Override
    public List<Player> getPlayerByname(String name) {
        // Check
        List<Player> players = playerRepository.findByName(name);
        if(players.isEmpty()){
            throw new InvalidDetailsException("No players found with name " + name) ;
        }
        else return players;
    }

    @Override
    public List<Player> getPlayerByTeam(String teamId) {

        teamService.getTeamById(teamId);

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
    public Player getPlayerById(String id) {
           return playerRepository.findById(id)
                   .orElseThrow( ()-> new InvalidDetailsException("Player not found with id " + id)) ;
    }

    @Override
    public PlayerPerformance getPlayerPerformance(String playerId, String matchId) {
        Match match = matchService.getMatchDetailsById(matchId);

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
