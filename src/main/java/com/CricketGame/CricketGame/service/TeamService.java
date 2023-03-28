package com.CricketGame.CricketGame.service;

import com.CricketGame.CricketGame.exception.InvalidDetailsException;
import com.CricketGame.CricketGame.model.Player;
import com.CricketGame.CricketGame.model.Team;

import java.util.List;

public interface TeamService {
    String createTeam(Team team);

    String updateTeam(Team team, String id);

    Team getTeamByname(String name) throws InvalidDetailsException;

    Team getTeamById(String id) throws InvalidDetailsException;

    String deleteTeam(String id);

    List<Team> getAllTeams();

    List<Player> getAllPlayers(String id) throws InvalidDetailsException;
}
