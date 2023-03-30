package com.CricketGame.CricketGame.controller;


import com.CricketGame.CricketGame.DTO.PlayerInput;
import com.CricketGame.CricketGame.DTO.PlayerPerformance;
import com.CricketGame.CricketGame.converter.PlayerInputToPlayerConverter;
import com.CricketGame.CricketGame.exception.InvalidDetailsException;
import com.CricketGame.CricketGame.model.Player;
import com.CricketGame.CricketGame.service.PlayerService;
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
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerInputToPlayerConverter playerInputToPlayerConverter;
    @Autowired
    private PlayerService playerService ;

    // validation required
    @PostMapping("/create")
    public Player createPlayer(@RequestBody @Valid PlayerInput player){
           return playerInputToPlayerConverter.createPlayer(player);
    }

    // validation required
    @PutMapping("/update/{id}")
    public Player updatePlayer(@RequestBody @Valid PlayerInput player, @PathVariable String id){
         return playerInputToPlayerConverter.updatePlayer(player, id);
    }

    @GetMapping("/{playerId}/match/{matchId}")
    public PlayerPerformance getPlayerPerformance(@PathVariable String playerId, @PathVariable String matchId){
           return playerService.getPlayerPerformance(playerId, matchId);
    }

    // player in series
    // highest wicket taker in series, highest run scorer in series

    @GetMapping("/byName/{name}")
    public List<Player> getPlayerByName(@PathVariable String name){
        return playerService.getPlayerByname(name);
    }

    @GetMapping("/byId/{id}")
    public Player getPlayerById(@PathVariable String id) {
        return playerService.getPlayerById(id);
    }

    @GetMapping("/byTeamId/{team}")
    public List<Player> getPlayerByTeam(@PathVariable String team) {
        return playerService.getPlayerByTeam(team);
    }

    @DeleteMapping("/delete/{id}")
    public String deletePlayer(@PathVariable String id){
        return playerService.deletePlayer(id);
    }

}
