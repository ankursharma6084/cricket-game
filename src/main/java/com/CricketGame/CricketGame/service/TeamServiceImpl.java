package com.CricketGame.CricketGame.service;

import com.CricketGame.CricketGame.model.Player;
import com.CricketGame.CricketGame.model.Team;
import com.CricketGame.CricketGame.repository.PlayerRepository;
import com.CricketGame.CricketGame.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService{

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Override
    public void createTeam(Team team) {
        teamRepository.save(team);
    }

    @Override
    public void updateTeam(Team team, String id) {
        // validate id
        team.setId(id);
        teamRepository.save(team);
    }

    @Override
    public Team getTeamByname(String name) {
        return teamRepository.findByName(name);
    }

    @Override
    public Team getTeamById(String id) {
        return teamRepository.findById(id).orElseThrow(()-> new RuntimeException());
    }

    @Override
    public void deleteTeam(String id) {
           teamRepository.deleteById(id);
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
            players.add(playerRepository.findById(playerId).orElseThrow());
        }
        return players;
    }
}
