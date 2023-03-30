package com.CricketGame.CricketGame.service;

import com.CricketGame.CricketGame.DTO.TeamDetails;
import com.CricketGame.CricketGame.DTO.TeamInput;
import com.CricketGame.CricketGame.exception.InvalidDetailsException;
import com.CricketGame.CricketGame.model.Player;
import com.CricketGame.CricketGame.model.Team;
import com.CricketGame.CricketGame.repository.PlayerRepository;
import com.CricketGame.CricketGame.repository.TeamRepository;
import com.mongodb.MongoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService{
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Override
    public Team createTeam(Team team) {
        // Doubt how to handle error in saving data to mongodb
        Team teamResponse = teamRepository.save(team);
        if(teamResponse.equals(null)){
            throw new MongoException("Team details not saved. Please try again");
        }
        return teamResponse;
    }

    @Override
    public Team updateTeam(Team team, String id) {
        // validate id
            Team teamResponse = teamRepository.save(team);
            if(teamResponse.equals(null)){
                throw new MongoException("Team details not saved. Please try again");
            }
            return teamResponse;
    }

    @Override
    public Team getTeamByname(String name) {
        return teamRepository.findByName(name)
                .orElseThrow( ()-> new InvalidDetailsException("Team not found with name " + name)) ;

    }

    @Override
    public Team getTeamById(String id) {
        return   teamRepository.findById(id).orElseThrow(()-> new InvalidDetailsException("Team not found with id " + id));
    }

    @Override
    public String deleteTeam(String id) {
        // how to know that team with this id existed or not
           teamRepository.deleteById(id);
           return "Team deleted";
    }

    @Override
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public List<Player> getAllPlayers(String id) {
        List<String> playerIds = teamRepository.findById(id).get().getPlayers();
        List<Player> players = new ArrayList<>();
        for(String playerId: playerIds){
            // Exception Handling Left
            players.add(playerRepository.findById(playerId)
                    .orElseThrow( ()-> new InvalidDetailsException("Player not found with id " + id ))) ;
        }
        return players;
    }
}
