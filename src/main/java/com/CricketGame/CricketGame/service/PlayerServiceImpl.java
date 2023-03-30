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
    public Player updatePlayer(Player player, String id) {
        // further validation
        // Does the same kind of check has to be required for player also ??

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
}
