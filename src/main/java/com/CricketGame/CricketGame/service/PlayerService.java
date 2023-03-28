package com.CricketGame.CricketGame.service;

import com.CricketGame.CricketGame.converter.PlayerPerformance;
import com.CricketGame.CricketGame.exception.InvalidDetailsException;
import com.CricketGame.CricketGame.model.Player;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface PlayerService {
    String createPlayer(Player player);

    String updatePlayer(Player player, String id);

    List<Player> getPlayerByname(String name) throws InvalidDetailsException;

    List<Player> getPlayerByTeam(String team) throws InvalidDetailsException;

    String deletePlayer(String id);

    Player getPlayerById(String id) throws InvalidDetailsException;

    PlayerPerformance getPlayerPerformance(String playerId, String matchId) throws InvalidDetailsException;
}
