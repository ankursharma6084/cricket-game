package com.CricketGame.CricketGame.repository;

import com.CricketGame.CricketGame.model.Team;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends MongoRepository<Team, String> {
    Team findByName(String name);
}
