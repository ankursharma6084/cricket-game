package com.CricketGame.CricketGame.service;

import com.CricketGame.CricketGame.model.Player;
import com.CricketGame.CricketGame.model.Team;
import com.CricketGame.CricketGame.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService{

    @Autowired
    TeamRepository teamRepository;
    @Override
    public void createTeam(Team team) {
          teamRepository.save(team);
    }

    @Override
    public void updateTeam(Team team, String id) {
        team.setId(id);
        teamRepository.save(team);
    }

    @Override
    public Team getTeamByname(String name) {
        return teamRepository.findByName(name);
    }

    @Override
    public Optional<Team> getTeamById(String id) {
        return teamRepository.findById(id);
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
        return teamRepository.findById(id).get().getPlayers();
    }
}
