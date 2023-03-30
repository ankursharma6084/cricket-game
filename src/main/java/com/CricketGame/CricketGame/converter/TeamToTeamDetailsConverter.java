package com.CricketGame.CricketGame.converter;

import com.CricketGame.CricketGame.DTO.TeamDetails;
import com.CricketGame.CricketGame.model.Team;
import com.CricketGame.CricketGame.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeamToTeamDetailsConverter {

    @Autowired
    TeamService teamService ;
    public TeamDetails getTeamByName(String name){
         Team team = teamService.getTeamByname(name);
         return createTeamDetailsFromTeam(team) ;
    }

    public TeamDetails getTeamById(String id){
           Team team = teamService.getTeamById(id);
           return createTeamDetailsFromTeam(team) ;
    }

    private TeamDetails createTeamDetailsFromTeam(Team team) {
        return new TeamDetails(team.getId(), team.getName(), teamService.getAllPlayers(team.getId())) ;
    }
}
