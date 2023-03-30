package com.CricketGame.CricketGame.service;

import com.CricketGame.CricketGame.exception.InvalidDetailsException;
import com.CricketGame.CricketGame.model.Player;
import com.CricketGame.CricketGame.model.Team;

import java.util.List;

public interface TeamService {
    Team createTeam(Team team);

    Team updateTeam(Team team, String id);

    Team getTeamByname(String name);

    Team getTeamById(String id);

    String deleteTeam(String id);

    List<Team> getAllTeams();

    List<Player> getAllPlayers(String id);
}
