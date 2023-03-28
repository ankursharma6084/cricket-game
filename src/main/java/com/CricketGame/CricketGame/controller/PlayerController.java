package com.CricketGame.CricketGame.controller;


import com.CricketGame.CricketGame.converter.PlayerPerformance;
import com.CricketGame.CricketGame.model.Player;
import com.CricketGame.CricketGame.service.PlayerService;
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
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @PostMapping("/player/create")
    public String createPlayer(@RequestBody Player player){
           playerService.createPlayer(player);
           return "Player Succesfully Created";
    }

    @PutMapping("/player/update/{id}")
    public String updatePlayer(@RequestBody Player player, @PathVariable String id){
        playerService.updatePlayer(player, id);
        return "Player Succesfully Updated";
    }

    @GetMapping("/player/{playerId}/match/{matchId}")
    public PlayerPerformance getPlayerPerformance(@PathVariable String playerId, @PathVariable String matchId){
           return playerService.getPlayerPerformance(playerId, matchId);
    }

    // player in series
    // highest wicket taker in series, highest run scorer in series

    @GetMapping("/player/byname/{name}")
    public List<Player> getPlayerByName(@PathVariable String name){
        return playerService.getPlayerByname(name);
    }

    @GetMapping("/player/byId/{id}")
    public Player getPlayerById(@PathVariable String id){
        return playerService.getPlayerById(id);
    }

    @GetMapping("/player/byteam/{team}")
    public List<Player> getPlayerByTeam(@PathVariable String team){
        return playerService.getPlayerByTeam(team);
    }

    @DeleteMapping("/player/delete/{id}")
    public String deletePlayer(@PathVariable String id){
        playerService.deletePlayer(id);
        return "Player Succesfully Deleted";
    }

}
