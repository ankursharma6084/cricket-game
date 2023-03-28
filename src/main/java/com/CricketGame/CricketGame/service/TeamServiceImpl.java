package com.CricketGame.CricketGame.service;

import com.CricketGame.CricketGame.exception.InvalidDetailsException;
import com.CricketGame.CricketGame.model.Player;
import com.CricketGame.CricketGame.model.Team;
import com.CricketGame.CricketGame.repository.PlayerRepository;
import com.CricketGame.CricketGame.repository.TeamRepository;
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
    public String createTeam(Team team) {
        // Doubt how to handle error in saving data to mongodb
        teamRepository.save(team);
        return "Team created successfully";
    }

    @Override
    public String updateTeam(Team team, String id) {
        // validate id
        if(team.getId().equals(id)){
            team.setId(id);
            teamRepository.save(team);
            return "Team updated successfully";
        }

        else {
            return "Please enter valid team id and corresponding details";
        }
    }

    @Override
    public Team getTeamByname(String name) throws InvalidDetailsException {
        return teamRepository.findByName(name)
                .orElseThrow( ()-> new InvalidDetailsException("Team not found with name " + name)) ;
    }

    @Override
    public Team getTeamById(String id) throws InvalidDetailsException {
        return teamRepository.findById(id)
                .orElseThrow( ()-> new InvalidDetailsException("Team not found with id " + id)) ;
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
    public List<Player> getAllPlayers(String id) throws InvalidDetailsException {
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
