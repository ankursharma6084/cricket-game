package com.CricketGame.CricketGame.service;

import com.CricketGame.CricketGame.model.Player;
import com.CricketGame.CricketGame.model.Team;

import java.util.List;
import java.util.Optional;

public interface TeamService {
    void createTeam(Team team);

    void updateTeam(Team team, String id);

    Team getTeamByname(String name);

    Optional<Team> getTeamById(String id);

    void deleteTeam(String id);

    List<Team> getAllTeams();

    List<Player> getAllPlayers(String id);
}
