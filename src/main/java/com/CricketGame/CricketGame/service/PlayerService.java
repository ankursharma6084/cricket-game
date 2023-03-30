package com.CricketGame.CricketGame.service;

import com.CricketGame.CricketGame.DTO.PlayerInput;
import com.CricketGame.CricketGame.DTO.PlayerPerformance;
import com.CricketGame.CricketGame.model.Player;

import java.util.List;

public interface PlayerService {
    Player createPlayer(Player player);

    Player updatePlayer(PlayerInput playerInput, String id);

    List<Player> getPlayerByname(String name) ;

    List<Player> getPlayerByTeam(String team) ;

    String deletePlayer(String id);

    Player getPlayerById(String id) ;

    PlayerPerformance getPlayerPerformance(String playerId, String matchId) ;
}
