package com.CricketGame.CricketGame.controller;

import com.CricketGame.CricketGame.DTO.TeamDetails;
import com.CricketGame.CricketGame.DTO.TeamInput;
import com.CricketGame.CricketGame.converter.TeamInputToTeamConverter;
import com.CricketGame.CricketGame.converter.TeamToTeamDetailsConverter;
import com.CricketGame.CricketGame.exception.InvalidDetailsException;
import com.CricketGame.CricketGame.model.Player;
import com.CricketGame.CricketGame.model.Team;
import com.CricketGame.CricketGame.service.TeamService;
import jakarta.validation.Valid;
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
    @Autowired
    TeamInputToTeamConverter teamInputToTeamConverter;
    @Autowired
    TeamToTeamDetailsConverter teamToTeamDetailsConverter;
    @GetMapping
    public String team(){
        return "Server is Up and running";
    }

    // validation required does @Valid not work with documents
    @PostMapping("/create")
    public Team createTeam(@RequestBody @Valid TeamInput team){
        return teamInputToTeamConverter.createTeam(team);
    }

    // validation required
    @PutMapping("/update/{id}")
    public Team updateTeam(@RequestBody @Valid TeamInput team, @PathVariable String id){
        return teamInputToTeamConverter.updateTeam(team, id);
    }
    @GetMapping("/byName/{name}")
    public TeamDetails getTeamByName(@PathVariable String name){
        return teamToTeamDetailsConverter.getTeamByName(name);
    }

    @GetMapping("/byId/{id}")
    public TeamDetails getTeamById(@PathVariable String id) {
        return teamToTeamDetailsConverter.getTeamById(id);
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
    public List<Player> getAllPlayers(@PathVariable String id) {
        return teamService.getAllPlayers(id);
    }

}
