package com.CricketGame.CricketGame.service;

import com.CricketGame.CricketGame.converter.PlayerPerformance;
import com.CricketGame.CricketGame.model.Player;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface PlayerService {
    void createPlayer(Player player);

    void updatePlayer(Player player, String id);

    List<Player> getPlayerByname(String name);

    List<Player> getPlayerByTeam(String team);

    void deletePlayer(String id);

    Player getPlayerById(String id);

    PlayerPerformance getPlayerPerformance(String playerId, String matchId);
}
