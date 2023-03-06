package com.CricketGame.CricketGame.repository;

import com.CricketGame.CricketGame.model.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends MongoRepository<Player, String> {
    List<Player> findByName(String name);
    List<Player> findByTeamId(String team);
}
