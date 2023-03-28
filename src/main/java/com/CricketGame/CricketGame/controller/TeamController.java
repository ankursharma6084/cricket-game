package com.CricketGame.CricketGame.controller;

import com.CricketGame.CricketGame.exception.InvalidDetailsException;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @GetMapping
    public String team(){
        return "Server is Up and running";
    }

    @PostMapping("/create")
    public String createTeam(@RequestBody Team team){
        return teamService.createTeam(team);
    }

    @PutMapping("/update/{id}")
    public String updateTeam(@RequestBody Team team, @PathVariable String id){
        return teamService.updateTeam(team, id);
    }

    @GetMapping("/byName/{name}")
    public Team getTeamByName(@PathVariable String name) throws InvalidDetailsException {
        return teamService.getTeamByname(name);
    }

    @GetMapping("/byId/{id}")
    public Team getTeamById(@PathVariable String id) throws InvalidDetailsException {
        return teamService.getTeamById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteTeam(@PathVariable String id){
        return teamService.deleteTeam(id);
    }

    @GetMapping("/getAllTeams")
    public List<Team> getAllTeams(){
        return teamService.getAllTeams();
    }

    @GetMapping("/{id}/getAllPlayers")
    public List<Player> getAllPlayers(@PathVariable String id) throws InvalidDetailsException {
        return teamService.getAllPlayers(id);
    }

}
