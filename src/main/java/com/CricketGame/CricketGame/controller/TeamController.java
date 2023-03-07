package com.CricketGame.CricketGame.controller;

import com.CricketGame.CricketGame.model.Player;
import com.CricketGame.CricketGame.model.Team;
import com.CricketGame.CricketGame.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class TeamController {
    @Autowired
    TeamService teamService;

    @GetMapping("/team")
    public String team(){
        return "Team India";
    }

    @PostMapping("/team/create")
    public String createTeam(@RequestBody Team team){
        teamService.createTeam(team);
        return "Team Succesfully Created";
    }

    @PutMapping("/team/update/{id}")
    public String updateTeam(@RequestBody Team team, @PathVariable String id){
        teamService.updateTeam(team, id);
        return "Team Succesfully Updated";
    }

    @GetMapping("/team/byname/{name}")
    public Team getTeamByName(@PathVariable String name){
        System.out.println(name);
        return teamService.getTeamByname(name);
    }

    @GetMapping("/team/byId/{id}")
    public Team getTeamById(@PathVariable String id){
        return teamService.getTeamById(id);
    }

    @DeleteMapping("/team/delete/{id}")
    public String deleteTeam(@PathVariable String id){
        teamService.deleteTeam(id);
        return "Player Succesfully Deleted";
    }

    @GetMapping("/team/getAllTeams")
    public List<Team> getAllTeams(){
        return teamService.getAllTeams();
    }

    @GetMapping("/team/{id}/getAllPlayers")
    public List<Player> getAllPlayers(@PathVariable String id){
        return teamService.getAllPlayers(id);
    }

}
