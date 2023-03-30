package com.CricketGame.CricketGame.converter;

import com.CricketGame.CricketGame.DTO.TeamInput;
import com.CricketGame.CricketGame.exception.InvalidDetailsException;
import com.CricketGame.CricketGame.model.Team;
import com.CricketGame.CricketGame.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class TeamInputToTeamConverter {

    @Autowired
    TeamService teamService;
    public Team createTeam(TeamInput teamInput) {
           Team team = new Team(teamInput.getName());
           ArrayList<String> players = new ArrayList<>() ;
           team.setPlayers(players);
           return teamService.createTeam(team) ;
    }

    public Team updateTeam(TeamInput teamInput, String id){
                Team team = teamService.getTeamById(id) ;
                team.setName(teamInput.getName());
                return teamService.updateTeam(team, id) ;
    }
}
