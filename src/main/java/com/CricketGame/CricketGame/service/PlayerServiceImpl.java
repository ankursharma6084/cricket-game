package com.CricketGame.CricketGame.service;

import com.CricketGame.CricketGame.model.Player;
import com.CricketGame.CricketGame.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    PlayerRepository playerRepository;
    @Override
    public void createPlayer(Player player) {
           playerRepository.save(player);
    }

    @Override
    public void updatePlayer(Player player, String id) {
           player.setId(id);
           playerRepository.save(player);
    }

    @Override
    public List<Player> getPlayerByname(String name) {
        return playerRepository.findByName(name);
    }

    @Override
    public List<Player> getPlayerByTeam(String team) {
        return playerRepository.findByTeamId(team);
    }

    @Override
    public void deletePlayer(String id) {
         playerRepository.deleteById(id);
    }

    @Override
    public Optional<Player> getPlayerById(String id) {
           return playerRepository.findById(id);
    }


}
