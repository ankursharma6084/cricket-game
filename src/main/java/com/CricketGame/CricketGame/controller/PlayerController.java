package com.CricketGame.CricketGame.controller;


import com.CricketGame.CricketGame.converter.PlayerPerformance;
import com.CricketGame.CricketGame.exception.InvalidDetailsException;
import com.CricketGame.CricketGame.model.Player;
import com.CricketGame.CricketGame.service.PlayerService;
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
import java.util.Optional;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @PostMapping("/create")
    public String createPlayer(@RequestBody Player player){
           return playerService.createPlayer(player);
    }

    @PutMapping("/update/{id}")
    public String updatePlayer(@RequestBody Player player, @PathVariable String id){
         return playerService.updatePlayer(player, id);
    }

    @GetMapping("/{playerId}/match/{matchId}")
    public PlayerPerformance getPlayerPerformance(@PathVariable String playerId, @PathVariable String matchId) throws InvalidDetailsException {
           return playerService.getPlayerPerformance(playerId, matchId);
    }

    // player in series
    // highest wicket taker in series, highest run scorer in series

    @GetMapping("/byName/{name}")
    public List<Player> getPlayerByName(@PathVariable String name) throws InvalidDetailsException {
        return playerService.getPlayerByname(name);
    }

    @GetMapping("/byId/{id}")
    public Player getPlayerById(@PathVariable String id) throws InvalidDetailsException {
        return playerService.getPlayerById(id);
    }

    @GetMapping("/byTeamId/{team}")
    public List<Player> getPlayerByTeam(@PathVariable String team) throws InvalidDetailsException {
        return playerService.getPlayerByTeam(team);
    }

    @DeleteMapping("/delete/{id}")
    public String deletePlayer(@PathVariable String id){
        return playerService.deletePlayer(id);
    }

}
